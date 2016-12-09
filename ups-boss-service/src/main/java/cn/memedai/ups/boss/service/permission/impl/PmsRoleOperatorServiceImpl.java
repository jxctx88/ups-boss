package cn.memedai.ups.boss.service.permission.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.dal.dao.PmsRoleOperatorDOMapper;
import cn.memedai.ups.boss.dal.model.PmsRoleOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsRoleOperatorDOExample;
import cn.memedai.ups.boss.service.permission.PmsRoleOperatorService;
@Service("pmsRoleOperatorService")
public class PmsRoleOperatorServiceImpl implements PmsRoleOperatorService {

	@Autowired
	PmsRoleOperatorDOMapper pmsRoleOperatorDOMapper;
	
	@Override
	public List<PmsRoleOperatorDO> listByOperatorId(long operatorId) {
		PmsRoleOperatorDOExample example = new PmsRoleOperatorDOExample();
		example.createCriteria().andOperatoridEqualTo(operatorId);
		
		return pmsRoleOperatorDOMapper.selectByExample(example);
	}

	@Override
	public List<PmsRoleOperatorDO> listByRoleId(long roleId) {
		PmsRoleOperatorDOExample example = new PmsRoleOperatorDOExample();
		example.createCriteria().andRoleidEqualTo(roleId);
		
		return pmsRoleOperatorDOMapper.selectByExample(example);
	}

	@Override
	public void deleteByOperatorId(long operatorId) {
		PmsRoleOperatorDOExample example = new PmsRoleOperatorDOExample();
		example.createCriteria().andOperatoridEqualTo(operatorId);
		
		pmsRoleOperatorDOMapper.deleteByExample(example);
	}

	@Override
	public void deleteByRoleId(long roleId) {
		PmsRoleOperatorDOExample example = new PmsRoleOperatorDOExample();
		example.createCriteria().andRoleidEqualTo(roleId);
		
		pmsRoleOperatorDOMapper.deleteByExample(example);
	}

	@Override
	public void deleteByRoleIdAndOperatorId(long roleId, long operatorId) {
		PmsRoleOperatorDOExample example = new PmsRoleOperatorDOExample();
		example.createCriteria().andRoleidEqualTo(roleId).
		andOperatoridEqualTo(operatorId);
		
		pmsRoleOperatorDOMapper.deleteByExample(example);
	}

	

}
