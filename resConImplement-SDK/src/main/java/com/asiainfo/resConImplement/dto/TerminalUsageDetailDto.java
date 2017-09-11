package com.asiainfo.resConImplement.dto;

public class TerminalUsageDetailDto {

	private Integer usage;
	
	private String serviceType;
	
	private Integer left;
	
	private String queryTime;
	
	public Integer getUsage() {
		return usage;
	}

	public void setUsage(Integer usage) {
		this.usage = usage;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getLeft() {
		return left;
	}

	public void setLeft(Integer left) {
		this.left = left;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	
	@Override
	public String toString() {
		return "usageDetail {usage="+usage+",serviceType="+serviceType+",left="+left+",queryTime="+queryTime+"}";
	}
}
