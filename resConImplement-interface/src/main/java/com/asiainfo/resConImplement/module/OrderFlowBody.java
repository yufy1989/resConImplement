package com.asiainfo.resConImplement.module;


/**
 * 功能描述：流量订购传入参数
 * @author pankx
 * @date 2016年7月27日
 */
public class OrderFlowBody {
	
	private String iccId;//卡号（正面贴标签的号，唯一标识）
	private String imsi; //卡号（卡芯里的唯一标识）
	private String spProductId;//sp产品ID
	
	
	public String getIccId() {
		return iccId;
	}

	public void setIccId(String iccId) {
		this.iccId = iccId;
	}
	
	
	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getSpProductId() {
		return spProductId;
	}

	public void setSpProductId(String spProductId) {
		this.spProductId = spProductId;
	}

	@Override
	public String toString() {
		return "OrderFlowBody [iccId=" + iccId + ", imsi=" + imsi + ", spProductId=" + spProductId + "]";
	}

	
}
