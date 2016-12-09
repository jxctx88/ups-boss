package cn.memedai.ups.boss.dal.dao;

import cn.memedai.ups.boss.dal.model.PmsOperatorLogDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorLogDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsOperatorLogDOMapper {
    int countByExample(PmsOperatorLogDOExample example);

    int deleteByExample(PmsOperatorLogDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsOperatorLogDO record);

    int insertSelective(PmsOperatorLogDO record);

    List<PmsOperatorLogDO> selectByExample(PmsOperatorLogDOExample example);

    PmsOperatorLogDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsOperatorLogDO record, @Param("example") PmsOperatorLogDOExample example);

    int updateByExample(@Param("record") PmsOperatorLogDO record, @Param("example") PmsOperatorLogDOExample example);

    int updateByPrimaryKeySelective(PmsOperatorLogDO record);

    int updateByPrimaryKey(PmsOperatorLogDO record);
}