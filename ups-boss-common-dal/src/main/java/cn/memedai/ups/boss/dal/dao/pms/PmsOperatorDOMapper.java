package cn.memedai.ups.boss.dal.dao.pms;

import cn.memedai.ups.boss.dal.model.pms.PmsOperatorDO;
import cn.memedai.ups.boss.dal.model.pms.PmsOperatorDOExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsOperatorDOMapper {
    int countByExample(PmsOperatorDOExample example);

    int deleteByExample(PmsOperatorDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsOperatorDO record);

    int insertSelective(PmsOperatorDO record);

    List<PmsOperatorDO> selectByExample(PmsOperatorDOExample example);

    PmsOperatorDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsOperatorDO record, @Param("example") PmsOperatorDOExample example);

    int updateByExample(@Param("record") PmsOperatorDO record, @Param("example") PmsOperatorDOExample example);

    int updateByPrimaryKeySelective(PmsOperatorDO record);

    int updateByPrimaryKey(PmsOperatorDO record);
}