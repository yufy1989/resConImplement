package com.asiainfo.resConImplement.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.asiainfo.resConImplement.common.MsgHeader;
import com.asiainfo.resConImplement.dto.EditNetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.NetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.module.EditNetworkAccessConfigBody;
import com.asiainfo.resConImplement.module.NetworkAccessConfigBody;

/**
 * 设备通信方案ID查询
 * @author zhenhw
 *
 */
@WebService
public interface NetworkAccessSer {

	public @WebResult(name = "networkAccessConfig")ResultDto<NetworkAccessConfigDto> getNetworkAccessConfig(@WebParam(name = "msgHeader")MsgHeader header,@WebParam(name = "msgBody")NetworkAccessConfigBody body);
	public @WebResult(name = "editonfig")ResultDto<EditNetworkAccessConfigDto> editNetworkAccessConfig(@WebParam(name = "msgHeader")MsgHeader header,@WebParam(name = "msgBody")EditNetworkAccessConfigBody body);
}
