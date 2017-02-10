package cn.memedai.ups.boss.service.pay;

import cn.memedai.ups.boss.dal.model.pay.OrderDO;
import cn.memedai.ups.boss.service.page.PageParam;

import com.github.pagehelper.PageInfo;

public interface OrderService {

	PageInfo<OrderDO> listPage(PageParam pageParam,OrderDO orderDO);
	
	OrderDO selectByPrimaryKey(Long orderId);
	
	int insertSelective(OrderDO record);
	
    int updateByPrimaryKeySelective(OrderDO record);


}
