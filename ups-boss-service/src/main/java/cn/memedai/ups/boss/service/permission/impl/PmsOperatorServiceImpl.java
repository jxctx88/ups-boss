package cn.memedai.ups.boss.service.permission.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.dal.dao.PmsOperatorDOMapper;
import cn.memedai.ups.boss.dal.dao.PmsRoleOperatorDOMapper;
import cn.memedai.ups.boss.dal.model.PmsMenuDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorDOExample;
import cn.memedai.ups.boss.dal.model.PmsRoleOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsRoleOperatorDOExample;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.permission.PmsOperatorService;
import cn.memedai.ups.boss.service.permission.PmsRoleOperatorService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("pmsOperatorService")
public class PmsOperatorServiceImpl implements PmsOperatorService {

	@Autowired
	PmsOperatorDOMapper pmsOperatorDOMapper;
	
	//角色-操作员服务
	@Autowired
	PmsRoleOperatorDOMapper pmsRoleOperatorDOMapper;
	
	@Autowired
	PmsRoleOperatorService pmsRoleOperatorService;
	
	@Override
	public PmsOperatorDO findByLoginName(String loginName) {
		PmsOperatorDOExample example = new PmsOperatorDOExample();
		example.createCriteria().andLoginnameEqualTo(loginName);
		
		List<PmsOperatorDO>  pmsOperatorDOList = pmsOperatorDOMapper.selectByExample(example);
		
		return CollectionUtils.isNotEmpty(pmsOperatorDOList) ? pmsOperatorDOList.get(0) : null;
	}

	@Override
	public List<PmsOperatorDO> listByRoleId(long roleId) {
		PmsRoleOperatorDOExample roleOperatorDOExample = new PmsRoleOperatorDOExample();
		roleOperatorDOExample.createCriteria().andRoleidEqualTo(roleId);
		List<PmsRoleOperatorDO> pmsRoleOperatorDOList = pmsRoleOperatorDOMapper.selectByExample(roleOperatorDOExample);
		
		List<Long> operatorIds = new ArrayList<Long>();
		for(PmsRoleOperatorDO pmsRoleOperatorDO : pmsRoleOperatorDOList){
			operatorIds.add(pmsRoleOperatorDO.getOperatorid());
		}
		PmsOperatorDOExample example = new PmsOperatorDOExample();
		
		if(CollectionUtils.isEmpty(operatorIds)){
			return null;
		}
		example.createCriteria().andIdIn(operatorIds);
		
		return pmsOperatorDOMapper.selectByExample(example);
	}

	@Override
	public PageInfo<PmsOperatorDO> listPage(PageParam pageParam,PmsOperatorDOExample example) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getNumPerPage());
		List<PmsOperatorDO> list = pmsOperatorDOMapper.selectByExample(example);
		return new PageInfo<PmsOperatorDO>(list);
	}

	@Override
	public void deleteOperatorById(long operatorId) {
		PmsOperatorDO pmsOperator = pmsOperatorDOMapper.selectByPrimaryKey(operatorId);
		if (pmsOperator != null) {
			if ("1".equals(pmsOperator.getType())) {
				throw new RuntimeException("【" + pmsOperator.getLoginname() + "】为超级管理员，不能删除！");
			}
			pmsOperatorDOMapper.deleteByPrimaryKey(pmsOperator.getId());
			// 删除原来的角色与操作员关联
			pmsRoleOperatorService.deleteByOperatorId(operatorId);
		}
	}

	@Override
	public int update(PmsOperatorDO operator) {
		return pmsOperatorDOMapper.updateByPrimaryKeySelective(operator);
	}

	@Override
	public int updateOperatorPwd(Long operatorId, String newPwd, boolean isTrue) {
		PmsOperatorDO pmsOperator = pmsOperatorDOMapper.selectByPrimaryKey(operatorId);
		pmsOperator.setLoginpwd(newPwd);
		pmsOperator.setPwderrorcount((short)0); // 密码错误次数重置为0
		pmsOperator.setIschangedpwd(isTrue ? (short)1 : (short)0); // 设置密码为已修改过
		return pmsOperatorDOMapper.updateByPrimaryKeySelective(pmsOperator);
	}

	@Override
	public PmsOperatorDO getById(Long operatorId) {
		return pmsOperatorDOMapper.selectByPrimaryKey(operatorId);
	}

	@Override
	public int create(PmsOperatorDO pmsOperator) {
		return pmsOperatorDOMapper.insertSelective(pmsOperator);
	}

	@Override
	public void saveOperator(PmsOperatorDO pmsOperator, String roleOperatorStr) {
		// 保存操作员信息
		pmsOperatorDOMapper.insertSelective(pmsOperator);
		// 保存角色关联信息
		if (StringUtils.isNotBlank(roleOperatorStr) && roleOperatorStr.length() > 0) {
			saveOrUpdateRoleOperator(pmsOperator.getId(), roleOperatorStr);
		}
	}
	
	/**
	 * 保存用户和角色之间的关联关系
	 */
	private void saveOrUpdateRoleOperator(long operatorId, String roleIdsStr) {
		// 删除原来的角色与操作员关联
		List<PmsRoleOperatorDO> listPmsRoleOperators = pmsRoleOperatorService.listByOperatorId(operatorId);
		Map<Long, PmsRoleOperatorDO> delMap = new HashMap<Long, PmsRoleOperatorDO>();
		for (PmsRoleOperatorDO pmsRoleOperator : listPmsRoleOperators) {
			delMap.put(pmsRoleOperator.getRoleid(), pmsRoleOperator);
		}
		if (StringUtils.isNotBlank(roleIdsStr)) {
			// 创建新的关联
			String[] roleIds = roleIdsStr.split(",");
			for (int i = 0; i < roleIds.length; i++) {
				long roleId = Long.parseLong(roleIds[i]);
				if (delMap.get(roleId) == null) {
					PmsRoleOperatorDO pmsRoleOperator = new PmsRoleOperatorDO();
					pmsRoleOperator.setOperatorid(operatorId);
					pmsRoleOperator.setRoleid(roleId);
					pmsRoleOperatorDOMapper.insert(pmsRoleOperator);
				} else {
					delMap.remove(roleId);
				}
			}
		}

		Iterator<Long> iterator = delMap.keySet().iterator();
		while (iterator.hasNext()) {
			long roleId = iterator.next();
			pmsRoleOperatorService.deleteByRoleIdAndOperatorId(roleId, operatorId);
		}
	}

	@Override
	public void updateOperator(PmsOperatorDO pmsOperator, String roleOperatorStr) {
		pmsOperatorDOMapper.updateByPrimaryKeySelective(pmsOperator);
		// 更新角色信息
		saveOrUpdateRoleOperator(pmsOperator.getId(), roleOperatorStr);
	}

	@Override
	public int countOperatorByRoleId(Long roleId) {
		List<PmsRoleOperatorDO> operatorList = pmsRoleOperatorService.listByRoleId(roleId);
		if (operatorList == null || operatorList.isEmpty()) {
			return 0;
		} else {
			return operatorList.size();
		}
	}

	@Override
	public List<PmsRoleOperatorDO> listRoleOperatorByOperatorId(long operatorId) {
		return pmsRoleOperatorService.listByOperatorId(operatorId);
	}



}
