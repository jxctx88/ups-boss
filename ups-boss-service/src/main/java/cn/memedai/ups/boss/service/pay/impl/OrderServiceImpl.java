package cn.memedai.ups.boss.service.pay.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.dal.dao.pay.OrderDOMapper;
import cn.memedai.ups.boss.dal.model.pay.OrderDO;
import cn.memedai.ups.boss.dal.model.pay.OrderDOExample;
import cn.memedai.ups.boss.dal.model.pay.OrderDOExample.Criteria;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.pay.OrderService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDOMapper orderDOMapper;
	
	@Override
	public PageInfo<OrderDO> listPage(PageParam pageParam, OrderDO orderDO) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getNumPerPage());
		OrderDOExample example = new OrderDOExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(orderDO.getBankAccount())){
			criteria.andBankAccountEqualTo(orderDO.getBankAccount());
		}
		if(StringUtils.isNotBlank(orderDO.getBankAccountName())){
			criteria.andBankAccountNameEqualTo(orderDO.getBankAccountName());
		}
		if(StringUtils.isNotBlank(orderDO.getBankCode())){
			criteria.andBankCodeEqualTo(orderDO.getBankCode());
		}
		if(StringUtils.isNotBlank(orderDO.getBankName())){
			criteria.andBankNameEqualTo(orderDO.getBankName());
		}
		if(StringUtils.isNotBlank(orderDO.getIdCard())){
			criteria.andIdCardEqualTo(orderDO.getIdCard());
		}
		if(StringUtils.isNotBlank(orderDO.getIsAgreeShortcut())){
			criteria.andIsAgreeShortcutEqualTo(orderDO.getIsAgreeShortcut());
		}
		if(StringUtils.isNotBlank(orderDO.getMemberId())){
			criteria.andMemberIdEqualTo(orderDO.getMemberId());
		}
		if(StringUtils.isNotBlank(orderDO.getMerchantCode())){
			criteria.andMerchantCodeEqualTo(orderDO.getMerchantCode());
		}
		if(StringUtils.isNotBlank(orderDO.getMerchantTradeCode())){
			criteria.andMerchantTradeCodeEqualTo(orderDO.getMerchantTradeCode());
		}
		if(StringUtils.isNotBlank(orderDO.getMerchantTradeCode())){
			criteria.andMerchantTradeCodeEqualTo(orderDO.getMerchantTradeCode());
		}
		if(StringUtils.isNotBlank(orderDO.getMobilePhone())){
			criteria.andMobilePhoneEqualTo(orderDO.getMobilePhone());
		}
		if(StringUtils.isNotBlank(orderDO.getPayGateway())){
			criteria.andPayGatewayEqualTo(orderDO.getPayGateway());
		}
		if(StringUtils.isNotBlank(orderDO.getPayType())){
			criteria.andPayTypeEqualTo(orderDO.getPayType());
		}
		if(StringUtils.isNotBlank(orderDO.getRespCode())){
			criteria.andRespCodeEqualTo(orderDO.getRespCode());
		}
		if(StringUtils.isNotBlank(orderDO.getStatus())){
			criteria.andStatusEqualTo(orderDO.getStatus());
		}
		if(StringUtils.isNotBlank(orderDO.getStorablePan())){
			criteria.andStorablePanEqualTo(orderDO.getStorablePan());
		}
		if(StringUtils.isNotBlank(orderDO.getUpsTransNum())){
			criteria.andUpsTransNumEqualTo(orderDO.getUpsTransNum());
		}
		if(StringUtils.isNotBlank(orderDO.getValidCode())){
			criteria.andValidCodeEqualTo(orderDO.getValidCode());
		}
		if(null != orderDO.getOrderId()){
			criteria.andOrderIdEqualTo(orderDO.getOrderId());
		}
		if(null != orderDO.getTradeAmount()){
			criteria.andTradeAmountEqualTo(orderDO.getTradeAmount());
		}
		example.setOrderByClause("ORDER_ID desc");
		
		List<OrderDO> list = orderDOMapper.selectByExample(example);
		
		return new PageInfo<OrderDO>(list);
	}

}
