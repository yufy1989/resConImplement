package com.asiainfo.resConImplement.interfaces.askInterfaces;

import com.asiainfo.resConImplement.dto.InvoiceDto;
import com.asiainfo.resConImplement.dto.ResultDto;

/**
* 类说明：调用jasper的getInvoice方法获得信息
* @author pankx
* @date 2016年8月27日 上午9:50:23
*/
public interface GetInvoiceService {

		/**
		 * 功能描述：调用jasper 返回信息
		 * @author pankx
		 * @date 2016年8月27日 上午9:59:09
		 * @param @param accountId
		 * @param @param cycleStartDate
		 * @param @param messageId
		 * @param @param askInterface
		 * @param @return 
		 * @return ResultDto<InvoiceDto>
		 */
		public ResultDto<InvoiceDto> getInvoice(String accountId,String cycleStartDate,String messageId,String askInterface,String token);
}
