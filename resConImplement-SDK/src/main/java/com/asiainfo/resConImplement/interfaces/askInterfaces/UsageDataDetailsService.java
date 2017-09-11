package com.asiainfo.resConImplement.interfaces.askInterfaces;

import java.util.Map;

/**
 * 类说明：计算指定区域下的用量服务
 * @author chencq
 * @date 2016年9月12日下午2:29:40
 */
public interface UsageDataDetailsService {
	/**
	 * @Title: getUsageDataDetails
	 * @Description: 根据服务类型计算该区域下的用量
	 * @return Map<String,Object>
	 * @Date 2016年9月12日 下午2:30:26
	 */
	public Map<String,Object> getUsageDataDetails(String iccid, String cycleStartDate, String serviceType, int pageNumber,String messageId,String askinterface,String token);
}
