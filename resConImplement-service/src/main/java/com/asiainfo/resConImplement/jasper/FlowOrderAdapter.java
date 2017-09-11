package com.asiainfo.resConImplement.jasper;

import java.io.IOException;
import java.util.Iterator;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.dto.AdditionalPolicyTypeDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.SubscriberPolicyRequestDto;
import com.asiainfo.resConImplement.dto.SubscriberPolicyResponseDto;
import com.asiainfo.resConImplement.dto.SubscriberPolicyTypeDto;
import com.asiainfo.resConImplement.mappings.JasperMethod;
import com.asiainfo.resConImplement.mappings.JasperService;
import com.sun.xml.wss.XWSSecurityException;

/**
 * 类说明：Jasper TrafficOrder  流量订购
 * 
 * @author pankx
 * @date 2016年7月20日 下午2:10:12
 */
@Service
@JasperService("updatesubscriberpolicy")
public class FlowOrderAdapter extends BaseAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(FlowOrderAdapter.class);
	/**
	 * 功能描述：[Adpater]流量订购-给ICCID的APN2或CustomerZone增加策略 
	 * 
	 * @author pkx
	 * @date 2016年7月21日 上午10:36:06
	 * @param @param
	 *            iccId
	 * @param @param
	 *            messageId
	 * @param @return
	 * @return List<String>
	 * @throws SOAPException 
	 * @throws XWSSecurityException 
	 * @throws IOException 
	 */
	@JasperMethod(method = "UpdateSubscriberPolicy", requestEl = "UpdateSubscriberPolicyRequest", responseEl = "UpdateSubscriberPolicyResponse")
	public ResultDto<SubscriberPolicyResponseDto> updateSubscriberPolicy(SubscriberPolicyRequestDto reqDto, String messageId, String token) throws SOAPException, IOException, XWSSecurityException,Exception{
	
		ResultDto<SubscriberPolicyResponseDto> resultDto = new ResultDto<SubscriberPolicyResponseDto>();
		// 0. 获取配置
		MethodConfig config = methodConfig.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		String methodName = config.getMethod();
		String requestEl = config.getRequestEl();
		String responseEl = config.getResponseEl();
		
			// 1. 创建请求报文
			SOAPMessage request = super.createSoapMessage(messageId, getSoapAction(service, methodName,false), requestEl, token);
			//创建节点传输参数
			makeNode(reqDto, request, requestEl, responseEl);
			
			// 3. 在报文中增加安全信息
			request = super.secureMessage(request, token);
			logger.info("MessageId:{}, SubscriberPolicyRequestDto: {}, Request:{}", messageId, reqDto, getSoapMessage2String(request));

			// 4. 调用 Jasper 并返回响应报文
			SOAPMessage response =super.callJasper(request);
			logger.info("MessageId:{}, SubscriberPolicyRequestDto: {}, Response:{}", messageId, reqDto, getSoapMessage2String(response));
			
			if(response==null){
				 resultDto.setCode("0000001");
				 resultDto.setMessage("失败，response is null");
				 return resultDto;
			 }
			
			if ( !response.getSOAPBody().hasFault()) {
				resultDto.setCode("0000000");
				resultDto.setMessage("success");
				// 5. 解析响应报文并返回
				resolveTrafficOrderResponse(response, responseEl, resultDto);
			} else {
				resultDto.setHead(super.buildFaultMessage(response));
			}
		return resultDto;
	}

	/**
	 * 功能描述：封装节点
	 * @author pankx
	 * @date 2016年7月26日 下午4:40:26
	 * @param  
	 * @return void
	 * @throws SOAPException 
	 */
	public void makeNode(SubscriberPolicyRequestDto reqDto,SOAPMessage request ,String requestEl,String responseEl) throws SOAPException {

		// 2. 在报文中增加imsi 节点
		if(StringUtils.isBlank(reqDto.getImsi())){
			logger.info("实体{}的参数imsi的值为空","SubscriberPolicyRequestDto");
		}else{
			super.addElement2Message(request, requestEl, "imsi", reqDto.getImsi());
		}
		
		// 2. 在报文中增加iccId节点
		if(StringUtils.isBlank(reqDto.getIccid())){
			logger.info("实体{}的参数iccId的值为空","SubscriberPolicyRequestDto");
		}else{
			super.addElement2Message(request, requestEl, "iccid", reqDto.getIccid());
		}
		// 2. 在报文中增加effectiveDate节点
		if(reqDto.getEffectiveDate()==null){
			logger.info("实体{}的参数effectiveDate的值为空","SubscriberPolicyRequestDto");
		}else{
			 super.addElement2Message(request, requestEl, "effectiveDate",reqDto.getEffectiveDate());
			//super.addElement2Message(request, requestEl, "effectiveDate","2016-08-30T09:30:10Z");
		}
		// 2. 在报文中增加inlineProcess节点
		if(reqDto.getInlineProcess()==null){
			logger.info("实体{}的参数inlineProcess的值为空","SubscriberPolicyRequestDto");
		}else{
			super.addElement2Message(request, requestEl, "inlineProcess",reqDto.getInlineProcess().toString());
		}
		// 2. 在报文中增加notificationURL节点
		if(StringUtils.isBlank(reqDto.getNotificationURL())){
			logger.info("实体{}的参数notificationURL的值为空","SubscriberPolicyRequestDto");
		}else{
			super.addElement2Message(request, requestEl, "notificationURL",reqDto.getNotificationURL());
		}
		
		
		if(reqDto.getSubscriberPolicies()==null || reqDto.getSubscriberPolicies().size()<=0){
			logger.info("实体{}的参数集合{}的值为空","SubscriberPolicyRequestDto","subscriberPolicies");
		
		}else{
			
			SOAPEnvelope envelope = request.getSOAPPart().getEnvelope();
			SOAPBody soapBody = request.getSOAPBody();
			String schema = getSchema();
			Name terminalRequestName = envelope.createName(requestEl, prefix, schema);
			Iterator<?> iterator = soapBody.getChildElements(terminalRequestName);
			SOAPElement subscriberPoliciesElement=null;
			if (iterator.hasNext()) {
				 SOAPBodyElement terminalRequestElement = ((SOAPBodyElement) iterator.next());
				 Name subscriberPoliciesName = envelope.createName("subscriberPolicies", prefix, schema);
				 subscriberPoliciesElement = terminalRequestElement.addChildElement(subscriberPoliciesName);
			}   
			
			
			
			for(SubscriberPolicyTypeDto spt:reqDto.getSubscriberPolicies()){
				 Name  subscriberPolicyName = envelope.createName("subscriberPolicy",prefix,schema);
				 SOAPElement subscriberPolicyElement = subscriberPoliciesElement.addChildElement(subscriberPolicyName);
				
				 if(StringUtils.isBlank(spt.getApnName())){
					logger.info("实体{}的参数集合{}的apnName值为空","SubscriberPolicyRequestDto","SubscriberPolicyTypeDto");
				}else{
					Name apnName = envelope.createName("apnName", prefix, schema);
					subscriberPolicyElement.addChildElement(apnName).setValue(spt.getApnName());;
				}
				//super.addElement2Message(request, "subscriberPolicies", "apnName",spt.getApnName());
				
				if(spt.getStreamId()==null){
					logger.info("实体{}的参数集合{}的streamId值为空","SubscriberPolicyRequestDto","SubscriberPolicyTypeDto");
				}else{
					 Name streamIdName = envelope.createName("streamId", prefix, schema);
					 SOAPElement streamIdElemnt=subscriberPolicyElement.addChildElement(streamIdName);
					 streamIdElemnt.setValue(spt.getStreamId().toString());
					 //super.addElement2Message(request, "subscriberPolicies","streamId",spt.getStreamId().toString());
				}
				
				if(StringUtils.isBlank(spt.getPolicyAction())){
					logger.info("实体{}的参数集合{}的policyAction值为空","SubscriberPolicyRequestDto","SubscriberPolicyTypeDto");
				}else{
					 Name policyActionName = envelope.createName("policyAction", prefix, schema);
					 SOAPElement policyActionNameElemnt=subscriberPolicyElement.addChildElement(policyActionName);
					 policyActionNameElemnt.setValue(spt.getPolicyAction());
				}
			}
		
		
		}
		
		if(reqDto.getAdditionalPolicies()==null || reqDto.getAdditionalPolicies().size()<=0){
			logger.info("实体{}的参数集合{}的值为空","SubscriberPolicyRequestDto","additionalPolicies");
		}else{
			 //super.addElement2Message(request, requestEl, "additionalPolicies",null);
			
			SOAPEnvelope envelope = request.getSOAPPart().getEnvelope();
			SOAPBody soapBody = request.getSOAPBody();
			String schema = getSchema();
			Name terminalRequestName = envelope.createName(requestEl, prefix, schema);
			Iterator<?> iterator = soapBody.getChildElements(terminalRequestName);
			SOAPElement additionalPoliciesElement=null;
			if (iterator.hasNext()) {
				SOAPBodyElement terminalRequestElement = ((SOAPBodyElement) iterator.next());
				Name additionalPoliciesName = envelope.createName("additionalPolicies", prefix, schema);
				additionalPoliciesElement = terminalRequestElement.addChildElement(additionalPoliciesName);
			}   
			
			for(AdditionalPolicyTypeDto apt:reqDto.getAdditionalPolicies()){
				 Name  additionalPolicyName = envelope.createName("additionalPolicy",prefix,schema);
				 SOAPElement additionalPolicyElement = additionalPoliciesElement.addChildElement(additionalPolicyName);
				
				if(StringUtils.isBlank(apt.getName())){
				}else{
					 Name nameName = envelope.createName("Name", prefix, schema);
					 SOAPElement nameNameElemnt=additionalPolicyElement.addChildElement(nameName);
					 nameNameElemnt.setValue(apt.getName());
				}
				
				if(StringUtils.isBlank(apt.getValue())){
					
					logger.info("实体{}的参数集合{}的name值为空","SubscriberPolicyRequestDto","SubscriberPolicyTypeDto");
				}else{
					 Name valueName = envelope.createName("Value", prefix, schema);
					 SOAPElement valueNameElemnt=additionalPolicyElement.addChildElement(valueName);
					 valueNameElemnt.setValue(apt.getValue());
				}
				
			}
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
	private void resolveTrafficOrderResponse(SOAPMessage message, String responseEl,ResultDto<SubscriberPolicyResponseDto> resultDto)
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
		Name terminal_imsi = envelope.createName("imsi", typeNamespace, schema);
		Name terminal_status = envelope.createName("status", typeNamespace, schema);
		//SOAPBodyElement terminalsElement_iccid = (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_iccid).next();
		SOAPBodyElement terminalsElement_imsi  = (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_imsi).next();
		SOAPBodyElement terminalsElement_status = (SOAPBodyElement) terminalResponseElement.getChildElements(terminal_status).next();
		SubscriberPolicyResponseDto body = new SubscriberPolicyResponseDto() ;
		//body.setIccid(terminalsElement_iccid.getTextContent());
		body.setImsi(terminalsElement_imsi.getTextContent());
		body.setStatus(terminalsElement_status.getTextContent());
	    resultDto.setBody(body);
	}

}
