package com.asiainfo.resConImplement.service.askService;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.dto.NetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.GettingNetworkAccessService;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniNetworkAccessServiceImpl;
import com.asiainfo.resConImplement.miniService.loggerMiniService.MiniInterAccessRecordServiceImpl;
import com.asiainfo.resConImplement.utils.Exceptions;
/**
 * 功能描述：查询SIM卡的通信计划
 * @author YUFY
 * @date 2016年8月18日
 */
@Service("gettingNetworkAccessService")
public class GettingNetworkAccessSerImpl implements GettingNetworkAccessService {
	private static Logger logger = LoggerFactory.getLogger(GettingNetworkAccessSerImpl.class); 

	@Autowired
	private MiniNetworkAccessServiceImpl miniNetworkAccessServiceImpl;
	@Autowired
	private MiniInterAccessRecordServiceImpl miniInterAccessRecordServiceImpl;
	/**
	 * 功能描述：查询SIM卡的通信计划
	 * @author YUFY
	 * @date 2016年7月21日 上午10:33:15
	 * @param @param iccId 卡号
	 * @param @param interfaceName发起调用接口名
	 * @param @param messageId 用于记录流水
	 * @param @param token 车厂标识
	 * @param @return
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public ResultDto<NetworkAccessConfigDto> getNetworkAccessConfig(Map<String,String> map) {
		logger.info("-------------------getNetworkAccessConfig  start!!!----------------{}------------------",map.get("messageId"));
		ResultDto<NetworkAccessConfigDto> getResult=new ResultDto<NetworkAccessConfigDto>();
		if(map.isEmpty()||
				StringUtils.isBlank(map.get("iccId"))
				||StringUtils.isBlank(map.get("interfaceName"))
				||StringUtils.isBlank(map.get("messageId"))
				||StringUtils.isBlank(map.get("token"))) {
			getResult.setCode("0000001");
			getResult.setMessage("error parameter!!!");
			logger.error("访问网络配置,参数为空。");
			return getResult;
		}
		
		//存储入口信息
		if (this.saveEntranceInfo(map)) {
			String iccId=map.get("iccId");
			String interfaceName=map.get("interfaceName");
			String messageId=map.get("messageId");
			String token=map.get("token");
			try {
				getResult = miniNetworkAccessServiceImpl.getNetworkAccessConfig(iccId, interfaceName, messageId,token);
			} catch (Exception e) {
				getResult.setCode("0000002");
				getResult.setMessage("调用jasper异常!");
				logger.error("--error--:{}",Exceptions.getStackTraceAsString(e));
			}
		} else {
			getResult.setCode("0000003");
			getResult.setMessage("存储入口日志表失败!业务终止");
		}
		logger.info("-------------------getNetworkAccessConfig  end!!!----------------------------------");
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
			dto.setEventname("查询SIM卡的通信计划");
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
