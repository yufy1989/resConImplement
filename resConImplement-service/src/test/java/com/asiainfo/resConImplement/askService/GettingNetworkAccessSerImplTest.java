package com.asiainfo.resConImplement.askService;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.resConImplement.service.askService.GettingNetworkAccessSerImpl;
import com.asiainfo.resConImplement.utils.Identities;
import com.google.common.collect.Maps;

/**
* 类说明：查询卡通信计划测试类
* @author yufy
* @date 2016年8月30日 下午2:30:59
*/
public class GettingNetworkAccessSerImplTest {
	private GettingNetworkAccessSerImpl service;
	@Before
	public void init() {
		System.setProperty("spring.profiles.active", "test");
		@SuppressWarnings("resource")
		ApplicationContext aCtx = new ClassPathXmlApplicationContext(new String[] { "classpath*:META-INF/spring/applicationContext.xml","classpath*:META-INF/spring/applicationContext-mybatis.xml"});
		System.out.println("----------------------INIT CONTEXT-----------------------------");
		if (aCtx != null) {
			service = (GettingNetworkAccessSerImpl) aCtx.getBean("gettingNetworkAccessService");
		}
	}
	@Test 
	public void getNetworkAccessConfig(){
		Map<String,String> map=Maps.newHashMap();
		map.put("iccId", "89860115623101106815");
		map.put("interfaceName", "TEST");
		map.put("messageId",String.valueOf(Identities.randomLong()));
		map.put("token", "Ford");
		service.getNetworkAccessConfig(map);
	}
}
