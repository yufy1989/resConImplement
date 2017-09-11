package com.asiainfo.resConImplement.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：获取网路配置传输对象
 * 
 * @author Baomz
 * @date 2016年7月21日 上午11:34:08
 */
public class NetworkAccessConfigDto extends BaseDto {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -8516644615686450793L;

	/**
	 * nacId 列表
	 */
	private List<NacIdTypeDto> nacIds = new ArrayList<NacIdTypeDto>();
	
	

	/**
	 * iccId
	 */
	private String iccId;

	public List<NacIdTypeDto> getNacIds() {
		return nacIds;
	}

	public void setNacIds(List<NacIdTypeDto> nacIds) {
		this.nacIds = nacIds;
	}

	public String getIccId() {
		return iccId;
	}

	public void setIccId(String iccId) {
		this.iccId = iccId;
	}

	public void addNacId(NacIdTypeDto nacId) {
		nacIds.add(nacId);
	}

	@Override
	public String toString() {
		return "NetworkAccessConfigDto [nacIds=" + nacIds + ", iccId=" + iccId + "]";
	}
	
}
