package com.asiainfo.resConImplement.service.Impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.resConImplement.common.MsgHeader;
import com.asiainfo.resConImplement.common.ToolUtils;
import com.asiainfo.resConImplement.dto.EditTerminalResponseParamGroupDto;
import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.TcuChangeDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.TcuChangeService;
import com.asiainfo.resConImplement.interfaces.loggerInterfaces.InterAccessRecordService;
import com.asiainfo.resConImplement.module.TcuChangeBody;
import com.asiainfo.resConImplement.service.TcuChangeSer;
import com.google.common.collect.Maps;
/**
 * 功能描述：TCU更换服务
 * @author yufy
 * @date 2016年7月27日
 */
public class TcuChangeSerImpl implements TcuChangeSer {
	private static Logger logger = LoggerFactory.getLogger(TcuChangeSerImpl.class); 

	@Resource
	private TcuChangeService tcuChangeService;
	@Resource
	private WebServiceContext context;
	@Resource
	private  InterAccessRecordService interAccessRecordService; //接口请求记录

	/**
	 * 功能描述：TCU更换服务
	 * @author yufy
	 * @date 2016年7月27日 下午3:05:28
	 * @param @param header
	 * @param @param body
	 * @param @return
	 */
	@Override
	public ResultDto<TcuChangeDto> tcuChange(MsgHeader header,TcuChangeBody body) {
		logger.info("--开始--TcuChangeImpl:TcuChangeReq:header:{},body:{}",header.toString(),body.toString());
		//组装调用接口记录信息
		InterAccessRecordDto interAccessRecordDto=this.makeInterA(header,body);
		//调用TCU变更操作
		Map<String,String> map=Maps.newHashMap();
		map.put("oldIccId", body.getOldIccId());
		map.put("newIccId", body.getNewIccId());
		map.put("carPricesName",body.getCarPricesName());
		map.put("carModelsName", body.getCarModelsName());
		map.put("carName", body.getCarName());
		map.put("messageId", header.getMessageId());
		map.put("imsi", body.getImsi());
		
		ResultDto<TcuChangeDto> tcuChangeResult = tcuChangeService.tcuChange(map);
		logger.info("--结束--TcuChangeImpl:TcuChangeResp:{}" + tcuChangeResult.toString());
		interAccessRecordDto.setQuerystate(tcuChangeResult.getHead().getCode());
		interAccessRecordDto.setRespinfo(tcuChangeResult.toString());
		interAccessRecordDto.setUpdatetime(new Date());
		interAccessRecordService.interAccessRecord(interAccessRecordDto);
		return tcuChangeResult;
	}
	/**
	 * 功能描述：组装调用接口记录信息
	 * @author yufy
	 * @date 2016年8月1日 下午3:14:56
	 * @param @param body
	 * @param @return 
	 * @return InterAccessRecordDto
	 */
	private InterAccessRecordDto makeInterA(MsgHeader header,TcuChangeBody body){
		InterAccessRecordDto interA = new InterAccessRecordDto();
//		interA.setId(Integer.parseInt(SeqID.SYSUSER_ID.getIdSeq())); 
		interA.setSerialnumber(header.getMessageId());
		interA.setIccid(body.getOldIccId());
		interA.setInterfacename("TcuChangeSer");
		interA.setEventname("tcuChange");
		interA.setChannelcode(header.getChannel_Code());
		interA.setChannelname(header.getChannel_Code());
		interA.setInputparameter(body.toString());
		interA.setQueryip(ToolUtils.getIP(context));
		interA.setCallingparty(header.getPlatform_Code()); //调用方平台
		interA.setPlatformcode("JASPER");
		interA.setQuerytime(new Date());
		interA.setReserve1(body.getNewIccId());
		return interA;
	}
}
