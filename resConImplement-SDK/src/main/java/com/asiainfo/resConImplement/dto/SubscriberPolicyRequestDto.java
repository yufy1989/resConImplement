package com.asiainfo.resConImplement.dto;

import java.io.Serializable;
import java.util.List;

/**
* 类说明：流量订购-传入参数
* @author pankx
* @date 2016年7月25日 下午1:52:22
*/
public class SubscriberPolicyRequestDto implements Serializable{
	
	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/
	private static final long serialVersionUID = 1L;

	private String imsi; //必须传
	
	private String iccid; 
	
	private String effectiveDate; //生效日期
	
	private Boolean inlineProcess;// //默认传true
	
	private String notificationURL; //
	
	private List<SubscriberPolicyTypeDto> subscriberPolicies; 

	private List<AdditionalPolicyTypeDto> additionalPolicies; 
	
	//调用接口名称
	private String callInterfaceName;
	
	
	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
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

	public String getNotificationURL() {
		return notificationURL;
	}

	public void setNotificationURL(String notificationURL) {
		this.notificationURL = notificationURL;
	}

	public List<SubscriberPolicyTypeDto> getSubscriberPolicies() {
		return subscriberPolicies;
	}

	public void setSubscriberPolicies(List<SubscriberPolicyTypeDto> subscriberPolicies) {
		this.subscriberPolicies = subscriberPolicies;
	}

	public List<AdditionalPolicyTypeDto> getAdditionalPolicies() {
		return additionalPolicies;
	}

	public void setAdditionalPolicies(List<AdditionalPolicyTypeDto> additionalPolicies) {
		this.additionalPolicies = additionalPolicies;
	}
	
	

	public String getCallInterfaceName() {
		return callInterfaceName;
	}

	public void setCallInterfaceName(String callInterfaceName) {
		this.callInterfaceName = callInterfaceName;
	}

	@Override
	public String toString() {
		return "SubscriberPolicyRequestDto [imsi=" + imsi + ", iccid=" + iccid + ", effectiveDate=" + effectiveDate
				+ ", inlineProcess=" + inlineProcess + ", notificationURL=" + notificationURL + ", subscriberPolicies="
				+ subscriberPolicies + ", additionalPolicies=" + additionalPolicies + ", callInterfaceName="
				+ callInterfaceName + "]";
	}



}
