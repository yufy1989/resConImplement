package com.asiainfo.resConImplement.jasper;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.dto.ActivateTerminalEventRequestDto;
import com.asiainfo.resConImplement.dto.ActivateTerminalEventResponseDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.mappings.JasperMethod;
import com.asiainfo.resConImplement.mappings.JasperService;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
* 类说明：事件计划服务，目前有激活事件接口
* @author baomz
* @date 2017年6月2日 下午2:57:02
*/
@Service
@JasperService("eventplan")
public class EventPlanAdapter extends BaseAdapter{
	
	private static Logger logger = LoggerFactory.getLogger(EventPlanAdapter.class);
	
	/**
	 * 功能描述：激活事件接口
	 * @author baomz
	 * @date 2017年6月3日 上午10:41:13
	 * @param @param dto 参数封装
	 * @param @param messageId 消息标识
	 * @param @param token 厂家
	 * @param @return
	 * @param @throws Exception 
	 * @return ResultDto
	 */
	@SuppressWarnings("rawtypes")
	@JasperMethod(method = "ActivateTerminalEvent", requestEl = "ActivateTerminalEventRequest", responseEl = "ActivateTerminalEventResponse")
	public ResultDto activateTerminalEvent(ActivateTerminalEventRequestDto dto, String messageId, String token) throws Exception {
		ResultDto<ActivateTerminalEventResponseDto> resultDto = new ResultDto<ActivateTerminalEventResponseDto>();
		// 0. 获取配置
		MethodConfig config = methodConfig.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		String methodName = config.getMethod();
		String requestEl = config.getRequestEl();
		String responseEl = config.getResponseEl();

		try {
			// 1. 创建请求报文
			SOAPMessage request = super.createSoapMessage(messageId, getSoapAction(service, methodName, true), requestEl, token);

			// 2. 在报文中增加iccid 节点
			super.addElement2Message(request, requestEl, "iccid", dto.getIccid());
			super.addElement2Message(request, requestEl, "eventName", dto.getEventName());
			if (dto.getEffectiveDate()!=null&&!"".equals(dto.getEffectiveDate().trim())) {
				super.addElement2Message(request, requestEl, "effectiveDate", dto.getEffectiveDate().toString());
			}
		
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
				resolveActivateTerminalEventResponse(response, responseEl,resultDto);
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
	
	private void resolveActivateTerminalEventResponse(SOAPMessage message, String responseEl,ResultDto<ActivateTerminalEventResponseDto> resultDto)
			throws SOAPException {

		// 1. 返回对象
		ActivateTerminalEventResponseDto dto = new ActivateTerminalEventResponseDto();

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
		Name status = envelope.createName("status", prefix, schema);
		SOAPBodyElement iccidElement = (SOAPBodyElement) terminalResponseElement.getChildElements(iccid).next();
		SOAPBodyElement statusElement = (SOAPBodyElement) terminalResponseElement.getChildElements(status).next();
		dto.setIccid(iccidElement.getTextContent());
		dto.setStatus(statusElement.getTextContent());
		resultDto.setBody(dto);

	}

}
