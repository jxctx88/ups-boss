package cn.memedai.ups.boss.dal.dao;

import cn.memedai.ups.boss.dal.model.PmsRoleMenuDO;
import cn.memedai.ups.boss.dal.model.PmsRoleMenuDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsRoleMenuDOMapper {
    int countByExample(PmsRoleMenuDOExample example);

    int deleteByExample(PmsRoleMenuDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsRoleMenuDO record);

    int insertSelective(PmsRoleMenuDO record);

    List<PmsRoleMenuDO> selectByExample(PmsRoleMenuDOExample example);

    PmsRoleMenuDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsRoleMenuDO record, @Param("example") PmsRoleMenuDOExample example);

    int updateByExample(@Param("record") PmsRoleMenuDO record, @Param("example") PmsRoleMenuDOExample example);

    int updateByPrimaryKeySelective(PmsRoleMenuDO record);

    int updateByPrimaryKey(PmsRoleMenuDO record);
}