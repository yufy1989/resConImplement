package com.asiainfo.resConImplement.dto;
import java.io.Serializable;
import java.util.Date;

public class RatingDto implements Serializable  {
	
	
	
	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/
	private static final long serialVersionUID = -6782444883374060976L;
	private String termStartDate;
	private String termEndDate;
	private String renewalPolicy;
	private String renewalRatePlan;
	private String totalPrimaryIncludedData;
	private String primaryDataRemaining;
	private String totalPrimaryIncludedSMS;
	private String primarySMSRemaining;
	
	@Override
	public String toString() {
		return "Rating [termStartDate=" + termStartDate + ", termEndDate="
				+ termEndDate + ", renewalPolicy=" + renewalPolicy
				+ ", renewalRatePlan=" + renewalRatePlan
				+ ", totalPrimaryIncludedData=" + totalPrimaryIncludedData
				+ ", primaryDataRemaining=" + primaryDataRemaining
				+ ", totalPrimaryIncludedSMS=" + totalPrimaryIncludedSMS
				+ ", primarySMSRemaining=" + primarySMSRemaining + "]";
	}

	public String getTermStartDate() {
		return termStartDate;
	}

	public void setTermStartDate(String termStartDate) {
		this.termStartDate = termStartDate;
	}

	public String getTermEndDate() {
		return termEndDate;
	}

	public void setTermEndDate(String termEndDate) {
		this.termEndDate = termEndDate;
	}

	public String getRenewalPolicy() {
		return renewalPolicy;
	}

	public void setRenewalPolicy(String renewalPolicy) {
		this.renewalPolicy = renewalPolicy;
	}

	public String getRenewalRatePlan() {
		return renewalRatePlan;
	}

	public void setRenewalRatePlan(String renewalRatePlan) {
		this.renewalRatePlan = renewalRatePlan;
	}

	public String getTotalPrimaryIncludedData() {
		return totalPrimaryIncludedData;
	}

	public void setTotalPrimaryIncludedData(String totalPrimaryIncludedData) {
		this.totalPrimaryIncludedData = totalPrimaryIncludedData;
	}

	public String getPrimaryDataRemaining() {
		return primaryDataRemaining;
	}

	public void setPrimaryDataRemaining(String primaryDataRemaining) {
		this.primaryDataRemaining = primaryDataRemaining;
	}

	public String getTotalPrimaryIncludedSMS() {
		return totalPrimaryIncludedSMS;
	}

	public void setTotalPrimaryIncludedSMS(String totalPrimaryIncludedSMS) {
		this.totalPrimaryIncludedSMS = totalPrimaryIncludedSMS;
	}

	public String getPrimarySMSRemaining() {
		return primarySMSRemaining;
	}

	public void setPrimarySMSRemaining(String primarySMSRemaining) {
		this.primarySMSRemaining = primarySMSRemaining;
	}
	
	
}
