package cn.memedai.ups.boss.service.permission.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.dal.dao.pms.PmsRoleActionDOMapper;
import cn.memedai.ups.boss.dal.dao.pms.PmsRoleDOMapper;
import cn.memedai.ups.boss.dal.model.pms.PmsRoleActionDO;
import cn.memedai.ups.boss.dal.model.pms.PmsRoleActionDOExample;
import cn.memedai.ups.boss.dal.model.pms.PmsRoleDO;
import cn.memedai.ups.boss.dal.model.pms.PmsRoleDOExample;
import cn.memedai.ups.boss.dal.model.pms.PmsRoleOperatorDO;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.permission.PmsRoleActionService;
import cn.memedai.ups.boss.service.permission.PmsRoleMenuService;
import cn.memedai.ups.boss.service.permission.PmsRoleOperatorService;
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
	
	@Autowired
	PmsRoleActionService pmsRoleActionService;
	
	@Autowired
	PmsRoleMenuService pmsRoleMenuService;
	
	@Autowired
	PmsRoleOperatorService pmsRoleOperatorService;
	
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

	@Override
	public int deleteById(Long id) {
		return pmsRoleDOMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PmsRoleDO getById(Long id) {
		return pmsRoleDOMapper.selectByPrimaryKey(id);
	}

	@Override
	public int create(PmsRoleDO pmsRole) {
		return pmsRoleDOMapper.insertSelective(pmsRole);
	}

	@Override
	public int update(PmsRoleDO pmsRole) {
		return pmsRoleDOMapper.updateByPrimaryKeySelective(pmsRole);
	}

	@Override
	public void deleteRoleById(Long roleId) {
		// 删除角色权限关联表中的数据
		pmsRoleActionService.deleteByRoleId(roleId);
		// 删除角色菜单关联表中的数据
		pmsRoleMenuService.deleteByRoleId(roleId);
		// 删除角色操作员关联表中的数据
		pmsRoleOperatorService.deleteByRoleId(roleId);
		// 最后删除角色信息
		deleteById(roleId);
		
	}

	@Override
	public String getRoleIdsByOperatorId(long operatorId) {
		// 得到操作员和角色列表
		List<PmsRoleOperatorDO> rpList = pmsRoleOperatorService.listByOperatorId(operatorId);
		// 构建StringBuffer来拼字符串
		StringBuffer roleIdsBuf = new StringBuffer("");
		for (PmsRoleOperatorDO rp : rpList) {
			roleIdsBuf.append(rp.getRoleid()).append(",");
		}
		String roleIds = roleIdsBuf.toString();
		// 截取字符串
		if (StringUtils.isNotBlank(roleIds) && roleIds.length() > 0) {
			roleIds = roleIds.substring(0, roleIds.length() - 1);
		}
		return roleIds;
	}

}
