package cn.memedai.ups.boss.service.permission.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.dal.dao.PmsRoleActionDOMapper;
import cn.memedai.ups.boss.dal.dao.PmsRoleDOMapper;
import cn.memedai.ups.boss.dal.model.PmsRoleActionDO;
import cn.memedai.ups.boss.dal.model.PmsRoleActionDOExample;
import cn.memedai.ups.boss.dal.model.PmsRoleDO;
import cn.memedai.ups.boss.dal.model.PmsRoleDOExample;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.permission.PmsRoleService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("pmsRoleService")
public class PmsRoleServiceImpl implements PmsRoleService {

	@Autowired
	PmsRoleDOMapper pmsRoleDOMapper;
	
	//角色-权限服务
	@Autowired
	PmsRoleActionDOMapper pmsRoleActionDOMapper;
	
	@Override
	public List<PmsRoleDO> listAll() {
		PmsRoleDOExample example = new PmsRoleDOExample();
		example.createCriteria();
		
		return pmsRoleDOMapper.selectByExample(example);
	}

	@Override
	public PmsRoleDO getByRoleName(String roleName) {
		PmsRoleDOExample example = new PmsRoleDOExample();
		example.createCriteria().andRolenameEqualTo(roleName);
		List<PmsRoleDO> pmsRoleDOList = pmsRoleDOMapper.selectByExample(example);
		return CollectionUtils.isNotEmpty(pmsRoleDOList) ? pmsRoleDOList.get(0) : null;
	}

	@Override
	public PmsRoleDO findByRoleNameNotEqId(Long id, String roleName) {
		PmsRoleDOExample example = new PmsRoleDOExample();
		example.createCriteria().andRolenameEqualTo(roleName).
		andIdNotEqualTo(id);
		List<PmsRoleDO> pmsRoleDOList = pmsRoleDOMapper.selectByExample(example);
		
		return CollectionUtils.isNotEmpty(pmsRoleDOList) ? pmsRoleDOList.get(0) : null;
	}

	@Override
	public List<PmsRoleDO> listByActionId(Long actionId) {
		PmsRoleActionDOExample roleActionDOExample = new PmsRoleActionDOExample();
		roleActionDOExample.createCriteria().andActionidEqualTo(actionId);
		List<PmsRoleActionDO> pmsRoleActionDOList = pmsRoleActionDOMapper.selectByExample(roleActionDOExample);
		
		List<Long> roleIdList = new ArrayList<Long>();
		for(PmsRoleActionDO pmsRoleActionDO : pmsRoleActionDOList){
			roleIdList.add(pmsRoleActionDO.getRoleid());
		}
		
		if(roleIdList.isEmpty()){
			return null;
		}
		
		PmsRoleDOExample example = new PmsRoleDOExample();
		example.createCriteria().andIdIn(roleIdList);
		return pmsRoleDOMapper.selectByExample(example);
	}

	@Override
	public PageInfo<PmsRoleDO> listPage(PageParam pageParam,
			PmsRoleDOExample example) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getNumPerPage());
		List<PmsRoleDO> list = pmsRoleDOMapper.selectByExample(example);
		return new PageInfo<PmsRoleDO>(list);
	}

}
