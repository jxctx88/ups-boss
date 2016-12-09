package cn.memedai.ups.boss.service.permission.biz;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.dal.dao.PmsActionDOMapper;
import cn.memedai.ups.boss.dal.dao.PmsMenuDOMapper;
import cn.memedai.ups.boss.dal.model.PmsActionDO;
import cn.memedai.ups.boss.dal.model.PmsActionDOExample;
import cn.memedai.ups.boss.dal.model.PmsMenuDO;
import cn.memedai.ups.boss.dal.model.PmsRoleActionDO;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.permission.PmsActionService;
import cn.memedai.ups.boss.service.permission.PmsRoleActionService;

import com.github.pagehelper.PageInfo;



/**
 * 
 * @描述: 权限表--服务层接口.
 * @作者: WuShuicheng .
 * @创建时间: 2013-7-25,下午10:40:40 .
 * @版本: 1.0 .
 */
@Service("pmsActionBiz")
public class PmsActionBiz {
	
	@Autowired
	private PmsActionService pmsActionService;
	@Autowired
	private PmsActionDOMapper pmsActionDOMapper;
	
	@Autowired
	PmsMenuDOMapper pmsMenuDOMapper;

	@Autowired
	private PmsRoleActionService pmsRoleActionService;


	/**
	 * 根据Action的id字符串得到相应的权限列表
	 */
	public List<PmsActionDO> findActionsByIdStr(String ids) {
		return pmsActionService.findByIds(ids);
	}

	/**
	 * 根据ID删除权限信息.
	 */
	public void deleteById(Long id) {
		pmsActionDOMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据权限名称查找权限（用于判断权限名是否已存在）.
	 * 
	 * @param actionName
	 *            .
	 * @return PmsAction.
	 */
	public PmsActionDO getByActionName(String actionName) {
		return pmsActionService.getByActionName(actionName);
	}

	/**
	 * 根据权限查找权限记录（用于判断权限是否已存在）.
	 * 
	 * @param action
	 *            .
	 * @return PmsAction.
	 */
	public PmsActionDO getByAction(String action) {
		return pmsActionService.getByAction(action);
	}

	/**
	 * 检查修改后的权限名是否会与其他权限名冲突.
	 * 
	 * @param actionName
	 * @param id
	 * @return PmsAction.
	 */
	public PmsActionDO getByActionNameNotEqId(String actionName, Long id) {
		return pmsActionService.getByActionNameNotEqId(actionName, id);
	}

	/**
	 * 检查修改后的权限是否会与其他权限冲突.
	 * 
	 * @param action
	 * @param id
	 * @return PmsAction.
	 */
	public PmsActionDO getByActionNotEqId(String action, Long id) {
		return pmsActionService.getByActionNotEqId(action, id);
	}

	/**
	 * 根据菜单ID查找权限集.
	 * 
	 * @param menuId
	 *            菜单ID.
	 * @return .
	 */
	public List<PmsActionDO> listByMenuId(Long menuId) {
		return pmsActionService.listByMenuId(menuId);
	}

	/**
	 * 查询并分页列出权限功能点.
	 * @param pageParam 分页参数
	 * @param example 查询条件
	 * @return
	 */
	public PageInfo<PmsActionDO> listPage(PageParam pageParam, PmsActionDOExample example) {
		return pmsActionService.listPage(pageParam, example);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public PmsActionDO getById(Long id) {
		PmsActionDO pmsActionDO =  pmsActionDOMapper.selectByPrimaryKey(id);
		PmsMenuDO pmsMenuDO = pmsMenuDOMapper.selectByPrimaryKey(pmsActionDO.getMenuid());
		pmsActionDO.setMenu(pmsMenuDO);
		return pmsActionDO;
	}

	/**
	 * 保存权限功能点
	 * @param act
	 */
	public void saveAction(PmsActionDO pmsActionDO) {
		pmsActionDOMapper.insertSelective(pmsActionDO);
		
	}

	/**
	 * 更新权限功能点.
	 * @param pmsAction
	 */
	public void updateAction(PmsActionDO pmsActionDO) {
		pmsActionDOMapper.updateByPrimaryKeySelective(pmsActionDO);
	}
	
	/**
	 * 根据权限ID删除权限并解除权限与角色的关联关系.
	 * 
	 * @param actionId
	 *            .
	 */
	public void deleteActionById(Long actionId) {
		pmsActionDOMapper.deleteByPrimaryKey(actionId);
		// 删除权限和角色关联表中的关联关系
		pmsRoleActionService.deleteByActionId(actionId);
	}
	
	/**
	 * 根据角色ID统计有多少权限关联到此角色.
	 * 
	 * @param roleId
	 *            角色ID.
	 * @return count.
	 */
	public int countActionByRoleId(Long roleId) {
		List<PmsRoleActionDO> actionList = pmsRoleActionService.listByRoleId(roleId);
		if (CollectionUtils.isEmpty(actionList)) {
			return 0;
		} else {
			return actionList.size();
		}
	}
	
	/**
	 * 根据角色ID，获取所有的功能权限ID集
	 * 
	 * @param roleId
	 * @return actionIds
	 */
	public String getActionIdsByRoleId(Long roleId) {
		List<PmsRoleActionDO> rmList = pmsRoleActionService.listByRoleId(roleId);
		StringBuffer actionIds = new StringBuffer();
		if (rmList != null && !rmList.isEmpty()) {
			for (PmsRoleActionDO rm : rmList) {
				actionIds.append(rm.getActionid()).append(",");
			}
		}
		return actionIds.toString();
	}
	
	/**
	 * 根据角色ID集得到所有权限ID集
	 * @param roleIds
	 * @return actionIds
	 */
	public String getActionIdsByRoleIds(String roleIds) {
		// 得到角色－权限表中roleiId在ids中的所有关联对象
		List<PmsRoleActionDO> listPmsRoleActions = pmsRoleActionService.listByRoleIds(roleIds);
		// 构建StringBuffer
		StringBuffer actionIdsBuf = new StringBuffer("");
		// 拼接字符串
		for (PmsRoleActionDO pmsRoleAction : listPmsRoleActions) {
			actionIdsBuf.append(pmsRoleAction.getActionid()).append(",");
		}
		String actionIds = actionIdsBuf.toString();
		// 截取字符串
		if (StringUtils.isNotBlank(actionIds) && actionIds.length() > 0) {
			actionIds = actionIds.substring(0, actionIds.length() - 1); // 去掉最后一个逗号
		}
		return actionIds;
	}

}
