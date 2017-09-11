package com.asiainfo.resConImplement.dto;

import java.math.BigDecimal;

/**
* 类说明：子账户资费
* @author pankx
* @date 2016年8月26日 下午2:40:35
*/
public class InvoiceDto extends BaseDto{
	
	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/
	private static final long serialVersionUID = 1L;

	private Long accountId; //子账户id
	
	private Long invoiceId; //
	
	private String currency;//货币
	
	private String invoiceDate;
	
	private String dueDate;
	
	private String cycleStartDate;
	
	private String cycleEndDate;
	
	private Long totalTerminals;//<!--设备数量 -->
	
	private BigDecimal dataVolume; //<!-- 流量 总消耗（M）-->
	
	private BigDecimal subscriptionCharge;//<!-- 套餐内费用 -->
	
	private BigDecimal overageCharge;//<!-- 超出费用 -->
	
	private BigDecimal totalCharge;//<!-- 总费用 -->
	
	private BigDecimal smsVolume;//<!-- 短信总条数（条） -->
	
	private BigDecimal smsCharge;
	
	private BigDecimal voiceVolume;
	
	private BigDecimal voiceCharge;
	
	private  BigDecimal otherCharge;

	private Long totalEvents;
	
	private  BigDecimal eventsCharge;
	
	private BigDecimal activationCharge;
	
	private BigDecimal discountApplied;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getCycleStartDate() {
		return cycleStartDate;
	}

	public void setCycleStartDate(String cycleStartDate) {
		this.cycleStartDate = cycleStartDate;
	}

	public String getCycleEndDate() {
		return cycleEndDate;
	}

	public void setCycleEndDate(String cycleEndDate) {
		this.cycleEndDate = cycleEndDate;
	}

	public Long getTotalTerminals() {
		return totalTerminals;
	}

	public void setTotalTerminals(Long totalTerminals) {
		this.totalTerminals = totalTerminals;
	}

	public BigDecimal getDataVolume() {
		return dataVolume;
	}

	public void setDataVolume(BigDecimal dataVolume) {
		this.dataVolume = dataVolume;
	}

	public BigDecimal getSubscriptionCharge() {
		return subscriptionCharge;
	}

	public void setSubscriptionCharge(BigDecimal subscriptionCharge) {
		this.subscriptionCharge = subscriptionCharge;
	}

	public BigDecimal getOverageCharge() {
		return overageCharge;
	}

	public void setOverageCharge(BigDecimal overageCharge) {
		this.overageCharge = overageCharge;
	}

	public BigDecimal getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}

	public BigDecimal getSmsVolume() {
		return smsVolume;
	}

	public void setSmsVolume(BigDecimal smsVolume) {
		this.smsVolume = smsVolume;
	}

	public BigDecimal getSmsCharge() {
		return smsCharge;
	}

	public void setSmsCharge(BigDecimal smsCharge) {
		this.smsCharge = smsCharge;
	}

	public BigDecimal getVoiceVolume() {
		return voiceVolume;
	}

	public void setVoiceVolume(BigDecimal voiceVolume) {
		this.voiceVolume = voiceVolume;
	}

	public BigDecimal getVoiceCharge() {
		return voiceCharge;
	}

	public void setVoiceCharge(BigDecimal voiceCharge) {
		this.voiceCharge = voiceCharge;
	}

	public BigDecimal getOtherCharge() {
		return otherCharge;
	}

	public void setOtherCharge(BigDecimal otherCharge) {
		this.otherCharge = otherCharge;
	}

	public Long getTotalEvents() {
		return totalEvents;
	}

	public void setTotalEvents(Long totalEvents) {
		this.totalEvents = totalEvents;
	}

	public BigDecimal getEventsCharge() {
		return eventsCharge;
	}

	public void setEventsCharge(BigDecimal eventsCharge) {
		this.eventsCharge = eventsCharge;
	}

	public BigDecimal getActivationCharge() {
		return activationCharge;
	}

	public void setActivationCharge(BigDecimal activationCharge) {
		this.activationCharge = activationCharge;
	}

	public BigDecimal getDiscountApplied() {
		return discountApplied;
	}

	public void setDiscountApplied(BigDecimal discountApplied) {
		this.discountApplied = discountApplied;
	}

	
	
	@Override
	public String toString() {
		return "InvoiceDto [accountId=" + accountId + ", invoiceId=" + invoiceId + ", currency=" + currency
				+ ", invoiceDate=" + invoiceDate + ", dueDate=" + dueDate + ", cycleStartDate=" + cycleStartDate
				+ ", cycleEndDate=" + cycleEndDate + ", totalTerminals=" + totalTerminals + ", dataVolume=" + dataVolume
				+ ", subscriptionCharge=" + subscriptionCharge + ", overageCharge=" + overageCharge + ", totalCharge="
				+ totalCharge + ", smsVolume=" + smsVolume + ", smsCharge=" + smsCharge + ", voiceVolume=" + voiceVolume
				+ ", voiceCharge=" + voiceCharge + ", otherCharge=" + otherCharge + ", totalEvents=" + totalEvents
				+ ", eventsCharge=" + eventsCharge + ", activationCharge=" + activationCharge + ", discountApplied="
				+ discountApplied + "]";
	}
	
	
}
