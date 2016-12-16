package cn.memedai.ups.boss.webapp.controller;

import java.text.SimpleDateFormat;
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

import cn.memedai.ups.boss.dal.model.PmsOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorLogDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorLogDOExample;
import cn.memedai.ups.boss.dal.model.PmsOperatorLogDOExample.Criteria;
import cn.memedai.ups.boss.enums.OperatorLogStatusEnum;
import cn.memedai.ups.boss.enums.OperatorLogTypeEnum;
import cn.memedai.ups.boss.service.permission.PmsOperatorLogService;
import cn.memedai.ups.boss.service.permission.PmsOperatorService;
import cn.memedai.ups.boss.service.permission.annotation.Permission;
import cn.memedai.ups.boss.utils.DateUtils;
import cn.memedai.ups.boss.webapp.base.PermissionBase;

import com.github.pagehelper.PageInfo;


/**
 * 操作员操作日志管理.
 * @author tongxiong.cheng
 * @date 2016-11-29 下午4:40:17
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/pmsOperatorLog")
public class PmsOperatorLogController extends PermissionBase {

	@Autowired
	PmsOperatorService pmsOperatorService;
	
	@Autowired
	private PmsOperatorLogService pmsOperatorLogService;

	/**
	 * 进入操作员操作日志信息查询页面.
	 * 
	 * @return
	 */
	@Permission("pms:operatorlog:view")
	@RequestMapping("/listPmsOperatorLogUI")
	public String listPmsOperatorLogUI(HttpServletRequest request) {
		// 日志状态
		request.setAttribute("OperatorLogStatusEnum", OperatorLogStatusEnum.toMap());
		request.setAttribute("OperatorLogStatusEnumList", OperatorLogStatusEnum.values());
		// 日志类型
		request.setAttribute("OperatorLogTypeEnum", OperatorLogTypeEnum.toMap());
		request.setAttribute("OperatorLogTypeEnumList", OperatorLogTypeEnum.values());
		return "/pms/PmsOperatorLogList";
	}

	/**
	 * 分页查询列出操作员操作日志信息.
	 * 
	 * @return
	 */
	@Permission("pms:operatorlog:view")
	@RequestMapping("/listPmsOperatorLog")
	@ResponseBody
	public Object listPmsOperatorLog(HttpServletRequest request) {
		try {
			ModelAndView model = new ModelAndView("/pms/PmsOperatorLogList");
			Map<String, Object> paramMap = new HashMap<String, Object>(); // 业务条件查询参数
			PmsOperatorLogDOExample example = new PmsOperatorLogDOExample();
			Criteria criteria = example.createCriteria();
			String operatorName = request.getParameter("operatorName"); // 操作员登录名
			if (StringUtils.isNotBlank(operatorName)) {
				paramMap.put("operatorName", operatorName);
				criteria.andOperatornameEqualTo(operatorName);
			}

			String operateType = request.getParameter("operateType");
			if (operateType != null) {
				paramMap.put("operateType", operateType);
				criteria.andOperatetypeEqualTo(Short.parseShort(operateType));
			}

			String status = request.getParameter("status");
			if (status != null) {
				paramMap.put("status", status);
				criteria.andStatusEqualTo(Short.parseShort(status));
			}

			String ip = request.getParameter("ip");
			if (StringUtils.isNotBlank(ip)) {
				paramMap.put("ip", ip);
				criteria.andIpEqualTo(ip);
			}
			String beginDate = request.getParameter("beginDate");
			if (StringUtils.isNotBlank(beginDate)) {
				paramMap.put("beginDate", beginDate);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				criteria.andCreatetimeGreaterThanOrEqualTo(sdf.parse(beginDate));
			}
			String endDate = request.getParameter("endDate");
			if (StringUtils.isNotBlank(endDate)) {
				paramMap.put("endDate", endDate);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				criteria.andCreatetimeLessThanOrEqualTo(sdf.parse(endDate+" 23:59:59"));
			}
			// 对时间进行校验
			if (beginDate != null && endDate != null) {
				// 取得两个日期之间的日数
				long a = DateUtils.daysBetween(DateUtils.toSqlTimestamp(beginDate), DateUtils.toSqlTimestamp(endDate));

				if (a > 0 && a > 5) {
					return this.operateError("开始时间不能大于结束时间而且前后区间不能大于6天");
				}
			} else if ((beginDate != null && endDate == null) || (beginDate == null && endDate != null)) {
				return this.operateError("不能只输入一个时间查询");
			}

			if (paramMap.isEmpty()) {
				log.info("== query param is empty");
				return this.operateError("查询参数不能为空");
			}

			//super.pageBean = pmsOperatorLogBiz.listPage(getPageParam(), paramMap);
			//super.pushData(pageBean);
			
			example.setOrderByClause("id desc");
			PageInfo<PmsOperatorLogDO> pageInfo = pmsOperatorLogService.listPage(getPageParam(), example);
			//super.pushData(paramMap); // 回显查询条件值
			// 日志状态
			//super.putData("OperatorLogStatusEnum", OperatorLogStatusEnum.toMap());
			//super.putData("OperatorLogStatusEnumList", OperatorLogStatusEnum.values());
			// 日志类型
			//super.putData("OperatorLogTypeEnum", OperatorLogTypeEnum.toMap());
			//super.putData("OperatorLogTypeEnumList", OperatorLogTypeEnum.values());
			
			model.addObject("pageInfo", pageInfo);
			model.addObject("paramMap", paramMap);
			model.addObject("OperatorLogStatusEnum", OperatorLogStatusEnum.toMap());
			model.addObject("OperatorLogStatusEnumList", OperatorLogStatusEnum.values());
			model.addObject("OperatorLogTypeEnum", OperatorLogTypeEnum.toMap());
			model.addObject("OperatorLogTypeEnumList", OperatorLogTypeEnum.values());
			
			return model;
		} catch (Exception e) {
			log.error("==>listPmsOperatorLog exception:", e);
			return operateError("获取数据失败");
		}
	}

	/**
	 * 根据操作员操作日志ID查看操作日志详情.
	 * 
	 * @return
	 */
	@Permission("pms:operatorlog:view")
	@RequestMapping("/viewById")
	public Object viewById() {
		ModelAndView model = new ModelAndView("/pms/PmsOperatorLogView");
		PmsOperatorLogDO operatorLog = pmsOperatorLogService.getById(Long.parseLong(getString("id")));
		PmsOperatorDO operator = pmsOperatorService.getById(operatorLog.getOperatorid());
		//super.putData("operator", operator);
		//super.putData("operatorLog", operatorLog);
		// 日志状态
		//super.putData("OperatorLogStatusEnum", OperatorLogStatusEnum.toMap());
		//super.putData("OperatorLogStatusEnumList", OperatorLogStatusEnum.values());
		// 日志类型
		//super.putData("OperatorLogTypeEnum", OperatorLogTypeEnum.toMap());
		//super.putData("OperatorLogTypeEnumList", OperatorLogTypeEnum.values());
		model.addObject("operator", operator);
		model.addObject("operatorLog",operatorLog);
		model.addObject("OperatorLogStatusEnum",OperatorLogStatusEnum.toMap());
		model.addObject("OperatorLogStatusEnumList",OperatorLogStatusEnum.values());
		model.addObject("OperatorLogTypeEnum", OperatorLogTypeEnum.toMap());
		model.addObject("OperatorLogTypeEnumList", OperatorLogTypeEnum.values());
		
		return model;
	}
}
