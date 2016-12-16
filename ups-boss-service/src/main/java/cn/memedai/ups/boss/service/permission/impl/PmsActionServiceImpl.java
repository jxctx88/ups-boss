package cn.memedai.ups.boss.service.permission.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.memedai.ups.boss.dal.dao.PmsActionDOMapper;
import cn.memedai.ups.boss.dal.dao.PmsMenuDOMapper;
import cn.memedai.ups.boss.dal.model.PmsActionDO;
import cn.memedai.ups.boss.dal.model.PmsActionDOExample;
import cn.memedai.ups.boss.dal.model.PmsMenuDO;
import cn.memedai.ups.boss.dal.model.PmsRoleActionDO;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.permission.PmsActionService;
import cn.memedai.ups.boss.service.permission.PmsRoleActionService;

@Service("pmsActionService")
public class PmsActionServiceImpl implements PmsActionService {

	@Autowired
	PmsActionDOMapper pmsActionDOMapper;
	
	@Autowired
	PmsMenuDOMapper pmsMenuDOMapper;
	
	@Autowired
	PmsRoleActionService pmsRoleActionService;
	
	@Override
	public List<PmsActionDO> findByIds(String idStr) {
		List<String> ids = Arrays.asList(idStr.split(","));
		List<Long> idList = new ArrayList<Long>();
		for(String id : ids){
			idList.add(Long.parseLong(id));
		}
		PmsActionDOExample example = new PmsActionDOExample();
		example.createCriteria().andIdIn(idList);
		return pmsActionDOMapper.selectByExample(example);
	}

	@Override
	public PmsActionDO getByActionName(String actionName) {
		PmsActionDOExample example = new PmsActionDOExample();
		example.createCriteria().andActionnameEqualTo(actionName);
		List<PmsActionDO> pmsActionDOList = pmsActionDOMapper.selectByExample(example);
		return CollectionUtils.isNotEmpty(pmsActionDOList) ? pmsActionDOList.get(0) : null;
	}

	@Override
	public PmsActionDO getByAction(String action) {
		PmsActionDOExample example = new PmsActionDOExample();
		example.createCriteria().andActionEqualTo(action);
		List<PmsActionDO> pmsActionDOList = pmsActionDOMapper.selectByExample(example);
		return CollectionUtils.isNotEmpty(pmsActionDOList) ? pmsActionDOList.get(0) : null;
	}

	@Override
	public PmsActionDO getByActionNameNotEqId(String actionName, Long id) {
		PmsActionDOExample example = new PmsActionDOExample();
		example.createCriteria().andActionnameEqualTo(actionName).andIdNotEqualTo(id);
		List<PmsActionDO> pmsActionDOList = pmsActionDOMapper.selectByExample(example);
		return CollectionUtils.isNotEmpty(pmsActionDOList) ? pmsActionDOList.get(0) : null;
	}

	@Override
	public PmsActionDO getByActionNotEqId(String action, Long id) {
		PmsActionDOExample example = new PmsActionDOExample();
		example.createCriteria().andActionEqualTo(action).andIdNotEqualTo(id);
		List<PmsActionDO> pmsActionDOList = pmsActionDOMapper.selectByExample(example);
		return CollectionUtils.isNotEmpty(pmsActionDOList) ? pmsActionDOList.get(0) : null;
	}

	@Override
	public List<PmsActionDO> listAllByMenuId(Long menuId) {
		PmsActionDOExample example = new PmsActionDOExample();
		example.createCriteria().andMenuidEqualTo(menuId);
		List<PmsActionDO> pmsActionDOList = pmsActionDOMapper.selectByExample(example);
		return pmsActionDOList;
	}

	@Override
	public List<PmsActionDO> listByMenuId(Long menuId) {
		PmsActionDOExample example = new PmsActionDOExample();
		example.createCriteria().andMenuidEqualTo(menuId);
		return pmsActionDOMapper.selectByExample(example);
	}

	@Override
	public PageInfo<PmsActionDO> listPage(PageParam pageParam,PmsActionDOExample example) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getNumPerPage());
		List<PmsActionDO> list = pmsActionDOMapper.selectByExample(example);
		//根据menuid查询菜单表(menu)
		for(PmsActionDO pmsActionDO : list){
			PmsMenuDO pmsMenuDO = pmsMenuDOMapper.selectByPrimaryKey(pmsActionDO.getMenuid());
			pmsActionDO.setMenu(pmsMenuDO);
		}
		return new PageInfo<PmsActionDO>(list);
	}

	@Override
	public int deleteById(Long id) {
		return pmsActionDOMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PmsActionDO getActionAndMenuById(Long id) {
		PmsActionDO pmsActionDO =  pmsActionDOMapper.selectByPrimaryKey(id);
		PmsMenuDO pmsMenuDO = pmsMenuDOMapper.selectByPrimaryKey(pmsActionDO.getMenuid());
		pmsActionDO.setMenu(pmsMenuDO);
		return pmsActionDO;
	}
	
	@Override
	public int saveAction(PmsActionDO pmsActionDO){
		return pmsActionDOMapper.insertSelective(pmsActionDO);
	}

	@Override
	public int updateAction(PmsActionDO pmsActionDO) {
		return pmsActionDOMapper.updateByPrimaryKeySelective(pmsActionDO);
	}

	@Override
	public void deleteActionAndRoleById(Long actionId) {
		pmsActionDOMapper.deleteByPrimaryKey(actionId);
		// 删除权限和角色关联表中的关联关系
		pmsRoleActionService.deleteByActionId(actionId);
	}

	@Override
	public int countActionByRoleId(Long roleId) {
		List<PmsRoleActionDO> actionList = pmsRoleActionService.listByRoleId(roleId);
		if (CollectionUtils.isEmpty(actionList)) {
			return 0;
		} else {
			return actionList.size();
		}
	}

	@Override
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

	@Override
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
