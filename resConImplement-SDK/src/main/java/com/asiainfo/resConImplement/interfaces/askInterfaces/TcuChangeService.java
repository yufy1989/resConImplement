package com.asiainfo.resConImplement.interfaces.askInterfaces;

import java.util.Map;

import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.TcuChangeDto;

/**
 * 功能描述：TCU变更服务
 * @author yufy
 * @date 2016年7月27日
 */
public interface TcuChangeService {

	/**
	 * 功能描述：TCU变更操作
	 * @author yufy
	 * @date 2016年7月27日 下午3:58:32
	 * @param @param body
	 * @param @return 
	 * @return ResultDto<NetworkAccessConfigDto>
	 */
	ResultDto<TcuChangeDto> tcuChange(Map<String,String> map);
}
