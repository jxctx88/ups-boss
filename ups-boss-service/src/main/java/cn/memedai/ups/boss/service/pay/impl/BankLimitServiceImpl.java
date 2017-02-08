package cn.memedai.ups.boss.service.pay.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.dal.dao.pay.BankLimitDOMapper;
import cn.memedai.ups.boss.dal.model.pay.BankLimitDO;
import cn.memedai.ups.boss.dal.model.pay.BankLimitDOExample;
import cn.memedai.ups.boss.dal.model.pay.BankLimitDOExample.Criteria;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.pay.BankLimitService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("bankLimitService")
public class BankLimitServiceImpl implements BankLimitService {

	@Autowired
	BankLimitDOMapper bankLimitDOMapper;
	
	@Override
	public int countByExample(BankLimitDOExample example) {
		return bankLimitDOMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(BankLimitDOExample example) {
		return bankLimitDOMapper.deleteByExample(example);
	}

	@Override
	public int insert(BankLimitDO record) {
		return bankLimitDOMapper.insert(record);
	}

	@Override
	public int insertSelective(BankLimitDO record) {
		return bankLimitDOMapper.insertSelective(record);
	}

	@Override
	public List<BankLimitDO> selectByExample(BankLimitDO bankLimitDO) {
		BankLimitDOExample example = new BankLimitDOExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(bankLimitDO.getBankId())){
			criteria.andBankIdEqualTo(bankLimitDO.getBankId());
		}
		if(StringUtils.isNotBlank(bankLimitDO.getBankName())){
			criteria.andBankNameEqualTo(bankLimitDO.getBankName());
		}
		if(StringUtils.isNotBlank(bankLimitDO.getChannelId())){
			criteria.andChannelIdEqualTo(bankLimitDO.getChannelId());
		}
		if(StringUtils.isNotBlank(bankLimitDO.getPayType())){
			criteria.andPayTypeEqualTo(bankLimitDO.getPayType());
		}
		if(StringUtils.isNotBlank(bankLimitDO.getStatus())){
			criteria.andStatusEqualTo(bankLimitDO.getStatus());
		}
		if(null != bankLimitDO.getDateLimit()){
			criteria.andDateLimitBetween(0L, bankLimitDO.getDateLimit());
		}
		if(null != bankLimitDO.getSingleLimit()){
			criteria.andSingleLimitBetween(0L, bankLimitDO.getSingleLimit());
		}
		example.setOrderByClause("id asc");
		return bankLimitDOMapper.selectByExample(example);
	}

	@Override
	public int updateByExampleSelective(BankLimitDO record,
			BankLimitDOExample example) {
		return bankLimitDOMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(BankLimitDO record, BankLimitDOExample example) {
		return bankLimitDOMapper.updateByExample(record, example);
	}

	@Override
	public PageInfo<BankLimitDO> listPage(PageParam pageParam,BankLimitDO bankLimitDO) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getNumPerPage());
		List<BankLimitDO> list = selectByExample(bankLimitDO);
		return new PageInfo<BankLimitDO>(list);
	}

	@Override
	public BankLimitDO selectByPrimaryKey(Long id) {
		BankLimitDOExample example = new BankLimitDOExample();
		example.createCriteria().andIdEqualTo(id);
		List<BankLimitDO> bankLimitDOList = bankLimitDOMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(bankLimitDOList)){
			return bankLimitDOList.get(0);
		}
		return null;
	}

}
