package cn.memedai.ups.boss.dal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * @descript: 权限管理-角色权限关联表
 * @author: chengtx
 * @创建时间: 2016-11-27,上午11:07:00
 *
 */
@Setter
@Getter
@ToString
public class PmsRoleActionDO {
    private Long id;
    /**
     *  角色ID
     */
    private Long roleid;
    /**
     * 权限ID
     */
    private Long actionid;

}