package cn.memedai.ups.boss.service.permission.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.dal.dao.PmsOperatorDOMapper;
import cn.memedai.ups.boss.dal.dao.PmsRoleOperatorDOMapper;
import cn.memedai.ups.boss.dal.model.PmsOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorDOExample;
import cn.memedai.ups.boss.dal.model.PmsRoleOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsRoleOperatorDOExample;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.permission.PmsOperatorService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("pmsOperatorService")
public class PmsOperatorServiceImpl implements PmsOperatorService {

	@Autowired
	PmsOperatorDOMapper pmsOperatorDOMapper;
	
	//角色-操作员服务
	@Autowired
	PmsRoleOperatorDOMapper pmsRoleOperatorDOMapper;
	
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
		
		example.createCriteria().andIdIn(operatorIds);
		
		return pmsOperatorDOMapper.selectByExample(example);
	}

	@Override
	public PageInfo<PmsOperatorDO> listPage(PageParam pageParam,PmsOperatorDOExample example) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getNumPerPage());
		List<PmsOperatorDO> list = pmsOperatorDOMapper.selectByExample(example);
		return new PageInfo<PmsOperatorDO>(list);
	}


}
