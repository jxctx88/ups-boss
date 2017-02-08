package cn.memedai.ups.boss.dal.dao.pms;

import cn.memedai.ups.boss.dal.model.pms.PmsActionDO;
import cn.memedai.ups.boss.dal.model.pms.PmsActionDOExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsActionDOMapper {
    int countByExample(PmsActionDOExample example);

    int deleteByExample(PmsActionDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsActionDO record);

    int insertSelective(PmsActionDO record);

    List<PmsActionDO> selectByExample(PmsActionDOExample example);

    PmsActionDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsActionDO record, @Param("example") PmsActionDOExample example);

    int updateByExample(@Param("record") PmsActionDO record, @Param("example") PmsActionDOExample example);

    int updateByPrimaryKeySelective(PmsActionDO record);

    int updateByPrimaryKey(PmsActionDO record);
    
    /**
     * 根据条件查询，包含菜单名称 
     * @param pmsActionDO
     * @return
     */
    List<PmsActionDO> listByExample(PmsActionDO pmsActionDO);
}

