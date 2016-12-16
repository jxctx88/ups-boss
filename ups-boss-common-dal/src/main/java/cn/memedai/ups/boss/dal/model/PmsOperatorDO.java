package cn.memedai.ups.boss.dal.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 权限管理-操作员
 * @descript:
 * @author: chengtx
 * @创建时间: 2016-11-27,上午11:04:03
 *
 */
@Setter
@Getter
@ToString(callSuper=true)
public class PmsOperatorDO  extends BaseEntity{
   
    /**
     * 登录名
     */
    private String loginname;
    /**
     * 登录密码
     */
    private String loginpwd;
    /**
     * 描述
     */
    private String remark;
    /**
     * 姓名
     */
    private String realname;
    /**
     * 手机号
     */
    private String mobileno;
    /**
     * 状态
     */
    private String status;
    /**
     * 操作员类型（1:超级管理员，0:普通操作员），超级管理员由系统初始化时添加，不能删除
     */
    private String type;
    /**
     * 最后登录时间
     */
    private Date lastlogintime;
    /**
     * 是否更改过密码(1:已修改，0:未修改)
     */
    private Short ischangedpwd;
    /**
     * 连续输错密码次数（连续5次输错就冻结帐号）
     */
    private Short pwderrorcount;
    /**
     * 最后输错密码的时间
     */
    private Date pwderrortime;

}