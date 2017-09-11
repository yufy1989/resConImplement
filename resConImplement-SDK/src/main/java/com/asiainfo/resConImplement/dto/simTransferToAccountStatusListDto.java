package com.asiainfo.resConImplement.dto;

import java.util.List;

/**
 * 类说明：
 * @author Administrator
 * @date 2016年8月5日 上午11:51:30
 */
public class simTransferToAccountStatusListDto extends BaseDto {
	private List<TransferSimsToAccountResponseDto> transferSimsToAccountResponseDtos;

	public List<TransferSimsToAccountResponseDto> getTransferSimsToAccountResponseDtos() {
		return transferSimsToAccountResponseDtos;
	}

	public void setTransferSimsToAccountResponseDtos(
			List<TransferSimsToAccountResponseDto> transferSimsToAccountResponseDtos) {
		this.transferSimsToAccountResponseDtos = transferSimsToAccountResponseDtos;
	} 

	
}
