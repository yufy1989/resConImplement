package com.asiainfo.resConImplement.mini;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.resConImplement.dto.EditTerminalRequestParamGroupDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.TerminalDto;
import com.asiainfo.resConImplement.dto.TransferSimsToAccountRequestDto;
import com.asiainfo.resConImplement.dto.simTransferToAccountStatusListDto;
import com.asiainfo.resConImplement.jasper.BaseAdapter;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniTerminalServiceImpl;
import com.asiainfo.resConImplement.service.askService.GetTerminalDetailsServiceImpl;
import com.google.common.collect.Maps;
public class TerminalServiceImplTest {
	
	private static Logger logger = LoggerFactory.getLogger(BaseAdapter.class);
	MiniTerminalServiceImpl miniTerminalServiceImpl = null;
	GetTerminalDetailsServiceImpl getTerminalDetailsServiceImpl=null;
	@Before
	public void init() {

		System.setProperty("spring.profiles.active", "test");
		@SuppressWarnings("resource")
		ApplicationContext aCtx = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/spring/applicationContext.xml",
						  "classpath*:META-INF/spring/applicationContext-mybatis.xml"});
		System.out.println("----------------------INIT CONTEXT-----------------------------");
		if (aCtx != null) {
			miniTerminalServiceImpl = (MiniTerminalServiceImpl) aCtx.getBean("miniTerminalServiceImpl");
			getTerminalDetailsServiceImpl = (GetTerminalDetailsServiceImpl) aCtx.getBean("getTerminalDetailsService");
		}
	} 
	
	
	@Test
	public void GetTerminalDetails(){
		//89860115623101107763
		miniTerminalServiceImpl.GetTerminalDetails("89860615010028471818", "11111111111","","Ford");
	}
	@Test
	public void GetTerminalDetailsServiceImpl(){
		Map<String,String> map=Maps.newHashMap();
		map.put("iccId", "89860114623100552697");
		map.put("interfaceName", "hahaha");
		map.put("messageId", "122232135");
		map.put("token", "Ford");
		ResultDto<TerminalDto> result = getTerminalDetailsServiceImpl.getTerminalDetails(map);
	}
//	@Test
//	public void EditTerminal(){
//		EditTerminalRequestParamGroupDto dto=new EditTerminalRequestParamGroupDto();
//		dto.setChangeType(1);
//		dto.setIccId("89860115623101106815");
//		dto.setTargetValue("5LMCJ2A90FUJ00143");
//		miniTerminalServiceImpl.EditTerminal(dto,"1222323511111","");
//	}
	@Test
	public void EditTerminal1(){
		EditTerminalRequestParamGroupDto dto=new EditTerminalRequestParamGroupDto();
		dto.setChangeType(17);
		dto.setIccId("11");
		dto.setTargetValue("Lincoln");
		miniTerminalServiceImpl.EditTerminal(dto,"1222323511111","","");
	}
	@Test
	public void EditTerminal2(){
		EditTerminalRequestParamGroupDto dto=new EditTerminalRequestParamGroupDto();
		dto.setChangeType(18);
		dto.setIccId("89860114623100552697");
		dto.setTargetValue("MKZ");
		miniTerminalServiceImpl.EditTerminal(dto,"1222323511111","","token");
	}
	@Test
	public void EditTerminal3(){
		EditTerminalRequestParamGroupDto dto=new EditTerminalRequestParamGroupDto();
		dto.setChangeType(19);
		dto.setIccId("89860114623100552697");
		dto.setTargetValue("2017");
		miniTerminalServiceImpl.EditTerminal(dto,"1222323511111","","token");
	}
	@Test
	public void EditTerminal4(){
		EditTerminalRequestParamGroupDto dto=new EditTerminalRequestParamGroupDto();
		dto.setChangeType(73);
		dto.setIccId("89860114623100552697");
		dto.setTargetValue("Gas");
		miniTerminalServiceImpl.EditTerminal(dto,"1222323511111","","token");
	}
////	
	@Test
	public void transferSimstoAccount(){
		long startTime=System.currentTimeMillis();
		TransferSimsToAccountRequestDto dto=new TransferSimsToAccountRequestDto();
		List<String> list=new ArrayList<String>();
//		list.add("89860115623101106591");
		list.add("89860114623100552697");
//		list.add("89860115623101106815");
		dto.setAccountId("1005390171");
//		dto.setCommPlanName("310WLW005703_DATA_SMSMT_4G_SP_3");
		dto.setCommPlanName("310WLW005702_3G_Migrated SIM Plan - DO NOT CHANGE");
		dto.setRatePlanName("Ford_MON-FLEX_20M_3G_MIG");
		dto.setIccidList(list);
		ResultDto<simTransferToAccountStatusListDto> result = miniTerminalServiceImpl.TransferSimstoAccount(dto,"1511111651711","ddcc","Ford");
		long endTime=System.currentTimeMillis();
		System.out.println(endTime-startTime);
	}
	
//	@Test
//	public void SimTest(){
//		long startTime=System.currentTimeMillis();
////		List<SimPo> allRecords = simTest.getAllRecord();
//		for (int i = 0; i < 100000; i++) {
////			SimPo record = allRecords.get(i);
////			List<String> list=new ArrayList<String>();
////			list.add(record.getIccid());
////			TransferSimsToAccountRequestDto dto=new TransferSimsToAccountRequestDto();
////			dto.setAccountId("100100318");
////			dto.setCommPlanName("310WLW000864_DATA_MT_4G_SP");
////			dto.setRatePlanName("310WLW000864_S_MON-FLEX_20M_NEW ");
////			dto.setIccidList(list);
////			ResultDto<simTransferToAccountStatusListDto> result = miniTerminalServiceImpl.TransferSimstoAccount(dto,"1511111651711","ddcc");
////			logger.info("完成第:"+i+"个!"+"lalala啦啦啦啦啦lalala啦啦啦啦啦lalala啦啦啦啦啦lalala啦啦啦啦啦lalala啦啦啦啦啦lalala啦啦啦啦啦lalala啦啦啦啦啦lalala啦啦啦啦啦lalala啦啦啦啦啦");
//		}
//		long endTime=System.currentTimeMillis();
//		System.out.println("开始时间:"+startTime+","+"结束时间:"+endTime+"消耗时间:"+(endTime-startTime));
//	}
}
