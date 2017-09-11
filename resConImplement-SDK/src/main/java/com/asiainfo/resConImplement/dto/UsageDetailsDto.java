package com.asiainfo.resConImplement.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：获取流量查询传输对象
 * 
 * @author chencq
 * @date 2016年7月25日 下午15:58:08
 */
public class UsageDetailsDto extends BaseDto {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 578633331130542073L;
	
	private List<DataUsageDetailDto> usageDetails = new ArrayList<>();
	
	private int totalPages;

	public void addUsageDetail(DataUsageDetailDto dto) {
		usageDetails.add(dto);
	}
	
	public List<DataUsageDetailDto> getUsageDetails() {
		return usageDetails;
	}

	public void setUsageDetails(List<DataUsageDetailDto> usageDetails) {
		this.usageDetails = usageDetails;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	@Override
	public String toString() {
		StringBuffer objStr = new StringBuffer();
		for (DataUsageDetailDto dataUsageDetailDto : usageDetails) {
			objStr.append(dataUsageDetailDto.toString());
		}
		return "usageDetails=[" + objStr.toString() + "],totalPages=" + totalPages + "]";
	}
}
