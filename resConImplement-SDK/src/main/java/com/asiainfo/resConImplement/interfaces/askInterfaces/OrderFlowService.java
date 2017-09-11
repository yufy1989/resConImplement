package com.asiainfo.resConImplement.interfaces.askInterfaces;

import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.SubscriberPolicyRequestDto;
import com.asiainfo.resConImplement.dto.SubscriberPolicyResponseDto;

/**
* 类说明：流量订购服务-接口
* @author pankx
* @date 2016年7月29日 上午10:02:23
*/
public interface OrderFlowService {
	
	/**
	 * 功能描述：流量订购
	 * @author pankx
	 * @date 2016年7月29日 上午10:19:22
	 * @param @param SubscriberPolicyRequestDto
	 * @param @param messageId
	 * @param @param callinterfaceName 调用接口名称
	 * @param @return 
	 * @return ResultDto<OrderFlowDto>
	 */
	public ResultDto<SubscriberPolicyResponseDto> flowOrder(SubscriberPolicyRequestDto reqDto, String messageId, String token);
	
	/**
	 * 功能描述：PCRF 单卡套餐订购追加
	 * @author baomz
	 * @date 2017年6月3日 下午2:37:59
	 * @param @param reqDto
	 * @param @param messageId
	 * @param @param askInterface
	 * @param @param token
	 * @param @return 
	 * @return ResultDto<SubscriberPolicyResponseDto>
	 */
	ResultDto<SubscriberPolicyResponseDto> updateSubscriberPolicy(SubscriberPolicyRequestDto reqDto,String  messageId, String askInterface, String token);

}
