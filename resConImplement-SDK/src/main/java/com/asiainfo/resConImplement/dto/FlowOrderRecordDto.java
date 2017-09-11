package com.asiainfo.resConImplement.dto;

import java.io.Serializable;
import java.util.Date;

public class FlowOrderRecordDto implements Serializable {
    private String serialnumber;

    private String returnstate;

    private String imsi;

    private String iccid;

    private String effectiveDate;

    private Boolean inlineProcess;

    private String notificationUrl;

    private String subscriberPolicies;

    private String additionalPolicies;

    private Date createTime;

    private String column11;

    private static final long serialVersionUID = 1L;

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
    }

    public String getReturnstate() {
        return returnstate;
    }

    public void setReturnstate(String returnstate) {
        this.returnstate = returnstate == null ? null : returnstate.trim();
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi == null ? null : imsi.trim();
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid == null ? null : iccid.trim();
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Boolean getInlineProcess() {
        return inlineProcess;
    }

    public void setInlineProcess(Boolean inlineProcess) {
        this.inlineProcess = inlineProcess;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl == null ? null : notificationUrl.trim();
    }

    public String getSubscriberPolicies() {
        return subscriberPolicies;
    }

    public void setSubscriberPolicies(String subscriberPolicies) {
        this.subscriberPolicies = subscriberPolicies == null ? null : subscriberPolicies.trim();
    }

    public String getAdditionalPolicies() {
        return additionalPolicies;
    }

    public void setAdditionalPolicies(String additionalPolicies) {
        this.additionalPolicies = additionalPolicies == null ? null : additionalPolicies.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getColumn11() {
        return column11;
    }

    public void setColumn11(String column11) {
        this.column11 = column11 == null ? null : column11.trim();
    }

	@Override
	public String toString() {
		return "FlowOrderRecordDto [serialnumber=" + serialnumber + ", returnstate=" + returnstate + ", imsi=" + imsi
				+ ", iccid=" + iccid + ", effectiveDate=" + effectiveDate + ", inlineProcess=" + inlineProcess
				+ ", notificationUrl=" + notificationUrl + ", subscriberPolicies=" + subscriberPolicies
				+ ", additionalPolicies=" + additionalPolicies + ", createTime=" + createTime + ", column11=" + column11
				+ "]";
	}
    
    
}