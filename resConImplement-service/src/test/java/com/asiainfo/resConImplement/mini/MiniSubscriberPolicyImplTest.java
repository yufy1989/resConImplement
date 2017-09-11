package com.asiainfo.resConImplement.mini;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.resConImplement.miniService.askMiniService.MiniSubscriberPolicyImpl;
public class MiniSubscriberPolicyImplTest {
	
	
	MiniSubscriberPolicyImpl subscriberPolicyService = null;
	@Before
	public void init() {

		System.setProperty("spring.profiles.active", "test");
		@SuppressWarnings("resource")
		ApplicationContext aCtx = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/applicationContext.xml",
						  "classpath*:META-INF/spring/applicationContext-mybatis.xml"});
		System.out.println("----------------------INIT CONTEXT-----------------------------");
		if (aCtx != null) {
			subscriberPolicyService = (MiniSubscriberPolicyImpl) aCtx.getBean("miniSubscriberPolicyImpl");
		}
	} 
	
	
	@Test
	public void updateSubscriberPolicyTest(){
		
		Map<String,Object> param = new HashMap<String,Object>();
		/*param.put("imsi", "460011092697303");
		param.put("iccid", "8986061561000000017");
		param.put("messageId", "20150601090151000001");
		param.put("productId","10010");
		subscriberPolicyService.updateSubscriberPolicy(param);*/
		
	}
}
