package com.asiainfo.resConImplement.mini;

import java.io.IOException;
import java.util.Date;

import javax.xml.soap.SOAPException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniNetworkAccessServiceImpl;
import com.asiainfo.resConImplement.utils.Identities;
import com.sun.xml.wss.XWSSecurityException;

/**
* 类说明：
* @author Baomz
* @date 2016年7月20日 下午5:47:37
*/
public class MiniNetworkAccessServiceImplTest {
	private MiniNetworkAccessServiceImpl service;
	@Before
	public void init() {
		System.setProperty("spring.profiles.active", "test");
		@SuppressWarnings("resource")
		ApplicationContext aCtx = new ClassPathXmlApplicationContext(new String[] { "classpath*:META-INF/spring/applicationContext.xml","classpath*:META-INF/spring/applicationContext-mybatis.xml"});
		System.out.println("----------------------INIT CONTEXT-----------------------------");
		if (aCtx != null) {
			service = (MiniNetworkAccessServiceImpl) aCtx.getBean("miniNetworkAccessServiceImpl");
		}
	}
	
	@Test 
	public void getNetworkAccessConfig() throws SOAPException, IOException, XWSSecurityException, Exception{
		service.getNetworkAccessConfig("89860115623100055864","TcuChangeSer112", Identities.uuid2(),"token");
	}
	
//	@Test 
//	public void editNetworkAccessConfig() throws SOAPException, IOException, XWSSecurityException, Exception{
//		ResultDto result = service.editNetworkAccessConfig("89860114623100552713","310WLW005702_DATA_SMSMT_4G_SP_3",null, "TcuChangeSer",Identities.uuid2());
//		System.out.println(result.toString());
//	}
}
