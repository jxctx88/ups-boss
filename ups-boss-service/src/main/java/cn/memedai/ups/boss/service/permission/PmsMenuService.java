package cn.memedai.ups.boss.service.permission;

import java.util.List;

import cn.memedai.ups.boss.dal.model.PmsMenuDO;

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

}