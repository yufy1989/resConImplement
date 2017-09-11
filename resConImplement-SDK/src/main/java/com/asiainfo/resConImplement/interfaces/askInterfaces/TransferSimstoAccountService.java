package com.asiainfo.resConImplement.interfaces.askInterfaces;

import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.TransferSimsToAccountRequestDto;
import com.asiainfo.resConImplement.dto.simTransferToAccountStatusListDto;

/**
 * 类说明：子账户迁移-接口（资费计划）
 * @author cuishuo
 * @date 2016年8月17日 下午2:54:43
 */
public interface TransferSimstoAccountService {
	/**
	 * 功能描述：子账户迁移
	 * @author cuishuo
	 * @date 2016年8月17日 下午2:59:54
	 * @param @param dto
	 * @param @param messageId
	 * @param @param interfaceName
	 * @param @return 
	 * @return ResultDto<simTransferToAccountStatusListDto>
	 */
	ResultDto<simTransferToAccountStatusListDto> TransferSimstoAccount(
			TransferSimsToAccountRequestDto dto, String messageId,
			String interfaceName,String token);

}
