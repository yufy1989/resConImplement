package com.asiainfo.resConImplement.jasper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.resConImplement.dto.InvoiceDto;
import com.asiainfo.resConImplement.dto.ResultDto;

/**
* 类说明：
* @author pankx
* @date 2016年8月26日 下午3:13:45
*/
public class GetInvoiceAdapterTest {
		
	
	GetInvoiceAdapter getInvoiceAdapter;
	@Before
	public void init() {

		System.setProperty("spring.profiles.active", "test");
		@SuppressWarnings("resource")
		ApplicationContext aCtx = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/applicationContext.xml",
						  "classpath*:META-INF/spring/applicationContext-mybatis.xml"});
		System.out.println("----------------------INIT CONTEXT-----------------------------");
		if (aCtx != null) {
			getInvoiceAdapter = (GetInvoiceAdapter) aCtx.getBean("getInvoiceAdapter");
		}
	} 
	
	@Test
	public void getInvoice(){
		try {
			ResultDto<InvoiceDto> invoice = getInvoiceAdapter.getInvoice("100100318","2016-07-02Z", "10000000","token");
			System.out.println("invoice::"+invoice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
