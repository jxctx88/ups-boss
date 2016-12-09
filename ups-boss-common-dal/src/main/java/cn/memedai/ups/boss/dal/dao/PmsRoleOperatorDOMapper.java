package cn.memedai.ups.boss.dal.dao;

import cn.memedai.ups.boss.dal.model.PmsRoleOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsRoleOperatorDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsRoleOperatorDOMapper {
    int countByExample(PmsRoleOperatorDOExample example);

    int deleteByExample(PmsRoleOperatorDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsRoleOperatorDO record);

    int insertSelective(PmsRoleOperatorDO record);

    List<PmsRoleOperatorDO> selectByExample(PmsRoleOperatorDOExample example);

    PmsRoleOperatorDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsRoleOperatorDO record, @Param("example") PmsRoleOperatorDOExample example);

    int updateByExample(@Param("record") PmsRoleOperatorDO record, @Param("example") PmsRoleOperatorDOExample example);

    int updateByPrimaryKeySelective(PmsRoleOperatorDO record);

    int updateByPrimaryKey(PmsRoleOperatorDO record);
}