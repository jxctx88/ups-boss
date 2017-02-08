package cn.memedai.ups.boss.service.permission;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.memedai.ups.boss.dal.model.pms.PmsActionDO;
import cn.memedai.ups.boss.dal.model.pms.PmsActionDOExample;
import cn.memedai.ups.boss.service.page.PageParam;

/**
 * 
 * @descript: 权限表数据访问层接口.
 * @author: chengtx
 * @创建时间: 2016-11-27,上午10:55:13
 *
 */
public interface PmsActionService{

	/**
	 * 根据实体ID集字符串获取获取对象列表.<br/>
	 * 根据Action的id字符串得到相应的权限列表
	 * @param ids
	 * @return
	 */
	List<PmsActionDO> findByIds(String ids);

	/**
	 * 根据权限名称查找权限（用于判断权限名是否已存在）.
	 * 
	 * @param actionName
	 *            .
	 * @return PmsAction.
	 */
	PmsActionDO getByActionName(String actionName);

	/**
	 * 根据权限查找权限记录（用于判断权限是否已存在）.
	 * 
	 * @param action
	 *            .
	 * @return PmsAction.
	 */
	PmsActionDO getByAction(String action);

	/**
	 * 检查修改后的权限名是否会与其他权限名冲突.
	 * 
	 * @param actionName
	 * @param id
	 * @return PmsAction.
	 */
	PmsActionDO getByActionNameNotEqId(String actionName, Long id);

	/**
	 * 检查修改后的权限是否会与其他权限冲突.
	 * 
	 * @param action
	 * @param id
	 * @return PmsAction.
	 */
	PmsActionDO getByActionNotEqId(String action, Long id);

	/**
	 * 根据菜单ID找出该菜单下的所有权限.
	 * 
	 * @param menuId
	 *            .
	 * @return
	 */
	List<PmsActionDO> listAllByMenuId(Long menuId);

	/**
	 * 根据菜单ID查找权限集.
	 * 
	 * @param menuId
	 *            菜单ID.
	 * @return .
	 */
	List<PmsActionDO> listByMenuId(Long menuId);

	/**
	 * 分页查询
	 * @param pageParam
	 * @param pmsActionDO 查询条件
	 * @return
	 */
	PageInfo<PmsActionDO> listPage(PageParam pageParam,PmsActionDO pmsActionDO);

	/**
	 * 根据ID删除权限信息
	 * @param id
	 */
	int deleteById(Long id);
	
	/**
	 * 通过ID查询权限，并查询出关联的菜单信息
	 * @param id
	 * @return
	 */
	PmsActionDO getActionAndMenuById(Long id);
	
	/**
	 * 保存权限功能点
	 * @param pmsActionDO
	 * @return
	 */
	int saveAction(PmsActionDO pmsActionDO);
	
	/**
	 * 根据主键ID更新权限功能
	 * @param pmsActionDO
	 * @return
	 */
	int updateAction(PmsActionDO pmsActionDO);
	
	/**
	 * 根据权限ID删除权限并解除权限与角色的关联关系.
	 * @param actionId
	 */
	void deleteActionAndRoleById(Long actionId);
	
	/**
	 * 根据角色ID统计有多少权限关联到此角色.
	 * 
	 * @param roleId 
	 * 			角色ID.
	 * @return count
	 */
	int countActionByRoleId(Long roleId);
	
	/**
	 * 根据角色ID，获取所有的功能权限ID集
	 * 
	 * @param roleId
	 * @return actionIds
	 */
	String getActionIdsByRoleId(Long roleId);
	
	/**
	 * 根据角色ID集得到所有权限ID集
	 * @param roleIds
	 * @return actionIds
	 */
	String getActionIdsByRoleIds(String roleIds);
}
