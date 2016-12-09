package cn.memedai.ups.boss.service.permission;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.memedai.ups.boss.dal.model.PmsOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorDOExample;
import cn.memedai.ups.boss.service.page.PageParam;


/**
 * 
 * @descript: 操作员表数据访问层接口.
 * @author: chengtx
 * @创建时间: 2016-11-27,上午10:56:44
 *
 */
public interface PmsOperatorService{

	/**
	 * 根据操作员登录名获取操作员信息.
	 * 
	 * @param loginName
	 *            .
	 * @return operator .
	 */
	PmsOperatorDO findByLoginName(String loginName);

	/**
	 * 根据角色ID找到操作员列表.
	 * 
	 * @param roleId
	 * @return
	 */
	List<PmsOperatorDO> listByRoleId(long roleId);

	/**
	 * 分页查询
	 * @param pageParam
	 * @param example
	 * @return
	 */
	PageInfo<PmsOperatorDO> listPage(PageParam pageParam,PmsOperatorDOExample example);

}
