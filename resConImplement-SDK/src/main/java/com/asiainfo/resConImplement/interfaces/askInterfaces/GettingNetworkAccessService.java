package com.asiainfo.resConImplement.interfaces.askInterfaces;

import java.util.Map;

import com.asiainfo.resConImplement.dto.NetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.ResultDto;

public interface GettingNetworkAccessService {
	/**
	 * 功能描述：查询SIM卡的通信计划服务
	 * @author yufy
	 * @date 2016年7月21日 上午10:21:55
	 *   @param @param map 查询服务入参( iccId 卡号,interfaceName 调用接口名称,messageId 流水号,token 车场标识)
	 * @return ResultDto<NetworkAccessConfigDto>
	 */
	ResultDto<NetworkAccessConfigDto> getNetworkAccessConfig(Map<String ,String> map);
}
