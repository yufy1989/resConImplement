package com.asiainfo.resConImplement.askService;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.resConImplement.service.askService.EditingNetworkAccessSerImpl;
import com.asiainfo.resConImplement.utils.Identities;

/**
* 类说明：通信计划变更测试
* @author yufy
* @date 2016年8月30日 下午2:41:09
*/
public class EditingNetworkAccessSerImplTest {
	private EditingNetworkAccessSerImpl service;
	@Before
	public void init() {
		System.setProperty("spring.profiles.active", "test");
		@SuppressWarnings("resource")
		ApplicationContext aCtx = new ClassPathXmlApplicationContext(new String[] { "classpath*:META-INF/spring/applicationContext.xml","classpath*:META-INF/spring/applicationContext-mybatis.xml"});
		System.out.println("----------------------INIT CONTEXT-----------------------------");
		if (aCtx != null) {
			service = (EditingNetworkAccessSerImpl) aCtx.getBean("editingNetworkAccessService");
		}
	}
	@Test 
	public void editNetworkAccessConfig(){
		Map<String,String> map=new HashMap<>();
		map.put("iccId", "11");
		map.put("nacId", "57318");
		map.put("effectiveDate", "");
		map.put("interfaceName", "TEST");
		map.put("messageId", String.valueOf(Identities.randomLong()));
		map.put("token", "Ford");
		service.editNetworkAccessConfig(map);
	}
}
