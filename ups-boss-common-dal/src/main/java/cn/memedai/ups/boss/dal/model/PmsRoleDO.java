package cn.memedai.ups.boss.dal.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * @descript: 权限管理-角色
 * @author: chengtx
 * @创建时间: 2016-11-27,上午11:07:16
 *
 */
@Setter
@Getter
@ToString
public class PmsRoleDO {
    private Long id;

    private Integer version;

    private Date createtime;
    /**
     * 角色类型（1:超级管理员角色，0:普通操作员角色）
     */
    private String roletype;
    /**
     * 角色名称
     */
    private String rolename;
    /**
     * 描述
     */
    private String remark;

}