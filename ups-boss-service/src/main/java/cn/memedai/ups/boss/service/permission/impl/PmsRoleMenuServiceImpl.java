package cn.memedai.ups.boss.service.permission.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.dal.dao.pms.PmsRoleMenuDOMapper;
import cn.memedai.ups.boss.dal.model.pms.PmsRoleMenuDO;
import cn.memedai.ups.boss.dal.model.pms.PmsRoleMenuDOExample;
import cn.memedai.ups.boss.service.permission.PmsRoleMenuService;

@Service("pmsRoleMenuService")
public class PmsRoleMenuServiceImpl implements PmsRoleMenuService {

	@Autowired
	PmsRoleMenuDOMapper pmsRoleMenuDOMapper;
	
	@Override
	public void deleteByRoleId(Long roleId) {
		PmsRoleMenuDOExample example = new PmsRoleMenuDOExample();
		example.createCriteria().andRoleidEqualTo(roleId);
		pmsRoleMenuDOMapper.deleteByExample(example);
	}

	@Override
	public List<PmsRoleMenuDO> listByRoleId(Long roleId) {
		PmsRoleMenuDOExample example = new PmsRoleMenuDOExample();
		example.createCriteria().andRoleidEqualTo(roleId);
		return pmsRoleMenuDOMapper.selectByExample(example);
	}


}
