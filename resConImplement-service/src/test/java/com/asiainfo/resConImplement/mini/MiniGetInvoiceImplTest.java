package com.asiainfo.resConImplement.mini;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.resConImplement.dto.InvoiceDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniGetInvoiceImpl;

/**
* 类说明：
* @author pankx
* @date 2016年8月27日 上午9:38:48
*/
public class MiniGetInvoiceImplTest {
	
	MiniGetInvoiceImpl miniGetInvoiceImpl;
	@Before
	public void init() {

		System.setProperty("spring.profiles.active", "test");
		@SuppressWarnings("resource")
		ApplicationContext aCtx = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/applicationContext.xml",
						  "classpath*:META-INF/spring/applicationContext-mybatis.xml"});
		System.out.println("----------------------INIT CONTEXT-----------------------------");
		if (aCtx != null) {
			miniGetInvoiceImpl = (MiniGetInvoiceImpl) aCtx.getBean("miniGetInvoiceImpl");
		}
	} 
	
	@Test
	public void getInvoice(){
		try {
			ResultDto<InvoiceDto> invoice = miniGetInvoiceImpl.getInvoice("100100318","2016-07-02Z", "10000000","getInvoiceTest","token");
			System.out.println("invoice::"+invoice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
