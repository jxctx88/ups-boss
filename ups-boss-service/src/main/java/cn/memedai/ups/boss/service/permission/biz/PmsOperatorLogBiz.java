/**
 * 
 */
package cn.memedai.ups.boss.service.permission.biz;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.memedai.ups.boss.dal.dao.PmsOperatorLogDOMapper;
import cn.memedai.ups.boss.dal.model.PmsOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorLogDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorLogDOExample;
import cn.memedai.ups.boss.enums.OperatorLogStatusEnum;
import cn.memedai.ups.boss.enums.OperatorLogTypeEnum;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.permission.PmsOperatorLogService;

import com.github.pagehelper.PageInfo;


/**
 * @描述: 权限管理-操作员操作日表业务层.
 * @作者: WuShuicheng.
 * @创建时间: 2013-12-31,下午4:22:22.
 * @版本号: V1.0 .
 * 
 */
@Slf4j
@Component("pmsOperatorLogBiz")
public class PmsOperatorLogBiz {

	@Autowired
	private PmsOperatorLogService pmsOperatorLogService;
	
	@Autowired
	private PmsOperatorLogDOMapper pmsOperatorLogDOMapper;

	/**
	 * 根据ID获取操作员操作日志信息.
	 * 
	 * @param id
	 *            .
	 * @return PmsOperatorLog.
	 */
	public PmsOperatorLogDO getById(Long id) {
		return pmsOperatorLogDOMapper.selectByPrimaryKey(id);
	}

	/**
	 * 分页查询操作员操作日志信息.
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param example
	 *            查询参数.
	 * @return
	 */
	public PageInfo<PmsOperatorLogDO> listPage(PageParam pageParam, PmsOperatorLogDOExample example) {
		return pmsOperatorLogService.listPage(pageParam, example);
	}
	
	/**
	 * 创建操作员操作记录.
	 * @param logTypeEnum 操作日志类型.
	 * @param logStatusEnum 操作日志状态.
	 * @param content 要记录的日志内容.
	 * @param loginedOperator 操作员信息.
	 * @param ipAddr IP地址.
	 */
	public void createOperatorLog(OperatorLogTypeEnum logTypeEnum, OperatorLogStatusEnum logStatusEnum, String content, PmsOperatorDO operator, String ipAddr) {
		if (operator == null) {
			log.warn("==> operator is null");
			return;
		}
		
		PmsOperatorLogDO optLog = new PmsOperatorLogDO();
		optLog.setOperatorid(operator.getId());
		optLog.setOperatorname(operator.getLoginname());
		optLog.setOperatetype((short)logTypeEnum.getValue());
		optLog.setStatus((short)logStatusEnum.getValue());
		optLog.setIp(ipAddr);
		optLog.setContent(content);
		optLog.setCreatetime(new Date());
		pmsOperatorLogDOMapper.insert(optLog);
	}

}
