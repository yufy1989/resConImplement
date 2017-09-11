package com.asiainfo.resConImplement.interfaces.askInterfaces;

import com.asiainfo.resConImplement.dto.EditTerminalRequestParamGroupDto;
import com.asiainfo.resConImplement.dto.EditTerminalResponseParamGroupDto;
import com.asiainfo.resConImplement.dto.ResultDto;

/**
* 类说明：
* @author Administrator
* @date 2016年8月29日 下午1:43:08
*/
public interface EditTerminalService {
	/**
	 * 功能描述：更新资费计划
	 * @author cuishuo
	 * @date 2016年8月17日 上午10:58:25
	 * @param @param dto 入参对象
	 * @param @param messageId 流水号
	 * @param @param interfaceName 调用资管管控平台服务的服务名称
	 * @param @return 
	 * @return ResultDto<EditTerminalRequestParamGroupDto>
	 */
	ResultDto<EditTerminalResponseParamGroupDto> EditTerminal(
			EditTerminalRequestParamGroupDto dto, String messageId,
			String interfaceName,String token);
}
