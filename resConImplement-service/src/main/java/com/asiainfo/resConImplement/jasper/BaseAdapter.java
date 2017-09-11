package com.asiainfo.resConImplement.jasper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.resConImplement.dto.HeadDto;
import com.asiainfo.resConImplement.dto.JasperTokenInfoBean;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.jasper.exception.JasperInvokeException;
import com.asiainfo.resConImplement.jasper.exception.UserTokenException;
import com.asiainfo.resConImplement.mappings.JasperMethod;
import com.asiainfo.resConImplement.mappings.JasperService;
import com.asiainfo.resConImplement.util.JasperUtil;
import com.sun.xml.wss.ProcessingContext;
import com.sun.xml.wss.XWSSProcessor;
import com.sun.xml.wss.XWSSProcessorFactory;
import com.sun.xml.wss.XWSSecurityException;
import com.sun.xml.wss.impl.callback.PasswordCallback;
import com.sun.xml.wss.impl.callback.UsernameCallback;

/**
 * 类说明：所有的解析类都需要继承此类,用户加密消息和获取密钥等
 * 
 * @author Baomz
 * @date 2016年7月20日 下午2:11:12
 */
public abstract class BaseAdapter {

	public String service;

	public Map<String, MethodConfig> methodConfig = null;

	private static Logger logger = LoggerFactory.getLogger(BaseAdapter.class);
	protected static SOAPConnectionFactory connectionFactory;
	private static MessageFactory messageFactory;
	protected URL url;
	private static String serviceUrl;

	/**
	 * 用于保存不同厂商的LicenseKey
	 */
	private static Map<String, JasperTokenInfoBean> jasperTokenInfos = new HashMap<String, JasperTokenInfoBean>();
	private static ReloadAllTokens reloadTokens = new ReloadAllTokens(jasperTokenInfos);
	protected static String prefix;
	private static String nsUrl;

	private static XWSSProcessorFactory processorFactory;

	static {
		try {
			connectionFactory = SOAPConnectionFactory.newInstance();
			messageFactory = MessageFactory.newInstance();
			processorFactory = XWSSProcessorFactory.newInstance();
			serviceUrl = JasperUtil.getServiceUrl();
			prefix = JasperUtil.getPrefix();
			nsUrl = JasperUtil.getNsUrl();
			logger.info("初始化基本配置");
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (XWSSecurityException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 功能描述：初始化服务名称
	 * 
	 * @author Baomz
	 * @date 2016年7月21日 下午2:37:56
	 * @param @param
	 *            c
	 * @param @return
	 * @return String
	 */
	public void initService(Class<?> c) {
		JasperService s = c.getAnnotation(JasperService.class);
		service = s.value();
	}

	/**
	 * 功能描述：初始化方法配置
	 * 
	 * @author Baomz
	 * @date 2016年7月21日 下午2:49:28
	 * @param @param
	 *            c
	 * @param @return
	 * @return Map<String,MethodConfig>
	 */
	public void initMethod(Class<?> c) throws JasperInvokeException {

		Map<String, MethodConfig> map = new HashMap<String, MethodConfig>();

		Method[] mList = null;
		try {
			mList = c.getMethods();
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new JasperInvokeException("初始化方法配置失败", e);
		}
		for (int i = 0, len = mList.length; i < len; i++) {
			Method m = mList[i];
			if (m.isAnnotationPresent(JasperMethod.class)) {
				JasperMethod jasm = m.getAnnotation(JasperMethod.class);
				MethodConfig mconfig = new MethodConfig();
				mconfig.setMethod(jasm.method());
				mconfig.setRequestEl(jasm.requestEl());
				mconfig.setResponseEl(jasm.responseEl());
				map.put(m.getName(), mconfig);
			}
		}
		methodConfig = map;

	}

	/**
	 * 功能描述：对消息添加安全内容
	 * 
	 * @author Baomz
	 * @date 2016年7月20日 下午3:22:54
	 * @param @param
	 *            message SOAP 报文
	 * @param @return
	 * @param @throws
	 *            IOException
	 * @param @throws
	 *            XWSSecurityException
	 * @return SOAPMessage
	 * @throws UserTokenException
	 */
	protected SOAPMessage secureMessage(SOAPMessage message, String token)
			throws UserTokenException, XWSSecurityException, IOException {
		final JasperTokenInfoBean jasperTokenInfo = getAndFindToken(token);

		final String userName = jasperTokenInfo.getUserName();
		final String password = jasperTokenInfo.getPassword();

		CallbackHandler callbackHandler = new CallbackHandler() {
			public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
				for (int i = 0; i < callbacks.length; i++) {
					if (callbacks[i] instanceof UsernameCallback) {
						UsernameCallback callback = (UsernameCallback) callbacks[i];
						callback.setUsername(userName);
					} else if (callbacks[i] instanceof PasswordCallback) {
						PasswordCallback callback = (PasswordCallback) callbacks[i];
						callback.setPassword(password);
					} else {
						throw new UnsupportedCallbackException(callbacks[i]);
					}
				}
			}
		};
		InputStream policyStream = null;
		XWSSProcessor processor = null;
		try {
			policyStream = getClass().getClassLoader().getResourceAsStream("securityPolicy.xml");
			processor = processorFactory.createProcessorForSecurityConfiguration(policyStream, callbackHandler);
		} finally {
			if (policyStream != null) {
				policyStream.close();
			}
		}
		ProcessingContext context = processor.createProcessingContext(message);
		return processor.secureOutboundMessage(context);
	}

	/**
	 * 功能描述：创建SOAPMessage
	 * 
	 * @author Baomz
	 * @date 2016年7月20日 下午4:59:05
	 * @param @param
	 *            messageId
	 * @param @param
	 *            soapAction
	 * @param @param
	 *            requestEl
	 * @param @return
	 * @param @throws
	 *            SOAPException
	 * @return SOAPMessage
	 * @throws UserTokenException
	 * @throws SOAPException
	 */
	protected SOAPMessage createSoapMessage(String messageId, String soapAction, String requestEl, String token)
			throws UserTokenException, SOAPException {

		final JasperTokenInfoBean jasperTokenInfo = getAndFindToken(token);
		String licenseKey = jasperTokenInfo.getLicenseKey();

		String schema = getSchema();
		SOAPMessage message = messageFactory.createMessage();
		message.getMimeHeaders().addHeader("SOAPAction", soapAction);
		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
		Name terminalRequestName = envelope.createName(requestEl, prefix, schema);
		SOAPBodyElement terminalRequestElement = message.getSOAPBody().addBodyElement(terminalRequestName);
		Name msgId = envelope.createName("messageId", prefix, schema);
		SOAPElement msgElement = terminalRequestElement.addChildElement(msgId);
		msgElement.setValue(messageId);
		Name version = envelope.createName("version", prefix, schema);
		SOAPElement versionElement = terminalRequestElement.addChildElement(version);
		versionElement.setValue("1.0");
		Name license = envelope.createName("licenseKey", prefix, schema);
		SOAPElement licenseElement = terminalRequestElement.addChildElement(license);
		licenseElement.setValue(licenseKey);

		return message;
	}

	/**
	 * 功能描述：在消息中增加节点
	 * 
	 * @author Baomz
	 * @date 2016年7月20日 下午5:01:21
	 * @param @param
	 *            message
	 * @param @param
	 *            requestEl
	 * @param @param
	 *            key
	 * @param @param
	 *            value
	 * @param @throws
	 *            SOAPException
	 * @return void
	 */
	protected void addElement2Message(SOAPMessage message, String requestEl, String key, String value)
			throws SOAPException {

		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
		SOAPBody soapBody = message.getSOAPBody();
		String schema = getSchema();
		Name terminalRequestName = envelope.createName(requestEl, prefix, schema);
		Iterator<?> iterator = soapBody.getChildElements(terminalRequestName);
		if (iterator.hasNext()) {
			SOAPBodyElement terminalRequestElement = ((SOAPBodyElement) iterator.next());
			Name iccidName = envelope.createName(key, prefix, schema);
			SOAPElement iccidElement = terminalRequestElement.addChildElement(iccidName);
			if (null != value)
				iccidElement.setValue(value);
		}
	}

	/**
	 * 功能描述：返回JasperTokenInfoBean对象或者抛出异常
	 * 
	 * @author Baomz
	 * @date 2016年10月14日 下午4:20:26
	 * @param @param
	 *            token
	 * @param @return
	 * @return JasperTokenInfoBean
	 */
	public JasperTokenInfoBean getAndFindToken(String token) throws UserTokenException {
		if (jasperTokenInfos.containsKey(token)) {
			return jasperTokenInfos.get(token);
		}
		String licenseKey = JasperUtil.getLicenseKey(token);
		String userName = JasperUtil.getUserName(token);
		String password = JasperUtil.getPassword(token);
		if (licenseKey == null || userName == null || password == null) {
			JasperUtil.reLoad();
			new Thread(reloadTokens).start();
			licenseKey = JasperUtil.getLicenseKey(token);
			userName = JasperUtil.getUserName(token);
			password = JasperUtil.getPassword(token);
		}
		if (licenseKey == null || userName == null || password == null) {
			throw new UserTokenException("请检查配置文件,是否有厂商[" + token + "]" + "相关的[Licensekey, UserName, Password]配置。");
		}

		JasperTokenInfoBean tokenInfo = new JasperTokenInfoBean(userName, password, licenseKey);
		jasperTokenInfos.put(token, tokenInfo);
		return tokenInfo;
	}

	/**
	 * 功能描述：根据服务名和密码返回soapaction
	 * 
	 * @author Baomz
	 * @date 2016年7月21日 上午10:42:04
	 * @param @param
	 *            service
	 * @param @param
	 *            method
	 * @param @return
	 * @return String
	 * @throws MalformedURLException
	 */
	protected String getSoapAction(String service, String method, boolean haveService) throws MalformedURLException {
		StringBuffer sb = new StringBuffer();
		sb.append(nsUrl).append("/service/");
		if (haveService) {
			sb.append(service).append("/");
		}
		sb.append(method);
		StringBuffer surl = new StringBuffer();
		surl.append(serviceUrl).append("/").append(service);
		url = new URL(surl.toString());

		return sb.toString();
	}

	/**
	 * 功能描述：用于获取 规范路径
	 * 
	 * @author Baomz
	 * @date 2016年7月21日 上午10:43:11
	 * @param @return
	 * @return String
	 */
	protected String getSchema() {
		return nsUrl + "/schema";
	}

	/**
	 * 功能描述：根据请求报文调用 Jasper 并返回响应报文
	 * 
	 * @author Baomz
	 * @date 2016年7月21日 上午10:43:45
	 * @param @param
	 *            request
	 * @param @return
	 * @return SOAPMessage
	 * @throws SOAPException
	 */
	protected SOAPMessage callJasper(SOAPMessage request) throws SOAPException {
		SOAPConnection connection = connectionFactory.createConnection();
		return connection.call(request, url);

	}

	/**
	 * 功能描述：获取一个SOAP报文的文本内容
	 * 
	 * @author Baomz
	 * @date 2016年7月21日 上午10:45:17
	 * @param @param
	 *            soapMessage
	 * @param @return
	 * @return String
	 * @throws IOException
	 * @throws SOAPException
	 */
	protected String getSoapMessage2String(SOAPMessage soapMessage) throws SOAPException, IOException {
		if (soapMessage == null) {
			return "SOAPMessage is null.";
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String reqMsgXml = null;

		((SOAPMessage) soapMessage).writeTo(out);
		reqMsgXml = out.toString("UTF-8");
		out.close();

		return reqMsgXml;
	}

	/**
	 * 功能描述：解析基本的属性
	 * 
	 * @author Baomz
	 * @date 2016年7月21日 下午12:52:58
	 * @param @param
	 *            message
	 * @param @param
	 *            dto
	 * @param @param
	 *            rsElement
	 * @return void
	 * @throws SOAPException
	 */
	protected void buildBase(SOAPMessage message, ResultDto<?> dto, SOAPBodyElement rsElement) throws SOAPException {
		SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
		String schema = getSchema();
		Name correlationId = envelope.createName("correlationId", prefix, schema);
		Name version = envelope.createName("version", prefix, schema);
		Name build = envelope.createName("build", prefix, schema);
		Name timestamp = envelope.createName("timestamp", prefix, schema);
		Iterator<?> correlationI = rsElement.getChildElements(correlationId);
		if (correlationI.hasNext()) {
			SOAPBodyElement el = ((SOAPBodyElement) correlationI.next());
			dto.setMessageId(el.getTextContent());
		}

		Iterator<?> versionI = rsElement.getChildElements(version);
		if (versionI.hasNext()) {
			SOAPBodyElement el = ((SOAPBodyElement) versionI.next());
			dto.setVersion(el.getTextContent());
		}

		Iterator<?> buildI = rsElement.getChildElements(build);
		if (buildI.hasNext()) {
			SOAPBodyElement el = ((SOAPBodyElement) buildI.next());
			dto.setBuild(el.getTextContent());
		}

		Iterator<?> timestampI = rsElement.getChildElements(timestamp);
		if (timestampI.hasNext()) {
			SOAPBodyElement el = ((SOAPBodyElement) timestampI.next());
			dto.setTimestamp(el.getTextContent());
		}

	}

	/**
	 * 功能描述：构建错误对象
	 * 
	 * @author Baomz
	 * @date 2016年7月21日 下午6:34:58
	 * @param @param
	 *            message
	 * @param @param
	 *            dto
	 * @return void
	 * @throws SOAPException
	 */
	protected HeadDto buildFaultMessage(SOAPMessage message) throws SOAPException {

		HeadDto dto = new HeadDto();
		SOAPEnvelope envelope;

		String schema = getSchema();
		envelope = message.getSOAPPart().getEnvelope();
		SOAPFault fault = message.getSOAPBody().getFault();
		Name detailName = envelope.createName("detail");
		Name errorName = envelope.createName("error", prefix, schema);
		Name requestIdName = envelope.createName("requestId", prefix, schema);
		Name messageName = envelope.createName("message", prefix, schema);
		SOAPElement detail = (SOAPElement) fault.getChildElements(detailName).next();
		String faultCode = fault.getFaultString();
		String faultMessage = "";
		String requestId = "";
		String faultError = "";
		dto.setCode(faultCode);
		Iterator<?> errorI = detail.getChildElements(errorName);

		if (errorI.hasNext()) {
			SOAPBodyElement el = ((SOAPBodyElement) errorI.next());
			faultError = el.getTextContent();
			dto.setFaultError(faultError);
		}
		Iterator<?> requestIdI = detail.getChildElements(requestIdName);

		if (requestIdI.hasNext()) {
			SOAPBodyElement el = ((SOAPBodyElement) requestIdI.next());
			requestId = el.getTextContent();
			dto.setFaultRequestId(requestId);

		}
		Iterator<?> messageI = detail.getChildElements(messageName);
		if (messageI.hasNext()) {
			SOAPBodyElement el = ((SOAPBodyElement) messageI.next());
			faultMessage = el.getTextContent();
			dto.setMessage(faultMessage);

		}
		logger.error("响应错误, Code:{}, Message:{}", faultCode, faultMessage);
		return dto;

	}
}
