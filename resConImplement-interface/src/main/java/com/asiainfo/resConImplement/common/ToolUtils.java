package com.asiainfo.resConImplement.common;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.transport.http.AbstractHTTPDestination;
/**
 * cxf工具类
 * @author zhenhw
 *
 */
public class ToolUtils {
	/**
	 * 返回请求ip地址
	 * 
	 * @return
	 */
	public static String getIP(WebServiceContext context) {
		try {
			MessageContext ctx = context.getMessageContext();
			HttpServletRequest request = (HttpServletRequest) ctx.get(AbstractHTTPDestination.HTTP_REQUEST);
			String ip = request.getRemoteAddr();
			return ip;
		} catch (Exception e) {
			return "error";
		}
	}
}
