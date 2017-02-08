package cn.memedai.ups.boss.webapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.memedai.ups.boss.dal.model.pay.BankLimitDO;
import cn.memedai.ups.boss.dal.model.pay.BankLimitDOExample;
import cn.memedai.ups.boss.service.pay.BankLimitService;
import cn.memedai.ups.boss.webapp.base.PermissionBase;

import com.github.pagehelper.PageInfo;
/**
 * 支付渠道控制类
 * @author tongxiong.cheng
 * @date 2017-2-7 下午2:02:08
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/payChannel")
public class PayChannelController extends PermissionBase {
	
	@Autowired
	private BankLimitService bankLimitService;
	

	/**
	 * 进入支付渠道页面.
	 * @return
	 */
	@RequestMapping("/listPayChannel")
	@ResponseBody
	public Object listPayChannel(HttpServletRequest request) {
		ModelAndView mov = new ModelAndView("/payRule/payChannel/payChannelList");
		
		Map<String, Object> paramMap = new HashMap<String, Object>(); // 业务条件查询参数
		
		BankLimitDO bankLimitDO = new BankLimitDO();
		String channel = request.getParameter("channel"); //支付通道
		if (StringUtils.isNotBlank(channel)) {
			paramMap.put("channel", channel);
			bankLimitDO.setChannelId(channel);
		}

		String payType = request.getParameter("payType");
		if (payType != null) {
			paramMap.put("payType", payType);
			bankLimitDO.setPayType(payType);
		}

		String status = request.getParameter("status");
		if (status != null) {
			paramMap.put("status", status);
			bankLimitDO.setStatus(status);
		}
		PageInfo<BankLimitDO> pageInfo = bankLimitService.listPage(getPageParam(),bankLimitDO);
		mov.addObject("paramMap", paramMap);
		mov.addObject("pageInfo", pageInfo);
		return mov;
	}

	@RequestMapping("/editPayChannelUI")
	@ResponseBody
	public Object editPayChannelUI(){
		try {
			ModelAndView mov = new ModelAndView("/payRule/payChannel/payChannelEdit");
			Long id = getLong("id");
			BankLimitDO bankLimitDO = bankLimitService.selectByPrimaryKey(id);
			if (bankLimitDO == null) {
				return operateError("无法获取要修改的数据");
			}
			mov.addObject("bankLimitDO", bankLimitDO);

			return mov;
		} catch (Exception e) {
			log.error("== editPayChannelUI exception:", e);
			return operateError("获取修改数据失败");
		}
	}
	
	@RequestMapping("/editPayChannel")
	@ResponseBody
	public Object editPayChannel(@RequestParam("id") long id,@RequestParam("channelId") String channelId,@RequestParam("singleLimit") long singleLimit,@RequestParam("dateLimit") long dateLimit){
		BankLimitDO record = new BankLimitDO();
		record.setChannelId(channelId);
		record.setSingleLimit(singleLimit);
		record.setDateLimit(dateLimit);
		record.setLastUpdateTime(new Date());
		BankLimitDOExample example = new BankLimitDOExample();
		example.createCriteria().andIdEqualTo(id);
		bankLimitService.updateByExampleSelective(record, example);
		return operateSuccess();
	}
	
	@RequestMapping("/changePayChannelStatus")
	@ResponseBody
	public Object changePayChannelStatus(){
		Long id = getLong("id");
		BankLimitDO bankLimitDO = bankLimitService.selectByPrimaryKey(id);
		if (bankLimitDO == null) {
			return operateError("无法获取要修改的数据");
		}
		String status = bankLimitDO.getStatus();
		BankLimitDOExample example = new BankLimitDOExample();
		example.createCriteria().andIdEqualTo(id);
		BankLimitDO record = new BankLimitDO();
		record.setStatus(status.equals("10") ? "20" : "10");
		record.setLastUpdateTime(new Date());
		bankLimitService.updateByExampleSelective(record, example);
		return operateSuccess();
	}
}
