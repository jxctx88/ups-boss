package cn.memedai.ups.boss.service.pay;

import cn.memedai.ups.boss.dal.model.pay.BatchOutcomeDO;
import cn.memedai.ups.boss.service.page.PageParam;

import com.github.pagehelper.PageInfo;

public interface BatchOutcomeService {
	
	PageInfo<BatchOutcomeDO> listPage(PageParam pageParam,BatchOutcomeDO batchOutcomeDO);
	
    int updateByPrimaryKeySelective(BatchOutcomeDO record);
	
	BatchOutcomeDO selectByPrimaryKey(Long id);

}
