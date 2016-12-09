package cn.memedai.ups.boss.dal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * @descript: 权限管理-角色,操作员关联表
 * @author: chengtx
 * @创建时间: 2016-11-27,上午11:09:12
 *
 */
@Setter
@Getter
@ToString
public class PmsRoleOperatorDO {
    private Long id;
    /**
     * 角色ID
     */
    private Long roleid;
    /**
     * 操作员ID
     */
    private Long operatorid;

}