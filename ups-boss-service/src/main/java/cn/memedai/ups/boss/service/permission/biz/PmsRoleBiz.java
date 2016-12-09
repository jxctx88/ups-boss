package cn.memedai.ups.boss.service.permission.biz;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.memedai.ups.boss.dal.dao.PmsRoleDOMapper;
import cn.memedai.ups.boss.dal.model.PmsRoleDO;
import cn.memedai.ups.boss.dal.model.PmsRoleDOExample;
import cn.memedai.ups.boss.dal.model.PmsRoleOperatorDO;
import cn.memedai.ups.boss.service.page.PageBean;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.permission.PmsRoleActionService;
import cn.memedai.ups.boss.service.permission.PmsRoleMenuService;
import cn.memedai.ups.boss.service.permission.PmsRoleOperatorService;
import cn.memedai.ups.boss.service.permission.PmsRoleService;

import com.github.pagehelper.PageInfo;


/**
 * 
 * @描述: 角色表--服务层接口.
 * @作者: WuShuicheng .
 * @创建时间: 2013-7-25,下午10:44:16 .
 * @版本: 1.0 .
 */
@Component("pmsRoleBiz")
public class PmsRoleBiz {

	@Autowired
	private PmsRoleService pmsRoleService;
	@Autowired
	private PmsRoleDOMapper pmsRoleDOMapper;
	
	
	@Autowired
	private PmsRoleActionService pmsRoleActionService;
	
	@Autowired
	private PmsRoleOperatorService pmsRoleOperatorService;
	
	@Autowired
	private PmsRoleMenuService pmsRoleMenuService;


	/**
	 * 获取所有角色列表，以供添加操作员时选择.
	 * 
	 * @return roleList .
	 */
	public List<PmsRoleDO> listAllRole() {
		return pmsRoleService.listAll();
	}

	/**
	 * 根据角色ID删除角色 .
	 */
	public void deleteById(Long id) {
		pmsRoleDOMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据角色名称获取角色记录（用于判断角色名是否已存在）.
	 * 
	 * @param roleName
	 *            角色名.
	 * @return PmsRole.
	 */
	public PmsRoleDO getByRoleName(String roleName) {
		return pmsRoleService.getByRoleName(roleName);
	}

	/**
	 * 查找是否存在与ID值不相同与角色名相同的角色记录（用于判断修改的角色名与其他的角色名冲突）。
	 * 
	 * @param id
	 *            角色ID .
	 * @param roleName
	 *            角色名.
	 * @return PmsRole.
	 */
	public PmsRoleDO findByRoleNameNotEqId(Long id, String roleName) {
		return pmsRoleService.findByRoleNameNotEqId(id, roleName);
	}

	/**
	 * 根据权限ID找出关联了此权限的角色.
	 * 
	 * @param actionId
	 *            .
	 * @return roleList.
	 */
	public List<PmsRoleDO> listByActionId(Long actionId) {
		return pmsRoleService.listByActionId(actionId);
	}

	/**
	 * 查询并分页列出角色信息.
	 * @param pageParam
	 * @param paramMap
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		//return pmsRoleService.listPage(pageParam, paramMap);
		return null;
	}

	/**
	 * 根据ID获取角色.
	 * @param id
	 * @return
	 */
	public PmsRoleDO getById(Long id) {
		return pmsRoleDOMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存角色
	 * @param pmsRole
	 */
	public void create(PmsRoleDO pmsRole) {
		pmsRoleDOMapper.insert(pmsRole);
		
	}

	/**
	 * 更新角色.
	 * @param pmsRole
	 */
	public void update(PmsRoleDO pmsRole) {
		pmsRoleDOMapper.updateByPrimaryKeySelective(pmsRole);
		
	}
	
	/**
	 * 根据角色ID删除角色，并删除与操作员、权限、菜单的关联关系.
	 * 
	 * @param roleId
	 *            .
	 */
	public void deleteRoleById(Long roleId) {
		// 删除角色权限关联表中的数据
		pmsRoleActionService.deleteByRoleId(roleId);
		// 删除角色菜单关联表中的数据
		pmsRoleMenuService.deleteByRoleId(roleId);
		// 删除角色操作员关联表中的数据
		pmsRoleOperatorService.deleteByRoleId(roleId);
		// 最后删除角色信息
		pmsRoleDOMapper.deleteByPrimaryKey(roleId);
		
	}
	
	/**
	 * 修改角色信息，并更新其关联的权限点.
	 * 
	 * @param pmsRole
	 *            角色信息.
	 * @param actionStr
	 *            要关联的权限点集字符串.
	 */
	public void updateRole(PmsRoleDO pmsRole) {
		pmsRoleDOMapper.updateByPrimaryKeySelective(pmsRole);
	}
	
	/**
	 * 保存角色并关联权限.
	 * 
	 * @param pmsRole
	 *            角色信息.
	 * @param pmsRole
	 *            要关联的权限点集字符串.
	 */
	public void saveRole(PmsRoleDO pmsRole) {
		pmsRoleDOMapper.insert(pmsRole);
	}
	
	/**
	 * 根据操作员ID获得该操作员的所有角色id所拼成的String，每个ID用“,”分隔
	 * @param operatorId 操作员ID
	 * @return roleIds
	 */
	public String getRoleIdsByOperatorId(long operatorId) {
		// 得到操作员和角色列表
		List<PmsRoleOperatorDO> rpList = pmsRoleOperatorService.listByOperatorId(operatorId);
		// 构建StringBuffer来拼字符串
		StringBuffer roleIdsBuf = new StringBuffer("");
		for (PmsRoleOperatorDO rp : rpList) {
			roleIdsBuf.append(rp.getRoleid()).append(",");
		}
		String roleIds = roleIdsBuf.toString();
		// 截取字符串
		if (StringUtils.isNotBlank(roleIds) && roleIds.length() > 0) {
			roleIds = roleIds.substring(0, roleIds.length() - 1);
		}
		return roleIds;
	}

	/**
	 * 分页查询
	 * @param pageParam
	 * @param example
	 * @return
	 */
	public PageInfo<PmsRoleDO> listPage(PageParam pageParam,PmsRoleDOExample example){
		return pmsRoleService.listPage(pageParam, example);
	}

}
