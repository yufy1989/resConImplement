package com.asiainfo.resConImplement.service.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.resConImplement.common.MsgHeader;
import com.asiainfo.resConImplement.common.ToolUtils;
import com.asiainfo.resConImplement.common.util.DateUtil;
import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.dto.OrderFlowDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.OrderFlowService;
import com.asiainfo.resConImplement.interfaces.loggerInterfaces.InterAccessRecordService;
import com.asiainfo.resConImplement.module.OrderFlowBody;
import com.asiainfo.resConImplement.service.OrderFlowSer;

/**
 * 流量订购
 * 
 * @author Pankx
 *
 */

public class OrderFlowSerImpl implements OrderFlowSer {

	private static Logger logger = LoggerFactory.getLogger(OrderFlowSerImpl.class);

	@Resource
	private OrderFlowService orderFlowService;

	@Resource
	private InterAccessRecordService interAccessRecordService; // 接口请求记录

	@Resource
	private WebServiceContext context;

	/**
	 * 功能描述：流量订购
	 * 
	 * @author pankx
	 * @date 2016年7月27日 下午4:05:58
	 * @param @param
	 *            header
	 * @param @param
	 *            body
	 * @param @return
	 */
	@Override
	public ResultDto<OrderFlowDto> flowOrder(MsgHeader header, OrderFlowBody body) {
		logger.debug("OrderFlowSerImpl:flowOrderReq:header:{},body:{}", header.toString(), body.toString());

		InterAccessRecordDto interAccessRecordDto = makeInterA(header, body);
		// 订购记录
		ResultDto<OrderFlowDto> reDto = new ResultDto<OrderFlowDto>();
		interAccessRecordDto.setQuerytime(new Date()); //请求时间

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("interfaceName", "orderFlowSer");
		param.put("imsi", body.getImsi());
		param.put("iccid", body.getIccId());
		param.put("messageId", header.getMessageId());
		//reDto = orderFlowService.flowOrder(param);
		
		// 添加调用接口记录信息
		interAccessRecordDto.setQuerystate(reDto.getHead().getCode());
		interAccessRecordDto.setRespinfo(reDto.toString());
		interAccessRecordDto.setUpdatetime(new Date());
		interAccessRecordService.interAccessRecord(interAccessRecordDto);
		return reDto;
	}

	/**
	 * 功能描述：组装调用接口记录信息
	 * 
	 * @author pankx
	 * @date 2016年8月1日 上午10:09:01
	 * @param @return
	 * @return InterAccessRecordDto
	 */
	public InterAccessRecordDto makeInterA(MsgHeader header, OrderFlowBody body) {
		InterAccessRecordDto interA = new InterAccessRecordDto();
		// interA.setId(Integer.parseInt(SeqID.SYSUSER_ID.getIdSeq()));
		interA.setSerialnumber(header.getMessageId());
		interA.setIccid(body.getIccId());
		interA.setInterfacename("orderFlowSer");
		interA.setEventname("flowOrder");
		interA.setChannelcode(header.getChannel_Code());
		interA.setChannelname(header.getChannel_Code());
		interA.setInputparameter(body.toString());
		interA.setQueryip(ToolUtils.getIP(context));
		interA.setCallingparty(header.getPlatform_Code()); // 调用方平台
		interA.setPlatformcode("JASPER");
		interA.setQuerytime(DateUtil.parse(header.getReqTime(), "yyyy-MM-dd HH:mm:ss"));
		return interA;
	}
}
