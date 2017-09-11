package com.asiainfo.resConImplement.miniService.askMiniService;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.common.util.ConversionUtil;
import com.asiainfo.resConImplement.dto.FlowOrderRecordDto;
import com.asiainfo.resConImplement.dto.InterQueryJasperInfoDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.SubscriberPolicyRequestDto;
import com.asiainfo.resConImplement.dto.SubscriberPolicyResponseDto;
import com.asiainfo.resConImplement.jasper.FlowOrderAdapter;
import com.asiainfo.resConImplement.mapper.FlowOrderRecordPoMapper;
import com.asiainfo.resConImplement.mapper.InterQueryJasperInfoPoMapper;
import com.asiainfo.resConImplement.po.FlowOrderRecordPo;
import com.asiainfo.resConImplement.po.InterQueryJasperInfoPo;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
 * 类说明：实现subscriberPolicy有关的服务
 * 
 * @author pankx
 * @date 2016年7月25日 下午5:48:36
 */
@Service
public class MiniSubscriberPolicyImpl {

	Logger logger = LoggerFactory.getLogger(MiniSubscriberPolicyImpl.class);

	@Autowired
	private FlowOrderAdapter trafficOrderAdapter;

	@Autowired
	private FlowOrderRecordPoMapper flowOrderRecordPoMapper;

	@Autowired
	private InterQueryJasperInfoPoMapper interQueryJasperInfoPoMapper;
	/**
	 * 功能描述：流量订购服务
	 * 
	 * @author pankx
	 * @date 2016年8月1日 下午2:03:22
	 * @param @param
	 *            reqDto 请求参数，看实体内容
	 * @param @param
	 *            messageId 消息唯一ID
	 * @param @return
	 * @return ResultDto<SubscriberPolicyResponseDto>
	 */
	public ResultDto<SubscriberPolicyResponseDto> updateSubscriberPolicy(SubscriberPolicyRequestDto reqDto,
			String messageId,String token) {
		logger.info("[start]MiniSubscriberPolicyImpl:updateSubscriberPolicy param is SubscriberPolicyRequestDto{}，messageId{},token:{}",reqDto,messageId,token);

		
		// 调流量订购-给ICCID的APN2或CustomerZone增加策略
		ResultDto<SubscriberPolicyResponseDto> updateSubscriberPolicy = new ResultDto<SubscriberPolicyResponseDto>();
		try {
			updateSubscriberPolicy = trafficOrderAdapter.updateSubscriberPolicy(reqDto, messageId, token);
			
			if(updateSubscriberPolicy == null){
				updateSubscriberPolicy = new ResultDto<SubscriberPolicyResponseDto>();
				updateSubscriberPolicy.setCode("000103");
				updateSubscriberPolicy.setMessage("调用Jasper出错,返回对象updateSubscriberPolicy 为空");
			}
		} catch (Exception e) {
			logger.error("[error] Exception message:" + Exceptions.getStackTraceAsString(e));
			updateSubscriberPolicy.setCode("000104");
			updateSubscriberPolicy.setMessage("调用Jasper出错");
		}
		
		// 流量订购记录信息
		FlowOrderRecordDto flowOrderRecordDto = makeFlowOrderRecord(reqDto,updateSubscriberPolicy,messageId,token);
		// 调用JASPER接口记录表
		InterQueryJasperInfoDto jsdDto = makeInterQueryJasper(reqDto,updateSubscriberPolicy,messageId,token);
		insertFlowRecord(flowOrderRecordDto);
		insertInterQueryJasper(jsdDto);
		
		
		logger.info(
				"[end]MiniSubscriberPolicyImpl:updateSubscriberPolicy respParam is  ResultDto<SubscriberPolicyResponseDto>:{}",
				updateSubscriberPolicy.toString());
		return updateSubscriberPolicy;
	}

	/**
	 * 功能描述：组装流量订购记录
	 * 
	 * @author pankx
	 * @date 2016年7月28日 下午2:38:37
	 * @param @param
	 *            reqDto
	 * @param @return
	 * @return FlowOrderRecordDto
	 */
	public FlowOrderRecordDto makeFlowOrderRecord(SubscriberPolicyRequestDto reqDto,ResultDto<SubscriberPolicyResponseDto> updateSubscriberPolicy,String messageId,String token) {
		logger.info(
				"[start]MiniSubscriberPolicyImpl:makeFlowOrderRecord reqParam SubscriberPolicyRequestDto:{},ResultDto<SubscriberPolicyResponseDto>{},messageId{},token:{}" + reqDto,updateSubscriberPolicy,messageId,token);
		FlowOrderRecordDto ford = new FlowOrderRecordDto();
		ford.setSerialnumber(messageId);
		// 2. 流量订购记录中增加imsi 节点
		if (StringUtils.isBlank(reqDto.getImsi())) {
			logger.info("实体{}的参数imsi的值为空", "SubscriberPolicyRequestDto");
		} else {
			ford.setImsi(reqDto.getImsi());
		}

		// 2.流量订购记录增加iccId节点
		if (StringUtils.isBlank(reqDto.getIccid())) {
			logger.info("实体{}的参数iccId的值为空", "SubscriberPolicyRequestDto");
		} else {
			ford.setIccid(reqDto.getIccid());
		}
		// 2. 流量订购记录增加effectiveDate节点
		if (reqDto.getEffectiveDate() == null) {
			logger.info("实体{}的参数effectiveDate的值为空", "SubscriberPolicyRequestDto");
		} else {
			ford.setEffectiveDate(reqDto.getEffectiveDate());
		}
		// 2.流量订购记录增加inlineProcess节点
		if (reqDto.getInlineProcess() == null) {
			logger.info("实体{}的参数inlineProcess的值为空", "SubscriberPolicyRequestDto");
		} else {
			ford.setInlineProcess(reqDto.getInlineProcess());
		}
		// 2. 流量订购记录增加notificationURL节点
		if (StringUtils.isBlank(reqDto.getNotificationURL())) {
			logger.info("实体{}的参数notificationURL的值为空", "SubscriberPolicyRequestDto");
		} else {
			ford.setNotificationUrl(reqDto.getNotificationURL());
		}

		if (reqDto.getSubscriberPolicies() == null || reqDto.getSubscriberPolicies().size() <= 0) {
			logger.info("实体{}的参数集合{}的值为空", "SubscriberPolicyRequestDto", "subscriberPolicies");

		} else {
			ford.setSubscriberPolicies(reqDto.getSubscriberPolicies().toString());
		}

		if (reqDto.getAdditionalPolicies() == null || reqDto.getAdditionalPolicies().size() <= 0) {
			logger.info("实体{}的参数集合{}的值为空", "SubscriberPolicyRequestDto", "additionalPolicies");
		} else {
			ford.setAdditionalPolicies(reqDto.getAdditionalPolicies().toString());
		}
		ford.setCreateTime(new Date());
		ford.setReturnstate(updateSubscriberPolicy.getHead().getCode());
		logger.info("[end]MiniSubscriberPolicyImpl:makeFlowOrderRecord respParam FlowOrderRecordDto:{}" + ford);
		return ford;
	}

	/**
	 * 功能描述：组装调用JASPER接口记录表实体
	 * 
	 * @author pankx
	 * @date 2016年7月29日 下午3:33:34
	 * @param @param
	 *            reqDto
	 * @param @return
	 * @return InterQueryJasperInfoDto
	 */
	public InterQueryJasperInfoDto makeInterQueryJasper(SubscriberPolicyRequestDto reqDto,ResultDto<SubscriberPolicyResponseDto> updateSubscriberPolicy,String messageId,String token) {
		InterQueryJasperInfoDto jsd = new InterQueryJasperInfoDto();
		jsd.setSerialnumber(messageId);
		// 2.流量订购记录增加iccId节点
		if (StringUtils.isBlank(reqDto.getIccid())) {
			logger.info("实体{}的参数iccId的值为空", "SubscriberPolicyRequestDto");
		} else {
			jsd.setIccid(reqDto.getIccid());
		}

		// jsd.setId(Integer.parseInt(SeqID.SYSUSER_ID.getIdSeq())); // TODO
		// ID的获取方式
		jsd.setQueryaskinterface(reqDto.getCallInterfaceName());
		jsd.setQueryjasperinterface("UpdateSubscriberPolicy");
		jsd.setQueryservicename("OrderFlowService");
		jsd.setInputparameter(reqDto.toString());
		jsd.setReturnparameter(updateSubscriberPolicy.toString());
		jsd.setReturnstate(updateSubscriberPolicy.getHead().getCode());
		jsd.setReturntime(new Date());
		return jsd;

	}

	/**
	 * 功能描述：调用JASPER接口记录表
	 * 
	 * @author pankx
	 * @date 2016年7月29日 下午3:31:27
	 * @param @param
	 *            jsdto
	 * @param @return
	 * @return boolean
	 */
	public boolean insertInterQueryJasper(InterQueryJasperInfoDto jsdto) {
		logger.info("FlowOrderRecordSerImpl:insertInterQueryJasper of param InterQueryJasperInfoDto is {}", jsdto);
		try {
			if (jsdto != null) {
				interQueryJasperInfoPoMapper.insertSelective(
						(InterQueryJasperInfoPo) ConversionUtil.dto2po(jsdto, InterQueryJasperInfoPo.class));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 功能描述：流量订购详细记录
	 * 
	 * @author pankx
	 * @date 2016年7月28日 下午2:19:38
	 * @param @param
	 *            ford
	 * @param @return
	 */
	public boolean insertFlowRecord(FlowOrderRecordDto ford) {
		logger.info("FlowOrderRecordSerImpl:insertFlowRecord of param FlowOrderRecordDto is {}", ford);
		try {
			if (ford != null) {
				flowOrderRecordPoMapper
						.insertSelective((FlowOrderRecordPo) ConversionUtil.dto2po(ford, FlowOrderRecordPo.class));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
