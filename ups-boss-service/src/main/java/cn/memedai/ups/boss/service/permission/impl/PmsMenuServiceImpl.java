package cn.memedai.ups.boss.service.permission.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.constants.GlobalConstant;
import cn.memedai.ups.boss.constants.PermissionConstant;
import cn.memedai.ups.boss.dal.dao.pms.PmsMenuDOMapper;
import cn.memedai.ups.boss.dal.dao.pms.PmsRoleActionDOMapper;
import cn.memedai.ups.boss.dal.dao.pms.PmsRoleMenuDOMapper;
import cn.memedai.ups.boss.dal.model.pms.PmsActionDO;
import cn.memedai.ups.boss.dal.model.pms.PmsMenuDO;
import cn.memedai.ups.boss.dal.model.pms.PmsMenuDOExample;
import cn.memedai.ups.boss.dal.model.pms.PmsRoleActionDO;
import cn.memedai.ups.boss.dal.model.pms.PmsRoleMenuDO;
import cn.memedai.ups.boss.dal.model.pms.PmsRoleMenuDOExample;
import cn.memedai.ups.boss.dal.model.pms.PmsMenuDOExample.Criteria;
import cn.memedai.ups.boss.service.context.ThreadLocalContext;
import cn.memedai.ups.boss.service.permission.PmsActionService;
import cn.memedai.ups.boss.service.permission.PmsMenuService;
import cn.memedai.ups.boss.service.permission.PmsRoleActionService;
import cn.memedai.ups.boss.service.permission.PmsRoleMenuService;
import cn.memedai.ups.boss.service.permission.exception.PermissionException;
@Service("pmsMenuService")
@Slf4j
public class PmsMenuServiceImpl implements PmsMenuService {

	@Autowired
	PmsMenuDOMapper pmsMenuDOMapper;
	
	//角色菜单关联服务
	@Autowired
	PmsRoleMenuDOMapper pmsRoleMenuDOMapper;
	@Autowired
	PmsRoleMenuService pmsRoleMenuService;
	@Autowired
	PmsActionService pmsActionService;
	
	@Autowired
	PmsRoleActionDOMapper pmsRoleActionDOMapper;
	
	@Autowired
	PmsRoleActionService pmsRoleActionService;
	
	@Override
	public List<PmsMenuDO> listByRoleIds(String roleIdsStr) {
		List<String> roldIds = Arrays.asList(roleIdsStr.split(","));
		List<Long> roleIDList = new ArrayList<Long>();
		for(String id : roldIds){
			roleIDList.add(Long.parseLong(id));
		}
		PmsRoleMenuDOExample roleMenuDOExample = new PmsRoleMenuDOExample();
		roleMenuDOExample.createCriteria().andRoleidIn(roleIDList);
		List<PmsRoleMenuDO> pmsRoleMenuDOList = pmsRoleMenuDOMapper.selectByExample(roleMenuDOExample);
		
		List<Long> menuIds = new ArrayList<Long>();
		for(PmsRoleMenuDO pmsRoleMenuDO : pmsRoleMenuDOList){
			menuIds.add(pmsRoleMenuDO.getMenuid());
		}
		if(CollectionUtils.isEmpty(menuIds)){
			return null;
		}
		PmsMenuDOExample example = new PmsMenuDOExample();
		example.createCriteria().andIdIn(menuIds);
		example.setOrderByClause("number asc");
		return pmsMenuDOMapper.selectByExample(example);
	}

	@Override
	public List<PmsMenuDO> listByParent(String parentId) {
		PmsMenuDOExample example = new PmsMenuDOExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(parentId)){
			criteria.andParentidEqualTo(Long.parseLong(parentId));
		}
		return pmsMenuDOMapper.selectByExample(example);
	}

	@Override
	public List<PmsMenuDO> listByParentId(Long parentId) {
		PmsMenuDOExample example = new PmsMenuDOExample();
		example.createCriteria().andParentidEqualTo(parentId);
		return pmsMenuDOMapper.selectByExample(example);
	}

	@Override
	public List<PmsMenuDO> getMenuByNameAndIsLeaf(Short isLeaf,String name) {
		PmsMenuDOExample example = new PmsMenuDOExample();
		example.createCriteria().andIsleafEqualTo(isLeaf).andNameEqualTo(name);
		return pmsMenuDOMapper.selectByExample(example);
	}

	@Override
	public String getTreeMenu(String actionUrl) {
		List<PmsMenuDO> treeData = getTreeData(null);
		StringBuffer strJson = new StringBuffer();
		recursionTreeMenu("0", strJson, treeData, actionUrl);
		return strJson.toString();
	}
	
	/**
	 * 根据父菜单ID获取该菜单下的所有子孙菜单.<br/>
	 * 
	 * @param parentId
	 *            (如果为空，则为获取所有的菜单).<br/>
	 * @return menuList.
	 */
	private List<PmsMenuDO> getTreeData(String parentId) {
		return listByParent(parentId);
	}
	
	/**
	 * 递归输出树形菜单
	 * 
	 * @param pId
	 * @param buffer
	 */
	private void recursionTreeMenu(String pId, StringBuffer buffer, List<PmsMenuDO> list, String url) {
		if (pId.equals("0")) {
			buffer.append("<ul class=\"tree treeFolder collapse \" >");
		} else {
			buffer.append("<ul>");
		}
		List<PmsMenuDO> listMap = getSonMenuListByPid(pId, list);
		for (PmsMenuDO pmsMenuDO : listMap) {
			String id = pmsMenuDO.getId().toString();// id
			String name = pmsMenuDO.getName().toString();// 名称
			String isLeaf = pmsMenuDO.getIsleaf().toString();// 是否叶子科目
			buffer.append("<li><a onclick=\"onClickMenuNode(" + id + ")\"  " +
					"href='" + ThreadLocalContext.getHttpRequest().getContextPath() +"/"+ url + "?id=" + id + "' target=\"ajax\" rel=\"jbsxBox\"  value=" + id + ">" + name + "</a>");
			if (!isLeaf.equals("1")) {
				recursionTreeMenu(id, buffer, list, url);
			}
			buffer.append("</li>");
		}
		buffer.append("</ul>");
	}
	
	/**
	 * 根据(pId)获取(menuList)中的所有子菜单集合.
	 * 
	 * @param pId
	 *            父菜单ID.
	 * @param menuList
	 *            菜单集合.
	 * @return sonMenuList.
	 */
	private List<PmsMenuDO> getSonMenuListByPid(String pId, List<PmsMenuDO> menuList) {
		List<PmsMenuDO> sonMenuList = new ArrayList<PmsMenuDO>();
		for (PmsMenuDO menu : menuList) {
			String parentId = menu.getParentid().toString();// 父id
			if (parentId.equals(pId)) {
				sonMenuList.add(menu);
			}
		}
		return sonMenuList;
	}

	@Override
	public int delete(Long id) {
		return pmsMenuDOMapper.deleteByPrimaryKey(id);
	}
	
	

	@Override
	public String buildPermissionTree(String roleIds) throws PermissionException {
		List<PmsMenuDO> treeData = null;
		try {
			treeData = listByRoleIds(roleIds);
			if (CollectionUtils.isEmpty(treeData)) {
				log.error("用户没有分配菜单权限");
				throw new PermissionException(PermissionException.PERMISSION_USER_NOT_MENU); // 该用户没有分配菜单权限
			}
		} catch (Exception e) {
			log.error("根据角色查询菜单出现错误", e);
			throw new PermissionException(PermissionException.PERMISSION_QUERY_MENU_BY_ROLE_ERROR); // 查询当前角色的
		}
		StringBuffer strJson = new StringBuffer();
		buildAdminPermissionTree("0", strJson, treeData);
		return strJson.toString();
	}
	
	/**
	 * 递归-构建管理后台的树形权限功能菜单
	 * 
	 * @param pId 父菜单ID
	 * @param treeBuf
	 * @param menuList
	 */
	private void buildAdminPermissionTree(String pId, StringBuffer treeBuf, List<PmsMenuDO> menuList) {
		//获取所以父ID的子菜单
		List<PmsMenuDO> listMap = getSonMenuListByPid(pId.toString(), menuList);
		
		for (PmsMenuDO pmsMenuDO : listMap) {
			String id = pmsMenuDO.getId().toString();// id
			String name = pmsMenuDO.getName().toString();// 名称
			String isLeaf = pmsMenuDO.getIsleaf().toString();// 是否叶子
			String level = pmsMenuDO.getLevel().toString();// 菜单层级（1、2、3、4）
			String url = pmsMenuDO.getUrl(); // ACTION访问地址
			String navTabId = "";
			if (null != pmsMenuDO.getTargetname() ) {
				navTabId = pmsMenuDO.getTargetname(); // 用于刷新查询页面
			}
			
			if ("1".equals(level)){
				treeBuf.append("<div class='accordionHeader'>");
				treeBuf.append("<h2>" + name + "</h2>");
				treeBuf.append("</div>");
				treeBuf.append("<div class='accordionContent'>");
			}
			//是叶子节点
			if ("1".equals(isLeaf)) {
				treeBuf.append("<li><a href='" + ThreadLocalContext.getHttpRequest().getContextPath() + "/" + url + "' target='navTab' rel='" + navTabId + "'>" + name + "</a></li>");
			} else {
				
				if ("1".equals(level)){
					treeBuf.append("<ul class='tree treeFolder'>");
				}else{
					treeBuf.append("<li><a>" + name + "</a>");
					treeBuf.append("<ul>");
				}
				
				buildAdminPermissionTree(id, treeBuf, menuList);
				
				if ("1".equals(level)){
					treeBuf.append("</ul>");
				}else{
					treeBuf.append("</ul></li>");
				}
			}
			
			if ("1".equals(level)){
				treeBuf.append("</div>");
			}
		}

	}

	@Override
	public void createMenu(PmsMenuDO model) throws PermissionException {
		try {
			PmsMenuDO newPmsMenu = model;
			PmsMenuDO parentPmsMenu =newPmsMenu.getParent();
			if (null == parentPmsMenu.getId()) {
				//parentPmsMenu = new PmsMenuDO();
				newPmsMenu.setIsleaf((short)1);//是叶子节点
				newPmsMenu.setLevel((short) 1);
				newPmsMenu.setParentid(0L);
				newPmsMenu.setCreatetime(new Date());
			} else {
				//如果有父节点
				parentPmsMenu = this.pmsMenuDOMapper.selectByPrimaryKey(parentPmsMenu.getId());
				newPmsMenu.setIsleaf((short)1);//是叶子节点
				newPmsMenu.setLevel((short) (parentPmsMenu.getLevel() + (short)1));
				parentPmsMenu.setIsleaf((short)0);
				newPmsMenu.setParentid(parentPmsMenu.getId());
				newPmsMenu.setCreatetime(new Date());
				//同时修改父节点不为子节点
				pmsMenuDOMapper.updateByPrimaryKeySelective(parentPmsMenu);
			}
			pmsMenuDOMapper.insertSelective(newPmsMenu);
			//同时插入角色菜单表中管理员的菜单权限
			PmsRoleMenuDO pmsRoleMenuDO = new PmsRoleMenuDO();
			pmsRoleMenuDO.setRoleid(PermissionConstant.ADMIN_ROLE_ID);//超级管理员角色ID
			pmsRoleMenuDO.setMenuid(newPmsMenu.getId());
			pmsRoleMenuDOMapper.insert(pmsRoleMenuDO);
			
		} catch (Exception e) {
			log.error("添加菜单报错", e);
			throw new PermissionException("添加菜单SQL报错");
		}
	}

	@Override
	public String getMenuIdsByRoleId(Long roleId) throws PermissionException {
		List<PmsRoleMenuDO> menuList = pmsRoleMenuService.listByRoleId(roleId);
		StringBuffer menuIds = new StringBuffer("");
		if (menuList != null && !menuList.isEmpty()) {
			for (PmsRoleMenuDO rm : menuList) {
				menuIds.append(rm.getMenuid()).append(",");
			}
		}
		return menuIds.toString();
	}

	@Override
	public String buildMenuActionTree(String menuIdsStr, String actionIdsStr) {
		List<PmsMenuDO> allMenuList = getTreeData(null); // 获取所有的菜单
		StringBuffer treeBuf = new StringBuffer();
		buildPermissionTree("0", treeBuf, allMenuList, menuIdsStr, actionIdsStr);
		return treeBuf.toString();
	}
	
	/**
	 * 创建分配权限的菜单树
	 * @param pId
	 * @param treeBuf
	 * @param allMenuList
	 * @param menuIds
	 * @param actionIds
	 */
	private void buildPermissionTree(String pId, StringBuffer treeBuf, List<PmsMenuDO> allMenuList, String menuIds, String actionIds) {
		if (pId.equals("0")) {
			treeBuf.append("<ul class=\"tree treeFolder treeCheck expand\" >");
		} else {
			treeBuf.append("<ul>");
		}

		List<PmsMenuDO> sonMenuList = getSonMenuListByPid(pId.toString(), allMenuList);
		for (PmsMenuDO sonMenu : sonMenuList) {
			String menuId = sonMenu.getId().toString();// id
			String parentId = sonMenu.getParentid().toString(); // PID
			String name = sonMenu.getName().toString();// 名称
			String isLeaf = sonMenu.getIsleaf().toString();// 是否叶子
			if (menuIds.indexOf("," + menuId + ",") > -1) {
				treeBuf.append("<li><a menuid='" + menuId + "' checked='true' pid='" + parentId + "' isleaf='" + isLeaf + "'>" + name + " (M)</a>");
			} else {
				treeBuf.append("<li><a menuid='" + menuId + "' pid='" + parentId + "' isleaf='" + isLeaf + "'>" + name + " (M)</a>");
			}
			if (isLeaf.equals("1")) {
				// 如果叶子菜单，则处理挂在此菜单下的权限功能点

				// 获取叶子菜单下所有的功能权限
				List<PmsActionDO> actionList = pmsActionService.listAllByMenuId(Long.valueOf(menuId));
				if (null != actionList && !actionList.isEmpty()) {
					treeBuf.append("<ul>");
					for (int j = 0; j < actionList.size(); j++) {
						PmsActionDO action = actionList.get(j);
						if (actionIds.indexOf("," + action.getId().toString() + ",") > -1) {
							treeBuf.append("<li><a checked='true' actionid='" + action.getId() + "'>" + action.getActionname() + " (A)</a>");
						} else {
							treeBuf.append("<li><a actionid='" + action.getId() + "'>" + action.getActionname() + " (A)</a>");
						}
					}
					treeBuf.append("</ul>");
				}

			} else {
				// 不是叶子菜单，递归
				buildPermissionTree(menuId, treeBuf, allMenuList, menuIds, actionIds);
			}
			treeBuf.append("</li>");
		}

		treeBuf.append("</ul>");
	}

	@Override
	public void assignPermission(Long roleId, String menuIds, String actionIds) throws PermissionException {
		if (null == roleId) {
			throw new PermissionException(PermissionException.PERMISSION_ASSIGN_MENU_ROLE_NULL); // 角色ID为空
		}
		
		// 先删除所有的菜单权限
		pmsRoleMenuService.deleteByRoleId(roleId);

		List<String> oldMenuIdList = new ArrayList<String>();
		// 删除功能权限
		pmsRoleActionService.deleteByRoleId(roleId);
		if (StringUtils.isNotBlank(menuIds)) {
			String[] menuArray = menuIds.split(",");
			for (String menuId : menuArray) {
				if(!oldMenuIdList.contains(menuId)){
					// 防止重复添加菜单权限
					PmsRoleMenuDO entity = new PmsRoleMenuDO();
					entity.setRoleid(roleId);
					entity.setMenuid(Long.valueOf(menuId));
					// 新增菜单权限
					pmsRoleMenuDOMapper.insert(entity);
				}
				oldMenuIdList.add(menuId);
			}
		}		
		
		if (StringUtils.isNotBlank(actionIds)) {
			String[] actionArray = actionIds.split(",");
			for (String actionId : actionArray) {
				PmsRoleActionDO entity = new PmsRoleActionDO();
				entity.setRoleid(roleId);
				entity.setActionid(Long.valueOf(actionId));
				// 新增功能权限
				pmsRoleActionDOMapper.insert(entity);
			}
		}
		
	}

	@Override
	public String buildLookUpMenu() {
		List<PmsMenuDO> treeData = getTreeData(null);
		StringBuffer strJson = new StringBuffer();
		recursionTreeMenuLookUp("0", strJson, treeData);
		return strJson.toString();
	}
	
	private void recursionTreeMenuLookUp(String pId, StringBuffer buffer, List<PmsMenuDO> list) {
		if ("0".equals(pId)) {
			buffer.append("<ul class=\"tree treeFolder\" >");
		} else {
			buffer.append("<ul>");
		}
		List<PmsMenuDO> listMap = getSonMenuListByPid(pId, list);
		for (PmsMenuDO pmsMenuDO : listMap) {
			String id = pmsMenuDO.getId().toString();// id
			String parentId = pmsMenuDO.getParentid().toString();// 父id
			String name = pmsMenuDO.getName().toString();// 名称
			String isLeaf = pmsMenuDO.getIsleaf().toString();// 是否叶子科目

			if (isLeaf.equals("1")) {
				buffer.append("<li><a onclick=\"$.bringBack({id:'" + id + "', name:'" + name + "'})\"  href=\"javascript:\"  >" + name + "</a>");
			} else {
				buffer.append("<li><a id='" + id + "' pid='" + parentId + "' isleaf='" + isLeaf + "'>" + name + "</a>");
			}

			if (!isLeaf.equals("1")) {
				recursionTreeMenuLookUp(id, buffer, list);
			}
			buffer.append("</li>");
		}
		buffer.append("</ul>");
	}

	@Override
	public PmsMenuDO getById(Long id) {
		PmsMenuDO pmsMenuDO = pmsMenuDOMapper.selectByPrimaryKey(id);
		PmsMenuDO parent = pmsMenuDOMapper.selectByPrimaryKey(pmsMenuDO.getParentid());
		pmsMenuDO.setParent(parent);
		return pmsMenuDO;
	}

	@Override
	public int update(PmsMenuDO menu) {
		return pmsMenuDOMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
	public int countMenuByRoleId(Long roleId) {
		List<PmsRoleMenuDO> meunList = pmsRoleMenuService.listByRoleId(roleId);
		if (meunList == null || meunList.isEmpty()) {
			return 0;
		} else {
			return meunList.size();
		}
	}

	@Override
	public String buildOperatorPermissionMenu(long operatorId) throws PermissionException {
		List<PmsMenuDO> treeData = new ArrayList<PmsMenuDO>();
		if(GlobalConstant.ADMON_OPERATOR_ID == operatorId){
			//超级管理员，获取所有菜单
			PmsMenuDOExample example = new PmsMenuDOExample();
			treeData = pmsMenuDOMapper.selectByExample(example);
		}else{
			treeData = pmsMenuDOMapper.listMenuByOperatorId(operatorId);
		}
		if (CollectionUtils.isEmpty(treeData)) {
			log.error("用户没有分配菜单权限");
			throw new PermissionException(PermissionException.PERMISSION_USER_NOT_MENU); // 该用户没有分配菜单权限
		}
		StringBuffer strJson = new StringBuffer();
		buildAdminPermissionTree("0", strJson, treeData);
		return strJson.toString();
	}


}
