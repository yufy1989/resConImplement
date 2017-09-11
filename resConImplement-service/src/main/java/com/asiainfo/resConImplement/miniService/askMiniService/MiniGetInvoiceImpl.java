package com.asiainfo.resConImplement.miniService.askMiniService;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.common.util.ConversionUtil;
import com.asiainfo.resConImplement.dto.InterQueryJasperInfoDto;
import com.asiainfo.resConImplement.dto.InvoiceDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.jasper.GetInvoiceAdapter;
import com.asiainfo.resConImplement.mapper.InterQueryJasperInfoPoMapper;
import com.asiainfo.resConImplement.po.InterQueryJasperInfoPo;
import com.asiainfo.resConImplement.utils.Exceptions;
import com.asiainfo.resConImplement.utils.Identities;
import com.google.common.collect.Maps;

/**
* 类说明：getInvoice 
* @author pankx
* @date 2016年8月26日 下午4:11:57
*/

@Service
public class MiniGetInvoiceImpl {
	private static Logger logger = LoggerFactory.getLogger(MiniGetInvoiceImpl.class);
	
	@Autowired
	private GetInvoiceAdapter getInvoiceAdapter;
	
	@Autowired
	private InterQueryJasperInfoPoMapper interQueryJasperInfoPoMapper;
	
	
	/**
	 * 功能描述：检索给定帐户的给定月份的发票信息。
	 * @author pankx
	 * @date 2016年8月26日 下午4:15:09
	 * @param @param accountId
	 * @param @param cycleStartDate
	 * @param @param messageId
	 * @param @return 
	 * @return ResultDto<InvoiceDto>
	 */
	public  ResultDto<InvoiceDto> getInvoice(String accountId,String cycleStartDate,String messageId,String askinterface,String token){
		logger.info("[input start param] MiniGetInvoiceImpl method getInvoice of param accountId:{},cycleStartDate:{},messageId:{}",accountId,cycleStartDate,messageId);
		ResultDto<InvoiceDto> resultDto = new ResultDto<InvoiceDto>();
		if(StringUtils.isBlank("accountId")|| StringUtils.isBlank(messageId)){
			logger.error("流量查询,参数为空。");
			resultDto.setCode("0000113");
			resultDto.setMessage("参数为空");
			resultDto.setBody(new InvoiceDto());
			return resultDto;
		}
		
		try {
			resultDto = getInvoiceAdapter.getInvoice(accountId, cycleStartDate, messageId,token);
			if(resultDto==null){
		    	resultDto = new ResultDto<InvoiceDto>();
		    	resultDto.setCode("0000114");
				resultDto.setMessage("调用jasper异常，返回对象为空");
		    }
		    
		} catch(Exception e){
			resultDto.setCode("0000115");
			resultDto.setMessage("调用jasper异常");
			logger.error("[error] exception info:{}"+Exceptions.getStackTraceAsString(e));
		}
		
		//Jasper记录实体类
		InterQueryJasperInfoDto jsdDto = initInterQueryJasper(accountId,cycleStartDate,messageId,resultDto,askinterface,token);
		//进行Jasper记录入库
		insertInterQueryJasper(jsdDto);
		logger.info("[end param] MiniGetInvoiceImpl method getInvoice of param accountId:{},cycleStartDate:{},messageId:{}",accountId,cycleStartDate,messageId);
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
	public InterQueryJasperInfoDto initInterQueryJasper(String accountId,String cycleStartDate,String messageId,ResultDto<InvoiceDto> resultDto ,String askinterface,String token){
		InterQueryJasperInfoDto jsd = new InterQueryJasperInfoDto();
		//输入参数
		Map<String,String> map=Maps.newHashMap();
		map.put("accountId", accountId);
		map.put("cycleStartDate",cycleStartDate);
		map.put("messageId",messageId);
		map.put("token",token);
		//jsd.setId((int)Identities.randomLong()); // TODO ID的获取方式
		//jsd.setIccid("");
		jsd.setReturntime(new Date());
		jsd.setQueryaskinterface(askinterface);
		jsd.setSerialnumber(messageId);
		jsd.setReturnparameter(resultDto.toString());
		jsd.setReturnstate(resultDto.getHead().getCode());
		jsd.setQueryjasperinterface("GetInvoice");
		jsd.setQueryservicename("GetInvoiceService");
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
	    	logger.error("call insertInterQuerJasper error,message:{}",Exceptions.getStackTraceAsString(e));
	    	//e.printStackTrace();
	    	return false;
	    }
		return false;
	}
}
