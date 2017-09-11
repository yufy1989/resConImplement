package com.asiainfo.resConImplement.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.asiainfo.resConImplement.common.MsgHeader;
import com.asiainfo.resConImplement.dto.EditTerminalResponseParamGroupDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.TerminalDto;
import com.asiainfo.resConImplement.module.TcuChangeBody;

/**
 * 功能描述：资费查询
 * @author zhaoxy9
 * @date 2016年8月3日
 */
@WebService
public interface GetTerminalDetailsSer {
	public @WebResult(name = "GetTerminalDetailsSer")ResultDto<TerminalDto> getTerminalDetails(@WebParam(name = "iccId")String iccId,@WebParam(name = "messageId")String messageId);
}
