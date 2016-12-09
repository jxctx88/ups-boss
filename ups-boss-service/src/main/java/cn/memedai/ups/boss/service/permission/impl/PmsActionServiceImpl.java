package cn.memedai.ups.boss.service.permission.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.memedai.ups.boss.dal.dao.PmsActionDOMapper;
import cn.memedai.ups.boss.dal.dao.PmsMenuDOMapper;
import cn.memedai.ups.boss.dal.model.PmsActionDO;
import cn.memedai.ups.boss.dal.model.PmsActionDOExample;
import cn.memedai.ups.boss.dal.model.PmsMenuDO;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.permission.PmsActionService;

@Service("pmsActionService")
public class PmsActionServiceImpl implements PmsActionService {

	@Autowired
	PmsActionDOMapper pmsActionDOMapper;
	
	@Autowired
	PmsMenuDOMapper pmsMenuDOMapper;
	
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

}
