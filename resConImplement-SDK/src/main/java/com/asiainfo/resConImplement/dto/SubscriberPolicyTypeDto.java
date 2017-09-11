package com.asiainfo.resConImplement.dto;

import java.io.Serializable;

/**
* 类说明：chinese:有关用户策略的详细信息
* English:The detail information about Subscriber Policy
* @author pankx
* @date 2016年7月25日 下午4:25:27
*/
public class SubscriberPolicyTypeDto implements Serializable{
	

	
	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/
	private static final long serialVersionUID = 1L;
	private String apnName; // 
	private Integer streamId;//
	private String policyAction;// 
	
	
	public String getApnName() {
		return apnName;
	}
	public void setApnName(String apnName) {
		this.apnName = apnName;
	}
	public Integer getStreamId() {
		return streamId;
	}
	public void setStreamId(Integer streamId) {
		this.streamId = streamId;
	}
	public String getPolicyAction() {
		return policyAction;
	}
	public void setPolicyAction(String policyAction) {
		this.policyAction = policyAction;
	}
	
	@Override
	public String toString() {
		return "SubscriberPolicyTypeDto [apnName=" + apnName + ", streamId=" + streamId + ", policyAction="
				+ policyAction + "]";
	}
	
	
	
	
}
