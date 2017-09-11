package com.asiainfo.resConImplement.interfaces.askInterfaces;

import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.UsageDetailsDto;

/**
 * 类说明：单卡不同区域的用量查询服务 
 * @author chencq
 * @date 2016年8月18日下午2:22:55
 */
public interface TerminalUsageDataDetailService {

	/**
	 * @Title: GetTerminalUsageDataDetails
	 * @Description: 单卡不同区域的用量查询
	 * @return ResultDto<UsageDetailsDto>
	 * @Date 2016年8月18日 下午2:23:33
	 */
	ResultDto<UsageDetailsDto> GetTerminalUsageDataDetails(String iccid, String cycleStartDate,int pageNumber,String messageId,String askinterface,String token);
}
