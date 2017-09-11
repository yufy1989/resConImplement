package com.asiainfo.resConImplement.askService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.resConImplement.dto.AdditionalPolicyTypeDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.SubscriberPolicyRequestDto;
import com.asiainfo.resConImplement.dto.SubscriberPolicyResponseDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.OrderFlowService;

/**
* 类说明：测试流量订购
* @author pankx
* @date 2016年8月1日 上午10:18:40
*/
public class OrderFlowServiceImplTest {
	OrderFlowService orderFlowService;
	@Before
	public void init() {

		System.setProperty("spring.profiles.active", "test");
		@SuppressWarnings("resource")
		ApplicationContext aCtx = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/applicationContext.xml",
						  "classpath*:META-INF/spring/applicationContext-mybatis.xml"});
		System.out.println("----------------------INIT CONTEXT-----------------------------");
		if (aCtx != null) {
			orderFlowService = (OrderFlowService) aCtx.getBean("orderFlowService");
		}
	} 
	
	@Test
	public void flowOrderTest(){
	/*	Map<String,Object> param = new HashMap<String,Object>();
		param.put("imsi", "460011092697303");
		//param.put("iccid", "8986061561000000017");
		param.put("messageId", "20150601090151000002");
		param.put("interfaceName","flowOrderTest");
		param.put("productId","10010");
		*/
	 SubscriberPolicyRequestDto reqDto = new SubscriberPolicyRequestDto();
	 reqDto.setImsi("460069001000794");
	 List<AdditionalPolicyTypeDto> additionalPolicies = new ArrayList<AdditionalPolicyTypeDto>();
	 AdditionalPolicyTypeDto policyDto = new AdditionalPolicyTypeDto();
	 policyDto.setName("S_wifi_1G_1month");
	 policyDto.setValue("true");
	 additionalPolicies.add(policyDto);
	 reqDto.setAdditionalPolicies(additionalPolicies);
	 reqDto.setEffectiveDate("2016-08-01T01:00:00Z");
	 reqDto.setCallInterfaceName("flowOrderTest");
	 reqDto.setAdditionalPolicies(additionalPolicies);
	 reqDto.setNotificationURL("http://www.baidu.com");
	 ResultDto<SubscriberPolicyResponseDto> flowOrder = orderFlowService.flowOrder(reqDto,"20150601090151w000002","Ford");
	 System.out.println("head:"+flowOrder.getHead());
	}
}
