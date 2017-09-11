package com.asiainfo.resConImplement.interfaces.loggerInterfaces;

import com.asiainfo.resConImplement.dto.InterAccessRecordDto;

/**
* 类说明：调用接口记录表
* @author pankx
* @date 2016年7月29日 下午5:45:14
*/
public interface InterAccessRecordService {
	
	
	/**
	 * 功能描述： 接口记录表
	 * @author pankx
	 * @date 2016年7月29日 下午5:55:19
	 * @param @param interA
	 * @param @return 
	 * @return boolean
	 */
	public boolean interAccessRecord(InterAccessRecordDto interA);

}
