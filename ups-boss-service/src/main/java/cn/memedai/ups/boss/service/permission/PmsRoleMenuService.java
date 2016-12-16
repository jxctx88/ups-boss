package cn.memedai.ups.boss.service.permission;

import java.util.List;

import cn.memedai.ups.boss.dal.model.PmsRoleMenuDO;


/**
 * 
 * @descript:
 * @author: chengtx
 * @创建时间: 2016-11-27,上午10:57:58
 *
 */
public interface PmsRoleMenuService{

	/**
	 * 根据角色ID删除菜单与角色的关联记录.
	 * 
	 * @param roleId
	 */
	void deleteByRoleId(Long roleId);

	/**
	 * 根据角色ID统计关联到此角色的菜单数.
	 * 
	 * @param roleId
	 *            角色ID.
	 * @return count.
	 */
	List<PmsRoleMenuDO> listByRoleId(Long roleId);
	
}