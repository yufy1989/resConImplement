package com.asiainfo.resConImplement.dto;

/**
 * 类说明：流量对象
 * 
 * @author chencq
 * @date 2016年7月25日 下午15:58:48
 */
public class QuotaDto extends BaseDto {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -3207725865959313335L;
	
	private String policyName;
	
	private String quotaName;
	
	private String balance;
	
	private String expiryDate;

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getQuotaName() {
		return quotaName;
	}

	public void setQuotaName(String quotaName) {
		this.quotaName = quotaName;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	@Override
	public String toString() {
		return "QuotaDto [policyName=" + policyName + ", quotaName=" + quotaName + ", balance=" + balance + ", expiryDate=" + expiryDate + "]";
	}
}
