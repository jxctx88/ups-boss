package cn.memedai.ups.boss.dal.model.pay;

import lombok.Data;

@Data
public class OrderDO {
    private Long orderId;

    private String upsTransNum;

    private Long tradeAmount;

    private String merchantCode;

    private String merchantTradeCode;

    private String productName;

    private String productDesc;

    private String merchantTransDate;

    private String merchantTransTime;

    private String memberId;

    private String bgUrl;

    private Long accountId;

    private String upsTransDate;

    private String upsTransTime;

    private String respCode;

    private String codeMsg;

    private String bankTransDate;

    private String bankTransTime;

    private String status;

    private String isAgreeShortcut;

    private String payGateway;

    private String createTeim;

    private String lastUpdateTime;

    private String payType;

    private String bankAccount;

    private String bankAccountName;

    private String bankCode;

    private String bankName;

    private String idCard;

    private String mobilePhone;

    private String storablePan;

    private String validCode;

}