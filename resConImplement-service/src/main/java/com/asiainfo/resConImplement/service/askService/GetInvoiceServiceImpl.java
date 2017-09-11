package com.asiainfo.resConImplement.service.askService;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.dto.InvoiceDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.GetInvoiceService;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniGetInvoiceImpl;
import com.asiainfo.resConImplement.miniService.loggerMiniService.MiniInterAccessRecordServiceImpl;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
* 类说明：子账户账单内容
* @author pankx
* @date 2016年8月27日 上午10:00:23
*/
@Service("get‌InvoiceService")
public class GetInvoiceServiceImpl implements GetInvoiceService {
	
	Logger logger = LoggerFactory.getLogger(GetInvoiceServiceImpl.class);

	@Autowired
	private MiniGetInvoiceImpl miniGetInvoiceImpl;
	@Autowired
	private MiniInterAccessRecordServiceImpl MiniInterAccessRecordServiceImpl;
	@Override
	public ResultDto<InvoiceDto> getInvoice(String accountId, String cycleStartDate, String messageId,
			String askinterface,String token) {
	 logger.info("[start input param] Get‌InvoiceServiceImpl method getInvoice of "
					+ "param accountId:{},cycleStartDate:{},messageId:{},askInterface:{}",accountId,cycleStartDate,messageId,askinterface);
		   
	  ResultDto<InvoiceDto> dtos = new ResultDto<InvoiceDto>();
	 
	  if(StringUtils.isBlank(accountId) || StringUtils.isBlank(messageId)||StringUtils.isBlank(askinterface)||StringUtils.isBlank(token)){
			dtos.setCode("0000111");
			dtos.setMessage("参数为为空");
		    return  dtos;
		}
		
	  InterAccessRecordDto iard = new InterAccessRecordDto();
	 try{
		 
		   iard.setSerialnumber(messageId);
		   iard.setInterfacename(askinterface);
		   iard.setEventname("子账户账单内容");
		   iard.setInputparameter("{accountid:"+accountId+",cycleStartDate:"+cycleStartDate+",messageId:"+messageId+",askinterface:"+askinterface+",token:"+token+"}");
		   iard.setCallingparty("TCRM");
		   iard.setPlatformcode("JASPER");
		   iard.setQuerystate("000000");
		   iard.setQuerytime(new Date());
		   iard.setUpdatetime(new Date());
		   MiniInterAccessRecordServiceImpl.insert(iard);
		   dtos = miniGetInvoiceImpl.getInvoice(accountId, cycleStartDate, messageId, askinterface,token);
	}catch(Exception e){
		logger.error("getInvoice 调用japser出错，出错信息："+Exceptions.getStackTraceAsString(e));
		dtos.setCode("0000112");
		dtos.setMessage("调用Jasper异常，未知的异常");
	}   
	   
	   logger.info("[end response param] Get‌InvoiceServiceImpl method getInvoice of response param dtos:{} ",dtos);
	  
	  return dtos;
	}

}
