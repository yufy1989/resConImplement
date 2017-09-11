package com.asiainfo.resConImplement.interfaces.askInterfaces;

import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.TerminalDto;

/**
 * 类说明：资费计划服务-接口
 * @author cuishuo
 * @date 2016年8月17日 上午10:48:20
 */
public interface TerminalService {
	
	/**
	 * 功能描述：查询资费计划
	 * @author cuishuo
	 * @date 2016年8月17日 上午10:57:01
	 * @param @param iccId
	 * @param @param messageId
	 * @param @param interfaceName
	 * @param @return 
	 * @return ResultDto<TerminalDto>
	 */
	ResultDto<TerminalDto> GetTerminalDetails(String iccId, String messageId,
			String interfaceName,String token);
}
