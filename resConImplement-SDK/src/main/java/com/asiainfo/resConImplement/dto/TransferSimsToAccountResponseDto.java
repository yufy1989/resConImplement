package com.asiainfo.resConImplement.dto;

import java.io.Serializable;

/**
 * 类说明：
 * @author Administrator
 * @date 2016年8月5日 上午11:51:30
 */
public class TransferSimsToAccountResponseDto  implements Serializable{
	
	private static final long serialVersionUID = -5954949201924401581L;
	private String iccid;
	private String transferstatus;
	private String errormessage;
	private String lax;
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getTransferstatus() {
		return transferstatus;
	}
	public void setTransferstatus(String transferstatus) {
		this.transferstatus = transferstatus;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	public String getLax() {
		return lax;
	}
	public void setLax(String lax) {
		this.lax = lax;
	}
	@Override
	public String toString() {
		return "SimTransferToAccountStatusTypeDto [iccid=" + iccid
				+ ", transferstatus=" + transferstatus + ", errormessage="
				+ errormessage + ", lax=" + lax + "]";
	}

	
}
