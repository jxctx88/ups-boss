package cn.memedai.ups.boss.webapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.memedai.ups.boss.dal.model.pay.OrderDO;
import cn.memedai.ups.boss.service.pay.OrderService;
import cn.memedai.ups.boss.service.permission.annotation.Permission;
import cn.memedai.ups.boss.utils.DateUtils;
import cn.memedai.ups.boss.utils.StrUtil;
import cn.memedai.ups.boss.webapp.base.PermissionBase;

import com.github.pagehelper.PageInfo;
/**
 * 支付查询控制类
 * @author tongxiong.cheng
 * @date 2017-2-7 下午2:02:08
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/pay/order")
public class PayQueryController extends PermissionBase {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 进入订单查询页面.
	 * @return
	 */
	@RequestMapping("/listOrder")
	@ResponseBody
	public Object listPayChannel(HttpServletRequest request) {
		ModelAndView mov = new ModelAndView("/pay/order/orderList");
		
		Map<String, Object> paramMap = new HashMap<String, Object>(); // 业务条件查询参数
		
		OrderDO orderDO = new OrderDO();
		String upsTransNum = request.getParameter("upsTransNum");
		if (StringUtils.isNotBlank(upsTransNum)) {
			paramMap.put("upsTransNum", upsTransNum);
			orderDO.setUpsTransNum(upsTransNum);
		}
		
		String merchantCode = request.getParameter("merchantCode");
		if (StringUtils.isNotBlank(merchantCode)) {
			paramMap.put("merchantCode", merchantCode);
			orderDO.setMerchantCode(merchantCode);
		}
		
		String merchantTradeCode = request.getParameter("merchantTradeCode");
		if (StringUtils.isNotBlank(merchantTradeCode)) {
			paramMap.put("merchantTradeCode", merchantTradeCode);
			orderDO.setMerchantTradeCode(merchantTradeCode);
		}
		
		String bankAccount = request.getParameter("bankAccount");
		if (StringUtils.isNotBlank(bankAccount)) {
			paramMap.put("bankAccount", bankAccount);
			orderDO.setBankAccount(bankAccount);
		}
		
		String bankAccountName = request.getParameter("bankAccountName");
		if (StringUtils.isNotBlank(bankAccountName)) {
			paramMap.put("bankAccountName", bankAccountName);
			orderDO.setBankAccountName(bankAccountName);
		}
		
		String idCard = request.getParameter("idCard");
		if (StringUtils.isNotBlank(idCard)) {
			paramMap.put("idCard", idCard);
			orderDO.setIdCard(idCard);
		}
		
		String mobilePhone = request.getParameter("mobilePhone");
		if (StringUtils.isNotBlank(mobilePhone)) {
			paramMap.put("mobilePhone", mobilePhone);
			orderDO.setMobilePhone(mobilePhone);
		}
		
		String payGateway = request.getParameter("payGateway");
		if (StringUtils.isNotBlank(payGateway)) {
			paramMap.put("payGateway", payGateway);
			orderDO.setPayGateway(payGateway);
		}
		
		String payType = request.getParameter("payType");
		if (StringUtils.isNotBlank(payType)) {
			paramMap.put("payType", payType);
			orderDO.setPayType(payType);
		}
		
		String status = request.getParameter("status");
		if (StringUtils.isNotBlank(status)) {
			paramMap.put("status", status);
			orderDO.setStatus(status);
		}

		PageInfo<OrderDO> pageInfo = orderService.listPage(getPageParam(),orderDO);
		List<OrderDO> orderDOList = pageInfo.getList();
		List<OrderDO> list = new ArrayList<OrderDO>();
		//隐藏敏感信息
		for(OrderDO oneOrderDO : orderDOList){
			oneOrderDO.setMobilePhone(StrUtil.foramtPhone(oneOrderDO.getMobilePhone()));
			oneOrderDO.setIdCard(StrUtil.foramtIDCard(oneOrderDO.getIdCard()));
			oneOrderDO.setBankAccount(StrUtil.foramtBankAccount(oneOrderDO.getBankAccount()));
			oneOrderDO.setBankAccountName(StrUtil.foramtBankAcountName(oneOrderDO.getBankAccountName()));
			list.add(oneOrderDO);
		}
		pageInfo.setList(list);
		mov.addObject("paramMap", paramMap);
		mov.addObject("pageInfo", pageInfo);
		return mov;
	}
	
	/**
	 * 跳转到订单修改页面
	 * @param request
	 * @return
	 */
	@Permission("pay:order:edit")
	@RequestMapping("/editOrderUI")
	@ResponseBody
	public Object editOrderUI(HttpServletRequest request) {
		try {
			String orderId = request.getParameter("orderId");
			OrderDO orderDO = orderService.selectByPrimaryKey(Long.parseLong(orderId));
			ModelAndView mov = new ModelAndView("/pay/order/orderEdit");
			mov.addObject("orderDO", orderDO);
			return mov;
		} catch (Exception e) {
			log.error("== editPmsActionUI exception:", e);
			return operateError("获取数据失败");
		}
	}
	
	/**
	 * 确认修改订单
	 * @param request
	 * @return
	 */
	@Permission("pay:order:edit")
	@RequestMapping("/editOrder")
	@ResponseBody
	public Object editOrder(HttpServletRequest request) {
		try {
			OrderDO orderDO = new OrderDO();
			Long orderId = getLong("orderId");
			String status = getString("status");
			orderDO.setOrderId(orderId);
			orderDO.setStatus(status);
			orderDO.setLastUpdateTime(DateUtils.getCurrDateTime());
			orderService.updateByPrimaryKeySelective(orderDO);
			super.logEdit("修改订单[" + orderId + "]"+orderDO.toString());
			return operateSuccess();
		} catch (Exception e) {
			log.error("== editOrder exception:", e);
			return operateError("获取数据失败");
		}
	}

}
