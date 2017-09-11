package com.asiainfo.resConImplement.service.askService;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.asiainfo.resConImplement.dto.ActivateTerminalEventRequestDto;
import com.asiainfo.resConImplement.dto.ActivateTerminalEventResponseDto;
import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.ActivateTerminalEventService;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniActivateTerminalEventImpl;
import com.asiainfo.resConImplement.miniService.loggerMiniService.MiniInterAccessRecordServiceImpl;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
 * 类说明：
 * 
 * @author baomz
 * @date 2017年6月2日 下午5:16:28
 */
@Service("eventPlanService")
public class ActivateTerminalEventServiceImpl extends BaseLogRecordImpl implements ActivateTerminalEventService {

	private static Logger logger = LoggerFactory.getLogger(ActivateTerminalEventServiceImpl.class);

	@Autowired
	private MiniActivateTerminalEventImpl miniActivateTerminalEventService;

	@Autowired
	private MiniInterAccessRecordServiceImpl miniInterAccessRecordService;

	@Override
	public ResultDto<?> activateTerminalEvent(ActivateTerminalEventRequestDto dto, String interfaceName,
			String messageId, String token) {

		logger.info("-------------------ActivateTerminalEvent  start!!!--------{}------------", messageId);
		ResultDto<ActivateTerminalEventResponseDto> result = new ResultDto<ActivateTerminalEventResponseDto>();
		if (dto == null || StringUtils.isBlank(dto.getIccid()) || StringUtils.isBlank(dto.getEventName())
				|| StringUtils.isBlank(messageId) || StringUtils.isBlank(interfaceName) || StringUtils.isBlank(token)) {
			result.setCode("0000001");
			result.setMessage("error parameter!!!");
			logger.error("访问网络配置,参数为空。");
			return result;
		}
		// 存储入口信息
		if (this.saveEntranceInfo(dto, messageId, interfaceName)) {
			try {
				result = miniActivateTerminalEventService.activateTerminalEvent(dto, messageId, interfaceName, token);
			} catch (Exception e) {
				result.setCode("0000002");
				result.setMessage("调用jasper异常!");
				logger.error("--error--:{}", Exceptions.getStackTraceAsString(e));
			}
		} else {
			result.setCode("0000003");
			result.setMessage("存储入口日志表失败!业务终止");
		}
		logger.info("-------------------ActivateTerminalEvent  end!!!----------------------------------");
		return result;
	}

	private boolean saveEntranceInfo(ActivateTerminalEventRequestDto ob, String messageId, String interfaceName) {
		boolean flag = false;
		try {
			InterAccessRecordDto dto = new InterAccessRecordDto();

			dto.setSerialnumber(messageId);
			dto.setInterfacename(interfaceName);
			dto.setEventname("激活事件");
			dto.setInputparameter(ob.toString() + "messageId:" + messageId + "interfaceName:" + interfaceName);
			dto.setCallingparty("TCRM");
			dto.setPlatformcode("JASPER");
			dto.setQuerystate("000000");
			dto.setQuerytime(new Date());
			dto.setUpdatetime(new Date());
			flag = miniInterAccessRecordService.insert(dto);
		} catch (Exception e) {
			logger.error("--error--:{}", Exceptions.getStackTraceAsString(e));
		}
		return flag;
	}
}
