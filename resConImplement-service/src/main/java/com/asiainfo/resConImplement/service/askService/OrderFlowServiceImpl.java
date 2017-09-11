package com.asiainfo.resConImplement.service.askService;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.SubscriberPolicyRequestDto;
import com.asiainfo.resConImplement.dto.SubscriberPolicyResponseDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.OrderFlowService;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniSubscriberPolicyImpl;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniTerminalServiceImpl;
import com.asiainfo.resConImplement.miniService.loggerMiniService.MiniInterAccessRecordServiceImpl;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
 * 类说明：流量订购
 * @author pankx
 * @date 2016年7月29日 上午10:03:14
 */
@Service("orderFlowService")
public class OrderFlowServiceImpl implements OrderFlowService {

	Logger logger = LoggerFactory.getLogger(OrderFlowServiceImpl.class);

	@Autowired
	private MiniSubscriberPolicyImpl subscriberPolicyImpl;
	
	@Autowired
	private MiniTerminalServiceImpl miniTerminalServiceImpl;
	
	@Autowired
	private MiniInterAccessRecordServiceImpl MiniInterAccessRecordServiceImpl;
	
	/**
	 * 功能描述：流量订购
	 * 
	 * @author pankx
	 * @date 2016年7月27日 下午4:05:58
	 * @param @param header
	 * @param @param body
	 * @param @param callinterfaceName 调用接口的名车
	 * @param @return
	 */
	@Override
	public ResultDto<SubscriberPolicyResponseDto> flowOrder(SubscriberPolicyRequestDto reqDto,String messageId,String token) {
		logger.info("OrderFlowServiceImpl:flowOrder param SubscriberPolicyRequestDto:{},messageId", reqDto,messageId);

		ResultDto<SubscriberPolicyResponseDto> reDto = new ResultDto<SubscriberPolicyResponseDto>();
		SubscriberPolicyResponseDto dto = new SubscriberPolicyResponseDto();
		if(reqDto==null || StringUtils.isBlank(messageId)
				|| StringUtils.isBlank(reqDto.getImsi())
				|| reqDto.getAdditionalPolicies()==null
				|| StringUtils.isBlank(reqDto.getAdditionalPolicies().get(0).getName())
				||StringUtils.isBlank(token)){
			logger.error("some param is null ,see input param log!");
			reDto.setCode("0000101");
			reDto.setMessage("某些参数为空");
			reDto.setBody(dto);
		}
		
		 InterAccessRecordDto iard = new InterAccessRecordDto();
		 ResultDto<SubscriberPolicyResponseDto> updateSubscriberPolicy  = new ResultDto<SubscriberPolicyResponseDto>();
			try{
			   iard.setSerialnumber(messageId);
			   iard.setInterfacename(reqDto.getCallInterfaceName());
			   iard.setEventname("流量订购");
			   iard.setInputparameter(reqDto.toString());
			   iard.setCallingparty("TCRM");
			   iard.setPlatformcode("JASPER");
			   iard.setQuerystate("000000");
			   iard.setQuerytime(new Date());
			   iard.setUpdatetime(new Date());
			   MiniInterAccessRecordServiceImpl.insert(iard);
			   // 流量订购-调用增加策略
		     updateSubscriberPolicy = subscriberPolicyImpl.updateSubscriberPolicy(reqDto,messageId,token);
		
			if (updateSubscriberPolicy == null) {
				logger.info("OrderFlowSerImpl:flowOrderResp call updateSubscriberPolicy is error,return updateSubscriberPolicy is null .");
				reDto.setCode("0000102");
				reDto.setMessage("订购失败");
				reDto.setBody(dto);
				return reDto;
			 }
			
		}catch(Exception e){
			logger.error("flowOrder 流量订购接口错误，错误信息："+Exceptions.getStackTraceAsString(e));
			reDto.setCode("0000105");
			reDto.setMessage("调用flowOrder报错信息，查看日志");
			reDto.setBody(dto);
			return reDto;
		}

		logger.info("OrderFlowSerImpl:flowOrderResp call updateSubscriberPolice,return updateSubscriberPolicy:{}",updateSubscriberPolicy);
		return reDto;
	}

	
	/**
	 * 功能描述：PCRF 单卡套餐订购追加
	 * @author baomz
	 * @date 2017年6月3日 上午10:05:09
	 * @param @param reqDto 接口参数封装
	 * @param @param messageId
	 * @param @param askInterface
	 * @param @param token
	 * @param @return 
	 * @return ResultDto<SubscriberPolicyResponseDto>
	 */
	public ResultDto<SubscriberPolicyResponseDto> updateSubscriberPolicy(SubscriberPolicyRequestDto reqDto, String messageId, String askInterface, String token) {
		
		logger.info("OrderFlowServiceImpl:updateSubscriberPolicy param SubscriberPolicyRequestDto:{},messageId", reqDto, messageId);
		
		ResultDto<SubscriberPolicyResponseDto> reDto = new ResultDto<SubscriberPolicyResponseDto>();
		SubscriberPolicyResponseDto dto = new SubscriberPolicyResponseDto();
		if(reqDto==null 
				|| StringUtils.isBlank(messageId)
				|| StringUtils.isBlank(reqDto.getImsi())
				|| reqDto.getAdditionalPolicies()==null
				|| StringUtils.isBlank(reqDto.getAdditionalPolicies().get(0).getName())
				|| StringUtils.isBlank(token)){
			logger.error("some param is null ,see input param log!");
			reDto.setCode("0000101");
			reDto.setMessage("某些参数为空");
			reDto.setBody(dto);
		}
		
		 InterAccessRecordDto iard = new InterAccessRecordDto();
		 ResultDto<SubscriberPolicyResponseDto> updateSubscriberPolicy  = new ResultDto<SubscriberPolicyResponseDto>();
		 
			try{
			   iard.setSerialnumber(messageId);
			   iard.setInterfacename(reqDto.getCallInterfaceName());
			   iard.setEventname("PCRF单卡套餐订购追加");
			   iard.setInputparameter(reqDto.toString());
			   iard.setCallingparty("TCRM");
			   iard.setPlatformcode("JASPER");
			   iard.setQuerystate("000000");
			   iard.setQuerytime(new Date());
			   iard.setUpdatetime(new Date());
			   MiniInterAccessRecordServiceImpl.insert(iard);
			   
			   // 流量订购-调用增加策略
		     updateSubscriberPolicy = subscriberPolicyImpl.updateSubscriberPolicy(reqDto, messageId, token);
		
			if (updateSubscriberPolicy == null) {
				reDto.setCode("0000102");
				reDto.setMessage("操作失败");
				reDto.setBody(dto);
				return reDto;
			 }
			
		}catch(Exception e){
			logger.error("PCRF单卡套餐订购追加接口错误，错误信息：" + Exceptions.getStackTraceAsString(e));
			reDto.setCode("0000105");
			reDto.setMessage("调用updateSubscriberPolicy报错信息，查看日志");
			reDto.setBody(dto);
			return reDto;
		}

		logger.info("OrderFlowSerImpl:updateSubscriberPolicy:{}", updateSubscriberPolicy);
		return reDto;
	}
}
