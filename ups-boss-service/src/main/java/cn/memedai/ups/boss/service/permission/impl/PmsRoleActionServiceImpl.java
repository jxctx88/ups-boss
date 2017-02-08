package cn.memedai.ups.boss.service.permission.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.dal.dao.pms.PmsRoleActionDOMapper;
import cn.memedai.ups.boss.dal.model.pms.PmsRoleActionDO;
import cn.memedai.ups.boss.dal.model.pms.PmsRoleActionDOExample;
import cn.memedai.ups.boss.service.permission.PmsRoleActionService;

@Service("pmsRoleActionService")
public class PmsRoleActionServiceImpl implements PmsRoleActionService {

	@Autowired
	PmsRoleActionDOMapper pmsRoleActionDOMapper;
	
	@Override
	public void deleteByActionId(long actionId) {
		PmsRoleActionDOExample example = new PmsRoleActionDOExample();
		example.createCriteria().andActionidEqualTo(actionId);
		pmsRoleActionDOMapper.deleteByExample(example);
	}

	@Override
	public void deleteByRoleId(long roleId) {
		PmsRoleActionDOExample example = new PmsRoleActionDOExample();
		example.createCriteria().andRoleidEqualTo(roleId);
		pmsRoleActionDOMapper.deleteByExample(example);
	}

	@Override
	public List<PmsRoleActionDO> listByRoleId(long roleId) {
		PmsRoleActionDOExample example = new PmsRoleActionDOExample();
		example.createCriteria().andRoleidEqualTo(roleId);
		return pmsRoleActionDOMapper.selectByExample(example);
	}

	@Override
	public List<PmsRoleActionDO> listByRoleIds(String roleIdsStr) {
		List<String> roledIds = Arrays.asList(roleIdsStr.split(","));
		List<Long> roleIdList = new ArrayList<Long>();
		for(String id : roledIds){
			roleIdList.add(Long.parseLong(id));
		}
		PmsRoleActionDOExample example = new PmsRoleActionDOExample();
		example.createCriteria().andRoleidIn(roleIdList);
		return pmsRoleActionDOMapper.selectByExample(example);
	}

	@Override
	public List<PmsRoleActionDO> listByActionId(long actionId) {
		PmsRoleActionDOExample example = new PmsRoleActionDOExample();
		example.createCriteria().andActionidEqualTo(actionId);
		return pmsRoleActionDOMapper.selectByExample(example);
	}

}
