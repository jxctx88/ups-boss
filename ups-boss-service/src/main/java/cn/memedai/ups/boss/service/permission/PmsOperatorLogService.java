package cn.memedai.ups.boss.service.permission;

import cn.memedai.ups.boss.dal.model.PmsOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorLogDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorLogDOExample;
import cn.memedai.ups.boss.enums.OperatorLogStatusEnum;
import cn.memedai.ups.boss.enums.OperatorLogTypeEnum;
import cn.memedai.ups.boss.service.page.PageParam;

import com.github.pagehelper.PageInfo;

/**
 * 
 * @descript: 权限管理-操作员操作日志数据访问层接口.
 * @author: chengtx
 * @创建时间: 2016-11-27,上午10:56:20
 *
 */
public interface PmsOperatorLogService{

	/**
	 * 分页查询
	 * @param pageParam
	 * @param example
	 * @return
	 */
	PageInfo<PmsOperatorLogDO> listPage(PageParam pageParam,
			PmsOperatorLogDOExample example);
	
	/**
	 * 根据ID获取操作员操作日志信息.
	 * 
	 * @param id
	 *            .
	 * @return PmsOperatorLog.
	 */
	PmsOperatorLogDO getById(Long id);

	/**
	 * 创建操作员操作记录.
	 * @param logTypeEnum 操作日志类型.
	 * @param logStatusEnum 操作日志状态.
	 * @param content 要记录的日志内容.
	 * @param loginedOperator 操作员信息.
	 * @param ipAddr IP地址.
	 */
	void createOperatorLog(OperatorLogTypeEnum logTypeEnum, OperatorLogStatusEnum logStatusEnum, String content, PmsOperatorDO operator, String ipAddr);
}
