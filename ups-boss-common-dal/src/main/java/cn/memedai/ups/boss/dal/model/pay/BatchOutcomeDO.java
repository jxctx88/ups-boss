package cn.memedai.ups.boss.dal.model.pay;

import java.util.Date;

public class BatchOutcomeDO {
    private Long id;

    private String batchId;

    private String channelId;

    private String sendFilename;

    private String status;

    private String respFilename;

    private Date createTime;

    private Date lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getSendFilename() {
        return sendFilename;
    }

    public void setSendFilename(String sendFilename) {
        this.sendFilename = sendFilename == null ? null : sendFilename.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRespFilename() {
        return respFilename;
    }

    public void setRespFilename(String respFilename) {
        this.respFilename = respFilename == null ? null : respFilename.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}