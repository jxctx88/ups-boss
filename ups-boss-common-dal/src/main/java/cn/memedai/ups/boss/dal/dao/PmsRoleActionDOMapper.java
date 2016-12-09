package cn.memedai.ups.boss.dal.dao;

import cn.memedai.ups.boss.dal.model.PmsRoleActionDO;
import cn.memedai.ups.boss.dal.model.PmsRoleActionDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsRoleActionDOMapper {
    int countByExample(PmsRoleActionDOExample example);

    int deleteByExample(PmsRoleActionDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsRoleActionDO record);

    int insertSelective(PmsRoleActionDO record);

    List<PmsRoleActionDO> selectByExample(PmsRoleActionDOExample example);

    PmsRoleActionDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsRoleActionDO record, @Param("example") PmsRoleActionDOExample example);

    int updateByExample(@Param("record") PmsRoleActionDO record, @Param("example") PmsRoleActionDOExample example);

    int updateByPrimaryKeySelective(PmsRoleActionDO record);

    int updateByPrimaryKey(PmsRoleActionDO record);
}