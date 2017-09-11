package com.asiainfo.resConImplement.jasper;

import java.util.Iterator;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.asiainfo.resConImplement.dto.DataUsageDetailDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.UsageDetailsDto;
import com.asiainfo.resConImplement.mappings.JasperMethod;
import com.asiainfo.resConImplement.mappings.JasperService;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
 * 类说明：Jasper billing 相关适配器
 * 
 * @author chencq
 * @date 2016年7月25日 下午18:23:43
 */
@Service
@JasperService("billing")
public class UsageDetailsAdapter extends BaseAdapter {
	private static Logger logger = LoggerFactory.getLogger(UsageDetailsAdapter.class);

	@JasperMethod(method = "GetTerminalUsageDataDetails", requestEl = "GetTerminalUsageDataDetailsRequest", responseEl = "GetTerminalUsageDataDetailsResponse")
	public ResultDto<UsageDetailsDto> getTerminalUsageDataDetails(String iccid, String cycleStartDate,int pageNumber,String messageId, String token) throws Exception {
		logger.info("[start]PolicyQuotaAdapter:GetTerminalUsageDataDetails param is iccid:{},cycleStartDate:{},pageNumber:{},messageId:{}",iccid,cycleStartDate,pageNumber,messageId);
		
		ResultDto<UsageDetailsDto> resultDto = new ResultDto<UsageDetailsDto>();

		// 0. 获取配置
		MethodConfig config = methodConfig.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		String methodName = config.getMethod();
		String requestEl = config.getRequestEl();
		String responseEl = config.getResponseEl();

		try {
			// 1. 创建请求报文
			SOAPMessage request = super.createSoapMessage(messageId, getSoapAction(service, methodName, true), requestEl, token);

			// 2. 在报文中增加iccid节点
			super.addElement2Message(request, requestEl, "iccid", iccid);
			super.addElement2Message(request, requestEl, "cycleStartDate", cycleStartDate);
			super.addElement2Message(request, requestEl, "pageNumber", String.valueOf(pageNumber));

			// 3. 在报文中增加安全信息
			request = super.secureMessage(request, token);
			logger.info("MessageId:{}, ICCID: {}, cycleStartDate: {}, pageNumber: {}, Request:{}", messageId, iccid,cycleStartDate,pageNumber, getSoapMessage2String(request));

			// 4. 调用Jasper并返回响应报文
			SOAPMessage response = super.callJasper(request);
			logger.info("MessageId:{}, ICCID: {}, cycleStartDate: {}, pageNumber: {}, Response:{}", messageId, iccid,cycleStartDate,pageNumber, getSoapMessage2String(response));
			if (!response.getSOAPBody().hasFault()) {
				resultDto.setCode("0000000");
				resultDto.setMessage("success");
				// 5. 解析响应报文并返回
				resolveGetTerminalUsageDataDetailsResponse(response, responseEl, resultDto);
			} else {
				resultDto.setHead(super.buildFaultMessage(response));
			}
		} catch (Exception e) {
			resultDto.setCode("0000001");
			resultDto.setMessage(e.getMessage());
			logger.error("---error---{}",Exceptions.getStackTraceAsString(e));
		}
		logger.info("[end]PolicyQuotaAdapter:GetTerminalUsageDataDetails param is ResultDto<UsageDetailsDto>:{}",resultDto);
		return resultDto;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void resolveGetTerminalUsageDataDetailsResponse(SOAPMessage message, String responseEl, ResultDto resultDto)
			throws SOAPException {
		logger.info("[start]PolicyQuotaAdapter:resolveGetTerminalUsageDataDetailsResponse param is message:{},responseEl:{},resultDto:{}",message,responseEl,resultDto);

		// 1. 返回对象
		UsageDetailsDto usageDetailsDto = new UsageDetailsDto();

		// 2. 获取报文信封
		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
		String schema = getSchema();

		// 3. 获取响应节点
		Name usageDetailResponseName = envelope.createName(responseEl, prefix, schema);
		SOAPBodyElement usageDetailResponseElement = (SOAPBodyElement) message.getSOAPBody()
				.getChildElements(usageDetailResponseName).next();

		//4. 获取报文中基本的属性放到 resultDto中
		super.buildBase(message, resultDto, usageDetailResponseElement);

		// 5. 获取具体报文中的其他属性
		Name usageDetails = envelope.createName("usageDetails", prefix, schema);
		Name usageDetail = envelope.createName("usageDetail", prefix, schema);
		Name totalPages = envelope.createName("totalPages", prefix, schema);
		SOAPBodyElement usageDetailsElement = (SOAPBodyElement) usageDetailResponseElement.getChildElements(usageDetails).next();
		SOAPBodyElement totalPagesElement = (SOAPBodyElement) usageDetailResponseElement.getChildElements(totalPages).next();
		boolean found = false;
		Iterator<?> itr = usageDetailsElement.getChildElements(usageDetail);

		DataUsageDetailDto dto = new DataUsageDetailDto();
		while (itr.hasNext()) {
			SOAPBodyElement usageDataElement = (SOAPBodyElement) itr.next();
			found = true;
			NodeList list = usageDataElement.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node n = list.item(i);
				String name = n.getLocalName();
				String value = n.getTextContent();
				if ("iccid".equals(name)) {
					dto.setIccid(value);
				} else if ("cycleStartDate".equals(name)) {
					dto.setCycleStartDate(value);
				} else if ("terminalId".equals(name)) {
					dto.setTerminalId(value);
				} else if ("endConsumerId".equals(name)) {
					dto.setEndConsumerId(value);
				} else if ("customer".equals(name)) {
					dto.setCustomer(value);
				} else if ("billable".equals(name)) {
					dto.setBillable(Boolean.parseBoolean(value));
				} else if ("zone".equals(name)) {
					dto.setZone(value);
				} else if ("sessionStartTime".equals(name)) {
					dto.setSessionStartTime(value);
				} else if ("duration".equals(name)) {
					dto.setDuration(Long.parseLong(value));
				} else if ("dataVolume".equals(name)) {
					dto.setDataVolume(Double.parseDouble(value));
				} else if ("countryCode".equals(name)) {
					dto.setCountryCode(value);
				} else if ("serviceType".equals(name)) {
					dto.setServiceType(value);
				}
			}
			usageDetailsDto.addUsageDetail(dto);
		}
		usageDetailsDto.setTotalPages(Integer.parseInt(totalPagesElement.getTextContent()));
		if (!found) {
			System.out.println("No session found for usageDetail.");
		}
		logger.info("[end]PolicyQuotaAdapter:resolvePolicyQuotaInfoResponse param is DataUsageDetailDto:{}",usageDetailsDto);
		resultDto.setBody(usageDetailsDto);
	}

}
