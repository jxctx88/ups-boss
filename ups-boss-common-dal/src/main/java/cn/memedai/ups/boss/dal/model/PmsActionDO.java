package cn.memedai.ups.boss.dal.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限-权限表实体
 * @descript:
 * @author: chengtx
 * @创建时间: 2016-11-27,上午11:00:31
 *
 */
@Setter
@Getter
@ToString
public class PmsActionDO {
    private Long id;

    private Integer version;

    private Date createtime;

    /**
     * 权限名称
     */
    private String actionname;
    /**
     * 权限标识
     */
    private String action;
    /**
     * 权限描述
     */
    private String remark;
    /**
     * 菜单ID
     */
    private Long menuid;
    
    
    private PmsMenuDO menu;
}