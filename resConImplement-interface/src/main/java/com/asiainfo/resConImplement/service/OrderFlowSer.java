package com.asiainfo.resConImplement.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.asiainfo.resConImplement.common.MsgHeader;
import com.asiainfo.resConImplement.dto.OrderFlowDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.module.OrderFlowBody;

/**
 * 设备通信方案ID查询
 * @author zhenhw
 *
 */
@WebService
public interface OrderFlowSer {

	public @WebResult(name = "orderFlowSer")ResultDto<OrderFlowDto> flowOrder(@WebParam(name = "msgHeader")MsgHeader header,@WebParam(name = "msgBody")OrderFlowBody body);

}
