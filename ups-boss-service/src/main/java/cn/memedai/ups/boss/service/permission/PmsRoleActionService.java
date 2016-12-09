package cn.memedai.ups.boss.service.permission;

import java.util.List;

import cn.memedai.ups.boss.dal.model.PmsRoleActionDO;


/**
 * 
 * @descript: 角色-权限点关联表数据访问层接口.
 * @author: chengtx
 * @创建时间: 2016-11-27,上午10:57:20
 *
 */
public interface PmsRoleActionService{


	/**
	 * 根据角色ID找到角色关联的权限点.
	 * 
	 * @param roleId
	 *            .
	 * @return roleActionList .
	 */
	List<PmsRoleActionDO> listByRoleId(long roleId);
	
	/**
	 * 根据角色ID字符串获取相应角色-权限关联信息.
	 * 
	 * @param roleIds
	 * @return
	 */
	List<PmsRoleActionDO> listByRoleIds(String roleIds);

	/**
	 * 根据权限ID查找权限所关联的角色.
	 * 
	 * @param actionId
	 *            .
	 * @return roleActionList.
	 */
	List<PmsRoleActionDO> listByActionId(long actionId);

	/**
	 * 根据权限ID删除权限与角色的关联记录.
	 * 
	 * @param actionId
	 *            .
	 */
	void deleteByActionId(long actionId);

	/**
	 * 根据角色ID删除角色与权限的关联记录.
	 * 
	 * @param roleId
	 *            .
	 */
	void deleteByRoleId(long roleId);

}
