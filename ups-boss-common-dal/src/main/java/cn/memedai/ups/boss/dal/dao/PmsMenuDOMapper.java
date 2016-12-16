package cn.memedai.ups.boss.dal.dao;

import cn.memedai.ups.boss.dal.model.PmsMenuDO;
import cn.memedai.ups.boss.dal.model.PmsMenuDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsMenuDOMapper {
    int countByExample(PmsMenuDOExample example);

    int deleteByExample(PmsMenuDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsMenuDO record);

    int insertSelective(PmsMenuDO record);

    List<PmsMenuDO> selectByExample(PmsMenuDOExample example);

    PmsMenuDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsMenuDO record, @Param("example") PmsMenuDOExample example);

    int updateByExample(@Param("record") PmsMenuDO record, @Param("example") PmsMenuDOExample example);

    int updateByPrimaryKeySelective(PmsMenuDO record);

    int updateByPrimaryKey(PmsMenuDO record);
    
	/**
	 * 根据操作员ID获取所有的菜单列表
	 * @param operatorId
	 * @return
	 */
	List<PmsMenuDO> listMenuByOperatorId(long operatorId);
}