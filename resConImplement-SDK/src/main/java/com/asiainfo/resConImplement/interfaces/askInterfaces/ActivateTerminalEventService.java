package com.asiainfo.resConImplement.interfaces.askInterfaces;

import com.asiainfo.resConImplement.dto.ActivateTerminalEventRequestDto;
import com.asiainfo.resConImplement.dto.ResultDto;

/**
* 类说明：
* @author baomz
* @date 2017年6月2日 下午5:13:43
*/
public interface ActivateTerminalEventService {

	/**
	 * 功能描述：
	 * @author baomz
	 * @date 2017年6月2日 下午5:15:47
	 * @param @param dto
	 * @param @param interfaceName
	 * @param @param messageId
	 * @param @param token
	 * @param @return 
	 * @return ResultDto<?>
	 */
	ResultDto<?> activateTerminalEvent(ActivateTerminalEventRequestDto dto, String interfaceName, String messageId,String token);
}
