package com.asiainfo.resConImplement.service.loggerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.dto.InterAccessRecordDto;
import com.asiainfo.resConImplement.interfaces.loggerInterfaces.InterAccessRecordService;
import com.asiainfo.resConImplement.miniService.loggerMiniService.MiniInterAccessRecordServiceImpl;

/**
* 类说明：调用接口记录
* @author pankx
* @date 2016年7月29日 下午5:52:59
*/
@Service("interAccessRecordService")
public class InterAccessRecordServiceImpl implements InterAccessRecordService{
	
	@Autowired
	private MiniInterAccessRecordServiceImpl miniInterAccessRecordServiceImpl;
	
	/**
	 * 功能描述：调用接口记录服务
	 * @author pankx
	 * @date 2016年8月1日 下午1:56:30
	 * @param @param interA
	 * @param @return
	 */
	@Override
	public boolean interAccessRecord(InterAccessRecordDto interA) {
		if(interA!=null){
			miniInterAccessRecordServiceImpl.insert(interA);
			return true;
		}
		return false;
	}

}
