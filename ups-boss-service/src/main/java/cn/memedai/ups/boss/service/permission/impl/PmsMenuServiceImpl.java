package cn.memedai.ups.boss.service.permission.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.dal.dao.PmsMenuDOMapper;
import cn.memedai.ups.boss.dal.dao.PmsRoleMenuDOMapper;
import cn.memedai.ups.boss.dal.model.PmsMenuDO;
import cn.memedai.ups.boss.dal.model.PmsMenuDOExample;
import cn.memedai.ups.boss.dal.model.PmsMenuDOExample.Criteria;
import cn.memedai.ups.boss.dal.model.PmsRoleMenuDO;
import cn.memedai.ups.boss.dal.model.PmsRoleMenuDOExample;
import cn.memedai.ups.boss.service.permission.PmsMenuService;
@Service("pmsMenuService")
public class PmsMenuServiceImpl implements PmsMenuService {

	@Autowired
	PmsMenuDOMapper pmsMenuDOMapper;
	
	//角色菜单关联服务
	@Autowired
	PmsRoleMenuDOMapper pmsRoleMenuDOMapper;
	
	@Override
	public List<PmsMenuDO> listByRoleIds(String roleIdsStr) {
		List<String> roldIds = Arrays.asList(roleIdsStr.split(","));
		List<Long> roleIDList = new ArrayList<Long>();
		for(String id : roldIds){
			roleIDList.add(Long.parseLong(id));
		}
		PmsRoleMenuDOExample roleMenuDOExample = new PmsRoleMenuDOExample();
		roleMenuDOExample.createCriteria().andRoleidIn(roleIDList);
		List<PmsRoleMenuDO> pmsRoleMenuDOList = pmsRoleMenuDOMapper.selectByExample(roleMenuDOExample);
		
		List<Long> menuIds = new ArrayList<Long>();
		for(PmsRoleMenuDO pmsRoleMenuDO : pmsRoleMenuDOList){
			menuIds.add(pmsRoleMenuDO.getMenuid());
		}
		PmsMenuDOExample example = new PmsMenuDOExample();
		example.createCriteria().andIdIn(menuIds);
		example.setOrderByClause("number asc");
		return pmsMenuDOMapper.selectByExample(example);
	}

	@Override
	public List<PmsMenuDO> listByParent(String parentId) {
		PmsMenuDOExample example = new PmsMenuDOExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(parentId)){
			criteria.andParentidEqualTo(Long.parseLong(parentId));
		}
		return pmsMenuDOMapper.selectByExample(example);
	}

	@Override
	public List<PmsMenuDO> listByParentId(Long parentId) {
		PmsMenuDOExample example = new PmsMenuDOExample();
		example.createCriteria().andParentidEqualTo(parentId);
		return pmsMenuDOMapper.selectByExample(example);
	}

	@Override
	public List<PmsMenuDO> getMenuByNameAndIsLeaf(Short isLeaf,String name) {
		PmsMenuDOExample example = new PmsMenuDOExample();
		example.createCriteria().andIsleafEqualTo(isLeaf).andNameEqualTo(name);
		return pmsMenuDOMapper.selectByExample(example);
	}


}
