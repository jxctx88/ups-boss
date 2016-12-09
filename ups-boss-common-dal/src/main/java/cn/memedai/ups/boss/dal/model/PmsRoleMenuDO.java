package cn.memedai.ups.boss.dal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * @descript: 角色菜单关联表
 * @author: chengtx
 * @创建时间: 2016-11-27,上午11:08:43
 *
 */
@Setter
@Getter
@ToString
public class PmsRoleMenuDO {
    private Long id;
    /**
     * 角色ID
     */
    private Long roleid;
    /**
     * 菜单ID
     */
    private Long menuid;

}