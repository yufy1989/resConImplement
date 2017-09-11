package com.asiainfo.resConImplement.interfaces.askInterfaces;

import com.asiainfo.resConImplement.dto.EditNetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.NetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.ResultDto;

/**
* 类说明：NetworkAccess 相关服务
* @author YUFY
* @date 2016年7月20日 上午11:27:56
*/

public interface NetworkAccessService {

	/**
	 * 功能描述：查询SIM卡的通信计划服务
	 * @author YUFY
	 * @date 2016年7月21日 上午10:21:55
	 * @param @param iccId
	 * @param @param messageId
	 * @param @return 
	 * @return List<String>
	 */
	ResultDto<NetworkAccessConfigDto> getNetworkAccessConfig(String iccId,String interfaceName,String messageId);
	/**
	 * 功能描述：更新SIM卡的通信计划服务
	 * @author YUFY
	 * @date 2016年7月25日 下午5:22:07
	 * @param @param iccId
	 * @param @param nacId
	 * @param @param effectiveDate
	 * @param @param messageId
	 * @param @return 
	 * @return EditNetworkAccessConfigDto
	 */
	ResultDto<EditNetworkAccessConfigDto> editNetworkAccessConfig(String iccId,String nacId,String interfaceName,String effectiveDate,String messageId);
}
