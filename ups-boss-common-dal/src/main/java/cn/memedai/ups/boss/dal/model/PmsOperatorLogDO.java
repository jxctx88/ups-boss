package cn.memedai.ups.boss.dal.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * @descript: 权限管理-操作员操作日志.
 * @author: chengtx
 * @创建时间: 2016-11-27,上午11:05:35
 *
 */
@Setter
@Getter
@ToString
public class PmsOperatorLogDO {
    private Long id;

    private Date createtime;
    /**
     * 操作员ID
     */
    private Long operatorid;
    /**
     * 操作员登录名
     */
    private String operatorname;
    /**
     * 操作类型（参与枚举:OperatorLogTypeEnum,1:增加,2:修改,3:删除,4:查询,5:登录）
     */
    private Short operatetype;
    /**
     * 操作状态（参与枚举:OperatorLogStatusEnum,100:成功，101:失败）
     */
    private Short status;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 操作内容
     */
    private String content;

}