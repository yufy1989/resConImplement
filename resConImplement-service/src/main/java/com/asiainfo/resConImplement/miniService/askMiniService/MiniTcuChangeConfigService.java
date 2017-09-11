package com.asiainfo.resConImplement.miniService.askMiniService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.common.util.ConversionUtil;
import com.asiainfo.resConImplement.dto.TcuChangeConfigDto;
import com.asiainfo.resConImplement.mapper.ext.TcuChangeConfigPoExtMapper;
import com.asiainfo.resConImplement.po.TcuChangeConfigPo;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
* 类说明：TCU变更操作微服务(数库相关操作)
* @author yufy
* @date 2016年8月8日 下午1:54:23
*/
@Service
public class MiniTcuChangeConfigService {
	
	Logger logger = LoggerFactory.getLogger(MiniTcuChangeConfigService.class);
	
	@Autowired
	private TcuChangeConfigPoExtMapper tcuChangeConfigPoExtMapper;
	/**
	 * 功能描述：根据车企编码查询车企SIM卡的使用状态
	 * @author yufy
	 * @date 2016年8月8日 下午1:59:21
	 * @param @param code 
	 * @return void
	 */
	public TcuChangeConfigDto selectCarpricesState(String name) {
		TcuChangeConfigDto dto= new TcuChangeConfigDto();
		try {
			dto.setCarpricesname(name);
			TcuChangeConfigPo result=tcuChangeConfigPoExtMapper.selectByCarPricesName((TcuChangeConfigPo) ConversionUtil.dto2po(dto, TcuChangeConfigPo.class));
			return (TcuChangeConfigDto) ConversionUtil.po2dto(result, TcuChangeConfigDto.class);
		} catch (Exception e) {
			logger.info("查询数据库错误:"+Exceptions.getStackTraceAsString(e));
		}
		return null;
	}
}
