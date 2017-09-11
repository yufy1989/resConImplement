package com.asiainfo.resConImplement.mini;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.miniService.askMiniService.MinTerminalUsageDataDetailImpl;

/**
* 类说明：getPolicyQuotaInfo微服务测试类
* @author chencq
* @date 2016年7月26日 下午15:43:37
*/
public class MinTerminalUsageDataDetailImplTest {

	private MinTerminalUsageDataDetailImpl service;

	@Before
	public void init() {

		System.setProperty("spring.profiles.active", "test");
		@SuppressWarnings("resource")
		ApplicationContext aCtx = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/applicationContext.xml",
				  "classpath*:META-INF/spring/applicationContext-mybatis.xml"});
		System.out.println("----------------------INIT CONTEXT-----------------------------");
		if (aCtx != null) {
			service = (MinTerminalUsageDataDetailImpl) aCtx.getBean("minTerminalUsageDataDetailImpl");
		}
	}
	
	//imsi国际移动用户识别码:460011092664390,460011092664445,460011092697303,
	@Test 
	public void getTerminalUsageDataDetails() throws Exception{
		long a = System.currentTimeMillis();
		ResultDto dto1 = service.getTerminalUsageDataDetails("89860114623100552713", "2016-08-04", 1, "12345678", "FlowQuerySerTest","Ford");
		System.out.println(dto1.toString() +"Time:"+ (System.currentTimeMillis() - a));
//		ResultDto dto2 = service.getPolicyQuotaInfo("460011092697303", "12345678","FlowQuerySerTest");
//		System.out.println(dto2.toString() +"Time:"+ (System.currentTimeMillis() - a));
//		ResultDto dto3 = service.getPolicyQuotaInfo("460011092697303", "12345678","FlowQuerySerTest");
//		System.out.println(dto3.toString() +"Time:"+ (System.currentTimeMillis() - a));
	}
}
