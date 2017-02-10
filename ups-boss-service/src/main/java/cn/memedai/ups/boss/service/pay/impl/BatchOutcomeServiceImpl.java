package cn.memedai.ups.boss.service.pay.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.dal.dao.pay.BatchOutcomeDOMapper;
import cn.memedai.ups.boss.dal.model.pay.BatchOutcomeDO;
import cn.memedai.ups.boss.dal.model.pay.BatchOutcomeDOExample;
import cn.memedai.ups.boss.dal.model.pay.BatchOutcomeDOExample.Criteria;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.pay.BatchOutcomeService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("batchOutcomeService")
public class BatchOutcomeServiceImpl implements BatchOutcomeService {

	@Autowired
	BatchOutcomeDOMapper batchOutcomeDOMapper;
	
	@Override
	public PageInfo<BatchOutcomeDO> listPage(PageParam pageParam,BatchOutcomeDO batchOutcomeDO) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getNumPerPage());
		BatchOutcomeDOExample example = new BatchOutcomeDOExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(batchOutcomeDO.getBatchId())){
			criteria.andBatchIdEqualTo(batchOutcomeDO.getBatchId());
		}
		if(StringUtils.isNotBlank(batchOutcomeDO.getChannelId())){
			criteria.andChannelIdEqualTo(batchOutcomeDO.getChannelId());
		}
		if(StringUtils.isNotBlank(batchOutcomeDO.getRespFilename())){
			criteria.andRespFilenameEqualTo(batchOutcomeDO.getRespFilename());
		}
		if(StringUtils.isNotBlank(batchOutcomeDO.getSendFilename())){
			criteria.andSendFilenameEqualTo(batchOutcomeDO.getSendFilename());
		}
		if(StringUtils.isNotBlank(batchOutcomeDO.getStatus())){
			criteria.andStatusEqualTo(batchOutcomeDO.getStatus());
		}
		example.setOrderByClause("id desc");
		List<BatchOutcomeDO> list = batchOutcomeDOMapper.selectByExample(example);
		
		return new PageInfo<BatchOutcomeDO>(list);
	}

	@Override
	public int updateByPrimaryKeySelective(BatchOutcomeDO record) {
		return batchOutcomeDOMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public BatchOutcomeDO selectByPrimaryKey(Long id) {
		return batchOutcomeDOMapper.selectByPrimaryKey(id);
	}

}
