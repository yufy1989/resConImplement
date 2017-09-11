package com.asiainfo.resConImplement.service.askService;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.asiainfo.resConImplement.dto.EditTerminalRequestParamGroupDto;
import com.asiainfo.resConImplement.dto.EditTerminalResponseParamGroupDto;
import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.EditTerminalService;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniTerminalServiceImpl;
import com.asiainfo.resConImplement.miniService.loggerMiniService.MiniInterAccessRecordServiceImpl;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
 * 类说明：资费计划实现
 * @author cuishuo
 * @date 2016年8月17日 上午10:49:10
 */
@Service("editTerminalService")
public class EditTerminalServiceImpl implements EditTerminalService {
	private static Logger logger = LoggerFactory.getLogger(EditTerminalServiceImpl.class); 

	@Autowired
	private MiniTerminalServiceImpl miniTerminalServiceImpl;
	@Autowired
	private MiniInterAccessRecordServiceImpl miniInterAccessRecordServiceImpl;

	@Override
	public ResultDto<EditTerminalResponseParamGroupDto> EditTerminal(EditTerminalRequestParamGroupDto dto, String messageId,String interfaceName,String token){
		logger.info("-------------------EditTerminal  start!!!--------{}------------",messageId);
		ResultDto<EditTerminalResponseParamGroupDto> getResult=new ResultDto<EditTerminalResponseParamGroupDto>();
		if(dto==null
				||StringUtils.isBlank(dto.getIccId())
//				||StringUtils.isBlank(dto.getTargetValue())
				||StringUtils.isBlank(messageId)
				||StringUtils.isBlank(interfaceName)
				||StringUtils.isBlank(token)){
			getResult.setCode("0000001");
			getResult.setMessage("error parameter!!!");
			logger.error("访问网络配置,参数为空。"); 
			return getResult;
		}
		//存储入口信息
		if (this.saveEntranceInfo(dto,messageId,interfaceName)) { 
			try {
				getResult = miniTerminalServiceImpl.EditTerminal(dto, messageId, interfaceName, token);
			} catch (Exception e) {
				getResult.setCode("0000002");
				getResult.setMessage("调用jasper异常!");
				logger.error("--error--:{}",Exceptions.getStackTraceAsString(e));
			}
		} else {
			getResult.setCode("0000003");
			getResult.setMessage("存储入口日志表失败!业务终止");
		}
		logger.info("-------------------EditTerminal  end!!!----------------------------------");
		return getResult;
	}
	/**
	 * 功能描述：存储入口信息
	 * @author yfy
	 * @date 2016年12月22日 下午2:59:23
	 * @param @param map
	 * @param @return 
	 * @return boolean
	 */
	private boolean  saveEntranceInfo(EditTerminalRequestParamGroupDto ob,String messageId,String interfaceName) {
		boolean flag=false;
		try {
			InterAccessRecordDto dto = new InterAccessRecordDto();
			dto.setSerialnumber(messageId);
			dto.setInterfacename(interfaceName);
			dto.setEventname("资费计划实现");
			dto.setInputparameter(ob.toString()+"messageId:"+messageId+"interfaceName:"+interfaceName);
			dto.setCallingparty("TCRM");
			dto.setPlatformcode("JASPER");
			dto.setQuerystate("000000");
			dto.setQuerytime(new Date());
			dto.setUpdatetime(new Date());
			flag = miniInterAccessRecordServiceImpl.insert(dto);
		} catch (Exception e) {
			logger.error("--error--:{}",Exceptions.getStackTraceAsString(e));
		}
		return flag;
	}
}
