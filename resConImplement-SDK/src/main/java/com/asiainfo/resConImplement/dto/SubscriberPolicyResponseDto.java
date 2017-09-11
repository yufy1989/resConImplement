package com.asiainfo.resConImplement.dto;
/**
* 类说明：流量订购-返回值
* @author pankx
* @date 2016年7月25日 下午4:17:23
*/
public class SubscriberPolicyResponseDto extends BaseDto{

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/
	private static final long serialVersionUID = 1L;

	private String iccid;
	
	private String imsi;
	
	private String status;
	
	

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SubscriberPolicyResponseDto [iccid=" + iccid + ", imsi=" + imsi + ", status=" + status + "]";
	}
	
}
