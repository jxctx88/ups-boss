package cn.memedai.ups.boss.service.permission;

import cn.memedai.ups.boss.dal.model.PmsOperatorLogDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorLogDOExample;
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

}
