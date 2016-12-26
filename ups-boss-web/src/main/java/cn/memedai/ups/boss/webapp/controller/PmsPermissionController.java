package cn.memedai.ups.boss.webapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.memedai.ups.boss.constants.PermissionConstant;
import cn.memedai.ups.boss.dal.model.PmsActionDO;
import cn.memedai.ups.boss.dal.model.PmsActionDOExample;
import cn.memedai.ups.boss.dal.model.PmsActionDOExample.Criteria;
import cn.memedai.ups.boss.dal.model.PmsMenuDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorDOExample;
import cn.memedai.ups.boss.dal.model.PmsRoleDO;
import cn.memedai.ups.boss.dal.model.PmsRoleDOExample;
import cn.memedai.ups.boss.dal.model.PmsRoleOperatorDO;
import cn.memedai.ups.boss.enums.OperatorStatusEnum;
import cn.memedai.ups.boss.enums.OperatorTypeEnum;
import cn.memedai.ups.boss.enums.RoleTypeEnum;
import cn.memedai.ups.boss.service.permission.PmsActionService;
import cn.memedai.ups.boss.service.permission.PmsMenuService;
import cn.memedai.ups.boss.service.permission.PmsOperatorService;
import cn.memedai.ups.boss.service.permission.PmsRoleService;
import cn.memedai.ups.boss.service.permission.annotation.Permission;
import cn.memedai.ups.boss.utils.ValidateUtils;
import cn.memedai.ups.boss.webapp.base.PermissionBase;

import com.github.pagehelper.PageInfo;

/**
 * 权限管理模块的Action类，包括权限点管理、角色管理、操作员管理.<br/>
 * @author tongxiong.cheng
 * @date 2016-11-29 下午4:29:17
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/pms")
public class PmsPermissionController extends PermissionBase {

	@Autowired
	private PmsActionService pmsActionService;
	
	@Autowired
	private PmsRoleService pmsRoleService;
	
	@Autowired
	private PmsOperatorService pmsOperatorService;
	
	@Autowired
	private PmsMenuService pmsMenuService;

	// //////////////////////////////////// 权限点管理
	// ///////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////

	/**
	 * 分页列出pms权限，也可根据权限获权限名称进行查询.
	 * 
	 * @return PmsActionList or operateError.
	 */
	@Permission("pms:action:view")
	@RequestMapping("/listPmsAction")
	public Object listPmsAction(HttpServletRequest request) {
		try {
			ModelAndView model = new ModelAndView("/pms/PmsActionList");
			Map<String, Object> paramMap = new HashMap<String, Object>(); // 业务条件查询参数
			//paramMap.put("actionName", getString("actionName")); // 权限名称（模糊查询）
			//paramMap.put("action", getString("act")); // 权限（精确查询）
			//paramMap.put("act", getString("act"));
			PmsActionDO pmsActionDO = new PmsActionDO();
			String actionname = getString("actionname");//权限名称（模糊查询）
			String action = getString("action");//权限（精确查询）
			paramMap.put("actionname", actionname);
			paramMap.put("action", action);
			pmsActionDO.setActionname(actionname);
			pmsActionDO.setAction(action);
			
			PageInfo<PmsActionDO> pageInfo = pmsActionService.listPage(getPageParam(), pmsActionDO);
			//super.pushData(pageBean);
			//super.pushData(paramMap); // 回显查询条件值
			model.addObject("paramMap", paramMap);
			model.addObject("pageInfo", pageInfo);
			//model.setViewName("/pms/PmsActionList");
			return model;
		} catch (Exception e) {
			log.error("== listPmsAction exception:", e);
			return operateError("获取数据失败");
		}
	}

	/**
	 * 进入添加Pms权限页面 .
	 * 
	 * @return addPmsActionUI .
	 */
	@Permission("pms:action:add")
	@RequestMapping("/addPmsActionUI")
	public String addPmsActionUI() {
		return "/pms/PmsActionAdd";
	}

	/**
	 * 将权限信息保存到数据库中
	 * 
	 * @return operateSuccess or operateError.
	 */
	@Permission("pms:action:add")
	@RequestMapping("/addPmsAction")
	@ResponseBody
	public Object addPmsAction(HttpServletRequest request) {
		try {
			String actionName = getString("actionName"); // 权限名称
			String action = getString("action"); // 权限标识
			String desc = getString("desc"); // 权限描述
			Long menuId = getLong("menu.id"); // 权限关联的菜单ID
			// 权限
			PmsActionDO act = new PmsActionDO();
			act.setActionname(actionName);
			act.setAction(action);
			act.setRemark(desc);
			act.setCreatetime(new Date());
			// 菜单
			PmsMenuDO menu = new PmsMenuDO();
			menu.setId(menuId);
			act.setMenuid(menuId);
			//act.setMenu(menu); // 设置菜单ID
			
			// 表单数据校验
			String validateMsg = validatePmsAction(act);
			if (StringUtils.isNotBlank(validateMsg)) {
				return operateError(validateMsg); // 返回错误信息
			}
			// 检查权限名称是否已存在
			PmsActionDO checkName = pmsActionService.getByActionName(actionName.trim());
			if (checkName != null) {
				return operateError("权限名称【" + actionName + "】已存在");
			}
			// 检查权限是否已存在
			PmsActionDO checkAction = pmsActionService.getByAction(action.trim());
			if (checkAction != null) {
				return operateError("权限【" + action + "】已存在");
			}

			pmsActionService.saveAction(act);

			super.logSave("添加权限[" + actionName + "," + action + "]");

			return operateSuccess(); // 返回operateSuccess视图,并提示“操作成功”
		} catch (Exception e) {
			log.error("== addPmsAction exception:", e);
			return operateError("保存失败");
		}
	}

	/**
	 * 添加或修改权限时，查找带回权限要关联的菜单ID.
	 * 
	 * @return .
	 */
	@RequestMapping("/pmsMenuLookUpUI")
	public Object pmsMenuLookUpUI() {
		ModelAndView mov = new ModelAndView("/pms/PmsMenuLookUp");
		//putData("tree", pmsMenuBiz.buildLookUpMenu());
		mov.addObject("tree", pmsMenuService.buildLookUpMenu());
		return mov;
	}

	/**
	 * 校验Pms权限信息.
	 * 
	 * @param pmsAction
	 *            .
	 * @return msg .
	 */
	private String validatePmsAction(PmsActionDO pmsAction) {
		String msg = ""; // 用于存放校验提示信息的变量
		String actionName = pmsAction.getActionname(); // 权限名称
		String action = pmsAction.getAction(); // 权限标识
		String desc = pmsAction.getRemark(); // 权限描述
		// 权限名称 actionName
		msg += lengthValidate("权限名称", actionName, true, 3, 90);
		// 权限标识 action
		msg += lengthValidate("权限标识", action, true, 3, 100);
		// 描述 desc
		msg += lengthValidate("描述", desc, true, 3, 60);
		// 校验菜单ID是否存在
		if (null != pmsAction.getMenuid()) {
			PmsMenuDO menu = pmsMenuService.getById(pmsAction.getMenuid());
			if (menu == null) {
				msg += "，请选择权限关联的菜单";
			}
		} else {
			msg += "，请选择权限关联的菜单";
		}
		return msg;
	}

	/**
	 * 转到权限修改页面 .
	 * 
	 * @return editPmsActionUI or operateError .
	 */
	@Permission("pms:action:edit")
	@RequestMapping("/editPmsActionUI")
	@ResponseBody
	public Object editPmsActionUI() {
		ModelAndView mov = new ModelAndView("/pms/PmsActionEdit");
		try {
			Long id = getLong("id");
			PmsActionDO pmsActionDO = pmsActionService.getActionAndMenuById(id);
			//super.putData("pmsAction", pmsAction);
			mov.addObject("pmsActionDO", pmsActionDO);
			return mov;
		} catch (Exception e) {
			log.error("== editPmsActionUI exception:", e);
			return operateError("获取数据失败");
		}
	}

	/**
	 * 保存修改后的权限信息
	 * 
	 * @return operateSuccess or operateError .
	 */
	@Permission("pms:action:edit")
	@RequestMapping("/editPmsAction")
	@ResponseBody
	public Object editPmsAction(HttpServletRequest request) {
		try {
			Long id = getLong("actionId");
			PmsActionDO pmsAction = pmsActionService.getActionAndMenuById(id);
			if (pmsAction == null) {
				return operateError("无法获取要修改的数据");
			} else {

				String actionName = getString("actionName");
				String action = getString("action");
				String desc = getString("desc");

				pmsAction.setActionname(actionName);
				pmsAction.setAction(action);
				pmsAction.setRemark(desc);

				// 表单数据校验
				String validateMsg = validatePmsAction(pmsAction);
				if (StringUtils.isNotBlank(validateMsg)) {
					return operateError(validateMsg); // 返回错误信息
				}

				// 检查权限名称是否已存在
				PmsActionDO checkName = pmsActionService.getByActionNameNotEqId(actionName, id);
				if (checkName != null) {
					return operateError("权限名称【" + actionName + "】已存在");
				}
				// 检查权限是否已存在
				// PmsAction checkAction =
				// pmsActionBiz.getByActionNotEqId(action, id);
				// if (checkAction != null){
				// return operateError("权限【"+action+"】已存在");
				// }

				pmsActionService.updateAction(pmsAction);

				super.logEdit("修改权限[" + actionName + "],[" + pmsAction.getAction() + "]");

				return operateSuccess();
			}
		} catch (Exception e) {
			log.error("== editPmsAction exception:", e);
			return operateError("修改失败");
		}
	}

	/**
	 * 删除一条权限记录
	 * 
	 * @return operateSuccess or operateError .
	 */
	@Permission("pms:action:delete")
	@RequestMapping("/deletePmsAction")
	@ResponseBody
	public Object deletePmsAction(HttpServletRequest request) {
		try {
			Long actionId = getLong("id");
			PmsActionDO act = pmsActionService.getActionAndMenuById(actionId);
			if (act == null) {
				return operateError("无法获取要删除的数据");
			}
			// 判断此权限是否关联有角色，要先解除与角色的关联后才能删除该权限
			List<PmsRoleDO> roleList = pmsRoleService.listByActionId(actionId);
			if (roleList != null && !roleList.isEmpty()) {
				return operateError("权限【" + act.getAction() + "】关联了【" + roleList.size() + "】个角色，要解除所有关联后才能删除。其中一个角色名为:" + roleList.get(0).getRolename());
			}
			pmsActionService.deleteActionAndRoleById(actionId);
			super.logDelete("删除权限[" + act.getActionname() + "],[" + act.getAction() + "]");
			return operateSuccess(); // 返回operateSuccess视图,并提示“操作成功”
		} catch (Exception e) {
			log.error("== deletePmsAction exception:", e);
			return operateError("删除限权异常");
		}
	}

	// /////////////////////////////////// 角色管理
	// ///////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 获取角色列表
	 * 
	 * @return listPmsRole or operateError .
	 */
	@Permission("pms:role:view")
	@RequestMapping("/listPmsRole")
	public Object listPmsRole() {
		try {
			ModelAndView model = new ModelAndView("/pms/PmsRoleList");
			//Map<String, Object> paramMap = new HashMap<String, Object>(); // 业务条件查询参数
			//paramMap.put("roleName", getString("roleName")); // 角色名称（模糊查询）
			String roleName = getString("roleName");//角色名称（模糊查询）
			PmsRoleDOExample example = new PmsRoleDOExample();
			cn.memedai.ups.boss.dal.model.PmsRoleDOExample.Criteria criteria = example.createCriteria();
			if(StringUtils.isNoneBlank(roleName)){
				criteria.andRolenameLike(roleName);
			}
			example.setOrderByClause(" id desc");
			//super.pageBean = pmsRoleBiz.listPage(getPageParam(), paramMap);
			PageInfo<PmsRoleDO> pageInfo = pmsRoleService.listPage(getPageParam(), example);
			PmsOperatorDO operator = this.getLoginedOperator();
			//super.pushData(operator);
			//super.pushData(pageBean);
			// 回显查询条件值
			//super.pushData(paramMap);		
			//super.putData("RoleTypeEnumList", RoleTypeEnum.values());
			//super.putData("RoleTypeEnum", RoleTypeEnum.toMap());
			//super.putData("OperatorTypeEnum", OperatorTypeEnum.toMap());
			model.addObject("operator", operator);
			model.addObject("pageInfo", pageInfo);
			model.addObject("RoleTypeEnumList", RoleTypeEnum.values());
			model.addObject("RoleTypeEnum", RoleTypeEnum.toMap());
			model.addObject("OperatorTypeEnum", OperatorTypeEnum.toMap());
			return model;
		} catch (Exception e) {
			log.error("== listPmsRole exception:", e);
			return operateError("获取数据失败");
		}
	}

	/**
	 * 转到添加角色页面 .
	 * 
	 * @return addPmsRoleUI or operateError .
	 */
	@Permission("pms:role:add")
	@RequestMapping("/addPmsRoleUI")
	public Object addPmsRoleUI() {
		try {
			return "/pms/PmsRoleAdd";
		} catch (Exception e) {
			log.error("== addPmsRoleUI get data exception:", e);
			return operateError("获取数据失败");
		}
	}

	/**
	 * 保存新添加的一个角色 .
	 * 
	 * @return operateSuccess or operateError .
	 */
	@Permission("pms:role:add")
	@RequestMapping("/addPmsRole")
	@ResponseBody
	public Object addPmsRole() {
		try {
			String roleName = getString("roleName");
			PmsRoleDO roleCheck = pmsRoleService.getByRoleName(roleName);
			if (roleCheck != null) {
				return operateError("角色名【" + roleName + "】已存在");
			}

			// 保存基本角色信息
			PmsRoleDO pmsRole = new PmsRoleDO();
			pmsRole.setRoletype(RoleTypeEnum.USER.getValue()); // 角色类型（1:超级管理员角色，0:普通操作员角色）
			pmsRole.setRolename(roleName);
			pmsRole.setRemark(getString("desc"));
			pmsRole.setCreatetime(new Date());
			pmsRole.setVersion(0);

			// 表单数据校验
			String validateMsg = validatePmsRole(pmsRole);
			if (StringUtils.isNotBlank(validateMsg)) {
				return operateError(validateMsg); // 返回错误信息
			}

			pmsRoleService.create(pmsRole);

			// 记录操作员操作日志
			super.logSave("添加角色信息，角色名称[" + pmsRole.getRolename() + "]");
			return operateSuccess();
		} catch (Exception e) {
			log.error("== addPmsRole exception:", e);
			return operateError("保存数据失败");
		}
	}

	/**
	 * 校验角色表单数据.
	 * 
	 * @param pmsRole
	 *            角色信息.
	 * @return msg .
	 */
	private String validatePmsRole(PmsRoleDO pmsRole) {
		String msg = ""; // 用于存放校验提示信息的变量
		String roleName = pmsRole.getRolename(); // 角色名称
		String desc = pmsRole.getRemark(); // 描述
		// 角色名称 actionName
		msg += lengthValidate("角色名称", roleName, true, 3, 90);
		// 描述 desc
		msg += lengthValidate("描述", desc, true, 3, 300);
		return msg;
	}

	/**
	 * 转到角色修改页面 .
	 * 
	 * @return editPmsRoleUI or operateError .
	 */
	@Permission("pms:role:edit")
	@RequestMapping("/editPmsRoleUI")
	@ResponseBody
	public Object editPmsRoleUI() {
		ModelAndView mov = new ModelAndView("/pms/PmsRoleEdit");
		try {
			
			Long roleId = getLong("roleId");
			PmsRoleDO pmsRole = pmsRoleService.getById(roleId);
			if (pmsRole == null) {
				return operateError("获取数据失败");
			}

			// 普通操作员没有修改超级管理员角色的权限
			if (OperatorTypeEnum.USER.getValue().equals(this.getLoginedOperator().getType()) 
			 && RoleTypeEnum.ADMIN.getValue().equals(pmsRole.getRoletype())) {
				return operateError("你没有修改超级管理员角色的权限");
			}

			//super.pushData(pmsRole);
			mov.addObject("pmsRole", pmsRole);
			return mov;
		} catch (Exception e) {
			log.error("== editPmsRoleUI exception:", e);
			return operateError("获取数据失败");
		}
	}

	/**
	 * 保存修改后的角色信息 .
	 * 
	 * @return operateSuccess or operateError .
	 */
	@Permission("pms:role:edit")
	@RequestMapping("/editPmsRole")
	@ResponseBody
	public Object editPmsRole() {
		try {
			Long id = getLong("id");

			PmsRoleDO pmsRole = pmsRoleService.getById(id);
			if (pmsRole == null) {
				return operateError("无法获取要修改的数据");
			}

			// 普通操作员没有修改超级管理员角色的权限
			if (OperatorTypeEnum.USER.getValue().equals(this.getLoginedOperator().getType()) 
			 && RoleTypeEnum.ADMIN.getValue().equals(pmsRole.getRoletype())) {
				return operateError("你没有修改超级管理员角色的权限");
			}

			String roleName = getString("roleName");
			PmsRoleDO roleCheck = pmsRoleService.findByRoleNameNotEqId(id, roleName);
			if (roleCheck != null) {
				return operateError("角色名【" + roleName + "】已存在");
			}

			pmsRole.setRolename(roleName);
			pmsRole.setRemark(getString("remark"));

			// 表单数据校验
			String validateMsg = validatePmsRole(pmsRole);
			if (StringUtils.isNotBlank(validateMsg)) {
				return operateError(validateMsg); // 返回错误信息
			}

			pmsRoleService.update(pmsRole);

			super.logEdit("修改角色[" + pmsRole.getRolename() + "]");

			return operateSuccess();
		} catch (Exception e) {
			log.error("== editPmsRole exception:", e);
			return operateError("保存失败");
		}
	}

	/**
	 * 删除一个角色
	 * 
	 * @return operateSuccess or operateError .
	 */
	@Permission("pms:role:delete")
	@RequestMapping("/deletePmsRole")
	@ResponseBody
	public Object deletePmsRole() {
		try {
			Long roleId = getLong("roleId");

			PmsRoleDO role = pmsRoleService.getById(roleId);
			if (role == null) {
				return operateError("无法获取要删除的角色");
			}
			if (RoleTypeEnum.ADMIN.getValue().equals(role.getRoletype())) {
				return operateError("超级管理员角色不可删除");
			}

			String msg = "";
			// 判断是否有操作员关联到此角色
			int operatorCount = pmsOperatorService.countOperatorByRoleId(roleId);
			if (operatorCount > 0) {
				msg += "【" + operatorCount + "】个操作员";
			}
			// 判断是否有权限关联到此角色 ----2014-02-18注释
			// int actionCount = pmsActionBiz.countActionByRoleId(roleId);
			// if (actionCount > 0){
			// msg += "【"+actionCount+"】个权限";
			// }
			// // 判断是否有菜单关联到此角色
			// int menuCount = pmsMenuBiz.countMenuByRoleId(roleId);
			// if (menuCount > 0){
			// msg += "【"+menuCount+"】个菜单";
			// }

			if (StringUtils.isNotBlank(msg)) {
				msg += "关联到此角色，要先解除所有关联后才能删除!";
				return operateError("有" + msg);
			}

			pmsRoleService.deleteRoleById(roleId);
			super.logDelete("删除角色，名称:" + role.getRolename());
			return operateSuccess();
		} catch (Exception e) {
			log.error("== deletePmsRole exception:", e);
			super.logDeleteError("删除角色出错:" + e.getMessage());
			return operateError("删除失败");
		}
	}

	/**
	 * 分配权限UI
	 * 
	 * @return
	 */
	@Permission("pms:role:assignpermission")
	@RequestMapping("/assignPermissionUI")
	@ResponseBody
	public Object assignPermissionUI() {
		ModelAndView mov = new ModelAndView("/pms/assignPermissionUI");
		Long roleId = getLong("roleId");

		PmsRoleDO role = pmsRoleService.getById(roleId);
		if (role == null) {
			return operateError("无法获取角色信息");
		}
		// 普通操作员没有修改超级管理员角色的权限
		if (OperatorTypeEnum.USER.getValue().equals(this.getLoginedOperator().getType()) 
		 && RoleTypeEnum.ADMIN.getValue().equals(role.getRoletype())) {
			return operateError("你没有修改超级管理员角色的权限");
		}

		String menuIds = "";
		String actionIds = "";
		try {
			menuIds = pmsMenuService.getMenuIdsByRoleId(roleId); // 根据角色查找角色对应的菜单ID集
			actionIds = pmsActionService.getActionIdsByRoleId(roleId); // 根据角色查找角色对应的功能权限ID集
		} catch (Exception e) {
			log.error("根据角色ID，找不到对应的菜单、权限", e);
		}

		// 前面加个逗号，方便接下来的处理
		menuIds = "," + menuIds;
		actionIds = "," + actionIds;

		//super.putData("menuActionTree", pmsMenuBiz.buildMenuActionTree(menuIds, actionIds));
		mov.addObject("menuActionTree", pmsMenuService.buildMenuActionTree(menuIds, actionIds));
		// 查询角色对应的用户
		List<PmsOperatorDO> userList = (List<PmsOperatorDO>) pmsOperatorService.listByRoleId(roleId);
		//super.putData("userList", userList);
		mov.addObject("userList", userList);
		//super.putData("roleId", roleId);
		mov.addObject("roleId", roleId);
		return mov;
	}

	/**
	 * 分配角色权限
	 */
	@Permission("pms:role:assignpermission")
	@RequestMapping("/assignPermission")
	public void assignPermission() {
		try {

			Long roleId = getLong("roleId");

			PmsRoleDO role = pmsRoleService.getById(roleId);
			if (role == null) {
				getOutputMsg().put("MSG", "无法获取角色信息");
				return;
			}
			// 普通操作员没有修改超级管理员角色的权限
			if (OperatorTypeEnum.USER.getValue().equals(this.getLoginedOperator().getType()) 
					 && RoleTypeEnum.ADMIN.getValue().equals(role.getRoletype())) {
				getOutputMsg().put("MSG", "你没有修改超级管理员角色的权限");
				return;
			}
			
			String menuIds = getString("menuIds");
			
			if (StringUtils.isNotBlank(menuIds)) {
				// 去除js错误选择导致的 undefined
				menuIds = menuIds.replaceAll("undefined,", "");
			}
			
			String actionIds = getString("actionIds");
			
			if (StringUtils.isNotBlank(actionIds)) {
				// 去除js错误选择导致的 undefined
				actionIds = actionIds.replaceAll("undefined,", "");
			}
			// 分配菜单权限，功能权限
			pmsMenuService.assignPermission(roleId, menuIds, actionIds);

			// String menuNameBuffer = theMenusIdsChangeNames(menuIds); // 查询菜单的

			// String actionNameBuffer = theActionIdsChangeNames(actionIds);
			super.logEdit("修改角色[" + role.getRolename() + "]的权限，菜单ID[" + menuIds + "],权限ID[" + actionIds + "]");
			// super.logEdit("修改角色[" + role.getRoleName() +
			// "]的权限，菜单名称[" + menuNameBuffer + "],权限名称[" + actionNameBuffer +
			// "]"); // TODO 暂时注释
			getOutputMsg().put("STATE", "SUCCESS");
		} catch (Exception e) {
			log.error("分配权限出现错误!", e);
			getOutputMsg().put("STATE", "FAIL");
			getOutputMsg().put("MSG", "分配权限出现错误。" + e.getMessage());
		}
		outPrint(getHttpResponse(), JSONObject.fromObject(getOutputMsg()));
	}

	/***
	 * 把权限的ID转换成NAME
	 * 
	 * @param actionIds
	 * @return
	 */
	@SuppressWarnings("unused")
	private String theActionIdsChangeNames(String actionIds) {
		if (StringUtils.isEmpty(actionIds))
			return null;
		StringBuffer actionBuffer = new StringBuffer();
		int actionNum = actionIds.indexOf(",");
		if (actionNum <= 0) {
			PmsActionDO action = pmsActionService.getActionAndMenuById(Long.valueOf(actionIds));
			actionBuffer.append(action.getActionname());
		} else {
			String[] actionArray = actionIds.split(",");
			for (int i = 0; i < actionArray.length; i++) {
				PmsActionDO action = pmsActionService.getActionAndMenuById(Long.valueOf(actionArray[i]));
				if (i == actionArray.length - 1) {
					actionBuffer.append(action.getActionname());
				} else {
					actionBuffer.append(action.getActionname()).append(",");
				}
			}
		}
		return actionBuffer.toString();
	}

	/***
	 * 把菜单的ID转换成name
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private String theMenusIdsChangeNames(String menuIds) {
		if (StringUtils.isEmpty(menuIds))
			return null;
		StringBuffer menuBuffer = new StringBuffer(); // 追加菜单的名称
		int menuNum = menuIds.indexOf(",");
		if (menuNum <= 0) {
			PmsMenuDO menu = pmsMenuService.getById(Long.valueOf(menuIds));
			menuBuffer.append(menu.getName());
		} else {
			String[] menuArray = menuIds.split(",");
			for (int i = 0; i < menuArray.length; i++) {
				PmsMenuDO menu = pmsMenuService.getById(Long.valueOf(menuArray[i]));
				if (i == menuArray.length - 1) {
					menuBuffer.append(menu.getName());
				} else {
					menuBuffer.append(menu.getName()).append(",");
				}
			}
		}
		return menuBuffer.toString();
	}

	// /////////////////////////////////// 操作员管理
	// /////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 分页列出操作员信息，并可按登录名获姓名进行查询.
	 * 
	 * @return listPmsOperator or operateError .
	 * 
	 */
	@Permission("pms:operator:view")
	@RequestMapping("/listPmsOperator")
	@ResponseBody
	public Object listPmsOperator() {
		try {
			ModelAndView model = new ModelAndView("/pms/PmsOperatorList");
			Map<String, Object> paramMap = new HashMap<String, Object>(); // 业务条件查询参数
//			paramMap.put("loginName", getString("loginName")); // 操作员登录名（精确查询）
//			paramMap.put("realName", getString("realName")); // 操作员姓名（模糊查询）
//			paramMap.put("status", getInteger("status")); // 状态
			String loginName = getString("loginName");// 操作员登录名（精确查询）
			String realName =  getString("realName");// 操作员姓名（模糊查询）
			String status = getString("status");// 状态
			paramMap.put("loginName", loginName);
			paramMap.put("realName", realName);
			paramMap.put("status", status);
			
			PmsOperatorDOExample example = new PmsOperatorDOExample();
			cn.memedai.ups.boss.dal.model.PmsOperatorDOExample.Criteria criteria = example.createCriteria();
			if(StringUtils.isNotBlank(loginName)){
				criteria.andLoginnameEqualTo(loginName);
			}
			if(StringUtils.isNotBlank(realName)){
				criteria.andRealnameLike(realName);
			}
			if(StringUtils.isNotBlank(status)){
				criteria.andStatusEqualTo(status);
			}
			example.setOrderByClause("id desc");
			
			PageInfo<PmsOperatorDO> pageInfo = pmsOperatorService.listPage(getPageParam(), example);
			
			//super.pageBean = pmsOperatorBiz.listPage(getPageParam(), paramMap);
			//super.pushData(pageBean);
			//PmsOperatorDO pmsOperator = getLoginedOperator();// 获取当前登录操作员对象
			//super.putData("currLoginName", pmsOperator.getLoginname());
			// 回显查询条件值
			//super.pushData(paramMap);
			//super.putData("OperatorStatusEnumList", OperatorStatusEnum.values());
			//super.putData("OperatorStatusEnum", OperatorStatusEnum.toMap());
			//super.putData("OperatorTypeEnumList", OperatorTypeEnum.values());
			//super.putData("OperatorTypeEnum", OperatorTypeEnum.toMap());
			
			model.addObject("pageInfo",pageInfo);
			model.addObject("paramMap", paramMap);
			model.addObject("OperatorStatusEnumList", OperatorStatusEnum.values());
			model.addObject("OperatorStatusEnum", OperatorStatusEnum.toMap());
			model.addObject("OperatorTypeEnumList", OperatorTypeEnum.values());
			model.addObject("OperatorTypeEnum", OperatorTypeEnum.toMap());
			
			return model;
		} catch (Exception e) {
			log.error("== listPmsOperator exception:", e);
			return operateError("获取数据失败");
		}
	}

	/**
	 * 查看操作员详情.
	 * 
	 * @return .
	 */
	@Permission("pms:operator:view")
	@RequestMapping("/viewPmsOperatorUI")
	@ResponseBody
	public Object viewPmsOperatorUI() {
		try {
			ModelAndView mov = new ModelAndView("/pms/PmsOperatorView");
			String operatorId = getString("id");
			PmsOperatorDO pmsOperator = pmsOperatorService.getById(Long.parseLong(operatorId));
			if (pmsOperator == null) {
				return operateError("无法获取要查看的数据");
			}

			// 普通操作员没有查看超级管理员的权限
			// if ("0".equals(this.getLoginedOperator().getType()) &&
			// "1".equals(pmsOperator.getType())) {
			// return operateError("权限不足");
			// }
			mov.addObject("pmsOperator",pmsOperator);
			//super.pushData(pmsOperator);
			// 准备角色列表
			//super.putData("rolesList", pmsRoleBiz.listAllRole());
			mov.addObject("rolesList", pmsRoleService.listAll());
			
			// 准备该用户拥有的角色ID字符串
			List<PmsRoleOperatorDO> lisPmsRoleOperators = pmsOperatorService.listRoleOperatorByOperatorId(Long.parseLong(operatorId));
			StringBuffer owenedRoleIdBuffer = new StringBuffer("");
			for (PmsRoleOperatorDO pmsRoleOperator : lisPmsRoleOperators) {
				owenedRoleIdBuffer.append(pmsRoleOperator.getRoleid());
				owenedRoleIdBuffer.append(",");
			}
			String owenedRoleIds = owenedRoleIdBuffer.toString();
			if (StringUtils.isNotBlank(owenedRoleIds) && owenedRoleIds.length() > 0) {
				owenedRoleIds = owenedRoleIds.substring(0, owenedRoleIds.length() - 1);
			}
			//super.putData("owenedRoleIds", owenedRoleIds);
			mov.addObject("owenedRoleIds", owenedRoleIds);
			return mov;
		} catch (Exception e) {
			log.error("== viewPmsOperatorUI exception:", e);
			return operateError("获取数据失败");
		}
	}

	/**
	 * 转到添加操作员页面 .
	 * 
	 * @return addPmsOperatorUI or operateError .
	 */
	@Permission("pms:operator:add")
	@RequestMapping("/addPmsOperatorUI")
	@ResponseBody
	public Object addPmsOperatorUI() {
		try {
			ModelAndView mov = new ModelAndView("/pms/PmsOperatorAdd");
			/*super.putData("rolesList", pmsRoleBiz.listAllRole());
			super.putData("OperatorStatusEnumList", OperatorStatusEnum.values());
			super.putData("RoleTypeEnum", RoleTypeEnum.toMap());*/
			mov.addObject("rolesList", pmsRoleService.listAll());
			mov.addObject("OperatorStatusEnumList", OperatorStatusEnum.values());
			mov.addObject("RoleTypeEnum", RoleTypeEnum.toMap());
			return mov;
		} catch (Exception e) {
			log.error("== addPmsOperatorUI exception:", e);
			return operateError("获取角色列表数据失败");
		}
	}

	/**
	 * 保存一个操作员
	 * 
	 */
	@Permission("pms:operator:add")
	@RequestMapping("/addPmsOperator")
	@ResponseBody
	public Object addPmsOperator() {
		try {
			String loginPwd = getString("loginPwdss"); // 初始登录密码

			String loginName = getString("loginNamess");

			PmsOperatorDO pmsOperator = new PmsOperatorDO();
			pmsOperator.setRealname(getString("realName")); // 姓名
			pmsOperator.setLoginname(loginName); // 登录名
			pmsOperator.setLoginpwd(loginPwd);
			pmsOperator.setRemark(getString("desc")); // 描述
			pmsOperator.setIschangedpwd((short)0);
			pmsOperator.setLastlogintime(null);
			pmsOperator.setMobileno(getString("mobileNo")); // 手机号码
			pmsOperator.setStatus(getInteger("status")+""); // 状态（100:'激活',101:'冻结'1）
			pmsOperator.setType(String.valueOf(OperatorTypeEnum.USER.getValue())); // 类型（ "0":'普通操作员',"1":'超级管理员'），只能添加普通操作员
			pmsOperator.setCreatetime(new Date());
			
			String roleOperatorStr = getRoleOperatorStr();

			// 表单数据校验
			String validateMsg = validatePmsOperator(pmsOperator, roleOperatorStr);

			// if (!loginPwdFormat(loginPwd)) {
			// return operateError("登录密码必须由字母、数字、特殊符号组成");
			// }

			if (StringUtils.isNotBlank(validateMsg)) {
				return operateError(validateMsg); // 返回错误信息
			}

			// 校验操作员登录名是否已存在
			PmsOperatorDO loginNameCheck = pmsOperatorService.findByLoginName(loginName);
			if (loginNameCheck != null) {
				return operateError("登录名【" + loginName + "】已存在");
			}

			pmsOperator.setLoginpwd(DigestUtils.sha1Hex(loginPwd)); // 存存前对密码进行加密

			pmsOperatorService.saveOperator(pmsOperator, roleOperatorStr);

			String roleNames = theRolesChangeNames(roleOperatorStr);

			super.logSave("添加操作员[" + loginName + "]，关联角色[" + roleNames + "]");

			return operateSuccess();
		} catch (Exception e) {
			log.error("== addPmsOperator exception:", e);
			return operateError("保存操作员信息失败");
		}
	}

	/***
	 * 把角色的ID转成NAME
	 * 
	 * @param roleOperatorStr
	 * @return
	 */
	private String theRolesChangeNames(String roleOperatorStr) {
		if (StringUtils.isEmpty(roleOperatorStr))
			return null;
		StringBuffer menuBuffer = new StringBuffer(); // 追加菜单的名称
		int roleNum = roleOperatorStr.indexOf(",");
		if (roleNum <= 0) {
			PmsRoleDO role = pmsRoleService.getById(Long.valueOf(roleOperatorStr));
			menuBuffer.append(role.getRolename());
		} else {
			String[] roleArray = roleOperatorStr.split(",");
			for (int i = 0; i < roleArray.length; i++) {
				PmsRoleDO role = pmsRoleService.getById(Long.valueOf(roleArray[i]));
				if (i == roleArray.length - 1) {
					menuBuffer.append(role.getRolename());
				} else {
					menuBuffer.append(role.getRolename()).append(",");
				}
			}
		}
		return menuBuffer.toString();
	}

	/**
	 * 验证输入的邮箱格式是否符合
	 * 
	 * @param email
	 * @return 是否合法
	 */
	public static boolean emailFormat(String email) {
		// boolean tag = true;
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		boolean result = Pattern.matches(check, email);
		return result;
	}

	/**
	 * 验证输入的密码格式是否符合
	 * 
	 * @param loginPwd
	 * @return 是否合法
	 */
	public static boolean loginPwdFormat(String loginPwd) {
		return loginPwd.matches(".*?[^a-zA-Z\\d]+.*?") && loginPwd.matches(".*?[a-zA-Z]+.*?") && loginPwd.matches(".*?[\\d]+.*?");
	}

	/**
	 * 验证输入的操作员姓名格式是否符合
	 * 
	 * @param loginPwd
	 * @return 是否合法
	 */
	public static boolean realNameFormat(String realName) {
		return realName.matches("[^\\x00-\\xff]+");
	}

	/**
	 * 校验Pms操作员表单数据.
	 * 
	 * @param PmsOperator
	 *            操作员信息.
	 * @param roleOperatorStr
	 *            关联的角色ID串.
	 * @return
	 */
	private String validatePmsOperator(PmsOperatorDO operator, String roleOperatorStr) {
		String msg = ""; // 用于存放校验提示信息的变量
		msg += lengthValidate("真实姓名", operator.getRealname(), true, 2, 15);
		msg += lengthValidate("登录名", operator.getLoginname(), true, 3, 50);

		
		 String specialChar = "`!@#$%^&*()_+\\/"; if
		 (operator.getLoginname().contains(specialChar)) { msg +=
		  "登录名不能包含特殊字符，"; }
		 
		if (!realNameFormat(operator.getRealname())) {
			msg += "操作员姓名必须为中文！";
		}

//		if (!emailFormat(operator.getLoginName())) {
//			msg += "账户名格式必须为邮箱地址！";
//		}
		
		// 登录密码
		String loginPwd = operator.getLoginpwd();
		String loginPwdMsg = lengthValidate("登录密码", loginPwd, true, 6, 50);
		
		  if (StringUtils.isBlank(loginPwdMsg) &&
		  !ValidateUtils.isAlphanumeric(loginPwd)) { loginPwdMsg +=
		  "登录密码应为字母或数字组成，"; }
		 
		msg += loginPwdMsg;

		// 手机号码
		String mobileNo = operator.getMobileno();
		String mobileNoMsg = lengthValidate("手机号", mobileNo, true, 0, 12);
		if (StringUtils.isBlank(mobileNoMsg) && !ValidateUtils.isMobile(mobileNo)) {
			mobileNoMsg += "手机号格式不正确，";
		}
		msg += mobileNoMsg;

		// 状态
		String status = operator.getStatus();
		if (status == null) {
			msg += "请选择状态，";
		} else if (Integer.parseInt(status) < 100 || Integer.parseInt(status) > 101) {
			msg += "状态值不正确，";
		}

		msg += lengthValidate("描述", operator.getRemark(), true, 3, 100);

		// 新增操作员的权限不能为空，为空没意义
		if (StringUtils.isBlank(roleOperatorStr) && operator.getId() == null) {
			msg += "操作员关联的角色不能为空";
		}
		return msg;
	}

	/**
	 * 删除操作员
	 * 
	 * @return
	 * */
	@Permission("pms:operator:delete")
	@RequestMapping("/deleteOperatorStatus")
	@ResponseBody
	public Object deleteOperatorStatus() {
		long id = getLong("id");
		PmsOperatorDO pmsOperator = pmsOperatorService.getById(id); // 查询操作员信息
		pmsOperatorService.deleteOperatorById(id);
		super.logDelete("删除操作员.操作员登录名[" + pmsOperator.getLoginname() + "]");
		return this.operateSuccess("操作成功");
	}

	/**
	 * 转到修改操作员界面
	 * 
	 * @return PmsOperatorEdit or operateError .
	 */
	@Permission("pms:operator:edit")
	@RequestMapping("/editPmsOperatorUI")
	@ResponseBody
	public Object editPmsOperatorUI() {
		try {
			ModelAndView mov = new ModelAndView("/pms/PmsOperatorEdit");
			Long id = getLong("id");
			PmsOperatorDO pmsOperator = pmsOperatorService.getById(id);
			if (pmsOperator == null) {
				return operateError("无法获取要修改的数据");
			}

			// 普通操作员没有修改超级管理员的权限
			if (OperatorTypeEnum.USER.getValue().equals(this.getLoginedOperator().getType()) 
			 && OperatorTypeEnum.ADMIN.getValue().equals(pmsOperator.getType())) {
				return operateError("权限不足");
			}

			//super.pushData(pmsOperator);
			mov.addObject("pmsOperator", pmsOperator);
			// 准备角色列表
			//super.putData("rolesList", pmsRoleBiz.listAllRole());
			mov.addObject("rolesList", pmsRoleService.listAll());
			// 准备该用户拥有的角色ID字符串
			List<PmsRoleOperatorDO> lisPmsRoleOperators = pmsOperatorService.listRoleOperatorByOperatorId(id);
			StringBuffer owenedRoleIdBuffer = new StringBuffer("");
			for (PmsRoleOperatorDO pmsRoleOperator : lisPmsRoleOperators) {
				owenedRoleIdBuffer.append(pmsRoleOperator.getRoleid());
				owenedRoleIdBuffer.append(",");
			}
			String owenedRoleIds = owenedRoleIdBuffer.toString();
			if (StringUtils.isNotBlank(owenedRoleIds) && owenedRoleIds.length() > 0) {
				owenedRoleIds = owenedRoleIds.substring(0, owenedRoleIds.length() - 1);
			}
			//super.putData("owenedRoleIds", owenedRoleIds);
			mov.addObject("owenedRoleIds", owenedRoleIds);
			//super.putData("OperatorStatusEnum", OperatorStatusEnum.toMap());
			//super.putData("OperatorTypeEnum", OperatorTypeEnum.toMap());
			//super.putData("RoleTypeEnum", RoleTypeEnum.toMap());
			mov.addObject("OperatorStatusEnum", OperatorStatusEnum.toMap());
			mov.addObject("OperatorTypeEnum", OperatorTypeEnum.toMap());
			mov.addObject("RoleTypeEnum", RoleTypeEnum.toMap());
			
			
			return mov;
		} catch (Exception e) {
			log.error("== editPmsOperatorUI exception:", e);
			return operateError("获取修改数据失败");
		}
	}

	/**
	 * 保存修改后的操作员信息
	 * 
	 * @return operateSuccess or operateError .
	 */
	@Permission("pms:operator:edit")
	@RequestMapping("/editPmsOperator")
	@ResponseBody
	public Object editPmsOperator() {
		try {
			Long id = getLong("id");

			PmsOperatorDO pmsOperator = pmsOperatorService.getById(id);
			if (pmsOperator == null) {
				return operateError("无法获取要修改的操作员信息");
			}

			// 普通操作员没有修改超级管理员的权限
			if ("0".equals(this.getLoginedOperator().getType()) && "1".equals(pmsOperator.getType())) {
				return operateError("权限不足");
			}

			pmsOperator.setRemark(getString("remark"));
			pmsOperator.setMobileno(getString("mobileno"));
			pmsOperator.setRealname(getString("realname"));
			// 修改时不能修状态
			// pmsOperator.setStatus(getInteger("status"));

			String roleOperatorStr = getRoleOperatorStr();
			String newStr = "";
			StringBuffer oldRoleNameBuffer = new StringBuffer();
			// 查询操作员原有的角色
			List<PmsRoleOperatorDO> list = pmsOperatorService.listRoleOperatorByOperatorId(id);
			for (PmsRoleOperatorDO ro : list) {
				if (newStr == null || "".equals(newStr) ) {
					newStr += ro.getRoleid();
				} else {
					newStr += "," + ro.getRoleid();
				}
				PmsRoleDO role = pmsRoleService.getById(ro.getRoleid());
				oldRoleNameBuffer.append(role.getRolename()).append(",");
			}

			// StringBuffer newRoleNameBuffer = new StringBuffer();
			// String[] newRoleIdList = roleOperatorStr.split(",");
			// for (String roleId : newRoleIdList) {
			// PmsRole role = pmsRoleBiz.getById(Long.valueOf(roleId));
			// newRoleNameBuffer.append(role.getRoleName()).append(",");
			// }
			String newRoleNames = theRolesChangeNames(roleOperatorStr);

			// 表单数据校验
			String validateMsg = validatePmsOperator(pmsOperator, roleOperatorStr);
			if (StringUtils.isNotBlank(validateMsg)) {
				return operateError(validateMsg); // 返回错误信息
			}

			pmsOperatorService.updateOperator(pmsOperator, roleOperatorStr);
			super.logEdit("修改操作员[" + pmsOperator.getLoginname() + "]，更改前角色[" + oldRoleNameBuffer + "]，更改后角色[" + newRoleNames + "]");
			return operateSuccess();
		} catch (Exception e) {
			log.error("== editPmsOperator exception:", e);
			return operateError("更新操作员信息失败");
		}
	}

	/**
	 * 根据ID冻结或激活操作员.
	 * 
	 * @return operateSuccess or operateError .
	 */
	@Permission("pms:operator:edit")
	@RequestMapping("/changeOperatorStatus")
	@ResponseBody
	public Object changeOperatorStatus() {
		try {
			Long operatorId = getLong("id");
			PmsOperatorDO operator = pmsOperatorService.getById(operatorId);
			if (operator == null) {
				return operateError("无法获取要操作的数据");
			}

			if (this.getLoginedOperator().getId() == operatorId) {
				return operateError("不能修改自己账户的状态");
			}

			// 普通操作员没有修改超级管理员的权限
			if ("0".equals(this.getLoginedOperator().getType()) && "1".equals(operator.getType())) {
				return operateError("你没有修改超级管理员的权限");
			}

			// 2014-01-02,由删除改为修改状态
			// pmsPermissionBiz.deleteOperator(id);
			// 激活的变冻结，冻结的则变激活
			if (Integer.parseInt(operator.getStatus()) == OperatorStatusEnum.ACTIVE.getValue()) {
				if ("1".equals(operator.getType())) {
					return operateError("【" + operator.getLoginname() + "】为超级管理员，不能冻结");
				}
				operator.setStatus(OperatorStatusEnum.INACTIVE.getValue()+"");
				pmsOperatorService.update(operator);
				super.logEdit("冻结操作员[" + operator.getLoginname() + "]");
			} else {
				operator.setStatus(OperatorStatusEnum.ACTIVE.getValue()+"");
				operator.setPwderrorcount((short)0);
				pmsOperatorService.update(operator);
				super.logEdit("激活操作员[" + operator.getLoginname() + "]");
			}
			return operateSuccess();
		} catch (Exception e) {
			log.error("== changeOperatorStatus exception:", e);
			return operateError("删除操作员失败:" + e.getMessage());
		}
	}

	/***
	 * 重置操作员的密码（注意：不是修改当前登录操作员自己的密码） .
	 * 
	 * @return
	 */
	@Permission("pms:operator:resetpwd")
	@RequestMapping("/resetOperatorPwdUI")
	@ResponseBody
	public Object resetOperatorPwdUI() {
		ModelAndView mov = new ModelAndView("/pms/PmsOperatorResetPwd");
		PmsOperatorDO operator = pmsOperatorService.getById(getLong("id"));
		if (operator == null) {
			return operateError("无法获取要重置的信息");
		}

		// 普通操作员没有修改超级管理员的权限
		if ("0".equals(this.getLoginedOperator().getType()) && "1".equals(operator.getType())) {
			return operateError("你没有修改超级管理员的权限");
		}

		//super.putData("operatorId", operator.getId());
		//super.pushData(operator);

		mov.addObject("operator", operator);
		
		return mov;
	}

	/**
	 * 重置操作员密码.
	 * 
	 * @return
	 */
	@Permission("pms:operator:resetpwd")
	@RequestMapping("/resetOperatorPwd")
	@ResponseBody
	public Object resetOperatorPwd() {
		try {
			Long operatorId = getLong("operatorId");
			PmsOperatorDO operator = pmsOperatorService.getById(operatorId);
			if (operator == null) {
				return operateError("无法获取要重置密码的操作员信息");
			}

			// 普通操作员没有修改超级管理员的权限
			if ("0".equals(this.getLoginedOperator().getType()) && "1".equals(operator.getType())) {
				return operateError("你没有修改超级管理员的权限");
			}

			String newPwd = getString("newPwd");
			String newPwd2 = getString("newPwd2");

			if (!loginPwdFormat(newPwd)) {
				return operateError("登录密码必须由字母、数字、特殊符号组成");
			}

			String validateMsg = validatePassword(newPwd, newPwd2);
			if (StringUtils.isNotBlank(validateMsg)) {
				return operateError(validateMsg); // 返回错误信息
			}
			
			pmsOperatorService.updateOperatorPwd(operatorId, DigestUtils.sha1Hex(newPwd), false);

			super.logEdit("重置操作员[" + operator.getLoginname() + "]的密码");
			return operateSuccess();
		} catch (Exception e) {
			log.error("== resetOperatorPwd exception:", e);
			return operateError("密码重置出错:" + e.getMessage());
		}
	}

	/**
	 * 进入重置当前登录操作员自己的密码的页面.
	 * 
	 * @return
	 */
	@RequestMapping("/operatorChangeOwnPwdUI")
	public String operatorChangeOwnPwdUI() {
		return "/pms/PmsOperatorChangeOwnPwd";
	}

	/**
	 * 重置当前登录操作员自己的密码.
	 * 
	 * @return
	 */
	@RequestMapping("/operatorChangeOwnPwd")
	@ResponseBody
	public Object operatorChangeOwnPwd(HttpSession session) {
		try {

			PmsOperatorDO operator = (PmsOperatorDO) session.getAttribute(PermissionConstant.OPERATOR_SESSION_KEY);// this.getLoginedOperator();
			if (operator == null) {
				return operateError("无法从会话中获取操作员信息");
			}

			// 判断旧密码是否正确
			String oldPwd = getString("oldPwd");
			if (StringUtils.isBlank(oldPwd)) {
				return operateError("请输入旧密码");
			}
			// 旧密码要判空，否则sha1Hex会出错
			if (!operator.getLoginpwd().equals(DigestUtils.sha1Hex(oldPwd))) {
				return operateError("旧密码不正确");
			}

			// 校验新密码
			String newPwd = getString("newPwd");
			if (oldPwd.equals(newPwd)) {
				return operateError("新密码不能与旧密码相同");
			}

			if (!loginPwdFormat(newPwd)) {
				return operateError("登录密码必须由字母、数字、特殊符号组成");
			}

			String newPwd2 = getString("newPwd2");
			String validateMsg = validatePassword(newPwd, newPwd2);
			if (StringUtils.isNotBlank(validateMsg)) {
				return operateError(validateMsg); // 返回错误信息
			}

			// 更新密码
			pmsOperatorService.updateOperatorPwd(operator.getId(), DigestUtils.sha1Hex(newPwd), true);

			// 修改密码成功后要清空session，以强制重新登录
			session.invalidate();//().remove(ConstantSession.OPERATOR_SESSION_KEY);
			//getSessionMap().remove(ConstantSession.ACTIONS_SESSION_KEY);
			//getSessionMap().clear();
			
			super.logEdit("修改了自己的密码,登录名[" + operator.getLoginname() + "]");

			return operateSuccess("密码修改成功，请重新登录!");
		} catch (Exception e) {
			log.error("== operatorChangeOwnPwd exception:", e);
			super.logEditError("修改了自己的密码出错:" + e.getMessage());
			return operateError("修改密码出错:" + e.getMessage());
		}
	}

	/**
	 * 当前登录的操作员查看自己帐号的详细信息.
	 * 
	 * @return
	 */
	@RequestMapping("/operatorViewOwnInfo")
	@ResponseBody
	public Object operatorViewOwnInfo(HttpSession session,HttpServletRequest request/*,ModelAndView model*/) {
		try {
			ModelAndView model = new ModelAndView();
			PmsOperatorDO pmsOperator = this.getLoginedOperator();
			if (pmsOperator == null) {
				return operateError("无法从会话中获取操作员信息");
			}

			PmsOperatorDO operator = pmsOperatorService.getById(pmsOperator.getId());
			if (operator == null) {
				return operateError("无法获取操作员信息");
			}
			request.setAttribute("forwardUrl", "/pms/PmsOperatorViewOwnInfo");

			//return operateSuccess("成功");
			//super.pushData(operator);
			model.addObject("operator", operator);
			model.setViewName("/pms/PmsOperatorViewOwnInfo");
			return model;
		} catch (Exception e) {
			log.error("== editPmsOperator exception:", e);
			return operateError("无法获取要修改的操作员信息失败");
		}
	}

	/**
	 * 得到角色和操作员关联的ID字符串
	 * 
	 * @return
	 */
	private String getRoleOperatorStr() throws Exception {
		String roleStr = getString("selectVal");
		if (StringUtils.isNotBlank(roleStr) && roleStr.length() > 0) {
			roleStr = roleStr.substring(0, roleStr.length() - 1);
		}
		return roleStr;
	}

	/***
	 * 验证重置密码
	 * 
	 * @param newPwd
	 * @param newPwd2
	 * @return
	 */
	private String validatePassword(String newPwd, String newPwd2) {
		String msg = ""; // 用于存放校验提示信息的变量
		if (StringUtils.isBlank(newPwd)) {
			msg += "新密码不能为空，";
		} else if (newPwd.length() < 6) {
			msg += "新密码不能少于6位长度，";
		}

		if (!newPwd.equals(newPwd2)) {
			msg += "两次输入的密码不一致";
		}
		return msg;
	}
}
