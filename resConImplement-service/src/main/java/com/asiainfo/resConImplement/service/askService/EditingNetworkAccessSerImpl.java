package com.asiainfo.resConImplement.service.askService;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.asiainfo.resConImplement.dto.EditNetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.EditingNetworkAccessService;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniNetworkAccessServiceImpl;
import com.asiainfo.resConImplement.miniService.loggerMiniService.MiniInterAccessRecordServiceImpl;
import com.asiainfo.resConImplement.utils.Exceptions;
/**
 * 功能描述：更新SIM卡的通信计划
 * @author yufy
 * @date 2016年8月18日
 */
@Service("editingNetworkAccessService")
public class EditingNetworkAccessSerImpl implements EditingNetworkAccessService {
	
	private static Logger logger = LoggerFactory.getLogger(EditingNetworkAccessSerImpl.class); 

	@Autowired
	private MiniNetworkAccessServiceImpl miniNetworkAccessServiceImpl;
	@Autowired
	private MiniInterAccessRecordServiceImpl miniInterAccessRecordServiceImpl;
	/**
	 * 功能描述：更新SIM卡的通信计划
	 * @author yufy
	 * @date 2016年7月25日 下午5:46:05
	 * @param map 通信计划变更入参
	 * @param iccId 新卡卡号
	 * @param nacId 通信计划ID
	 * @param effectiveDate 生效时间
	 * @param interfaceName 发起调用接口名
	 * @param messageId 流水号
	 * @param token 车厂标识
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public ResultDto<EditNetworkAccessConfigDto> editNetworkAccessConfig(Map<String,String> map) {
		ResultDto<EditNetworkAccessConfigDto> getResult=new ResultDto<EditNetworkAccessConfigDto>();
		if(map.isEmpty()
				||StringUtils.isBlank(map.get("iccId"))
				||StringUtils.isBlank(map.get("nacId"))
				||StringUtils.isBlank(map.get("interfaceName"))
				||StringUtils.isBlank(map.get("messageId"))
				||StringUtils.isBlank(map.get("token"))){
			getResult.setCode("0000001");
			getResult.setMessage("error parameter!!!");
			logger.error("访问网络配置,参数为空。");
			return getResult;
		}
		logger.info("-------------editNetworkAccessConfig  start!!!-----------{}------------",map.get("messageId"));
		
		//存储入口信息.无论存储成功与否都不能影响业务
		if (this.saveEntranceInfo(map)) {
			String iccId = map.get("iccId");
			String nacId = map.get("nacId");
			String effectiveDate = map.get("effectiveDate");
			String interfaceName = map.get("interfaceName");
			String messageId = map.get("messageId");
			String token = map.get("token");
			try {
				getResult = miniNetworkAccessServiceImpl.editNetworkAccessConfig(iccId, nacId, effectiveDate, interfaceName,messageId, token);
			} catch (Exception e) {
				getResult.setCode("0000002");
				getResult.setMessage("调用jasper异常!");
				logger.error("--error--:{}",Exceptions.getStackTraceAsString(e));
			}
			logger.info("-------------------editNetworkAccessConfig  end!!!----------------------------------");
		} else {
			getResult.setCode("0000003");
			getResult.setMessage("存储入口日志表失败!业务终止");
		}

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
	private boolean  saveEntranceInfo(Map<String,String> map) {
		boolean flag=false;
		try {
			InterAccessRecordDto dto = new InterAccessRecordDto();
			dto.setSerialnumber(map.get("messageId"));
			dto.setInterfacename(map.get("interfaceName"));
			dto.setEventname("更新SIM卡通信计划");
			dto.setInputparameter(map.toString());
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
