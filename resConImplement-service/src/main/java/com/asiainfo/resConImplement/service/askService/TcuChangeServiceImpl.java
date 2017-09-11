//package com.asiainfo.resConImplement.service.askService;
//
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.alibaba.dubbo.common.utils.StringUtils;
//import com.asiainfo.resConImplement.common.util.DateUtil;
//import com.asiainfo.resConImplement.dto.AdditionalPolicyTypeDto;
//import com.asiainfo.resConImplement.dto.EditNetworkAccessConfigDto;
//import com.asiainfo.resConImplement.dto.EditTerminalRequestParamGroupDto;
//import com.asiainfo.resConImplement.dto.NacIdTypeDto;
//import com.asiainfo.resConImplement.dto.NetworkAccessConfigDto;
//import com.asiainfo.resConImplement.dto.PolicyQuotaDto;
//import com.asiainfo.resConImplement.dto.ResultDto;
//import com.asiainfo.resConImplement.dto.SubscriberPolicyRequestDto;
//import com.asiainfo.resConImplement.dto.SubscriberPolicyResponseDto;
//import com.asiainfo.resConImplement.dto.TcuChangeConfigDto;
//import com.asiainfo.resConImplement.dto.TcuChangeDto;
//import com.asiainfo.resConImplement.dto.TerminalDto;
//import com.asiainfo.resConImplement.interfaces.askInterfaces.TcuChangeService;
//import com.asiainfo.resConImplement.miniService.askMiniService.MinPolicyQuotaImpl;
//import com.asiainfo.resConImplement.miniService.askMiniService.MiniNetworkAccessServiceImpl;
//import com.asiainfo.resConImplement.miniService.askMiniService.MiniSubscriberPolicyImpl;
//import com.asiainfo.resConImplement.miniService.askMiniService.MiniTcuChangeConfigService;
//import com.asiainfo.resConImplement.miniService.askMiniService.MiniTerminalServiceImpl;
//import com.google.common.collect.Lists;
//
///**
//* 类说明：Tcu变更服务实现类
//* @author yufy
//* @date 2016年7月27日 下午4:14:01
//*/
////@Service("tcuChangeService")
//public class TcuChangeServiceImpl implements TcuChangeService {
//	
//	private static Logger logger = LoggerFactory.getLogger(TcuChangeServiceImpl.class); 
//	@Autowired
//	private MiniNetworkAccessServiceImpl miniNetworkAccessServiceImpl;
//	@Autowired
//	private MiniTerminalServiceImpl miniTerminalServiceImpl;
//	@Autowired
//	private MiniTcuChangeConfigService miniTcuChangeConfigService;
//	@Autowired
//	private MiniSubscriberPolicyImpl miniSubscriberPolicyImpl;
//	@Autowired
//	private MinPolicyQuotaImpl minPolicyQuotaImpl;
//
//	/**
//	 * 功能描述：Tcu变更服务操作
//	 * @author yufy
//	 * @date 2016年7月27日 下午4:14:38
//	 * @param @param iccId
//	 * @param @param nacId
//	 * @param @param effectiveDate
//	 * @param @param messageId
//	 */
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Override
//	public ResultDto<TcuChangeDto> tcuChange(Map<String,String> map) {
//		logger.info("--开始--Tcu变更服务操作,入参:{}",map.toString());
//		String oldIccId=map.get("oldIccId");
//		String newIccId=map.get("newIccId");
//		String messageId=map.get("messageId");
//		String carPricesName=map.get("carPricesName");
//		if(StringUtils.isBlank(oldIccId.trim())||StringUtils.isBlank(newIccId)||StringUtils.isBlank(messageId)||StringUtils.isBlank(carPricesName)){
//			logger.error("访问网络配置,参数为空。");
//			return null;
//		}
//		ResultDto<TcuChangeDto> tcuFinalResult=new  ResultDto<TcuChangeDto>();
//		TcuChangeDto tcuDto=new TcuChangeDto();
//		 tcuDto.setOldIccId(oldIccId);
//		 tcuDto.setNewIccId(newIccId);
//		 
//		logger.info("--开始--1.查询旧SIM卡的资费计划-GetTerminalDetails");
//		ResultDto<TerminalDto> getTDResult = miniTerminalServiceImpl.GetTerminalDetails(oldIccId, messageId,"TcuChangeSer");
//		if (!"0000000".equals(getTDResult.getHead().getCode())) {
//			tcuFinalResult.setBody(tcuDto);
//			tcuFinalResult.setHead(getTDResult.getHead());
////			return tcuFinalResult;
//		}
//		
//		logger.info("--开始--2.更新新SIM卡的资费计划-EditTerminal");
//		EditTerminalRequestParamGroupDto dto=new EditTerminalRequestParamGroupDto();
//		dto.setIccId(newIccId);
//		dto.setChangeType(4);
//		dto.setTargetValue(getTDResult.getBody().getRatePlan());
//		ResultDto editTerminalResult = miniTerminalServiceImpl.EditTerminal(dto, messageId,"TcuChangeSer");
//		if (!"0000000".equals(editTerminalResult.getHead().getCode())) {
//			tcuDto.setOldState(getTDResult.getBody().getStatus());
//			tcuFinalResult.setBody(tcuDto);
//			tcuFinalResult.setHead(editTerminalResult.getHead());
////			return tcuFinalResult;
//		}
//		
//		logger.info("--开始--3.询旧SIM卡的通信计划-getNetworkAccessConfig");
//		List<NacIdTypeDto> nacIdsList=Lists.newArrayList();
//		ResultDto<NetworkAccessConfigDto> netResult = miniNetworkAccessServiceImpl.getNetworkAccessConfig(oldIccId, "TcuChangeSer,getNetworkAccessConfig", messageId);
//		nacIdsList = netResult.getBody().getNacIds();
//		if (!"0000000".equals(netResult.getHead().getCode())||nacIdsList==null||nacIdsList.size()==0) {
//			tcuFinalResult.setBody(tcuDto);
//			tcuFinalResult.setHead(netResult.getHead());
////			return tcuFinalResult;
//		}
//		
//		logger.info("--开始--4.更新新SIM卡的通信计划-editNetworkAccessConfig");
//		Iterator<NacIdTypeDto> iterator = nacIdsList.iterator();
//		while (iterator.hasNext()) {
//			NacIdTypeDto nacIdTypeDto = (NacIdTypeDto) iterator.next();
//			ResultDto<EditNetworkAccessConfigDto> editResult = miniNetworkAccessServiceImpl.editNetworkAccessConfig(newIccId, nacIdTypeDto.getNacId(), "TcuChangeSer,editNetworkAccessConfig", messageId);
//			if (!"0000000".equals(editResult.getHead().getCode())) {
//				tcuFinalResult.setBody(tcuDto);
//				tcuFinalResult.setHead(editResult.getHead());
////				return tcuFinalResult;
//			} 
//		}
//		
//		logger.info("--开始--5.更新新SIM卡状态和被替换 SIM 卡状态一样-EditTerminal");
//		dto.setIccId(newIccId);
//		dto.setChangeType(3);
//		dto.setTargetValue(getTDResult.getBody().getStatus());
//		ResultDto editTerminal = miniTerminalServiceImpl.EditTerminal(dto, messageId,"TcuChangeSer");
//		if (!"0000000".equals(editTerminal.getHead().getCode())) {
//			tcuFinalResult.setBody(tcuDto);
//			tcuFinalResult.setHead(editTerminal.getHead());
////			return tcuFinalResult;
//		}
//		tcuDto.setNewState(getTDResult.getBody().getStatus());
//		
//		logger.info("--开始--6.更新旧的SIM卡状态为关闭-EditTerminal");
//		TcuChangeConfigDto selectResult = miniTcuChangeConfigService.selectCarpricesState(carPricesName);
//		if (selectResult!=null) {
//			dto.setIccId(newIccId);
//			dto.setChangeType(3);
//			dto.setTargetValue(selectResult.getState());
//			ResultDto editTerminal2=miniTerminalServiceImpl.EditTerminal(dto, messageId,"TcuChangeSer");
//			if (!"0000000".equals(editTerminal2.getHead().getCode())) {
//				tcuFinalResult.setBody(tcuDto);
//				tcuFinalResult.setHead(editTerminal.getHead());
////				return tcuFinalResult;
//			}
//			tcuDto.setOldState(selectResult.getState());
//		}
//		
//		logger.info("--开始--7.查询被替换 SIM 卡的 WiFi 剩余配额信息-GetPolicyQuotaInfo");
//		ResultDto<PolicyQuotaDto> policyQuotaInfo = minPolicyQuotaImpl.getPolicyQuotaInfo(getTDResult.getBody().getImsi(), messageId, "TcuChangeSer");
//		if (!"0000000".equals(policyQuotaInfo.getHead().getCode())) {
//			tcuFinalResult.setBody(tcuDto);
//			tcuFinalResult.setHead(editTerminal.getHead());
////			return tcuFinalResult;
//		}
//		
//		logger.info("--开始--8.更新新 SIM 卡的策略-UpdateSubscriberPolicy");
//		SubscriberPolicyRequestDto  reqDto=new SubscriberPolicyRequestDto();
//		AdditionalPolicyTypeDto addDto=new AdditionalPolicyTypeDto();
//		List<AdditionalPolicyTypeDto> additionalPolicies=Lists.newArrayList(); 
//		addDto.setName(policyQuotaInfo.getBody().getQuotaInfo().get(0).getPolicyName());
//		reqDto.setIccid(newIccId);
//		additionalPolicies.add(addDto);
//		reqDto.setAdditionalPolicies(additionalPolicies);
//		ResultDto<SubscriberPolicyResponseDto> updateSubscriberPolicy = miniSubscriberPolicyImpl.updateSubscriberPolicy(reqDto, messageId);
//		if (!"0000000".equals(updateSubscriberPolicy.getHead().getCode())) {
//			tcuFinalResult.setBody(tcuDto);
//			tcuFinalResult.setHead(updateSubscriberPolicy.getHead());
////			return tcuFinalResult;
//		}
//		logger.info("--开始--9.为新SIM卡添加VIN信息-EditTerminal");
//		dto.setIccId(newIccId);
//		dto.setChangeType(1);
//		dto.setTargetValue(getTDResult.getBody().getMsisdn());
//		ResultDto<EditTerminalRequestParamGroupDto> editResult=miniTerminalServiceImpl.EditTerminal(dto, messageId,"TcuChangeSer");
//		if (!"0000000".equals(editResult.getHead().getCode())) {
//			tcuFinalResult.setBody(tcuDto);
//			tcuFinalResult.setHead(editResult.getHead());
////			return tcuFinalResult;
//		}
//		tcuDto.setTimeStamp(DateUtil.DateToStr(new Date()));
//		tcuFinalResult.setBody(tcuDto);
//		tcuFinalResult.setHead(editResult.getHead());
//		logger.info("--结束--TCU更换服务成功!");
//		return tcuFinalResult;
//	}
//}
