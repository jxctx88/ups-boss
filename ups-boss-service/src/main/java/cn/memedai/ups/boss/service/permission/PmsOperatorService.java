package cn.memedai.ups.boss.service.permission;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.memedai.ups.boss.dal.model.PmsMenuDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorDOExample;
import cn.memedai.ups.boss.dal.model.PmsRoleOperatorDO;
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

	/**
	 * 根据ID删除一个操作员，同时删除与该操作员关联的角色关联信息. type="1"的超级管理员不能删除.
	 * 
	 * @param id
	 *            操作员ID.
	 */
	void deleteOperatorById(long operatorId);
	
	/**
	 * 更新操作员信息.
	 * @param operator
	 */ 
	int update(PmsOperatorDO operator);
	
	/**
	 * 根据操作员ID更新操作员密码.
	 * 
	 * @param operatorId
	 * @param newPwd
	 *            (已进行SHA1加密)
	 */
	int updateOperatorPwd(Long operatorId, String newPwd, boolean isTrue);
	
	/**
	 * 根据ID获取操作员信息.
	 * @param operatorId
	 * @return
	 */
	PmsOperatorDO getById(Long operatorId);
	
	/**
	 * 保存操作员信息.
	 * @param pmsOperator
	 */
	int create(PmsOperatorDO pmsOperator);
	
	/**
	 * 保存操作員信息及其关联的角色.
	 * @param pmsOperator
	 * @param roleOperatorStr
	 *            .
	 */
	void saveOperator(PmsOperatorDO pmsOperator, String roleOperatorStr);
	
	/**
	 * 修改操作員信息及其关联的角色.
	 * 
	 * @param pmsOperator
	 * @param roleOperatorStr
	 */
	void updateOperator(PmsOperatorDO pmsOperator, String roleOperatorStr);
	
	/**
	 * 根据角色ID统计有多少个操作员关联到此角色.
	 * @param roleId
	 * @return count.
	 */
	int countOperatorByRoleId(Long roleId);
	
	/**
	 * 根据操作员ID获得所有操作员－角色关联列表
	 */
	List<PmsRoleOperatorDO> listRoleOperatorByOperatorId(long operatorId);
	
}
