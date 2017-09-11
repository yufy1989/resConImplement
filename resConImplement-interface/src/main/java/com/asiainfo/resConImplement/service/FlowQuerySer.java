package com.asiainfo.resConImplement.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.asiainfo.resConImplement.common.MsgHeader;
import com.asiainfo.resConImplement.dto.FlowQueryDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.module.FlowQuotaBody;

/**
 * 流量查询接口
 * @author chencq
 * @date 2016年7月27日
 */
@WebService
public interface FlowQuerySer {
	public @WebResult(name = "flowQuerySer")ResultDto<FlowQueryDto> flowQuery(@WebParam(name = "msgHeader")MsgHeader header,@WebParam(name = "msgBody")FlowQuotaBody body);
}
