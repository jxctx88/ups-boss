package cn.memedai.ups.boss.dal.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * @descript: 系统菜单
 * @author: chengtx
 * @创建时间: 2016-11-27,上午11:02:08
 *
 */
@Setter
@Getter
@ToString
public class PmsMenuDO {
    private Long id;

    private Integer version;

    private Date createtime;
    /**
     * 菜单名称NAME
     */
    private String name;
    /**
     * 菜单地址URL
     */
    private String url;
    /**
     * 菜单编号NUMBER
     */
    private String number;
    /**
     * 是否叶子节点
     * 0:不是,1:是
     */
    private Short isleaf;
    /**
     * 级别
     */
    private Short level;
    /**
     * 父菜单PARNETID
     */
    private Long parentid;
    /**
     * TARGETNAME; //用于刷新页面的配置
     */
    private String targetname;
    /**
     * 父菜单PARNETID
     */
    PmsMenuDO parent;

}