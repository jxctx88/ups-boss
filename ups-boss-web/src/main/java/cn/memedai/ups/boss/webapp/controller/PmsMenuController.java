package cn.memedai.ups.boss.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.memedai.ups.boss.dal.model.PmsActionDO;
import cn.memedai.ups.boss.dal.model.PmsMenuDO;
import cn.memedai.ups.boss.service.permission.PmsActionService;
import cn.memedai.ups.boss.service.permission.PmsMenuService;
import cn.memedai.ups.boss.service.permission.annotation.Permission;
import cn.memedai.ups.boss.webapp.base.PermissionBase;

/**
 * 菜单权限控制器
 * @author tongxiong.cheng
 * @date 2016-11-29 下午4:40:28
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/pmsMenu")
public class PmsMenuController extends PermissionBase {


	private static final String EDIT_MENU_ACTION = "pmsMenu/editPmsMenuUI";

	/*private PmsMenuDO pmsMenu = new PmsMenuDO();

	public PmsMenuDO getModel() {
		return pmsMenu;//(PmsMenuDO) ModelDrivenUtil.cleanModel(pmsMenu);
	}*/
	
	@Autowired
	private PmsMenuService pmsMenuService;

	@Autowired
	private PmsActionService pmsActionService;

	/**
	 * 列出要管理的菜单.
	 * 
	 * @return PmsMenuList .
	 */
	@Permission("pms:menu:view")
	@RequestMapping("/listPmsMenu")
	public Object listPmsMenu(HttpServletRequest request) {
		String str = pmsMenuService.getTreeMenu(EDIT_MENU_ACTION);
		ModelAndView mov = new ModelAndView("/pms/PmsMenuList");
		mov.addObject("tree", str);
		return mov;
	}

	/**
	 * 进入新菜单添加页面.
	 * 
	 * @return PmsMenuAdd .
	 */
	@Permission("pms:menu:add")
	@RequestMapping("/addPmsMenuUI")
	public Object addPmsMenuUI(HttpServletRequest request ) {
		ModelAndView mov = new ModelAndView("/pms/PmsMenuAdd");
		String pid = request.getParameter("pid");
		if (null != pid) {
			PmsMenuDO pmsMenuDO = pmsMenuService.getById(Long.valueOf(pid));
			PmsMenuDO pmsMenu = new PmsMenuDO();
			pmsMenu.setParent(pmsMenuDO);
			mov.addObject("model", pmsMenu);
			//pmsMenu.setParent(parentMenu);
		}
		return mov;
	}

	/**
	 * 保存新增菜单.
	 * 
	 * @return operateSuccess or operateError .
	 */
	@Permission("pms:menu:add")
	@RequestMapping("/addPmsMenu")
	@ResponseBody
	public Object addPmsMenu(HttpServletRequest request,PmsMenuDO model) {
		try {
			String name = model.getName();
			//Map<String, Object> map = new HashMap<String, Object>();
			List<PmsMenuDO> list = pmsMenuService.getMenuByNameAndIsLeaf((short) 1,name);
			if (list.size() > 0) {
				return operateError("同级菜单名称不能重复");
			}
			pmsMenuService.createMenu(model);
			super.logSave("添加菜单[" + model.getName() + "]");
		} catch (Exception e) {
			// 记录系统操作日志
			log.error("== addPmsMenu exception:", e);
			super.logSaveError("增加菜单");
			return operateError("添加菜单出错");
		}
		return operateSuccess();
	}

	/**
	 * 进入菜单修改页面.
	 * 
	 * @return
	 */
	@Permission("pms:menu:view")
	@RequestMapping("/editPmsMenuUI")
	public Object editPmsMenuUI(HttpServletRequest request) {
		ModelAndView mov = new ModelAndView("/pms/PmsMenuEdit");
		String id = request.getParameter("id");
		if (null != id) {
			PmsMenuDO pmsMenu = pmsMenuService.getById(Long.valueOf(id));
			mov.addObject("pmsMenu", pmsMenu);
			//super.pushData(pmsMenu);
		}
		return mov;
	}

	/**
	 * 保存要修改的菜单.
	 * 
	 * @return
	 */
	@Permission("pms:menu:edit")
	@RequestMapping("/editPmsMenu")
	@ResponseBody
	public Object editPmsMenu(HttpServletRequest request,PmsMenuDO menu) {
		try {

			//PmsMenuDO menu = getModel();
			PmsMenuDO parentMenu = menu.getParent();
			if (null == parentMenu) {
				parentMenu = new PmsMenuDO();
				parentMenu.setId(0L);
			}
			if(StringUtils.isBlank(menu.getUrl())){
				menu.setUrl("");
			}
			if(StringUtils.isBlank(menu.getTargetname())){
				menu.setTargetname("");
			}
			menu.setParent(parentMenu);
			pmsMenuService.update(menu);
			// 记录系统操作日志
			super.logEdit("修改菜单,菜单名称[" + menu.getName() + "]");
			return operateSuccess();
		} catch (Exception e) {
			// 记录系统操作日志
			log.error("== editPmsMenu exception:", e);
			super.logEditError("修改菜单,菜单名称[" + menu.getName() + "]");
			return operateError("保存菜单出错");
		}

	}

	/**
	 * 删除菜单.
	 * 
	 * @return
	 */
	@Permission("pms:menu:delete")
	@RequestMapping("/delPmsMenu")
	@ResponseBody
	public Object delPmsMenu(HttpServletRequest request) {
		String menuName = null;
		try {
			Long menuId = getLong("id");
			if (menuId == null || menuId.longValue() == 0) {
				return operateError("无法获取要删除的数据");
			}
			PmsMenuDO menu = pmsMenuService.getById(menuId);
			if (menu == null) {
				return operateError("无法获取要删除的数据");
			}
			menuName = menu.getName();
			Long parentId = menu.getParentid(); // 获取父菜单ID

			// 先判断此菜单下是否有子菜单
			List<PmsMenuDO> childMenuList = pmsMenuService.listByParentId(menuId);
			if (childMenuList != null && !childMenuList.isEmpty()) {
				return operateError("此菜单下关联有【" + childMenuList.size() + "】个子菜单，不能支接删除!");
			}

			// 判断是否有权限关联到此菜单上，如有则不能删除
			List<PmsActionDO> actionList = pmsActionService.listByMenuId(menuId);
			if (actionList != null && !actionList.isEmpty()) {
				return operateError("此菜单下关联有【" + actionList.size() + "】个权限，要先解除关联后才能删除此菜单!");
			}

			// 删除掉菜单
			pmsMenuService.delete(menuId);

			// 删除菜单后，要判断其父菜单是否还有子菜单，如果没有子菜单了就要将其父菜单设为叶子节点
			if(0 != parentId){
				List<PmsMenuDO> childList = pmsMenuService.listByParentId(parentId);
				if (childList == null || childList.isEmpty()) {
					// 此时要将父菜单设为叶子
					PmsMenuDO parent = pmsMenuService.getById(parentId);
					//parent.setIsleaf(true);
					parent.setIsleaf((short)1);
					pmsMenuService.update(parent);
				}
			}
			// 记录系统操作日志
			super.logDelete("删除菜单,菜单名称[" + menu.getName() + "]");
			return operateSuccess();
		} catch (Exception e) {
			// 记录系统操作日志
			log.error("== delPmsMenu exception:", e);
			super.logDeleteError("删除菜单,菜单名称[" + menuName + "]");
			return operateError("删除菜单出错");
		}
	}

}
