package com.asiainfo.resConImplement.dto;

import java.util.Date;

/**
* 类说明：更新新SIM卡的通信计划ResponseDto
* @author yufy
* @date 2016年7月25日 下午4:43:29
*/
public class EditNetworkAccessConfigDto extends BaseDto{

	private static final long serialVersionUID = 6601674362908497014L;
	private String iccId;
	private Date effectiveDate;
	public String getIccId() {
		return iccId;
	}
	public void setIccId(String iccId) {
		this.iccId = iccId;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	@Override
	public String toString() {
		return "EditNetworkAccessConfigDto [iccId=" + iccId + ", effectiveDate=" + effectiveDate + "]";
	}
}
