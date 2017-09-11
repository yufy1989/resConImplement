package com.asiainfo.resConImplement.service.askService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.dto.DataUsageDetailDto;
import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.TerminalUsageDetailDto;
import com.asiainfo.resConImplement.dto.UsageDetailsDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.UsageDataDetailsService;
import com.asiainfo.resConImplement.miniService.askMiniService.MinServiceTypeZoneRuleImpl;
import com.asiainfo.resConImplement.miniService.askMiniService.MinTerminalUsageDataDetailImpl;
import com.asiainfo.resConImplement.miniService.loggerMiniService.MiniInterAccessRecordServiceImpl;
import com.asiainfo.resConImplement.utils.Exceptions;

@Service("usageDataDetailsService")
public class UsageDataDetailsServiceImpl implements UsageDataDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UsageDataDetailsServiceImpl.class);

	@Autowired
	private MinTerminalUsageDataDetailImpl minTerminalUsageDataDetailImpl;
	
	@Autowired
	private MinServiceTypeZoneRuleImpl minServiceTypeZoneRuleImpl;
	
	@Autowired
	private MiniInterAccessRecordServiceImpl MiniInterAccessRecordServiceImpl;
	
	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> getUsageDataDetails(String iccid, String cycleStartDate, String serviceType, int pageNumber,
			String messageId,String askinterface,String token) {
		logger.info("UsageDataDetailsServiceImpl:getUsageDataDetails request param iccid:{},cycleStartDate:{},serviceType:{},pageNumber:{},messageId:{},askinterface:{},token:{}",iccid,cycleStartDate,serviceType,pageNumber,messageId,askinterface,token);
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		if(StringUtils.isBlank(iccid) || StringUtils.isBlank(cycleStartDate) || "null".equals(String.valueOf(pageNumber)) || StringUtils.isBlank(messageId) || StringUtils.isBlank(askinterface)||StringUtils.isBlank(token)){
			resultMap.put("code", "0000111");
			resultMap.put("message", "参数为空！");
			resultMap.put("data", null);
		    return  resultMap;
		}
		
		/*入库访问记录表*/
		String paramStr = "{iccid:"+iccid+",cycleStartDate:"+cycleStartDate+",pageNumber:"+pageNumber+",messageId:"+messageId+",askinterface:"+askinterface+",token:"+token+"}";
		interAccessRecord(iccid,messageId,askinterface,paramStr);
		
		try {
			ResultDto<UsageDetailsDto> usageDetails = minTerminalUsageDataDetailImpl.getTerminalUsageDataDetails(iccid, cycleStartDate, pageNumber, messageId,askinterface,token);
			if (usageDetails != null && "0000000".equals(usageDetails.getHead().getCode())) {
				String zone = minServiceTypeZoneRuleImpl.selectByServiceType(serviceType).getZone();//资费计划区域
				if(zone==null){
					logger.error("没有与该服务类型对应的zone,serviceType：{},zone:{}","",zone);
					resultMap.put("code", "0000113");
					resultMap.put("message", "没有与该服务类型对应的zone");
					resultMap.put("data", null);
					return resultMap;
				}
				int usage = 0;//用量
				if(usageDetails.getBody().getUsageDetails()!=null){
					for (DataUsageDetailDto dataUsageDetailDto : usageDetails.getBody().getUsageDetails()) {
						if(zone.equals(dataUsageDetailDto.getZone())){
							if(dataUsageDetailDto.getDataVolume()!=null){
								usage += dataUsageDetailDto.getDataVolume();
							}
						}
					}
				}
				TerminalUsageDetailDto terminalUsageDetailDto = new TerminalUsageDetailDto();
				terminalUsageDetailDto.setUsage(usage);
				terminalUsageDetailDto.setServiceType(serviceType);
				terminalUsageDetailDto.setLeft(null);
				terminalUsageDetailDto.setQueryTime(new Date().toLocaleString());
				
				resultMap.put("code", "0000000");
				resultMap.put("message", "success");
				resultMap.put("data", terminalUsageDetailDto);
			}else{
				resultMap.put("code", usageDetails.getHead().getCode());
				resultMap.put("message", usageDetails.getHead().getMessage());
				resultMap.put("data", null);
			}
		} catch (Exception e) {
			logger.error("getUsageDataDetails 调用出错，出错信息：{}",Exceptions.getStackTraceAsString(e));
			resultMap.put("code", "0000112");
			resultMap.put("message", "调用getUsageDataDetails异常，未知的异常");
		}
		
		logger.info("UsageDataDetailsServiceImpl:getUsageDataDetails response param Map<String, Object>:{}",String.valueOf(resultMap));
		return resultMap;
	}
	
	/**
	 * @Title: interAccessRecord
	 * @Description: 
	 * @return boolean
	 * @Date 2016年12月22日 上午10:42:28
	 */
	@SuppressWarnings("unused")
	public boolean interAccessRecord(String iccid,String messageId,String interfaceName,String params){
		InterAccessRecordDto ardto = new InterAccessRecordDto();
		ardto.setIccid(iccid);
		ardto.setInterfacename(interfaceName);
		ardto.setInputparameter(String.valueOf(params));
		ardto.setCallingparty("TCRM");
		ardto.setPlatformcode("JASPER");
		ardto.setQuerystate("00000");
		ardto.setQuerytime(new Date());
		ardto.setUpdatetime(new Date());
		ardto.setSerialnumber(messageId);
		ardto.setEventname("用量查询---usageDataDetailsService");
		logger.info("UsageDataDetailsServiceImpl:interAccessRecord of param InterQueryJasperInfoDto is {}",String.valueOf(ardto));
		try{
	    	if(ardto!=null){
	    		MiniInterAccessRecordServiceImpl.insert(ardto);
	    		return true;
	    	}
	    }catch(Exception e){
	    	logger.error("插入访问记录日志异常！[error] exception info:{}",Exceptions.getStackTraceAsString(e));
	    	return false;
	    }
		return false;
	}

}
