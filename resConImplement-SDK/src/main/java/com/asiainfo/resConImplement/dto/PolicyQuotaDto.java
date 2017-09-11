package com.asiainfo.resConImplement.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：获取流量查询传输对象
 * 
 * @author chencq
 * @date 2016年7月25日 下午15:58:08
 */
public class PolicyQuotaDto extends BaseDto {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 4589081932642391212L;
	
	private List<QuotaDto> quotaInfo = new ArrayList<>();
	
	public List<QuotaDto> getQuotaInfo() {
		return quotaInfo;
	}

	public void setQuotaInfo(List<QuotaDto> quotaInfo) {
		this.quotaInfo = quotaInfo;
	}
	
	public void addQuota(QuotaDto quota) {
		quotaInfo.add(quota);
	}
	
	@Override
	public String toString() {
		return "PolicyQuotaDto [quotaInfo=" + quotaInfo + "]";
	}
}
