package com.asiainfo.resConImplement.miniService.askMiniService;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.common.util.ConversionUtil;
import com.asiainfo.resConImplement.dto.InterQueryJasperInfoDto;
import com.asiainfo.resConImplement.dto.PolicyQuotaDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.jasper.PolicyQuotaAdapter;
import com.asiainfo.resConImplement.mapper.InterQueryJasperInfoPoMapper;
import com.asiainfo.resConImplement.po.InterQueryJasperInfoPo;
import com.asiainfo.resConImplement.utils.Exceptions;
import com.google.common.collect.Maps;

/**
* 类说明：流量查询接口微服务
* @author chencq
* @date 2016年7月25日 上午19:25:55
*/
@Service
public class MinPolicyQuotaImpl {
	
	private static Logger logger = LoggerFactory.getLogger(MinPolicyQuotaImpl.class);
	
	/**
	 * 访问Jasper的PolicyQuotaAdapter适配器
	 */
	@Autowired
	private PolicyQuotaAdapter policyQuotaAdapter;
	
	@Autowired
	private InterQueryJasperInfoPoMapper interQueryJasperInfoPoMapper;

	/**
	 * 功能描述：流量查询
	 * @author chencq
	 * @date 2016年7月25日 下午19:25:55
	 * @param @param imsi
	 * @param @param messageId 用于记录流水
	 * @param @return
	 */
	public ResultDto<PolicyQuotaDto> getPolicyQuotaInfo(String imsi,String messageId,String askinterface,String token) {
		logger.info("[start]MinPolicyQuotaImpl:getPolicyQuotaInfo param is imsi:{},messageId:{}",imsi,messageId);
		
		if(imsi==null||"".equals(imsi.trim())||messageId==null||"".equals(messageId.trim())){
			logger.error("流量查询,参数为空。");
			return null;
		}
		ResultDto<PolicyQuotaDto> resultDto = null;
		
		try {
			resultDto = policyQuotaAdapter.getPolicyQuotaInfo(imsi, messageId,token);
		} catch(Exception e){
			logger.error("[error] Exception message:" + Exceptions.getStackTraceAsString(e));
			resultDto.setCode("0000115");
			resultDto.setMessage("调用jasper异常");
			//Jasper记录实体类
			InterQueryJasperInfoDto jsdDto = initInterQueryJasper(imsi,messageId,resultDto.toString(),resultDto.getHead().getCode(),askinterface);
			//进行Jasper记录入库
			insertInterQueryJasper(jsdDto);
		}
		//Jasper记录实体类
		InterQueryJasperInfoDto jsdDto = initInterQueryJasper(imsi,messageId,resultDto.toString(),resultDto.getHead().getCode(),askinterface);
		//进行Jasper记录入库
		insertInterQueryJasper(jsdDto);
		logger.info("[end]MinPolicyQuotaImpl:getPolicyQuotaInfo respParam is ResultDto<PolicyQuotaDto>:{}",resultDto.toString());
		return resultDto;
	}
	
	/**
	 * 功能描述：组装调用JASPER接口记录表实体
	 * @author chencq
	 * @date 2016年7月29日 下午17:40:43
	 * @param @param reqDto
	 * @param @return 
	 * @return InterQueryJasperInfoDto
	 */
	public InterQueryJasperInfoDto initInterQueryJasper(String imsi,String messageId,String resultDto,String code,String askinterface){
		InterQueryJasperInfoDto jsd = new InterQueryJasperInfoDto();
		
		//输入参数
		Map<String,String> map=Maps.newHashMap();
		map.put("imsi", imsi);
		//jsd.setId((int)Identities.randomLong());
		//jsd.setIccid("");
		jsd.setReturntime(new Date());
		jsd.setQueryaskinterface(askinterface);
		jsd.setSerialnumber(messageId);
		jsd.setReturnparameter(resultDto);
		jsd.setReturnstate(code);
		jsd.setQueryjasperinterface("getpolicyquotainfo");
		jsd.setQueryservicename("FlowQueryService");
		jsd.setInputparameter(map.toString());
		return jsd;
	}
	
	/**
	 * 功能描述：调用JASPER接口记录表
	 * @author chencq
	 * @date 2016年7月29日 下午17:47:36
	 * @param @param jsdto
	 * @param @return 
	 * @return boolean
	 */
	public boolean insertInterQueryJasper(InterQueryJasperInfoDto jsdto){
		logger.info("MinPolicyQuotaImpl:insertInterQueryJasper of param InterQueryJasperInfoDto is {}",jsdto);
		try{
	    	if(jsdto!=null){
	    		interQueryJasperInfoPoMapper.insertSelective((InterQueryJasperInfoPo)ConversionUtil.dto2po(jsdto, InterQueryJasperInfoPo.class));
	    		return true;
	    	}
	    }catch(Exception e){
	    	logger.error("[error] MinPolicyQuotaImpl insertInterQueryJasper exception info:{}",Exceptions.getStackTraceAsString(e));
	    	return false;
	    }
		return false;
	}

}
