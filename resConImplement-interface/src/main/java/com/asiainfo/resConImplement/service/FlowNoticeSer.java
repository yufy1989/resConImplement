package com.asiainfo.resConImplement.service;

import javax.jws.WebParam;
import javax.jws.WebResult;

import com.asiainfo.resConImplement.common.MsgHeader;
import com.asiainfo.resConImplement.dto.FlowNoticeDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.module.FlowNoticeBody;

/**
* 类说明：流量通知相关服务
* @author Baomz
* @date 2016年7月27日 下午4:05:37
*/
public interface FlowNoticeSer {

	/**
	 * 功能描述：流量通知 push API
	 * @author Baomz
	 * @date 2016年7月27日 下午4:12:26
	 * @param @param header
	 * @param @param body
	 * @param @return 
	 * @return ResultDto<FlowNoticeDto>
	 */
	@WebResult(name = "getFlowNotice")
	ResultDto<FlowNoticeDto>  getFlowNotice(@WebParam(name = "msgHeader")MsgHeader header,@WebParam(name = "msgBody")FlowNoticeBody body);
	
}
