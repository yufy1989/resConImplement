package com.asiainfo.resConImplement.module;

import java.io.Serializable;

/**
 * 功能描述：流量查询入参模型
 * @author chencq
 * @date 2016年7月27日
 */
public class FlowQuotaBody implements Serializable {
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 8240366861424356429L;

	private String imsi;
	
	private String iccid;
	
	private String cycleStartDate;
	
	private int pageNumber;

	public String getCycleStartDate() {
		return cycleStartDate;
	}

	public void setCycleStartDate(String cycleStartDate) {
		this.cycleStartDate = cycleStartDate;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

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

	@Override
	public String toString() {
		return "PolicyQuotaBody [imsi=" + imsi + ", iccid=" + iccid + ", cycleStartDate=" + cycleStartDate + ", pageNumber=" + pageNumber + "]";
	}
}
