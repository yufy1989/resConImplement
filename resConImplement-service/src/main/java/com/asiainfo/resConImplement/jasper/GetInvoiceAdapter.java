package com.asiainfo.resConImplement.jasper;

import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.dto.InvoiceDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.mappings.JasperMethod;
import com.asiainfo.resConImplement.mappings.JasperService;
import com.sun.xml.wss.XWSSecurityException;

@Service
@JasperService("billing")
public class GetInvoiceAdapter extends BaseAdapter {
	private static Logger logger = LoggerFactory.getLogger(GetInvoiceAdapter.class);
	
	/**
	 * 
	 * 检索给定帐户的给定月份的账单信息
	 * @param accountId
	 * @param cycleStartDate
	 * @param messageId
	 * @return
	 * @throws SOAPException
	 * @throws IOException
	 * @throws XWSSecurityException
	 * @throws Exception
	 */
	
	@JasperMethod(method = "GetInvoice", requestEl = "GetInvoiceRequest", responseEl = "GetInvoiceResponse")
	public ResultDto<InvoiceDto> getInvoice(String accountId,String cycleStartDate, String messageId, String token) throws SOAPException, IOException, XWSSecurityException,Exception{
		logger.info("[param] input param accountId:{},cycleStartDate:{},messageId:{}",accountId,cycleStartDate,messageId);
		
	    ResultDto<InvoiceDto> resultDto = new ResultDto<InvoiceDto>();
		// 0. 获取配置
		MethodConfig config = methodConfig.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		String methodName = config.getMethod();
		String requestEl = config.getRequestEl();
		String responseEl = config.getResponseEl();
		
		// 1. 创建请求报文
		SOAPMessage request = super.createSoapMessage(messageId, getSoapAction(service, methodName,true), requestEl, token);
		//创建节点传输参数
		makeNode(accountId,cycleStartDate,request, requestEl, responseEl);
		
		// 3. 在报文中增加安全信息
		request = super.secureMessage(request, token);
		logger.info("MessageId:{}, accountId: {},cycleStartDate:{}, Request:{}", messageId, accountId,cycleStartDate, getSoapMessage2String(request));
		// 4. 调用 Jasper 并返回响应报文
		SOAPMessage response =super.callJasper(request);
		logger.info("MessageId:{}, accountId: {},cycleStartDate:{}, Response:{}", messageId, accountId,cycleStartDate, getSoapMessage2String(response));
		
		
		if(response==null){
			 resultDto.setCode("0000001");
			 resultDto.setMessage("失败，response is null");
			 return resultDto;
		 }
		
		if ( !response.getSOAPBody().hasFault()) {
			resultDto.setCode("0000000");
			resultDto.setMessage("success");
			// 5. 解析响应报文并返回
			resolveInvoiceResponse(response, responseEl, resultDto);
		} else {
			resultDto.setHead(super.buildFaultMessage(response));
		}
		return resultDto;
	}
	
	/**
	 * 功能描述：创建调用Jasper的节点
	 * @author pankx
	 * @date 2016年8月26日 下午2:53:38
	 * @param @param accountId
	 * @param @param cycleStartDate 
	 * @return void
	 * @throws SOAPException 
	 */
	public void makeNode(String accountId,String cycleStartDate,SOAPMessage request ,String requestEl,String responseEl) throws SOAPException{
		logger.info("[param] makeNode of param accountId:{},cycleStartDate:{}",accountId,cycleStartDate);
		
		//添加账户Id
		
		if(StringUtils.isBlank(accountId)){
			logger.info("[param] input param accountId is null or '' ");
		}else{
			super.addElement2Message(request, requestEl, "accountId",accountId);
		}
		
		//添加开始账期
		if(StringUtils.isNotBlank(cycleStartDate)){
			super.addElement2Message(request, requestEl, "cycleStartDate",cycleStartDate);
		}
		
	
	}
	
	/**
	 * 功能描述：解析响应报文
	 * 
	 * @author Baomz
	 * @date 2016年7月21日 上午10:52:52
	 * @param @param
	 *            message
	 * @param @return
	 * @param @throws
	 *            SOAPException
	 * @return List<String>
	 */
	private void resolveInvoiceResponse(SOAPMessage message, String responseEl,ResultDto<InvoiceDto> resultDto)
			throws SOAPException {


		// 2. 获取报文信封
		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
		String schema = getSchema();

		//Name _terminalResponseName = envelope.createName(responseEl);
		SOAPBody soapBody = message.getSOAPBody();
		SOAPBodyElement next = (SOAPBodyElement)soapBody.getChildElements().next();
		String typeNamespace = next.getPrefix();
		
		Name terminalResponseName = envelope.createName(responseEl, prefix, schema);
		// 3. 获取响应节点
		SOAPBodyElement terminalResponseElement = (SOAPBodyElement) soapBody
				.getChildElements(terminalResponseName).next();

		/**
		 * 4. 获取报文中基本的属性 放到 dto 中
		 */
		super.buildBase(message,resultDto,terminalResponseElement);

		// 5. 获取具体报文中的其他属性
		//Name terminal_iccid = envelope.createName("iccid", prefix, schema);
		Name terminal_accountId = envelope.createName("accountId", typeNamespace, schema);
		Name terminal_invoiceId = envelope.createName("invoiceId", typeNamespace, schema);
		Name terminal_currency = envelope.createName("currency", typeNamespace, schema);
		Name terminal_invoiceDate = envelope.createName("invoiceDate", typeNamespace, schema);
		Name terminal_dueDate = envelope.createName("dueDate", typeNamespace, schema);
		Name terminal_cycleStartDate = envelope.createName("cycleStartDate", typeNamespace, schema);
		Name terminal_cycleEndDate = envelope.createName("cycleEndDate", typeNamespace, schema);
		Name terminal_totalTerminals = envelope.createName("totalTerminals", typeNamespace, schema);
		Name terminal_dataVolume = envelope.createName("dataVolume", typeNamespace, schema);
		Name terminal_subscriptionCharge = envelope.createName("subscriptionCharge", typeNamespace, schema);
		Name terminal_overageCharge = envelope.createName("overageCharge", typeNamespace, schema);
		Name terminal_totalCharge = envelope.createName("totalCharge", typeNamespace, schema);
		Name terminal_smsVolume = envelope.createName("smsVolume", typeNamespace, schema);
		Name terminal_smsCharge = envelope.createName("smsCharge", typeNamespace, schema);
		Name terminal_voiceVolume = envelope.createName("voiceVolume", typeNamespace, schema);
		Name terminal_voiceCharge = envelope.createName("voiceCharge", typeNamespace, schema);
		Name terminal_otherCharge = envelope.createName("otherCharge", typeNamespace, schema);
		Name terminal_totalEvents= envelope.createName("totalEvents", typeNamespace, schema);
		Name terminal_eventsCharge= envelope.createName("eventsCharge", typeNamespace, schema);
		Name terminal_activationCharge= envelope.createName("activationCharge", typeNamespace, schema);
		Name terminal_discountApplied= envelope.createName("discountApplied", typeNamespace, schema);
		
		
		//SOAPBodyElement terminalsElement_iccid = (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_iccid).next();
		SOAPBodyElement terminalsElement_accountId  = (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_accountId).next();
		SOAPBodyElement terminalsElement_invoiceId = (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_invoiceId).next();
		SOAPBodyElement terminalsElement_currency = (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_currency).next();
		SOAPBodyElement terminalsElement_invoiceDate = (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_invoiceDate).next();
		SOAPBodyElement terminalsElement_dueDate = (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_dueDate).next();
		SOAPBodyElement terminalsElement_cycleStartDate = (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_cycleStartDate).next();
		SOAPBodyElement terminalsElement_cycleEndDate = (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_cycleEndDate).next();
		SOAPBodyElement terminalsElement_totalTerminals = (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_totalTerminals).next();
		SOAPBodyElement terminalsElement_dataVolume = (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_dataVolume).next();
		SOAPBodyElement terminalsElement_subscriptionCharge= (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_subscriptionCharge).next();
		SOAPBodyElement terminalsElement_overageCharge= (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_overageCharge).next();
		SOAPBodyElement terminalsElement_totalCharge= (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_totalCharge).next();
		SOAPBodyElement terminalsElement_smsVolume= (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_smsVolume).next();
		SOAPBodyElement terminalsElement_smsCharge= (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_smsCharge).next();
		SOAPBodyElement terminalsElement_voiceVolume= (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_voiceVolume).next();
		SOAPBodyElement terminalsElement_voiceCharge= (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_voiceCharge).next();
		SOAPBodyElement terminalsElement_otherCharge= (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_otherCharge).next();
		SOAPBodyElement terminalsElement_totalEvents= (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_totalEvents).next();
		SOAPBodyElement terminalsElement_eventsCharge= (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_eventsCharge).next();
		SOAPBodyElement terminalsElement_activationCharge= (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_activationCharge).next();
		SOAPBodyElement terminalsElement_discountApplied= (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_discountApplied).next();
		
		
		InvoiceDto body = new InvoiceDto() ;
		//body.setIccid(terminalsElement_iccid.getTextContent());
		body.setAccountId(Long.parseLong(terminalsElement_accountId.getTextContent()));
		body.setInvoiceId(Long.parseLong(terminalsElement_invoiceId.getTextContent()));
		body.setCurrency(terminalsElement_currency.getTextContent());
		body.setInvoiceDate(terminalsElement_invoiceDate.getTextContent());
		body.setDueDate(terminalsElement_dueDate.getTextContent());
		body.setCycleStartDate(terminalsElement_cycleStartDate.getTextContent());
		body.setCycleEndDate(terminalsElement_cycleEndDate.getTextContent());
		body.setTotalTerminals(Long.parseLong(terminalsElement_totalTerminals.getTextContent()));
		body.setDataVolume(new BigDecimal(terminalsElement_dataVolume.getTextContent()));
		body.setSubscriptionCharge(new BigDecimal(terminalsElement_subscriptionCharge.getTextContent()));
		body.setOverageCharge(new BigDecimal(terminalsElement_overageCharge.getTextContent()));
		body.setTotalCharge(new  BigDecimal(terminalsElement_totalCharge.getTextContent()));
		body.setSmsVolume(new BigDecimal(terminalsElement_smsVolume.getTextContent()));
		body.setSmsCharge(new BigDecimal(terminalsElement_smsCharge.getTextContent()));
		body.setVoiceVolume(new BigDecimal(terminalsElement_voiceVolume.getTextContent()));
		body.setVoiceCharge(new BigDecimal(terminalsElement_voiceCharge.getTextContent()));
		body.setOtherCharge(new BigDecimal(terminalsElement_otherCharge.getTextContent()));
		body.setTotalEvents(Long.parseLong(terminalsElement_totalEvents.getTextContent()));
		body.setEventsCharge(new BigDecimal(terminalsElement_eventsCharge.getTextContent()));
		body.setActivationCharge(new BigDecimal(terminalsElement_activationCharge.getTextContent()));
		body.setDiscountApplied(new BigDecimal(terminalsElement_discountApplied.getTextContent()));
	    resultDto.setBody(body);
	}
}
