package com.asiainfo.resConImplement.service.askService;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.TerminalDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.GetTerminalDetailsService;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniTerminalServiceImpl;
import com.asiainfo.resConImplement.miniService.loggerMiniService.MiniInterAccessRecordServiceImpl;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
* 类说明：卡资费信息查询服务
* @author yufy
* @date 2016年7月27日 下午4:14:01
*/
@Service("getTerminalDetailsService")
public class GetTerminalDetailsServiceImpl implements GetTerminalDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(GetTerminalDetailsServiceImpl.class); 
	@Autowired
	private MiniTerminalServiceImpl miniTerminalServiceImpl;
	@Autowired
	private MiniInterAccessRecordServiceImpl miniInterAccessRecordServiceImpl;
	/**
	 * 功能描述：查询设备信息
	 * @author yufy
	 * @date 2016年7月25日 下午5:46:05
	 * @param map 查询设备信息
	 * @param iccId 卡号
	 * @param interfaceName 发起调用接口名
	 * @param messageId 流水号
	 * @param token 车厂标识
	 */
	@Override
	public ResultDto<TerminalDto> getTerminalDetails(Map<String,String> map) {
		logger.info("-------------------getTerminalDetails  start!!!----------------{}------------------",map.get("messageId"));
		ResultDto<TerminalDto> getResult=new ResultDto<TerminalDto>();
		if(map.isEmpty()||
				StringUtils.isBlank(map.get("iccId"))||
				StringUtils.isBlank(map.get("interfaceName"))||
				StringUtils.isBlank(map.get("messageId"))||
				StringUtils.isBlank(map.get("token"))){
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
				getResult= miniTerminalServiceImpl.GetTerminalDetails(iccId, messageId,interfaceName,token);
			} catch (Exception e) {
				getResult.setCode("0000002");
				getResult.setMessage("调用jasper异常!");
				logger.error("--error--:{}",Exceptions.getStackTraceAsString(e));
			}
		} else {
			getResult.setCode("0000003");
			getResult.setMessage("存储入口日志表失败!业务终止");
		}
		logger.info("-------------------getTerminalDetails  end!!!----------------------------------");
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
			dto.setEventname("查询设备信息");
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
