package com.asiainfo.resConImplement.dto;

public class DataUsageDetailDto extends BaseDto {

	private static final long serialVersionUID = 1450367107172942329L;

	private String iccid;
	
	private String cycleStartDate;
	
	private String terminalId;
	
	private String endConsumerId;
	
	private String customer;
	
	private boolean billable;
	
	private String zone;
	
	private String sessionStartTime;
	
	private long duration;
	
	private Double dataVolume;
	
	private String countryCode;
	
	private String serviceType;
	
	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getCycleStartDate() {
		return cycleStartDate;
	}

	public void setCycleStartDate(String cycleStartDate) {
		this.cycleStartDate = cycleStartDate;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getEndConsumerId() {
		return endConsumerId;
	}

	public void setEndConsumerId(String endConsumerId) {
		this.endConsumerId = endConsumerId;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public boolean isBillable() {
		return billable;
	}

	public void setBillable(boolean billable) {
		this.billable = billable;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getSessionStartTime() {
		return sessionStartTime;
	}

	public void setSessionStartTime(String sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public Double getDataVolume() {
		return dataVolume;
	}

	public void setDataVolume(Double dataVolume) {
		this.dataVolume = dataVolume;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	@Override
	public String toString() {
		return "usageDetail {iccid="+iccid+",cycleStartDate="+cycleStartDate+",terminalId="+terminalId+",endConsumerId="+endConsumerId+",customer="+customer+",billable="+billable+",zone="+zone+",sessionStartTime="+sessionStartTime+",duration="+duration+",dataVolume="+dataVolume+",countryCode="+countryCode+",serviceType="+serviceType+"}";
	}
}
