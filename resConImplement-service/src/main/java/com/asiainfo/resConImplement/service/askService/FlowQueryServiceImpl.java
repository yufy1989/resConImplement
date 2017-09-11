package com.asiainfo.resConImplement.service.askService;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.dto.FlowQueryDto;
import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.dto.PolicyQuotaDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.UsageDetailsDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.FlowQueryService;
import com.asiainfo.resConImplement.miniService.askMiniService.MinPolicyQuotaImpl;
import com.asiainfo.resConImplement.miniService.askMiniService.MinTerminalUsageDataDetailImpl;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniTerminalServiceImpl;
import com.asiainfo.resConImplement.miniService.loggerMiniService.MiniInterAccessRecordServiceImpl;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
 * 类说明：流量查询接口实现
 * @author chencq
 * @date 2016年7月29日下午2:16:01
 */
@Service("flowQueryService")
public class FlowQueryServiceImpl implements FlowQueryService {
	
	private static Logger logger = LoggerFactory.getLogger(FlowQueryServiceImpl.class); 
	
	@Resource
	private MinPolicyQuotaImpl policyQuotaService;
	
	@Resource
	private MinTerminalUsageDataDetailImpl usageDetailService;
	
	@Resource
	private MiniTerminalServiceImpl miniTerminalServiceImpl;
	
	@Autowired
	private MiniInterAccessRecordServiceImpl MiniInterAccessRecordServiceImpl;

	/**
	 * @Title: flowQuery
	 * @Description: 流量查询方法实现
	 * @return ResultDto<FlowQueryDto>
	 * @Date 2016年7月29日 下午2:14:16
	 */
	@Override
	public ResultDto<FlowQueryDto> flowQuery(String imsi, String iccid, String cycleStartDate, int pageNumber, String messageId,String askinterface,String token) {
		logger.debug("FlowQuerySerImpl:flowQuery Param imsi:{},iccid:{},messageId:{},askinterface:{},token:{}",imsi,iccid,messageId,askinterface,token);

		//返回参数
		ResultDto<FlowQueryDto> result = new ResultDto<FlowQueryDto>();
		if(StringUtils.isBlank(imsi) || StringUtils.isBlank(iccid) || StringUtils.isBlank(cycleStartDate) || "null".equals(String.valueOf(pageNumber)) || StringUtils.isBlank(messageId) || StringUtils.isBlank(askinterface)||StringUtils.isBlank(token)){
			result.setCode("0000111");
			result.setMessage("参数为为空");
		    return  result;
		}
		
		/*入库访问记录表*/
		String paramStr = "{iccid:"+iccid+",imsi:"+imsi+",cycleStartDate:"+cycleStartDate+",pageNumber:"+pageNumber+",messageId:"+messageId+",askinterface:"+askinterface+",token:"+token+"}";
		interAccessRecord(iccid,messageId,askinterface,paramStr);
		
		FlowQueryDto dto = new FlowQueryDto();
		try {
			//调用GetTerminalUsageDataDetails接口
			ResultDto<UsageDetailsDto> reqResult = usageDetailService.getTerminalUsageDataDetails(iccid, cycleStartDate, pageNumber, messageId, askinterface, token);
			
			if(reqResult!=null&&!"000000".equals(reqResult.getHead().getCode())){
				logger.info("FlowQuerySerImpl:flowQuery call GetTerminalUsageDataDetails is error return code：{}:",reqResult.getHead().getCode());
				result.setBody(dto);
				return result;
			}
			//获取serviceType和zone参数
			UsageDetailsDto usageDetailsDto = reqResult.getBody();
			String serviceType = usageDetailsDto.getUsageDetails().get(0).getServiceType();
			String zone = usageDetailsDto.getUsageDetails().get(0).getServiceType();
			if(serviceType==null||"".equals(serviceType)){
				logger.info("FlowQuerySerImpl:flowQuery call GetTerminalUsageDataDetails responseParam of serviceType is null return code：{}:",reqResult.getHead().getCode());
				result.setBody(dto);
				return result;
			}
			if(zone==null||"".equals(zone)){
				logger.info("FlowQuerySerImpl:flowQuery call GetTerminalUsageDataDetails responseParam of zone is null return code：{}:",reqResult.getHead().getCode());
				result.setBody(dto);
				return result;
			}
			//绿色服务(车厂部分)
			if("1".equals(serviceType)){
				dto.setResult(miniTerminalServiceImpl.GetTerminalDetails(iccid, messageId,askinterface,token));
				result.setBody(dto);
			}
			//灰色服务(车厂部分)
			else if("2".equals(serviceType)){
				dto.setResult(miniTerminalServiceImpl.GetTerminalDetails(iccid, messageId,askinterface,token));
				result.setBody(dto);
			}
			//蓝色服务(余量)
			else if("3".equals(serviceType)){
				dto.setResult(policyQuotaService.getPolicyQuotaInfo(imsi, messageId,askinterface,token));
				result.setBody(dto);
			}
		} catch (Exception e) {
			logger.error("flowQuery 调用japser出错，出错信息：{}",Exceptions.getStackTraceAsString(e));
			result.setCode("0000112");
			result.setMessage("调用Jasper异常，未知的异常");
		}
		
		logger.info("FlowQuerySerImpl:flowQueryResp:{}"+String.valueOf(result));
		return result;
	}
	
	/**
	 * 功能描述：PCRF 单卡套餐余量查询
	 * @author baomz
	 * @date 2017年6月2日 下午12:14:11
	 * @param @param imsi
	 * @param @param messageId
	 * @param @param askinterface
	 * @param @param token
	 * @param @return 
	 * @return ResultDto<PolicyQuotaDto>
	 */
	public ResultDto<PolicyQuotaDto> getPolicyQuotaInfo(String iccid, String imsi,String messageId,String askinterface,String token) {
		ResultDto<PolicyQuotaDto> result = new ResultDto<PolicyQuotaDto>();
		if(StringUtils.isBlank(iccid) || StringUtils.isBlank(imsi) || StringUtils.isBlank(messageId) || StringUtils.isBlank(askinterface)||StringUtils.isBlank(token)){
			result.setCode("0000111");
			result.setMessage("参数为为空");
		    return  result;
		}
		
		/*入库访问记录表*/
		String paramStr = "{imsi:"+imsi+",messageId:"+messageId+",askinterface:"+askinterface+",token:"+token+"}";
		interAccessRecord(iccid,messageId,askinterface,paramStr);
		
		ResultDto<PolicyQuotaDto>  dto = policyQuotaService.getPolicyQuotaInfo(imsi, messageId, askinterface, token);
		return dto;
		
		
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
		ardto.setSerialnumber("");
		ardto.setEventname("流量查询---flowQuery");
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
