package cn.memedai.ups.boss.service.pay;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.memedai.ups.boss.dal.model.pay.BankLimitDO;
import cn.memedai.ups.boss.dal.model.pay.BankLimitDOExample;
import cn.memedai.ups.boss.service.page.PageParam;

import com.github.pagehelper.PageInfo;

public interface BankLimitService {
	int countByExample(BankLimitDOExample example);

    int deleteByExample(BankLimitDOExample example);

    int insert(BankLimitDO record);

    int insertSelective(BankLimitDO record);

    List<BankLimitDO> selectByExample(BankLimitDO bankLimitDO);
    
    BankLimitDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BankLimitDO record, @Param("example") BankLimitDOExample example);

    int updateByExample(@Param("record") BankLimitDO record, @Param("example") BankLimitDOExample example);

    PageInfo<BankLimitDO> listPage(PageParam pageParam,BankLimitDO bankLimitDO);
}
