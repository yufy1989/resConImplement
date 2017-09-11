package com.asiainfo.resConImplement.service.Impl;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.asiainfo.resConImplement.common.MsgHeader;
import com.asiainfo.resConImplement.common.ToolUtils;
import com.asiainfo.resConImplement.dto.EditNetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.NetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.module.EditNetworkAccessConfigBody;
import com.asiainfo.resConImplement.module.NetworkAccessConfigBody;
import com.asiainfo.resConImplement.service.NetworkAccessSer;

/**
 * 设备通信方案ID查询实现
 * 
 * @author zhenhw
 *
 */
public class NetworkAccessSerImpl implements NetworkAccessSer {
	private static Logger logger = LoggerFactory.getLogger(NetworkAccessSerImpl.class);

//	@Autowired
//	private NetworkAccessConfig networkAccess;
	@Resource
	private WebServiceContext context;
	// TODO 数据库日志记录

	@Override
	public ResultDto<NetworkAccessConfigDto> getNetworkAccessConfig(MsgHeader header, NetworkAccessConfigBody body) {
		logger.debug("NetworkAccessSerImpl:getNetworkAccessConfigReq:header:{},body:{}", header.toString(),
				body.toString());
		// TODO 数据库日志记录

		ResultDto<NetworkAccessConfigDto> na =null;// networkAccess.getNetworkAccessConfig(body.getIccId(),
//				header.getMessageId());
		logger.debug("NetworkAccessSerImpl:getNetworkAccessConfigResp:{}" + na.toString());
		// na.getHead().getCode();
		// na.getHead().getMessage();
		// 获取请求ip
		String ip = ToolUtils.getIP(context);
		logger.debug("IP:" + ip);
		// TODO 数据库日志记录
		return na;
	}

	@Override
	public ResultDto<EditNetworkAccessConfigDto> editNetworkAccessConfig(MsgHeader header,
			EditNetworkAccessConfigBody body) {
		ResultDto<EditNetworkAccessConfigDto> editNetworkAccessConfigResult = null;//networkAccess.editNetworkAccessConfig(body.getIccId(), body.getNacId(), "", header.getMessageId());
		return  editNetworkAccessConfigResult;
	}
}
