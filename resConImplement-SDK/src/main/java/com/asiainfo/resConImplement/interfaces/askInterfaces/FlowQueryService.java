package com.asiainfo.resConImplement.interfaces.askInterfaces;

import com.asiainfo.resConImplement.dto.FlowQueryDto;
import com.asiainfo.resConImplement.dto.PolicyQuotaDto;
import com.asiainfo.resConImplement.dto.ResultDto;

/**
 * 类说明：流量查询接口
 * @author chencq
 * @date 2016年7月29日下午2:14:36
 */
public interface FlowQueryService {

	/**
	 * @Title: flowQuery
	 * @Description: 流量查询
	 * @return ResultDto<FlowQueryDto>
	 * @Date 2016年7月29日 下午2:14:16
	 */
	public ResultDto<FlowQueryDto> flowQuery(String imsi,String iccid, String cycleStartDate, int pageNumber, String messageId,String askinterface,String token);
	
	/**
	 * 功能描述：
	 * @author baomz
	 * @date 2017年6月2日 下午5:11:08
	 * @param @param imsi
	 * @param @param messageId
	 * @param @param askinterface
	 * @param @param token
	 * @param @return 
	 * @return ResultDto<PolicyQuotaDto>
	 */
	ResultDto<PolicyQuotaDto> getPolicyQuotaInfo(String iccid, String imsi, String messageId, String askinterface, String token);
}
 