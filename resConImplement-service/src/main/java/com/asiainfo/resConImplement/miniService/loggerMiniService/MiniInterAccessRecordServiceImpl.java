package com.asiainfo.resConImplement.miniService.loggerMiniService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.common.util.ConversionUtil;
import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.mapper.InterAccessRecordPoMapper;
import com.asiainfo.resConImplement.po.InterAccessRecordPo;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
* 类说明：接口调用记录
* @author pankx
* @date 2016年7月29日 下午5:57:42
*/
@Service
public class MiniInterAccessRecordServiceImpl {
	Logger logger = LoggerFactory.getLogger(MiniInterAccessRecordServiceImpl.class);
	
	@Autowired
	private InterAccessRecordPoMapper interAccessRecordPoMapper;
	
	/**
	 * 功能描述：插入接口调用记录日志
	 * @author pankx
	 * @date 2016年7月29日 下午6:04:54
	 * @param @param dto
	 * @param @return 
	 * @return boolean
	 */
	public boolean insert(InterAccessRecordDto dto){
		logger.error("[start] MiniInterAccessRecordServiceImpl:insert reqParam InterAccessRecordDto:{}",dto);
	    try{
	    	if(dto!=null){
	    		interAccessRecordPoMapper.insertSelective((InterAccessRecordPo)ConversionUtil.dto2po(dto,InterAccessRecordPo.class));
	    		return true;
	    	}
	    }catch(Exception e){
	    	logger.error("[error] MiniInterAccessRecordServiceImpl:insert error,message:{}",Exceptions.getStackTraceAsString(e));
	    	return false;
	    }
		return false;
	}

}
