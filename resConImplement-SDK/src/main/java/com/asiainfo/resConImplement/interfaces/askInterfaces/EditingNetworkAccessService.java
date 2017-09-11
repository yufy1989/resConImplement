package com.asiainfo.resConImplement.interfaces.askInterfaces;

import java.util.Map;

import com.asiainfo.resConImplement.dto.EditNetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.ResultDto;

public interface EditingNetworkAccessService {
	/**
	 * 功能描述：更新SIM卡的通信计划服务
	 * @author YUFY
	 * @date 2016年7月25日 下午5:22:07
	 * @param @param iccId 卡号
	 * @param @param nacId 通信计划ID
	 * @param @param effectiveDate 生效时间
	 * @param @param messageId 流水号
	 * @param @return 
	 * @return EditNetworkAccessConfigDto
	 */
	ResultDto<EditNetworkAccessConfigDto> editNetworkAccessConfig(Map<String,String> map);
}
