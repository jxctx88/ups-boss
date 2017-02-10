package cn.memedai.ups.boss.dal.dao.pay;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.memedai.ups.boss.dal.model.pay.BatchOutcomeDO;
import cn.memedai.ups.boss.dal.model.pay.BatchOutcomeDOExample;

public interface BatchOutcomeDOMapper {
    int countByExample(BatchOutcomeDOExample example);

    int deleteByExample(BatchOutcomeDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BatchOutcomeDO record);

    int insertSelective(BatchOutcomeDO record);

    List<BatchOutcomeDO> selectByExample(BatchOutcomeDOExample example);

    BatchOutcomeDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BatchOutcomeDO record, @Param("example") BatchOutcomeDOExample example);

    int updateByExample(@Param("record") BatchOutcomeDO record, @Param("example") BatchOutcomeDOExample example);

    int updateByPrimaryKeySelective(BatchOutcomeDO record);

    int updateByPrimaryKey(BatchOutcomeDO record);
}