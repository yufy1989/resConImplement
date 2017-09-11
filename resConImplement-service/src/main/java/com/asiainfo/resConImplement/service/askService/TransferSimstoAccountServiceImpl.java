package com.asiainfo.resConImplement.service.askService;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.TransferSimsToAccountRequestDto;
import com.asiainfo.resConImplement.dto.simTransferToAccountStatusListDto;
import com.asiainfo.resConImplement.interfaces.askInterfaces.TransferSimstoAccountService;
import com.asiainfo.resConImplement.miniService.askMiniService.MiniTerminalServiceImpl;
import com.asiainfo.resConImplement.miniService.loggerMiniService.MiniInterAccessRecordServiceImpl;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
 * 类说明：子账户迁移实现（资费计划）
 * @author yfy
 * @date 2016年8月17日 下午2:56:34
 */
@Service("transferSimstoAccountService")
public class TransferSimstoAccountServiceImpl implements TransferSimstoAccountService {
	private static Logger logger = LoggerFactory.getLogger(TransferSimstoAccountServiceImpl.class); 
	@Autowired
	private MiniTerminalServiceImpl miniTerminalServiceImpl;
	@Autowired
	private MiniInterAccessRecordServiceImpl miniInterAccessRecordServiceImpl;
	/**
	 * 功能描述：子账户迁移实现（资费计划）
	 * @author yfy
	 * @date 2016年12月22日 下午4:03:06
	 * @param @param dto
	 * @param @param messageId
	 * @param @param interfaceName
	 * @param @param token
	 * @param @return
	 */
	@Override
	public ResultDto<simTransferToAccountStatusListDto> TransferSimstoAccount(TransferSimsToAccountRequestDto dto, String messageId,String interfaceName,String token) {
		ResultDto<simTransferToAccountStatusListDto> getResult=new ResultDto<simTransferToAccountStatusListDto>();
		if(dto==null
				||dto.getIccidList()==null
				||dto.getIccidList().size()==0
				||StringUtils.isBlank(dto.getAccountId())
				||StringUtils.isBlank(dto.getCommPlanName())
				||StringUtils.isBlank(dto.getAccountId())
				||StringUtils.isBlank(interfaceName)
				||StringUtils.isBlank(messageId)
				||StringUtils.isBlank(token)) {
			getResult.setCode("0000001");
			getResult.setMessage("error parameter!!!");
			logger.error("访问网络配置,参数为空。");
			return getResult;
		}
		logger.info("-------------------TransferSimstoAccount  start!!!-----------------{}-----------------",messageId);
		//存储入口信息
		if (this.saveEntranceInfo(dto,messageId,interfaceName)) {
			try {
				getResult= miniTerminalServiceImpl.TransferSimstoAccount(dto, messageId, interfaceName,token);
			} catch (Exception e) {
				getResult.setCode("0000002");
				getResult.setMessage("调用jasper异常!");
				logger.error("--error--:{}",Exceptions.getStackTraceAsString(e));
			}
		} else {
			getResult.setCode("0000003");
			getResult.setMessage("存储入口日志表失败!业务终止");
		}
		logger.info("-------------------TransferSimstoAccount  end!!!----------------------------------");
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
	private boolean  saveEntranceInfo(TransferSimsToAccountRequestDto ob, String messageId,String interfaceName) {
		boolean flag=false;
		try {
			InterAccessRecordDto dto = new InterAccessRecordDto();
			dto.setSerialnumber(messageId);
			dto.setInterfacename(interfaceName);
			dto.setEventname("子账户迁移实现");
			dto.setInputparameter(ob.toString()+"messageId:"+messageId+"interfaceName:"+interfaceName);
			dto.setCallingparty("TCRM");
			dto.setPlatformcode("JASPER");
			dto.setQuerystate("000000");
			dto.setQuerytime(new Date());
			dto.setUpdatetime(new Date());
			flag=miniInterAccessRecordServiceImpl.insert(dto);
		} catch (Exception e) {
			logger.error("--error--:{}",Exceptions.getStackTraceAsString(e));
		}
		return flag;
	}
}
