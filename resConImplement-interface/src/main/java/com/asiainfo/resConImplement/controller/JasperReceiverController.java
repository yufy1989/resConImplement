package com.asiainfo.resConImplement.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.Enumeration;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * 类说明：接收API
 * @author chencq
 * @date 2016年8月5日 上午11:21:23
 */
@Controller
@RequestMapping("/jasperReceiver")
public class JasperReceiverController {
	
	private static Logger logger = LoggerFactory.getLogger(JasperReceiverController.class);
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    protected static String apiSecretKey = "default";
	
	/**
	 * 功能描述：接收机
	 * @author chencq
	 * @date 2016年8月5日 上午11:21:43
	 * @param model
	 * @param request
	 * @param request
	 * @return String
	 * @throws IOException 
	 */
	@RequestMapping(value = "receiver", method = RequestMethod.POST)
	public String receiver(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		logger.info("Received message from: "+request.getRemoteHost());

        Enumeration paramNameEnum = request.getParameterNames();
        while (paramNameEnum.hasMoreElements()) {
            String name = (String)paramNameEnum.nextElement();
            System.out.println(name+"="+request.getParameter(name));
        }

        // 验证签名,如果签名无效，不要处理事件.
        if (!isValidSignature(request.getParameter("timestamp"), request.getParameter("signature"))) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("text/html");
            response.getWriter().println("Bad signature");
            return "error";
        }

        // 验证签名（SHA-256）,如果签名无效,不处理事件.
        if (!isValidSignature256(request.getParameter("timestamp"), request.getParameter("signature2"))) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("text/html");
            response.getWriter().println("Bad signature2");
            return "error";
        }

        try {
            String eventType = request.getParameter("eventType");
            String dataXml = request.getParameter("data");
            InputSource inputSource = new InputSource(new StringReader(dataXml));
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(inputSource);

            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            if ("SESSION_START".equals(eventType)) {
                String iccid = xpath.evaluate("//Session/iccid/text()", doc);
                String dateSessionStarted = xpath.evaluate("//Session/dateSessionStarted/text()", doc);
                logger.info("===== Push event: session started for ICCID "+iccid+" at "+dateSessionStarted);
            } else if ("SESSION_STOP".equals(eventType)) {
                String iccid = xpath.evaluate("//Session/iccid/text()", doc);
                String dateSessionEnded = xpath.evaluate("//Session/dateSessionEnded/text()", doc);
                logger.info("===== Push event: session ended for ICCID "+iccid+" at "+dateSessionEnded);
            } else {
                logger.info("===== Push event: "+eventType);
            }

            // ==========================================================================
            // 在这里添加事件处理逻辑（如插入事件到数据库）.
            // ==========================================================================
        } catch (Throwable t) {
            logger.error("Error processing event: "+t);
            t.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/html");
            response.getWriter().println("Error processing event: "+t.getMessage());
            return "error";
        }

        // 一旦事件被成功处理,向Jasper返回一个HTTP状态：200.
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");
		return "success";
	}
	
	private boolean isValidSignature(String timestamp, String signature) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(apiSecretKey.getBytes(), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(keySpec);
            String expectedSignature = new String(Base64.encodeBase64(mac.doFinal(timestamp.getBytes())));
            if (expectedSignature.equals(signature)) {
                return true;
            } else {
                logger.error("Invalid signature: "+signature+" does not match expected signature: "+expectedSignature);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error verifying signature: "+e);
            return false;
        }
    }

    private boolean isValidSignature256(String timestamp, String signature2) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(apiSecretKey.getBytes(), HMAC_SHA256_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(keySpec);
            String expectedSignature2 = new String(Base64.encodeBase64(mac.doFinal(timestamp.getBytes())));
            if (expectedSignature2.equals(signature2)) {
                return true;
            } else {
                logger.error("Invalid signature2: "+signature2+" does not match expected signature2: "+expectedSignature2);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error verifying signature2: "+e);
            return false;
        }
    }

	public static void setApiSecretKey(String apiSecretKey) {
		JasperReceiverController.apiSecretKey = apiSecretKey;
	}
}
