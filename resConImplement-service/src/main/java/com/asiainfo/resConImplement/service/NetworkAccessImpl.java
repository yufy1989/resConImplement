//package com.asiainfo.resConImplement.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.alibaba.dubbo.common.utils.StringUtils;
//import com.asiainfo.resConImplement.dto.NetworkAccessConfigDto;
//import com.asiainfo.resConImplement.dto.ResultDto;
//import com.asiainfo.resConImplement.interfaces.askInterfaces.NetworkAccessService;
//import com.asiainfo.resConImplement.miniService.askMiniService.MiniNetworkAccessServiceImpl;
//
///**
//* 类说明：实现 NetworkAccess 相关服务
//* @author YUFY
//* @date 2016年7月20日 上午11:39:08
//*/
//@Service("networkAccessService")
//public class NetworkAccessImpl implements NetworkAccessService{
//
//	private static Logger logger = LoggerFactory.getLogger(NetworkAccessImpl.class); 
//
//	@Autowired
//	private MiniNetworkAccessServiceImpl miniNetworkAccessServiceImpl;
//	/**
//	 * 功能描述：
//	 * @author YUFY
//	 * @date 2016年7月21日 上午10:33:15
//	 * @param @param iccId ICCID 老卡的iccId
//	 * @param @param messageId 用于记录流水
//	 * @param @return
//	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Override
//	public ResultDto getNetworkAccessConfig(String iccId,String interfaceName,String messageId) {
//		if(StringUtils.isBlank(iccId)||StringUtils.isBlank(interfaceName)||StringUtils.isBlank(messageId)) {
//			ResultDto<NetworkAccessConfigDto> getResult=new ResultDto<NetworkAccessConfigDto>();
//			getResult.setCode("0000001");
//			getResult.setMessage("error parameter!!!");
//			logger.error("访问网络配置,参数为空。");
//			return null;
//		}
//		return miniNetworkAccessServiceImpl.getNetworkAccessConfig(iccId, interfaceName, messageId);
//	}
//	/**
//	 * 功能描述：更新SIM卡的通信计划
//	 * @author yufy
//	 * @date 2016年7月25日 下午5:46:05
//	 * @param @param iccId 新卡的iccId
//	 * @param @param nacId 老卡的通信计划Id
//	 * @param @param effectiveDate 生效时间
//	 * @param @param messageId 流水ID
//	 * @param @param interfaceName 访问该jasper的接口名
//	 * @param @return
//	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Override	
//	public ResultDto editNetworkAccessConfig(String iccId, String nacId,String interfaceName, String effectiveDate,
//			String messageId) {
//		if(StringUtils.isBlank(iccId)||StringUtils.isBlank(nacId)||StringUtils.isBlank(interfaceName)||StringUtils.isBlank(messageId)){
//			ResultDto<NetworkAccessConfigDto> getResult=new ResultDto<NetworkAccessConfigDto>();
//			getResult.setCode("0000001");
//			getResult.setMessage("error parameter!!!");
//			logger.error("访问网络配置,参数为空。");
//			return null;
//		}
//		return miniNetworkAccessServiceImpl.editNetworkAccessConfig(iccId, nacId,effectiveDate,interfaceName, messageId);
//	}
//}
