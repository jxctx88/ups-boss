package cn.memedai.ups.boss.dal.dao;

import cn.memedai.ups.boss.dal.model.PmsRoleDO;
import cn.memedai.ups.boss.dal.model.PmsRoleDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsRoleDOMapper {
    int countByExample(PmsRoleDOExample example);

    int deleteByExample(PmsRoleDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsRoleDO record);

    int insertSelective(PmsRoleDO record);

    List<PmsRoleDO> selectByExample(PmsRoleDOExample example);

    PmsRoleDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsRoleDO record, @Param("example") PmsRoleDOExample example);

    int updateByExample(@Param("record") PmsRoleDO record, @Param("example") PmsRoleDOExample example);

    int updateByPrimaryKeySelective(PmsRoleDO record);

    int updateByPrimaryKey(PmsRoleDO record);
}