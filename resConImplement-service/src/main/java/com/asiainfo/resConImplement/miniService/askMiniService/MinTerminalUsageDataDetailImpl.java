package com.asiainfo.resConImplement.miniService.askMiniService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.common.util.ConversionUtil;
import com.asiainfo.resConImplement.dto.InterQueryJasperInfoDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.UsageDetailsDto;
import com.asiainfo.resConImplement.jasper.UsageDetailsAdapter;
import com.asiainfo.resConImplement.mapper.InterQueryJasperInfoPoMapper;
import com.asiainfo.resConImplement.po.InterQueryJasperInfoPo;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
* 类说明：单卡不同区域的用量查询服务
* @author chencq
* @date 2016年7月25日 上午19:25:55
*/
@Service
public class MinTerminalUsageDataDetailImpl {
	
	private static Logger logger = LoggerFactory.getLogger(MinTerminalUsageDataDetailImpl.class);
	
	@Autowired
	private UsageDetailsAdapter usageDetailsAdapter;
	
	@Autowired
	private InterQueryJasperInfoPoMapper interQueryJasperInfoPoMapper;

	/**
	 * 功能描述：流量查询
	 * @author chencq
	 * @date 2016年7月25日 下午19:25:55
	 * @param @param imsi
	 * @param @param messageId 用于记录流水
	 * @param @return
	 * @throws Exception 
	 */
	public ResultDto<UsageDetailsDto> getTerminalUsageDataDetails(String iccid, String cycleStartDate,int pageNumber,String messageId,String askinterface,String token) {
		logger.info("[start]MinTerminalUsageDataDetailImpl:GetTerminalUsageDataDetails param is is iccid:{},cycleStartDate:{},pageNumber:{},messageId:{}",iccid,cycleStartDate,pageNumber,messageId);
		
		ResultDto<UsageDetailsDto> resultDto = null;
		
		if(iccid==null||"".equals(iccid.trim())||cycleStartDate==null||"".equals(cycleStartDate.trim())
				||messageId==null||"".equals(messageId.trim())){
			logger.error("流量查询,参数为空。");
			resultDto = new ResultDto<>();
			resultDto.setCode("0000011");
			resultDto.setMessage("error parameter,parameter is null!!!");
			return resultDto;
		}
		
		try {
			resultDto = usageDetailsAdapter.getTerminalUsageDataDetails(iccid, cycleStartDate, pageNumber, messageId,token);
		} catch (Exception e) {
			logger.error("[error] Exception message:" + Exceptions.getStackTraceAsString(e));
			resultDto.setCode("0000115");
			resultDto.setMessage("调用jasper异常");
			//Jasper记录实体类并入库
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("iccid", iccid);
			paramMap.put("cycleStartDate", cycleStartDate);
			paramMap.put("pageNumber", pageNumber);
			InterQueryJasperInfoDto jsdDto = initInterQueryJasper(paramMap,messageId,resultDto.toString(),resultDto.getHead().getCode(),askinterface);
			insertInterQueryJasper(jsdDto);//进行Jasper记录入库
		}
		
		//Jasper记录实体类
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("iccid", iccid);
		paramMap.put("cycleStartDate", cycleStartDate);
		paramMap.put("pageNumber", pageNumber);
		InterQueryJasperInfoDto jsdDto = initInterQueryJasper(paramMap,messageId,resultDto.toString(),resultDto.getHead().getCode(),askinterface);
		//进行Jasper记录入库
		insertInterQueryJasper(jsdDto);
		logger.info("[end]MinPolicyQuotaImpl:GetTerminalUsageDataDetails respParam is ResultDto<PolicyQuotaDto>:{}",resultDto.toString());
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
	public InterQueryJasperInfoDto initInterQueryJasper(Map<String,Object> paramMap,String messageId,String resultDto,String code,String askinterface){
		InterQueryJasperInfoDto jsd = new InterQueryJasperInfoDto();
		
		//jsd.setId((int)Identities.randomLong());
		//jsd.setIccid("");
		jsd.setReturntime(new Date());
		jsd.setQueryaskinterface(askinterface);
		jsd.setSerialnumber(messageId);
		jsd.setReturnparameter(resultDto);
		jsd.setReturnstate(code);
		jsd.setQueryjasperinterface("billing");
		jsd.setQueryservicename("UsageDataDetailsService");
		jsd.setInputparameter(paramMap.toString());
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
		logger.info("MinTerminalUsageDataDetailImpl:insertInterQueryJasper of param InterQueryJasperInfoDto is {}",jsdto);
		try{
	    	if(jsdto!=null){
	    		interQueryJasperInfoPoMapper.insertSelective((InterQueryJasperInfoPo)ConversionUtil.dto2po(jsdto, InterQueryJasperInfoPo.class));
	    		return true;
	    	}
	    }catch(Exception e){
	    	logger.error("[error] insertInterQueryJasper exception info:{}",Exceptions.getStackTraceAsString(e));
	    	return false;
	    }
		return false;
	}

}
