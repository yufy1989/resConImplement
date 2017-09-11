//package com.asiainfo.resConImplement.askService;
//
//import java.util.Map;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.asiainfo.resConImplement.dto.TcuChangeConfigDto;
//import com.asiainfo.resConImplement.miniService.askMiniService.MiniTcuChangeConfigService;
//import com.asiainfo.resConImplement.service.askService.TcuChangeServiceImpl;
//import com.asiainfo.resConImplement.utils.Identities;
//import com.google.common.collect.Maps;
//
///**
// * 功能描述：Tch变更测试类
// * @author yufy
// * @date 2016年8月1日
// */
//public class TcuChangeServiceImplTest {
//	private TcuChangeServiceImpl service;
//	private MiniTcuChangeConfigService miniTcuChangeConfigService;
//	@Before
//	public void init() {
//		System.setProperty("spring.profiles.active", "test");
//		@SuppressWarnings("resource")
//		ApplicationContext aCtx = new ClassPathXmlApplicationContext(new String[] { "classpath*:META-INF/spring/applicationContext.xml","classpath*:META-INF/spring/applicationContext-mybatis.xml"});
//		System.out.println("----------------------INIT CONTEXT-----------------------------");
//		if (aCtx != null) {
//			service = (TcuChangeServiceImpl) aCtx.getBean("tcuChangeService");
//			miniTcuChangeConfigService = (MiniTcuChangeConfigService) aCtx.getBean("miniTcuChangeConfigService");
//		}
//	}
//	
//	@Test 
//	public void tcuChange(){
//		Map<String,String> map=Maps.newHashMap();
//		map.put("oldIccId","89860616010000747440");
//		map.put("newIccId","89860616010000747457");
//		map.put("messageId",Identities.uuid2());
//		map.put("carPricesName","宝马");
//		service.tcuChange(map);
//	}
//	@Test
//	public void tcuChangeConfigPoExtMapperTest(){
//		TcuChangeConfigDto selectCarpricesState = miniTcuChangeConfigService.selectCarpricesState("宝马");
//		System.out.println(selectCarpricesState.toString());
//	}
//}
