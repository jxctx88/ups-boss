package cn.memedai.ups.boss.service.permission;

import java.util.List;

import cn.memedai.ups.boss.dal.model.pms.PmsMenuDO;
import cn.memedai.ups.boss.service.permission.exception.PermissionException;

/**
 * 
 * @descript:
 * @author: chengtx
 * @创建时间: 2016-11-27,上午10:55:28
 *
 */
public interface PmsMenuService {
	/**
	 * 根据角色id查找菜单列表
	 * @param roleIdsStr
	 * @return
	 * @throws Exception
	 */
	public List<PmsMenuDO> listByRoleIds(String roleIdsStr);

	/**
	 * 根据父菜单ID获取该菜单下的所有子孙菜单.<br/>
	 * 
	 * @param parentId
	 *            (如果为空，则为获取所有的菜单).<br/>
	 * @return menuList.
	 */
	public List<PmsMenuDO> listByParent(String parentId);

	/**
	 * 根据菜单ID查找菜单（可用于判断菜单下是否还有子菜单）.
	 * 
	 * @param parentId
	 *            .
	 * @return menuList.
	 */
	public List<PmsMenuDO> listByParentId(Long parentId);

	/***
	 * 根据名称和是否叶子节点查询数据
	 * 
	 * @param isLeaf
	 *            是否是叶子节点
	 * @param name
	 *            节点名称
	 * @return
	 */
	public List<PmsMenuDO> getMenuByNameAndIsLeaf(Short isLeaf,String name);

	/**
	 * 获取用于编制菜单时的树.
	 * @param actionUrl
	 * @return
	 */
	String getTreeMenu(String actionUrl);
	
	/**
	 * 根据主键ID删除菜单
	 * @param id
	 * @return
	 */
	int delete(Long id); 
	
	/**
	 * 根据操作员拥有的角色ID,构建管理后台的树形权限功能菜单
	 * @param roleIds
	 * @return
	 * @throws PermissionException
	 */
	String buildPermissionTree(String roleIds) throws PermissionException;
	
	void createMenu(PmsMenuDO model) throws PermissionException;
	/**
	 * 根据角色ID，获取菜单ID集合(逗号分隔的菜单ID字符串)
	 * 
	 * @param roleId
	 * @return menuIdStr
	 * @throws Exception
	 */
	String getMenuIdsByRoleId(Long roleId) throws PermissionException;

	/**
	 * 根据已有的菜单ID集合、角色的功能点集合，生成菜单权限树
	 * 
	 * @param menuIdsStr
	 * @param actionIdsStr
	 */
	String buildMenuActionTree(String menuIdsStr, String actionIdsStr);

	/**
	 * 为角色分配权限 <br/>
	 * 
	 * @param roleId
	 *            角色ID.<br/>
	 * @param menuIds
	 *            菜单ID集.<br/>
	 * @param actionIds
	 *            权限菜单集.<br/>
	 */
	void assignPermission(Long roleId, String menuIds, String actionIds) throws PermissionException;
	
	
	String buildLookUpMenu();
	
	/**
	 * 根据菜单ID获取菜单和父菜单.
	 * @param id 菜单ID
	 * @return
	 */
	PmsMenuDO getById(Long id);
	
	/**
	 * 根据主键更新菜单.
	 * @param menu
	 */
	int update(PmsMenuDO menu);
	
	/**
	 * 根据角色ID统计关联到此角色的菜单数.
	 * 
	 * @param roleId
	 *            角色ID.
	 * @return count.
	 */
	int countMenuByRoleId(Long roleId);
	
	/**
	 * 根据操作员id构建权限菜单
	 * @param operatorId
	 * @return
	 * @throws PermissionException
	 */
	String buildOperatorPermissionMenu(long operatorId) throws PermissionException;
}