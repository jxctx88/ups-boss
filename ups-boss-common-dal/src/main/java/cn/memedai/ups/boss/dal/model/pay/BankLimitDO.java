package cn.memedai.ups.boss.dal.model.pay;

import java.util.Date;

import lombok.Data;
@Data
public class BankLimitDO {
    private Long id;

    private String channelId;

    private String bankId;

    private String bankName;

    private Long singleLimit;

    private Long dateLimit;

    private String payType;

    private String status;

    private Date createTime;

    private Date lastUpdateTime;

}