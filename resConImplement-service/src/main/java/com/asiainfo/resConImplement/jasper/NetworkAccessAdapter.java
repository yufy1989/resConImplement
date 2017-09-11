package com.asiainfo.resConImplement.jasper;

import java.io.IOException;
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

import com.alibaba.dubbo.common.utils.StringUtils;
import com.asiainfo.resConImplement.common.util.DateUtil;
import com.asiainfo.resConImplement.dto.EditNetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.NacIdTypeDto;
import com.asiainfo.resConImplement.dto.NetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.mappings.JasperMethod;
import com.asiainfo.resConImplement.mappings.JasperService;
import com.sun.xml.wss.XWSSecurityException;

/**
 * 类说明：Jasper NetworkAccess 相关适配器
 * 
 * @author Baomz
 * @date 2016年7月20日 下午2:10:12
 */
@Service
@JasperService("networkaccess")
public class NetworkAccessAdapter extends BaseAdapter {
	private static Logger logger = LoggerFactory.getLogger(NetworkAccessAdapter.class);
	/**
	 * 功能描述：查询旧SIM卡的资费计划
	 * @author Baomz
	 * @date 2016年7月21日 上午10:36:06
	 * @param @param iccId
	 * @param @param messageId
	 * @param @return
	 * @return List<String>
	 * @throws SOAPException 
	 * @throws XWSSecurityException 
	 * @throws IOException 
	 */
	@JasperMethod(method = "GetNetworkAccessConfig", requestEl = "GetNetworkAccessConfigRequest", responseEl = "GetNetworkAccessConfigResponse")
	public ResultDto<NetworkAccessConfigDto> getNetworkAccessConfig(String iccId, String messageId, String token) throws SOAPException, IOException, XWSSecurityException,Exception {
		ResultDto<NetworkAccessConfigDto> resultDto = new ResultDto<NetworkAccessConfigDto>();
		// 0. 获取配置
		MethodConfig config = methodConfig.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		String methodName = config.getMethod();
		String requestEl = config.getRequestEl();
		String responseEl = config.getResponseEl();
		// 1. 创建请求报文
		SOAPMessage request = super.createSoapMessage(messageId, getSoapAction(service, methodName, true), requestEl, token);
		// 2. 在报文中增加iccid 节点
		super.addElement2Message(request, requestEl, "iccid", iccId);
		// 3. 在报文中增加安全信息
		request = super.secureMessage(request, token);
		logger.info("****************************Request:{}",getSoapMessage2String(request));
		// 4. 调用 Jasper 并返回响应报文
		SOAPMessage response = super.callJasper(request);
		logger.info("#####################Response:{}",getSoapMessage2String(response));
		if (response == null) {
			resultDto.setCode("0000001");
			resultDto.setMessage("jsper返回response为空");
			return resultDto;
		} else if (!response.getSOAPBody().hasFault()) {
			resultDto.setCode("0000000");
			resultDto.setMessage("success");
			// 5. 解析响应报文并返回
			resolveNetworkAccessConfigResponse(response, responseEl, resultDto);
		} else {
			resultDto.setHead(super.buildFaultMessage(response));
		}
		return resultDto;
	}

	/**
	 * 功能描述：解析响应报文
	 * 
	 * @author Baomz
	 * @date 2016年7月21日 上午10:52:52
	 * @param @param message
	 * @param @return
	 * @param @throws SOAPException
	 * @return List<String>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void resolveNetworkAccessConfigResponse(SOAPMessage message, String responseEl, ResultDto resultDto)
			throws SOAPException {
		// 1. 返回对象
		NetworkAccessConfigDto dto = new NetworkAccessConfigDto();
		// 2. 获取报文信封
		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
		String schema = getSchema();
		// 3. 获取响应节点
		Name terminalResponseName = envelope.createName(responseEl, prefix, schema);
		SOAPBodyElement terminalResponseElement = (SOAPBodyElement) message.getSOAPBody().getChildElements(terminalResponseName).next();
		// 获取报文中基本的属性 放到 dto 中
		super.buildBase(message, resultDto, terminalResponseElement);
		// 5. 获取具体报文中的其他属性
		Name terminals = envelope.createName("nacIds", prefix, schema);
		Name terminal = envelope.createName("nacId", prefix, schema);
		SOAPBodyElement terminalsElement = (SOAPBodyElement) terminalResponseElement.getChildElements(terminals).next();
		boolean found = false;
		Iterator<?> itr = terminalsElement.getChildElements(terminal);
		while (itr.hasNext()) {
			SOAPBodyElement terminalElement = (SOAPBodyElement) itr.next();
			found = true;
			NodeList list = terminalElement.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node n = list.item(i);
				String name = n.getLocalName();
				String value = n.getTextContent();
				if (name.equals("nacId")) {
					NacIdTypeDto nacId = new NacIdTypeDto();
					nacId.setNacId(value);
					dto.addNacId(nacId);
				} else if (name.equals("iccid")) {
					dto.setIccId(value);
				}
			}
		}
		if (!found) {
			System.out.println("No session found for ICCID ");
		}
		resultDto.setBody(dto);
	}
	/**
	 * 功能描述：更新新SIM卡的通信计划
	 * @author yufy
	 * @date 2016年7月25日 下午5:41:39
	 * @param @param iccId
	 * @param @param nacId
	 * @param @param effectiveDate
	 * @param @param messageId
	 * @param @return 
	 * @return EditNetworkAccessConfigDto
	 * @throws SOAPException 
	 * @throws XWSSecurityException,Exception 
	 * @throws IOException 
	 */
	@JasperMethod(method ="EditNetworkAccessConfig", requestEl = "EditNetworkAccessConfigRequest", responseEl = "EditNetworkAccessConfigResponse")
	public ResultDto<EditNetworkAccessConfigDto> editNetworkAccessConfig(String iccId, String nacId,String effectiveDate,String messageId,String token) throws SOAPException, IOException, XWSSecurityException,Exception {
		ResultDto<EditNetworkAccessConfigDto> resultDto = new ResultDto<EditNetworkAccessConfigDto>();
		MethodConfig config = methodConfig.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		String methodName = config.getMethod();
		String requestEl = config.getRequestEl();
		String responseEl = config.getResponseEl();
		// 1. 创建请求报文
		SOAPMessage request = super.createSoapMessage(messageId, getSoapAction(service, methodName,true), requestEl, token);
		// 2. 在报文中增加节点
		if (!StringUtils.isBlank(effectiveDate)) {
			super.addElement2Message(request, requestEl, "effectiveDate", effectiveDate);
		}
		super.addElement2Message(request, requestEl, "iccid", iccId);
		super.addElement2Message(request, requestEl, "nacId", nacId);
		// 3. 在报文中增加安全信息
		request = super.secureMessage(request, token);
		logger.info("****************************Request:{}",getSoapMessage2String(request));
		// 4. 调用 Jasper 并返回响应报文
		SOAPMessage response =super.callJasper(request);
		logger.info("#####################Response:{}",getSoapMessage2String(response));
		if (response == null) {
			resultDto.setCode("0000001");
			resultDto.setMessage("jsper返回response为空");
			return resultDto;
		} else if (!response.getSOAPBody().hasFault()) {
			resultDto.setCode("0000000");
			resultDto.setMessage("success");
			// 5. 解析响应报文并返回
			resolveEditNetworkAccessConfigResponse(response, responseEl, resultDto);
		} else {
			resultDto.setHead(super.buildFaultMessage(response));
		}
		return resultDto;
		}
	/**
	 * 功能描述：解析更新新SIM卡的通信计划返回报文
	 * @author yufy
	 * @date 2016年7月25日 下午6:09:47
	 * @param @param response
	 * @param @param responseEl
	 * @param @return
	 * @param @throws SOAPException 
	 * @return EditNetworkAccessConfigDto
	 */
	@SuppressWarnings("unchecked")
	private void resolveEditNetworkAccessConfigResponse(SOAPMessage message, String responseEl,@SuppressWarnings("rawtypes") ResultDto resultDto) throws SOAPException,Exception{
		// 1. 返回对象
		EditNetworkAccessConfigDto dto = new EditNetworkAccessConfigDto();
		// 2. 获取报文信封
		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
		String schema = getSchema();
		// 3. 获取响应节点
		Name terminalResponseName = envelope.createName(responseEl, prefix, schema);
		SOAPBodyElement terminalResponseElement = (SOAPBodyElement) message.getSOAPBody().getChildElements(terminalResponseName).next();
		super.buildBase(message, resultDto, terminalResponseElement);
		// 5. 获取具体报文中的其他属性
		Name iccid = envelope.createName("iccid", prefix, schema);
		Name effectiveDate = envelope.createName("effectiveDate", prefix, schema);
		SOAPBodyElement iccidElement = (SOAPBodyElement) terminalResponseElement.getChildElements(iccid).next();
		SOAPBodyElement effectiveDateElement = (SOAPBodyElement) terminalResponseElement.getChildElements(effectiveDate).next();
		dto.setIccId(iccidElement.getTextContent());
		dto.setEffectiveDate(DateUtil.parse(effectiveDateElement.getTextContent(),"yyyy-MM-dd"));
		resultDto.setBody(dto);
	}
	
}
