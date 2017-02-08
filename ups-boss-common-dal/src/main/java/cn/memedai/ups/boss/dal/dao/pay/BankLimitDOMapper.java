package cn.memedai.ups.boss.dal.dao.pay;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.memedai.ups.boss.dal.model.pay.BankLimitDO;
import cn.memedai.ups.boss.dal.model.pay.BankLimitDOExample;

public interface BankLimitDOMapper {
    int countByExample(BankLimitDOExample example);

    int deleteByExample(BankLimitDOExample example);

    int insert(BankLimitDO record);

    int insertSelective(BankLimitDO record);

    List<BankLimitDO> selectByExample(BankLimitDOExample example);

    int updateByExampleSelective(@Param("record") BankLimitDO record, @Param("example") BankLimitDOExample example);

    int updateByExample(@Param("record") BankLimitDO record, @Param("example") BankLimitDOExample example);
}