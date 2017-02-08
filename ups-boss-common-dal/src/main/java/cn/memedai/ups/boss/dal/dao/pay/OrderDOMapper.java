package cn.memedai.ups.boss.dal.dao.pay;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.memedai.ups.boss.dal.model.pay.OrderDO;
import cn.memedai.ups.boss.dal.model.pay.OrderDOExample;

public interface OrderDOMapper {
    int countByExample(OrderDOExample example);

    int deleteByExample(OrderDOExample example);

    int deleteByPrimaryKey(Long orderId);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    List<OrderDO> selectByExample(OrderDOExample example);

    OrderDO selectByPrimaryKey(Long orderId);

    int updateByExampleSelective(@Param("record") OrderDO record, @Param("example") OrderDOExample example);

    int updateByExample(@Param("record") OrderDO record, @Param("example") OrderDOExample example);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}