package com.asiainfo.resConImplement.askService;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.resConImplement.dto.FlowQueryDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.UsageDetailsDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.FlowQueryService;
import com.asiainfo.resConImplement.interfaces.askInterfaces.TerminalUsageDataDetailService;

/**
* 类说明：流量查询测试类
* @author chencq
* @date 2016年8月1日 下午14:11:34
*/
public class TerminalUsageDataDetailServiceTest {
	
	TerminalUsageDataDetailService terminalUsageDataDetailService;
	
	@Before
	public void init() {

		System.setProperty("spring.profiles.active", "test");
		@SuppressWarnings("resource")
		ApplicationContext aCtx = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/applicationContext.xml",
						  "classpath*:META-INF/spring/applicationContext-mybatis.xml"});
		System.out.println("----------------------INIT CONTEXT-----------------------------");
		if (aCtx != null) {
			terminalUsageDataDetailService = (TerminalUsageDataDetailService) aCtx.getBean("terminalUsageDataDetailService");
		}
	} 
	
	@Test
	public void flowQueryTest(){
	 String iccid = "8986061561000000017";
	 String cycleStartDate = "2016-08-08";
	 int pageNumber = 1;
	 String messageId="1000010101010";
	 ResultDto<UsageDetailsDto> d = terminalUsageDataDetailService.GetTerminalUsageDataDetails(iccid, cycleStartDate, pageNumber, messageId, "Test", "Ford");
		System.out.println("head:"+d.getHead());
	}
}
