package cn.memedai.ups.boss.webapp.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.memedai.ups.boss.constants.PermissionConstant;
import cn.memedai.ups.boss.dal.model.PmsOperatorDO;
import cn.memedai.ups.boss.enums.OperatorLogStatusEnum;
import cn.memedai.ups.boss.enums.OperatorLogTypeEnum;
import cn.memedai.ups.boss.service.context.ThreadLocalContext;
import cn.memedai.ups.boss.service.permission.biz.PmsOperatorLogBiz;


/**
 * Web系统权限模块基础支撑
 * @author tongxiong.cheng
 * @date 2016-11-29 下午3:38:14
 * @version 1.0
 */
@Service("permissionBase")
public class PermissionBase extends ControllerSupport{

	@Autowired
	private PmsOperatorLogBiz pmsOperatorLogBiz;

	/**
	 * 取出当前登录操作员对象
	 */
	public PmsOperatorDO getLoginedOperator() {
		PmsOperatorDO operator = (PmsOperatorDO) ThreadLocalContext.getHttpRequest().getSession().getAttribute(PermissionConstant.OPERATOR_SESSION_KEY);
		return operator;
	}
	
	
	/**
	 * 登录系统时记录日志.
	 * 
	 * @param content
	 */
	protected void logLogin(String content){
		pmsOperatorLogBiz.createOperatorLog(OperatorLogTypeEnum.LOGIN, OperatorLogStatusEnum.SUCCESS, content, getLoginedOperator(), getIpAddr());
	}

	/**
	 * 登录系统失败时记录日志.
	 * 
	 * @param content
	 */
	protected void logLoginError(String content, PmsOperatorDO operator){
		pmsOperatorLogBiz.createOperatorLog(OperatorLogTypeEnum.LOGIN, OperatorLogStatusEnum.ERROR, content, operator, getIpAddr());
	}
	
	/**
	 * 保存数据的时记录日志.
	 * 
	 * @param content
	 */
	protected void logSave(String content){
		pmsOperatorLogBiz.createOperatorLog(OperatorLogTypeEnum.ADD, OperatorLogStatusEnum.SUCCESS, content, getLoginedOperator(), getIpAddr());
	}

	/**
	 * 保存数据的失败时记录日志.
	 * 
	 * @param content
	 */
	protected void logSaveError(String content){
		pmsOperatorLogBiz.createOperatorLog(OperatorLogTypeEnum.ADD, OperatorLogStatusEnum.ERROR, content, getLoginedOperator(), getIpAddr());
	}
	
	/**
	 * 更新数据时记录日志.
	 * 
	 * @param content
	 */
	protected void logEdit(String content){
		pmsOperatorLogBiz.createOperatorLog(OperatorLogTypeEnum.EDIT, OperatorLogStatusEnum.SUCCESS, content, getLoginedOperator(), getIpAddr());
	}

	/**
	 * 更新数据失败时记录日志.
	 * 
	 * @param content
	 */
	protected void logEditError(String content){
		pmsOperatorLogBiz.createOperatorLog(OperatorLogTypeEnum.EDIT, OperatorLogStatusEnum.ERROR, content, getLoginedOperator(), getIpAddr());
	}
	
	/**
	 * 删除数据时记录日志.
	 * 
	 * @param content
	 */
	protected void logDelete(String content){
		pmsOperatorLogBiz.createOperatorLog(OperatorLogTypeEnum.DELETE, OperatorLogStatusEnum.SUCCESS, content, getLoginedOperator(), getIpAddr());
	}

	/**
	 * 删除数据失败时记录日志.
	 * 
	 * @param content
	 */
	protected void logDeleteError(String content){
		pmsOperatorLogBiz.createOperatorLog(OperatorLogTypeEnum.DELETE, OperatorLogStatusEnum.ERROR, content, getLoginedOperator(), getIpAddr());
	}
	
	/**
	 * 查询数据时记录日志.
	 * @param content
	 */
	protected void logQuery(String content) {
		pmsOperatorLogBiz.createOperatorLog(OperatorLogTypeEnum.QUERYA, OperatorLogStatusEnum.SUCCESS, content, getLoginedOperator(), getIpAddr());
	}

	/**
	 * 查询数据失败时记录日志.
	 * @param content
	 */
	protected void logQueryError(String content) {
		pmsOperatorLogBiz.createOperatorLog(OperatorLogTypeEnum.QUERYA, OperatorLogStatusEnum.ERROR, content, getLoginedOperator(),getIpAddr());
	}

}
