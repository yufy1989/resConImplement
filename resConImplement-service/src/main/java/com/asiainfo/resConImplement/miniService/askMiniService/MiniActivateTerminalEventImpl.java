package com.asiainfo.resConImplement.miniService.askMiniService;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.common.util.ConversionUtil;
import com.asiainfo.resConImplement.dto.ActivateTerminalEventRequestDto;
import com.asiainfo.resConImplement.dto.ActivateTerminalEventResponseDto;
import com.asiainfo.resConImplement.dto.InterQueryJasperInfoDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.jasper.EventPlanAdapter;
import com.asiainfo.resConImplement.mapper.InterQueryJasperInfoPoMapper;
import com.asiainfo.resConImplement.po.InterQueryJasperInfoPo;
import com.asiainfo.resConImplement.utils.Exceptions;

/**
* 类说明：
* @author baomz
* @date 2017年6月2日 下午3:23:05
*/
@Service
public class MiniActivateTerminalEventImpl {

	private static Logger logger = LoggerFactory.getLogger(MiniActivateTerminalEventImpl.class);
	
	
	@Autowired
	private EventPlanAdapter eventPlanAdapter;
	
	@Autowired
	private InterQueryJasperInfoPoMapper interQueryJasperInfoPoMapper;
	
	
	/**
	 * 功能描述：
	 * @author baomz
	 * @date 2017年6月2日 下午3:56:33
	 * @param @param dto
	 * @param @param interfaceName
	 * @param @param messageId
	 * @param @param token
	 * @param @return 
	 * @return ResultDto<?>
	 */
	@SuppressWarnings("unchecked")
	public ResultDto<ActivateTerminalEventResponseDto> activateTerminalEvent(ActivateTerminalEventRequestDto dto,String interfaceName,String messageId,String token){
		ResultDto<ActivateTerminalEventResponseDto> result=new ResultDto<ActivateTerminalEventResponseDto>();
		String iccid = dto.getIccid();
		logger.info("--开始--入参:iccid>>{},messageId>>{}" + iccid, messageId);
		//查询旧SIM卡的通信计划
		try {
			result = eventPlanAdapter.activateTerminalEvent(dto, messageId, token);
			logger.info("--结束--出参:ResultDto>>{}"+result.toString());
		} catch (Exception e1) {
			result.setCode("0000002");
			result.setMessage("调用jasper异常!");
			logger.error("---失败---:{}",Exceptions.getStackTraceAsString(e1));
		}finally {
			InterQueryJasperInfoDto interDto=new InterQueryJasperInfoDto();
			interDto.setReturntime(new Date());
			interDto.setSerialnumber(messageId);
			interDto.setQueryaskinterface(interfaceName);
			interDto.setQueryjasperinterface("getNetworkAccessConfig");
			interDto.setIccid(iccid);
			interDto.setQueryservicename("GettingNetworkAccessService");
			interDto.setInputparameter(iccid + "," + messageId);
			interDto.setReturnparameter(result.toString());
			interDto.setReturnstate(result.getHead().getCode());
			if (this.saveInterQueryJasperInfoRecord(interDto, result)) {
				logger.info("--成功--保存调用jasper信息");
			}
		}
		return result;
	}
	
	/**
	 * 功能描述：
	 * @author baomz
	 * @date 2017年6月2日 下午3:56:44
	 * @param @param interDto
	 * @param @param resultDto
	 * @param @return 
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	private boolean saveInterQueryJasperInfoRecord(InterQueryJasperInfoDto interDto, ResultDto resultDto) {
		logger.info("--开始--保存调用JASPER接口记录表参数:interDto>>{},resultDto>>{}",interDto.toString(), resultDto.toString());
		try{	
			interQueryJasperInfoPoMapper.insertSelective((InterQueryJasperInfoPo) ConversionUtil.dto2po(interDto, InterQueryJasperInfoPo.class));
			logger.info("--成功--保存调用JASPER接口记录表参数:{}",interDto.toString());
			return true;
		} catch (Exception e) {
			resultDto.setCode("0000003");
			resultDto.setMessage("存储日志表失败!");
			logger.error("--失败--保存调用JASPER接口记录表信息异常:"+Exceptions.getStackTraceAsString(e));
		}
		return false;
	}
	
}
