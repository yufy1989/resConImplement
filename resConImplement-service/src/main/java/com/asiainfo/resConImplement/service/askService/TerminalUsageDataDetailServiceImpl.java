package com.asiainfo.resConImplement.service.askService;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.UsageDetailsDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.TerminalUsageDataDetailService;
import com.asiainfo.resConImplement.miniService.askMiniService.MinTerminalUsageDataDetailImpl;
import com.asiainfo.resConImplement.miniService.loggerMiniService.MiniInterAccessRecordServiceImpl;
import com.asiainfo.resConImplement.utils.Exceptions;

@Service("terminalUsageDataDetailService")
public class TerminalUsageDataDetailServiceImpl implements TerminalUsageDataDetailService {

	private static Logger logger = LoggerFactory.getLogger(GetTerminalDetailsServiceImpl.class);
	
	@Autowired
	private MinTerminalUsageDataDetailImpl minTerminalUsageDataDetailImpl;
	
	@Autowired
	private MiniInterAccessRecordServiceImpl MiniInterAccessRecordServiceImpl;
	
	@Override
	public ResultDto<UsageDetailsDto> GetTerminalUsageDataDetails(String iccid, String cycleStartDate, int pageNumber,
			String messageId,String askinterface,String token) {
		logger.info("TerminalUsageDataDetailServiceImpl:GetTerminalUsageDataDetails request param iccid:{},cycleStartDate:{},pageNumber:{},messageId:{},askinterface:{},token:{}",iccid,cycleStartDate,pageNumber,messageId,askinterface,token);

		ResultDto<UsageDetailsDto> usageDetails = null;
		if(StringUtils.isBlank(iccid) || StringUtils.isBlank(cycleStartDate) || "null".equals(String.valueOf(pageNumber)) || StringUtils.isBlank(messageId) || StringUtils.isBlank(askinterface)||StringUtils.isBlank(token)){
			usageDetails = new ResultDto<>();
			usageDetails.setCode("0000111");
			usageDetails.setMessage("参数为为空");
		    return  usageDetails;
		}
		
		/*入库访问记录表*/
		String paramStr = "{iccid:"+iccid+",cycleStartDate:"+cycleStartDate+",pageNumber:"+pageNumber+",messageId:"+messageId+",askinterface:"+askinterface+",token:"+token+"}";
		interAccessRecord(iccid,messageId,askinterface,paramStr);
		
		try {
			//执行查询操作
			usageDetails = minTerminalUsageDataDetailImpl.getTerminalUsageDataDetails(iccid, cycleStartDate, pageNumber, messageId, askinterface,token);
		} catch (Exception e) {
			logger.error("GetTerminalUsageDataDetails 调用japser出错，出错信息："+Exceptions.getStackTraceAsString(e));
			usageDetails.setCode("0000112");
			usageDetails.setMessage("调用Jasper异常，未知的异常");
		}
		
		logger.info("TerminalUsageDataDetailServiceImpl:GetTerminalUsageDataDetails response param ResultDto<UsageDetailsDto>:{}",String.valueOf(usageDetails));
		
		return usageDetails;
	}
	
	/**
	 * @Title: interAccessRecord
	 * @Description: 访问记录入库
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