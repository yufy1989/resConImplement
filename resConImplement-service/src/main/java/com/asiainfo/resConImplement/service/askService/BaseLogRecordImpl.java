package com.asiainfo.resConImplement.service.askService;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.miniService.loggerMiniService.MiniInterAccessRecordServiceImpl;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
* 类说明：
* @author baomz
* @date 2017年6月3日 上午10:57:25
*/
public class BaseLogRecordImpl {
	
	private static Logger logger = LoggerFactory.getLogger(BaseLogRecordImpl.class);
	
	@Autowired
	private MiniInterAccessRecordServiceImpl miniInterAccessRecordService;
	

	/**
	 * 功能描述：保存内部访问记录
	 * @author baomz
	 * @date 2017年6月3日 上午11:22:59
	 * @param @param iccid 卡标识
	 * @param @param messageId 消息标识
	 * @param @param interfaceName 接口名称
	 * @param @param callInParty  
	 * @param @param platFormCode 平台编码
	 * @param @param queryState 查询状态
	 * @param @param eventName 事件名称
	 * @param @param params 参数
	 * @param @return 
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	public boolean saveInterAccessRecord(String iccid, String messageId, String interfaceName, String callInParty, String platFormCode, String queryState, String eventName, String params){
		InterAccessRecordDto ardto = new InterAccessRecordDto();
		ardto.setIccid(iccid);
		ardto.setInterfacename(interfaceName);
		ardto.setInputparameter(String.valueOf(params));
		ardto.setCallingparty(callInParty);
		ardto.setPlatformcode(platFormCode);
		ardto.setQuerystate(queryState);
		ardto.setQuerytime(new Date());
		ardto.setUpdatetime(new Date());
		ardto.setSerialnumber("");
		ardto.setEventname(eventName);
		logger.info("UsageDataDetailsServiceImpl:interAccessRecord of param InterQueryJasperInfoDto is {}", String.valueOf(ardto));
		try{
	    	if(ardto!=null){
	    		miniInterAccessRecordService.insert(ardto);
	    		return true;
	    	}
	    }catch(Exception e){
	    	logger.error("插入访问记录日志异常！[error] exception info:{}",Exceptions.getStackTraceAsString(e));
	    	return false;
	    }
		return false;
	}
}
