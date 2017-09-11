package com.asiainfo.resConImplement.interfaces.askInterfaces;

import java.util.Map;

import com.asiainfo.resConImplement.dto.EditTerminalResponseParamGroupDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.TerminalDto;

/**
 * 功能描述：查询卡资费信息状态
 * @author zhaoxy9
 * @date 2016年8月3日
 */
public interface GetTerminalDetailsService {

	/**
	 * 功能描述：查询卡资费信息
	 * @author zhaoxy9
	 * @date 2016年8月3日 下午6:02:34
	 * @param @param iccId 卡号
	 * @param @param messageId 流水号
	 * @param @return 
	 * @return ResultDto<TerminalDto>
	 */
	ResultDto<TerminalDto> getTerminalDetails(Map<String,String> map);
}
