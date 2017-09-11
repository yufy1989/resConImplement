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

import com.asiainfo.resConImplement.dto.PolicyQuotaDto;
import com.asiainfo.resConImplement.dto.QuotaDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.mappings.JasperMethod;
import com.asiainfo.resConImplement.mappings.JasperService;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
 * 类说明：Jasper policyquota 相关适配器
 * 
 * @author chencq
 * @date 2016年7月25日 下午18:23:43
 */
@Service
@JasperService("getpolicyquotainfo")
public class PolicyQuotaAdapter extends BaseAdapter {
	private static Logger logger = LoggerFactory.getLogger(PolicyQuotaAdapter.class);

	/**
	 * 功能描述：流量查询
	 * 
	 * @author chencq
	 * @date 2016年7月25日 下午18:26:46
	 * @param @param
	 *            imsi
	 * @param @param
	 *            messageId
	 * @param @return
	 * @return List<String>
	 * @throws Exception 
	 */
	@JasperMethod(method = "GetPolicyQuotaInfo", requestEl = "GetPolicyQuotaInfoRequest", responseEl = "GetPolicyQuotaInfoResponse")
	public ResultDto<PolicyQuotaDto> getPolicyQuotaInfo(String imsi, String messageId, String token) throws Exception {
		logger.info("[start]PolicyQuotaAdapter:GetPolicyQuotaInfo param is imsi:{},messageId:{}",imsi,messageId);
		
		ResultDto<PolicyQuotaDto> resultDto = new ResultDto<PolicyQuotaDto>();

		// 0. 获取配置
		MethodConfig config = methodConfig.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		String methodName = config.getMethod();
		String requestEl = config.getRequestEl();
		String responseEl = config.getResponseEl();

		try {
			// 1. 创建请求报文
			SOAPMessage request = super.createSoapMessage(messageId, getSoapAction(service, methodName, false), requestEl, token);

			// 2. 在报文中增加imsi节点
			super.addElement2Message(request, requestEl, "imsi", imsi);

			// 3. 在报文中增加安全信息
			request = super.secureMessage(request, token);
			logger.info("MessageId:{}, IMSI: {}, Request:{}", messageId, imsi, getSoapMessage2String(request));

			// 4. 调用Jasper并返回响应报文
			SOAPMessage response = super.callJasper(request);
			logger.info("MessageId:{}, IMSI: {}, Response:{}", messageId, imsi, getSoapMessage2String(response));
			if (!response.getSOAPBody().hasFault()) {
				resultDto.setCode("0000000");
				resultDto.setMessage("success");
				// 5. 解析响应报文并返回
				resolvePolicyQuotaInfoResponse(response, responseEl, resultDto);
			} else {
				resultDto.setHead(super.buildFaultMessage(response));
			}
		} catch (Exception e) {
			resultDto.setCode("0000001");
			resultDto.setMessage(e.getMessage());
			logger.error("---error---{}",Exceptions.getStackTraceAsString(e));
		}
		logger.info("[end]PolicyQuotaAdapter:GetPolicyQuotaInfo param is ResultDto<PolicyQuotaDto>:{}",resultDto);
		return resultDto;
	}

	/**
	 * 功能描述：解析响应报文
	 * 
	 * @author chencq
	 * @date 2016年7月21日 上午10:52:52
	 * @param @param
	 *            message
	 * @param @return
	 * @param @throws
	 *            SOAPException
	 * @return List<String>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void resolvePolicyQuotaInfoResponse(SOAPMessage message, String responseEl, ResultDto resultDto)
			throws SOAPException {
		logger.info("[start]PolicyQuotaAdapter:resolvePolicyQuotaInfoResponse param is message:{},responseEl:{},resultDto:{}",message,responseEl,resultDto);

		// 1. 返回对象
		PolicyQuotaDto policyQuotaDto = new PolicyQuotaDto();

		// 2. 获取报文信封
		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
		String schema = getSchema();

		// 3. 获取响应节点
		Name quotaInfoResponseName = envelope.createName(responseEl, prefix, schema);
		SOAPBodyElement quotaInfoResponseElement = (SOAPBodyElement) message.getSOAPBody()
				.getChildElements(quotaInfoResponseName).next();

		//4. 获取报文中基本的属性放到 resultDto中
		super.buildBase(message, resultDto, quotaInfoResponseElement);

		// 5. 获取具体报文中的其他属性
		Name quotaInfo = envelope.createName("quotaInfo", prefix, schema);
		Name quota = envelope.createName("quota", prefix, schema);
		SOAPBodyElement quotaInfoElement = (SOAPBodyElement) quotaInfoResponseElement.getChildElements(quotaInfo).next();
		boolean found = false;
		Iterator<?> itr = quotaInfoElement.getChildElements(quota);
		while (itr.hasNext()) {
			QuotaDto dto = new QuotaDto();
			SOAPBodyElement quotaElement = (SOAPBodyElement) itr.next();
			found = true;
			NodeList list = quotaElement.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node n = list.item(i);
				String name = n.getLocalName();
				String value = n.getTextContent();
				if ("policyName".equals(name)) {
					dto.setPolicyName(value);
				} else if ("quotaName".equals(name)) {
					dto.setQuotaName(value);
				} else if ("balance".equals(name)) {
					dto.setBalance(value);
				} else if ("expiryDate".equals(name)) {
					dto.setExpiryDate(value);
				}
			}
			policyQuotaDto.addQuota(dto);
		}
		if (!found) {
			System.out.println("No session found for Quota.");
		}
		logger.info("[end]PolicyQuotaAdapter:resolvePolicyQuotaInfoResponse param is PolicyQuotaDto:{}",policyQuotaDto);
		resultDto.setBody(policyQuotaDto);
	}
}
