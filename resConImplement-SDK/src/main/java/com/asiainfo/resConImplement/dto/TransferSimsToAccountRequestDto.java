package com.asiainfo.resConImplement.dto;

import java.util.List;

/**
 * 类说明：账户组的子账户之间迁移入参
 * @author zhaoxy9
 * @date 2016年8月5日 上午11:48:06
 */
public class TransferSimsToAccountRequestDto extends BaseDto{
	private  List<String> iccidList;//卡号集合
	private  String accountId;//账户ID
	private  String ratePlanName;//资费计划名称
	private  String commPlanName;//通信计划名称
	public List<String> getIccidList() {
		return iccidList;
	}
	public void setIccidList(List<String> iccidList) {
		this.iccidList = iccidList;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getRatePlanName() {
		return ratePlanName;
	}
	public void setRatePlanName(String ratePlanName) {
		this.ratePlanName = ratePlanName;
	}
	public String getCommPlanName() {
		return commPlanName;
	}
	public void setCommPlanName(String commPlanName) {
		this.commPlanName = commPlanName;
	}
	@Override
	public String toString() {
		return "TransferSimsToAccountRequestDto [iccidList=" + iccidList
				+ ", accountId=" + accountId + ", ratePlanName=" + ratePlanName
				+ ", commPlanName=" + commPlanName + "]";
	}
	
	
}
