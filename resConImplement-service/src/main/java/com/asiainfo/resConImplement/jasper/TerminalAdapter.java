package com.asiainfo.resConImplement.jasper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.asiainfo.resConImplement.common.util.ConversionUtil;
import com.asiainfo.resConImplement.dto.EditTerminalRequestParamGroupDto;
import com.asiainfo.resConImplement.dto.EditTerminalResponseParamGroupDto;
import com.asiainfo.resConImplement.dto.RatingDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.TerminalDto;
import com.asiainfo.resConImplement.dto.TransferSimsToAccountRequestDto;
import com.asiainfo.resConImplement.dto.TransferSimsToAccountResponseDto;
import com.asiainfo.resConImplement.dto.simTransferToAccountStatusListDto;
import com.asiainfo.resConImplement.mappings.JasperMethod;
import com.asiainfo.resConImplement.mappings.JasperService;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
 * 功能描述：查询更新SIM卡资费计划
 * @author zhaoxy9
 * @date 2016年7月25日
 */
@Service
@JasperService("terminal")
public class TerminalAdapter extends BaseAdapter {
	private static Logger logger = LoggerFactory.getLogger(TerminalAdapter.class);
	/**
	 * 功能描述：查询SIM卡资费计划
	 * @author zhaoxy9
	 * @date 2016年7月25日 下午3:53:22
	 * @param @param iccId
	 * @param @param messageId
	 * @param @return 
	 * @return NetworkAccessConfigDto
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@JasperMethod(method = "GetTerminalDetails", requestEl = "GetTerminalDetailsRequest", responseEl = "GetTerminalDetailsResponse")
	public ResultDto GetTerminalDetails(String iccId, String messageId, String token) throws Exception {
		
		ResultDto<TerminalDto> resultDto = new ResultDto<TerminalDto>();
		// 0. 获取配置
		MethodConfig config = methodConfig.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		String methodName = config.getMethod();
		String requestEl = config.getRequestEl();
		String responseEl = config.getResponseEl();

		try {
			// 1. 创建请求报文
			SOAPMessage request = super.createSoapMessage(messageId, getSoapAction(service, methodName,true), requestEl, token);

			// 2. 在报文中增加iccid 节点
			SOAPEnvelope envelope = request.getSOAPPart().getEnvelope();
			SOAPBody soapBody = request.getSOAPBody();
			String schema = getSchema();
			Name terminalRequestName = envelope.createName(requestEl, prefix, schema);
			Iterator<?> iterator = soapBody.getChildElements(terminalRequestName);
			if (iterator.hasNext()) {
				SOAPBodyElement terminalRequestElement = ((SOAPBodyElement) iterator.next());
				Name iccidName = envelope.createName("iccids", prefix, schema);
				Name iccidName1 = envelope.createName("iccid", prefix, schema);
				SOAPElement iccidElement = terminalRequestElement.addChildElement(iccidName);
				iccidElement.addChildElement(iccidName1).setValue(iccId);;
			}
		
			// 3. 在报文中增加安全信息
			request = super.secureMessage(request, token);
			logger.info("**********************request:{}",getSoapMessage2String(request));

			// 4. 调用 Jasper 并返回响应报文
			SOAPMessage response = super.callJasper(request);
			logger.info("*********************Response:{}",getSoapMessage2String(response));
			if (response == null) {
				resultDto.setCode("0000001");
				resultDto.setMessage("jasper返回response为空");
			}else if (!response.getSOAPBody().hasFault()) {
				resultDto.setCode("0000000");
				resultDto.setMessage("success");
				// 5. 解析响应报文并返回
				 resolveGetTerminalDetailsConfigResponse(response, responseEl,resultDto);
			} else {
				
				resultDto.setHead(super.buildFaultMessage(response));
			}
		} catch (Exception e) {
			resultDto.setCode("0000001");
			resultDto.setMessage(e.getMessage());
			logger.error("---error---{}",Exceptions.getStackTraceAsString(e));
		}
		return resultDto;
	}
	
	/**
	 * 功能描述：取得查询SIM卡资费计划返回报文
	 * @author zhaoxy9
	 * @date 2016年7月25日 下午7:25:12
	 * @param @param message
	 * @param @param responseEl
	 * @param @return
	 * @param @throws SOAPException 
	 * @return EditTerminalResponseParamGroupDto
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void resolveGetTerminalDetailsConfigResponse(SOAPMessage message, String responseEl,ResultDto resultDto)
			throws SOAPException {

		// 1. 返回对象
		TerminalDto dto = new TerminalDto();

		// 2. 获取报文信封
		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
		String schema = getSchema();

		// 3. 获取响应节点
		Name terminalResponseName = envelope.createName(responseEl, prefix, schema);
		SOAPBodyElement terminalResponseElement = (SOAPBodyElement) message.getSOAPBody()
				.getChildElements(terminalResponseName).next();

		/**
		 * 4. 获取报文中基本的属性 放到 dto 中
		 */
		super.buildBase(message, resultDto, terminalResponseElement);

		// 5. 获取具体报文中的其他属性
		Name terminals = envelope.createName("terminals", prefix, schema);
		Name terminal = envelope.createName("terminal", prefix, schema);
		Name rating = envelope.createName("rating", prefix, schema);
		SOAPBodyElement terminalsElement = (SOAPBodyElement) terminalResponseElement.getChildElements(terminals).next();
		SOAPBodyElement itr = (SOAPBodyElement)terminalsElement.getChildElements(terminal).next();
		String msisdn=itr.getAttribute("msisdn");
		NodeList list = itr.getChildNodes();
		Map<String,Object> map=new HashMap<String, Object>();
			map.put("msisdn", msisdn);
			for (int i = 0; i < list.getLength(); i++) 
			{
				Node n = list.item(i);
				String name = n.getLocalName();
				String value = n.getTextContent();
				map.put(name, value);
			}
		SOAPBodyElement ratingElement = (SOAPBodyElement)itr.getChildElements(rating).next();
		NodeList ratingList = ratingElement.getChildNodes();
		Map<String,Object> ratingMap=new HashMap<String, Object>();
			for (int i = 0; i < ratingList.getLength(); i++) 
			{
				Node n = ratingList.item(i);
				String name = n.getLocalName();
				String value = n.getTextContent();
				ratingMap.put(name, value);
			}
		dto=(TerminalDto)ConversionUtil.map2dto(map, TerminalDto.class);
		RatingDto ratingDto=(RatingDto)ConversionUtil.map2dto(ratingMap, RatingDto.class);
		dto.setRatingDto(ratingDto);
		System.out.println(dto.toString());
		resultDto.setBody(dto);

	}
	
	/**
	 * 功能描述：更新SIM卡资费计划
	 * @author zhaoxy9
	 * @date 2016年7月25日 下午3:53:22
	 * @param @param iccId
	 * @param @param messageId
	 * @param @return 
	 * @return NetworkAccessConfigDto
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@JasperMethod(method = "EditTerminal", requestEl = "EditTerminalRequest", responseEl = "EditTerminalResponse")
	public ResultDto EditTerminal(EditTerminalRequestParamGroupDto dto, String messageId, String token) throws Exception {
		ResultDto<EditTerminalResponseParamGroupDto> resultDto = new ResultDto<EditTerminalResponseParamGroupDto>();
		// 0. 获取配置
		MethodConfig config = methodConfig.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		String methodName = config.getMethod();
		String requestEl = config.getRequestEl();
		String responseEl = config.getResponseEl();

		try {
			// 1. 创建请求报文
			SOAPMessage request = super.createSoapMessage(messageId, getSoapAction(service, methodName,true), requestEl, token);

			// 2. 在报文中增加iccid 节点
			super.addElement2Message(request, requestEl, "iccid", dto.getIccId());
			if (dto.getEffectiveDate()!=null&&!"".equals(dto.getEffectiveDate().trim())) {
				super.addElement2Message(request, requestEl, "effectiveDate", dto.getEffectiveDate().toString());
			}
			if (dto.getTargetValue()!=null&&!"".equals(dto.getTargetValue().trim())) {
				super.addElement2Message(request, requestEl, "targetValue", dto.getTargetValue());
			}
			super.addElement2Message(request, requestEl, "changeType", Integer.toString(dto.getChangeType()));
			// 3. 在报文中增加安全信息
			request = super.secureMessage(request, token);
			logger.info("************************Request:{}",getSoapMessage2String(request));

			// 4. 调用 Jasper 并返回响应报文
			SOAPMessage response = super.callJasper(request);
			logger.info("***********************response:{}",getSoapMessage2String(response));
			if (response == null) {
				resultDto.setCode("0000001");
				resultDto.setMessage("jasper返回response为空");
				return resultDto;
			}else if (!response.getSOAPBody().hasFault()) {
				resultDto.setCode("0000000");
				resultDto.setMessage("success");
				// 5. 解析响应报文并返回
				resolveEditTerminalConfigResponse(response, responseEl,resultDto);
			} else {
				resultDto.setHead(super.buildFaultMessage(response));
			}
		
		}catch (Exception e) {
			resultDto.setCode("0000001");
			resultDto.setMessage(e.getMessage());
			logger.error("---error---{}",Exceptions.getStackTraceAsString(e));
		}
		logger.info("^^^^^^^^^^变更结果:{}",resultDto.getHead().getMessage());
		return resultDto;
	}

	/**
	 * 功能描述：取得更新SIM卡资费计划返回报文
	 * @author zhaoxy9
	 * @date 2016年7月25日 下午7:25:12
	 * @param @param message
	 * @param @param responseEl
	 * @param @return
	 * @param @throws SOAPException 
	 * @return EditTerminalResponseParamGroupDto
	 */
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	private void resolveEditTerminalConfigResponse(SOAPMessage message, String responseEl,ResultDto resultDto)
			throws SOAPException {

		// 1. 返回对象
		EditTerminalResponseParamGroupDto dto = new EditTerminalResponseParamGroupDto();

		// 2. 获取报文信封
		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
		String schema = getSchema();

		// 3. 获取响应节点
		Name terminalResponseName = envelope.createName(responseEl, prefix, schema);
		SOAPBodyElement terminalResponseElement = (SOAPBodyElement) message.getSOAPBody()
				.getChildElements(terminalResponseName).next();

		/**
		 * 4. 获取报文中基本的属性 放到 dto 中
		 */
		super.buildBase(message, resultDto, terminalResponseElement);

		// 5. 获取具体报文中的其他属性
		Name iccid = envelope.createName("iccid", prefix, schema);
		Name effectiveDate = envelope.createName("effectiveDate", prefix, schema);
		SOAPBodyElement iccidElement = (SOAPBodyElement) terminalResponseElement.getChildElements(iccid).next();
		SOAPBodyElement effectiveDateElement = (SOAPBodyElement) terminalResponseElement.getChildElements(effectiveDate).next();
		dto.setIccId(iccidElement.getTextContent());
		//dto.setEffectiveDate(new Date(effectiveDateElement.getTextContent()));
		resultDto.setBody(dto);

	}

	/**
	 * 功能描述：子账户迁移
	 * @author zhaoxy9
	 * @date 2016年8月8日 上午10:50:50
	 * @param @param iccId
	 * @param @param messageId
	 * @param @return 
	 * @return ResultDto
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@JasperMethod(method = "TransferSimsToAccount", requestEl = "TransferSimsToAccountRequest", responseEl = "TransferSimsToAccountResponse")
	public ResultDto TransferSimstoAccount(TransferSimsToAccountRequestDto dto, String messageId, String token) throws Exception {
		
		ResultDto<simTransferToAccountStatusListDto> resultDto = new ResultDto<simTransferToAccountStatusListDto>();
		// 0. 获取配置
		MethodConfig config = methodConfig.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		String methodName = config.getMethod();
		String requestEl = config.getRequestEl();
		String responseEl = config.getResponseEl();

		try {
			// 1. 创建请求报文
			SOAPMessage request = super.createSoapMessage(messageId, getSoapAction(service, methodName,true), requestEl, token);

			// 2. 在报文中增加iccid 节点
			SOAPEnvelope envelope = request.getSOAPPart().getEnvelope();
			SOAPBody soapBody = request.getSOAPBody();
			String schema = getSchema();
			Name terminalRequestName = envelope.createName(requestEl, prefix, schema);
			Iterator<?> iterator = soapBody.getChildElements(terminalRequestName);
			if (iterator.hasNext()) {
				SOAPBodyElement terminalRequestElement = ((SOAPBodyElement) iterator.next());
				Name iccidList = envelope.createName("iccidList", prefix, schema);
				Name iccid = envelope.createName("iccid", prefix, schema);
				Name accountId = envelope.createName("accountId", prefix, schema);
				Name ratePlanName = envelope.createName("ratePlanName", prefix, schema);
				Name commPlanName = envelope.createName("commPlanName", prefix, schema);
				SOAPElement iccidElement = terminalRequestElement.addChildElement(iccidList);
				if (!"".equals(dto.getAccountId())) {
					terminalRequestElement.addChildElement(accountId).setValue(dto.getAccountId());;
				}
				if (!"".equals(dto.getRatePlanName())) {
					terminalRequestElement.addChildElement(ratePlanName).setValue(dto.getRatePlanName());;
				}
				if (!"".equals(dto.getCommPlanName())) {
					terminalRequestElement.addChildElement(commPlanName).setValue(dto.getCommPlanName());
				}
				for (int i = 0; i < dto.getIccidList().size(); i++) {
					iccidElement.addChildElement(iccid).setValue(dto.getIccidList().get(i));;
				}
			}
			// 3. 在报文中增加安全信息
			request = super.secureMessage(request, token);
			logger.info("&&&&&&&&&&&&&&&&&Request:{}",getSoapMessage2String(request));

			// 4. 调用 Jasper 并返回响应报文
			SOAPMessage response = super.callJasper(request);
			logger.info("****************************Response:{}",getSoapMessage2String(response));
			if (response == null) {
				resultDto.setCode("0000001");
				resultDto.setMessage("jasper返回response为空");
				return resultDto;
			}else if(!response.getSOAPBody().hasFault()) {
				resultDto.setCode("0000000");
				resultDto.setMessage("success");
				// 5. 解析响应报文并返回
				 resolveTransferSimstoAccountConfigResponse(response, responseEl,resultDto);
			} else {
				
				resultDto.setHead(super.buildFaultMessage(response));
			}
		}catch (Exception e) {
			resultDto.setCode("0000001");
			resultDto.setMessage(e.getMessage());
			logger.error("---error---{}",Exceptions.getStackTraceAsString(e));
		}
		return resultDto;
	}
	
	/**
	 * 功能描述：解析变更子账户报文
	 * @author zhaoxy9
	 * @date 2016年8月8日 上午11:25:47
	 * @param @param message
	 * @param @param responseEl
	 * @param @param resultDto
	 * @param @throws SOAPException 
	 * @return void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void resolveTransferSimstoAccountConfigResponse(SOAPMessage message, String responseEl,ResultDto resultDto)
			throws SOAPException {
		simTransferToAccountStatusListDto statusListDto = new simTransferToAccountStatusListDto();
		// 1. 返回对象
		TransferSimsToAccountResponseDto dto = new TransferSimsToAccountResponseDto();
		List<TransferSimsToAccountResponseDto> dtoList = new ArrayList<TransferSimsToAccountResponseDto>();
		// 2. 获取报文信封
		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
		String schema = getSchema();

		// 3. 获取响应节点
		Name terminalResponseName = envelope.createName(responseEl, prefix, schema);
		SOAPBodyElement terminalResponseElement = (SOAPBodyElement) message.getSOAPBody()
				.getChildElements(terminalResponseName).next();

		/**
		 * 4. 获取报文中基本的属性 放到 dto 中
		 */
		super.buildBase(message, resultDto, terminalResponseElement);

		// 5. 获取具体报文中的其他属性
		Name terminals = envelope.createName("simTransferToAccountStatusList", prefix, schema);
		Name terminal = envelope.createName("simtransfertoaccountstatus", prefix, schema);
		SOAPBodyElement terminalsElement = (SOAPBodyElement) terminalResponseElement.getChildElements(terminals).next();
		SOAPBodyElement itr = (SOAPBodyElement)terminalsElement.getChildElements(terminal).next();
		NodeList list = itr.getChildNodes();
		Map<String,Object> map=new HashMap<String, Object>();
			for (int i = 0; i < list.getLength(); i++) 
			{
				Node n = list.item(i);
				String name = n.getLocalName();
				String value = n.getTextContent();
				map.put(name, value);
				dto=(TransferSimsToAccountResponseDto)ConversionUtil.map2dto(map, TransferSimsToAccountResponseDto.class);
				dtoList.add(dto);
			}
		statusListDto.setTransferSimsToAccountResponseDtos(dtoList);
		if (!"true".equals(dto.getTransferstatus())) {
			resultDto.setCode(dto.getTransferstatus());
			resultDto.setMessage(dto.getErrormessage());
		}
		logger.info("^^^^^^^^^^变更结果:{}",dto.getTransferstatus());
		resultDto.setBody(statusListDto);
	}
}
