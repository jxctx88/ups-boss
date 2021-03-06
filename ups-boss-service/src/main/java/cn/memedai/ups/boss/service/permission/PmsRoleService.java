package cn.memedai.ups.boss.service.permission;

import java.util.List;

import cn.memedai.ups.boss.dal.model.pms.PmsRoleDO;
import cn.memedai.ups.boss.dal.model.pms.PmsRoleDOExample;
import cn.memedai.ups.boss.service.page.PageParam;

import com.github.pagehelper.PageInfo;


/**
 * 
 * @descript: 角色表数据访问层接口.
 * @author: chengtx
 * @创建时间: 2016-11-27,上午10:58:50
 *
 */
public interface PmsRoleService{

	/**
	 * 列出所有角色，以供添加操作员时选择.
	 * 
	 * @return roleList .
	 */
	List<PmsRoleDO> listAll();

	/**
	 * 根据角色名称获取角色记录（用于判断角色名是否已存在）.
	 * 
	 * @param roleName
	 *            角色名.
	 * @return PmsRole.
	 */
	PmsRoleDO getByRoleName(String roleName);

	/**
	 * 查找是否存在与ID值不相同与角色名相同的角色记录（用于判断修改的角色名与其他的角色名冲突）。
	 * 
	 * @param id
	 *            角色ID .
	 * @param roleName
	 *            角色名.
	 * @return PmsRole.
	 */
	PmsRoleDO findByRoleNameNotEqId(Long id, String roleName);

	/**
	 * 根据权限ID找出关联了此权限的角色.
	 * 
	 * @param actionId
	 *            .
	 * @return roleList.
	 */
	List<PmsRoleDO> listByActionId(Long actionId);

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param example 查询条件
	 * @return
	 */
	PageInfo<PmsRoleDO> listPage(PageParam pageParam,PmsRoleDOExample example);
	
	/**
	 * 根据角色ID删除角色 .
	 * @param id
	 * @return
	 */
	int deleteById(Long id);
	
	/**
	 * 根据ID获取角色.
	 * @param id
	 * @return
	 */
	PmsRoleDO getById(Long id);
	
	/**
	 * 保存角色
	 * @param pmsRole
	 * @return
	 */
	int create(PmsRoleDO pmsRole);
	
	/**
	 * 根据主键ID更新角色
	 * @param pmsRole
	 * @return
	 */
	int update(PmsRoleDO pmsRole);
	
	/**
	 * 根据角色ID删除角色，并删除与操作员、权限、菜单的关联关系.
	 * 
	 * @param roleId
	 *            .
	 */
	void deleteRoleById(Long roleId);
	
	/**
	 * 根据操作员ID获得该操作员的所有角色id所拼成的String，每个ID用“,”分隔
	 * @param operatorId 操作员ID
	 * @return roleIds
	 */
	String getRoleIdsByOperatorId(long operatorId);
}
