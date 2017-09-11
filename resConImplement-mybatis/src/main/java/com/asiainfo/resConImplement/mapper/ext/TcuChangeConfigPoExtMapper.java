package com.asiainfo.resConImplement.mapper.ext;

import com.asiainfo.resConImplement.po.TcuChangeConfigPo;

public interface TcuChangeConfigPoExtMapper {
	/**
	 * 功能描述：根据车企编码查询车企SIM卡的使用状态
	 * @author yufy
	 * @date 2016年8月8日 下午2:02:44
	 * @param @param record
	 * @param @return 
	 * @return TcuChangeConfigPo
	 */
	TcuChangeConfigPo selectByCarPricesName(TcuChangeConfigPo record);
    
}