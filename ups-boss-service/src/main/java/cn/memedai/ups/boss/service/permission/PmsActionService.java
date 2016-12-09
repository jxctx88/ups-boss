package cn.memedai.ups.boss.service.permission;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.memedai.ups.boss.dal.model.PmsActionDO;
import cn.memedai.ups.boss.dal.model.PmsActionDOExample;
import cn.memedai.ups.boss.service.page.PageParam;

/**
 * 
 * @descript: 权限点表数据访问层接口.
 * @author: chengtx
 * @创建时间: 2016-11-27,上午10:55:13
 *
 */
public interface PmsActionService{

	/**
	 * 根据实体ID集字符串获取获取对象列表.
	 * 
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
	 * @param example 查询条件
	 * @return
	 */
	PageInfo<PmsActionDO> listPage(PageParam pageParam,PmsActionDOExample example);
	
}
