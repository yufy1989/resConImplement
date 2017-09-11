package com.asiainfo.resConImplement.mini;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.resConImplement.dto.ActivateTerminalEventRequestDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniActivateTerminalEventImpl;

/**
* 类说明：
* @author baomz
* @date 2017年6月2日 下午4:21:03
*/
public class MiniActivateTerminalEventImplTest {

	MiniActivateTerminalEventImpl miniService;
	@Before
	public void init() {

		System.setProperty("spring.profiles.active", "test");
		@SuppressWarnings("resource")
		ApplicationContext aCtx = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/applicationContext.xml",
						  "classpath*:META-INF/spring/applicationContext-mybatis.xml"});
		System.out.println("----------------------INIT CONTEXT-----------------------------");
		if (aCtx != null) {
			miniService = (MiniActivateTerminalEventImpl) aCtx.getBean("miniActivateTerminalEventImpl");
		}
	} 
	
	@Test
	public void activateTerminalEvent(){
		try {
			ActivateTerminalEventRequestDto dto = new ActivateTerminalEventRequestDto();
			//89860116770001067404、89860116770001067396
			dto.setIccid("89860116770001067404");
			dto.setEventName("eventName");
			ResultDto invoice = miniService.activateTerminalEvent(dto, "activateTerminalEvent", "000d", "Ford");
			System.out.println("invoice::"+invoice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
