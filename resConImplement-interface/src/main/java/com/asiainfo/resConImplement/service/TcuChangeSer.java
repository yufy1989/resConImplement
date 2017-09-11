package com.asiainfo.resConImplement.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.asiainfo.resConImplement.common.MsgHeader;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.TcuChangeDto;
import com.asiainfo.resConImplement.module.TcuChangeBody;

/**
 * 功能描述：TCU更换服务
 * @author yufy
 * @date 2016年7月27日
 */
@WebService
public interface TcuChangeSer {
	public @WebResult(name = "TcuChangeSer")ResultDto<TcuChangeDto> tcuChange(@WebParam(name = "msgHeader")MsgHeader header,@WebParam(name = "msgBody")TcuChangeBody body);
}
