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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.memedai.ups.boss.dal.model.pay.BatchOutcomeDO;
import cn.memedai.ups.boss.service.pay.BatchOutcomeService;
import cn.memedai.ups.boss.service.permission.annotation.Permission;
import cn.memedai.ups.boss.webapp.base.PermissionBase;

import com.github.pagehelper.PageInfo;
/**
 * 批次出款控制类
 * @author tongxiong.cheng
 * @date 2017-2-7 下午2:02:08
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/pay/batchOutcome")
public class PayBatchOutcomController extends PermissionBase {
	
	@Autowired
	private BatchOutcomeService batchOutcomeService;
	
	/**
	 * 进入订单查询页面.
	 * @return
	 */
	@RequestMapping("/listBatchOutcome")
	@ResponseBody
	public Object listPayChannel(HttpServletRequest request) {
		ModelAndView mov = new ModelAndView("/pay/batchOutcome/batchOutcomeList");
		
		Map<String, Object> paramMap = new HashMap<String, Object>(); // 业务条件查询参数
		
		BatchOutcomeDO batchOutcomeDO = new BatchOutcomeDO();
		String batchId = request.getParameter("batchId");
		if (StringUtils.isNotBlank(batchId)) {
			paramMap.put("batchId", batchId);
			batchOutcomeDO.setBatchId(batchId);
		}
		
		String status = request.getParameter("status");
		if (StringUtils.isNotBlank(status)) {
			paramMap.put("status", status);
			batchOutcomeDO.setStatus(status);
		}
		
		String sendFilename = request.getParameter("sendFilename");
		if (StringUtils.isNotBlank(sendFilename)) {
			paramMap.put("sendFilename", sendFilename);
			batchOutcomeDO.setSendFilename(sendFilename);
		}
		
		String respFilename = request.getParameter("respFilename");
		if (StringUtils.isNotBlank(respFilename)) {
			paramMap.put("respFilename", respFilename);
			batchOutcomeDO.setRespFilename(respFilename);
		}

		PageInfo<BatchOutcomeDO> pageInfo = batchOutcomeService.listPage(getPageParam(),batchOutcomeDO);
		mov.addObject("paramMap", paramMap);
		mov.addObject("pageInfo", pageInfo);
		return mov;
	}
	
	/**
	 * 跳转到订单修改页面
	 * @param request
	 * @return
	 */
	@Permission("pay:batctOutcome:edit")
	@RequestMapping("/editBatchOutcomeUI")
	@ResponseBody
	public Object editBatchOutcomeUI(HttpServletRequest request) {
		try {
			long id  = getLong("id");
			BatchOutcomeDO batchOutcomeDO = batchOutcomeService.selectByPrimaryKey(id);
			ModelAndView mov = new ModelAndView("/pay/batchOutcome/batchOutcomeEdit");
			mov.addObject("batchOutcomeDO", batchOutcomeDO);
			return mov;
		} catch (Exception e) {
			log.error("== editBatchOutcomeUI exception:", e);
			return operateError("获取数据失败");
		}
	}
	
	/**
	 * 确认修改订单
	 * @param request
	 * @return
	 */
	@Permission("pay:batchOutcome:editBatchOutcome")
	@RequestMapping("/editBatchOutcome")
	@ResponseBody
	public Object editBatchOutcome(HttpServletRequest request) {
		try {
			BatchOutcomeDO batchOutcomeDO = new BatchOutcomeDO();
			Long id = getLong("id");
			String status = getString("status");
			batchOutcomeDO.setId(id);
			batchOutcomeDO.setStatus(status);
			batchOutcomeDO.setLastUpdateTime(new Date());
			batchOutcomeService.updateByPrimaryKeySelective(batchOutcomeDO);
			super.logEdit("修改订单[" + id + "]"+batchOutcomeDO.toString());
			return operateSuccess();
		} catch (Exception e) {
			log.error("== editBatchOutcome exception:", e);
			return operateError("获取数据失败");
		}
	}

}
