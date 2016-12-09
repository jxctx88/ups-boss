package cn.memedai.ups.boss.service.permission;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.memedai.ups.boss.dal.model.PmsOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorDOExample;
import cn.memedai.ups.boss.dal.model.PmsRoleOperatorDO;
import cn.memedai.ups.boss.service.page.PageParam;


/**
 * 
 * @descript: 角色-操作员关联表数据访问层接口.
 * @author: chengtx
 * @创建时间: 2016-11-27,上午10:58:15
 *
 */
public interface PmsRoleOperatorService {

	/**
	 * 根据操作员ID查找该操作员关联的角色.
	 * 
	 * @param operatorId
	 *            .
	 * @return list .
	 */
	List<PmsRoleOperatorDO> listByOperatorId(long operatorId);

	/**
	 * 根据角色ID查找该操作员关联的操作员.
	 * 
	 * @param roleId
	 * @return
	 */
	List<PmsRoleOperatorDO> listByRoleId(long roleId);

	/**
	 * 根据操作员ID删除与角色的关联记录.
	 * 
	 * @param operatorId
	 *            .
	 */
	void deleteByOperatorId(long operatorId);

	/**
	 * 根据角色ID删除操作员与角色的关联关系.
	 * 
	 * @param roleId
	 *            .
	 */
	void deleteByRoleId(long roleId);

	/**
	 * 根据角色ID和操作员ID删除关联数据(用于更新操作员的角色).
	 * @param roleId 角色ID.
	 * @param operatorId 操作员ID.
	 */
	void deleteByRoleIdAndOperatorId(long roleId, long operatorId);


}
