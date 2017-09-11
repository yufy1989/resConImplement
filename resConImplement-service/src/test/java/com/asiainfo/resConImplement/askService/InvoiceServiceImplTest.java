package com.asiainfo.resConImplement.askService;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.resConImplement.dto.InvoiceDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.GetInvoiceService;
/**
* 类说明：
* @author pankx
* @date 2016年8月27日 上午10:13:52
*/
public class InvoiceServiceImplTest {
	
	GetInvoiceService  get‌InvoiceService;
	@Before
	public void init() {

		System.setProperty("spring.profiles.active", "test");
		@SuppressWarnings("resource")
		ApplicationContext aCtx = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/applicationContext.xml",
						  "classpath*:META-INF/spring/applicationContext-mybatis.xml"});
		System.out.println("----------------------INIT CONTEXT-----------------------------");
		if (aCtx != null) {
			get‌InvoiceService = (GetInvoiceService) aCtx.getBean("get‌InvoiceService");
		}
	} 
	
	
	@Test
	public void getInvoice(){
		ResultDto<InvoiceDto> invoice = get‌InvoiceService.getInvoice("100100318","2016-07-02Z", "10000000","getInvoiceTest","ford");
		System.out.println("invoice::"+invoice);
	}
}
