package com.asiainfo.resConImplement.miniService.askMiniService;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.common.util.ConversionUtil;
import com.asiainfo.resConImplement.dto.EditNetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.InterQueryJasperInfoDto;
import com.asiainfo.resConImplement.dto.NetworkAccessConfigDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.UpdateNetworkAccessConfigDto;
import com.asiainfo.resConImplement.jasper.NetworkAccessAdapter;
import com.asiainfo.resConImplement.mapper.InterQueryJasperInfoPoMapper;
import com.asiainfo.resConImplement.mapper.UpdateNetworkAccessConfigPoMapper;
import com.asiainfo.resConImplement.po.InterQueryJasperInfoPo;
import com.asiainfo.resConImplement.po.UpdateNetworkAccessConfigPo;
import com.asiainfo.resConImplement.utils.Exceptions;
import com.google.common.collect.Maps;

/**
* 类说明：实现 NetworkAccess 相关服务
* @author yufy
* @date 2016年7月20日 上午11:39:08
*/
@Service
public class MiniNetworkAccessServiceImpl{
	private static Logger logger = LoggerFactory.getLogger(MiniNetworkAccessServiceImpl.class); 
	@Autowired
	private NetworkAccessAdapter networkAccessAdapter;
	@Autowired
	private UpdateNetworkAccessConfigPoMapper updateNetworkAccessConfigPoMapper;
	@Autowired
	private InterQueryJasperInfoPoMapper interQueryJasperInfoPoMapper;
	/**
	 * 功能描述：查询旧SIM卡的通信计划
	 * @author yufy
	 * @date 2016年7月21日 上午10:33:15
	 * @param @param iccId ICCID
	 * @param @param messageId 用于记录流水
	 * @param @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultDto getNetworkAccessConfig(String oldIccId,String interfaceName,String messageId,String token){
		ResultDto<NetworkAccessConfigDto> getResult=new ResultDto<NetworkAccessConfigDto>();
		logger.info("--开始--入参:oldIccId>>{},messageId>>{}"+oldIccId,messageId);
		//查询旧SIM卡的通信计划
		try {
			getResult = networkAccessAdapter.getNetworkAccessConfig(oldIccId, messageId,token);
			logger.info("--结束--出参:ResultDto>>{}"+getResult.toString());
		} catch (Exception e1) {
			getResult.setCode("0000002");
			getResult.setMessage("调用jasper异常!");
			logger.error("---失败---:{}",Exceptions.getStackTraceAsString(e1));
		}finally {
			InterQueryJasperInfoDto interDto=new InterQueryJasperInfoDto();
			interDto.setReturntime(new Date());
			interDto.setSerialnumber(messageId);
			interDto.setQueryaskinterface(interfaceName);
			interDto.setQueryjasperinterface("getNetworkAccessConfig");
			interDto.setIccid(oldIccId);
			interDto.setQueryservicename("GettingNetworkAccessService");
			interDto.setInputparameter(oldIccId+","+messageId);
			interDto.setReturnparameter(getResult.toString());
			interDto.setReturnstate(getResult.getHead().getCode());
			if (this.saveInterQueryJasperInfoRecord(interDto, getResult)) {
				logger.info("--成功--保存调用jasper信息");
			}
		}
		return getResult;
	}
	/**
	 * 功能描述：更新新SIM卡的通信计划
	 * @author yufy
	 * @date 2016年7月25日 下午5:46:05
	 * @param @param iccId 卡号
	 * @param @param nacId 通信计划Id
	 * @param @param effectiveDate 生效时间
	 * @param @param messageId 唯一流水号
	 * @param @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultDto editNetworkAccessConfig(String newIccId, String nacId,String effectiveDate,String interfaceName,String messageId,String token) {
		ResultDto<EditNetworkAccessConfigDto> editNResult=new ResultDto<EditNetworkAccessConfigDto>();
		logger.info("--开始--入参:newIccId>>{},nacId>>{},messageId>>{},interfaceName>>{}"+newIccId,nacId,messageId,interfaceName);
		try {
			//开始调用jasper
			editNResult = networkAccessAdapter.editNetworkAccessConfig(newIccId, nacId,effectiveDate, messageId,token);
			logger.info("--结束--出参:editNResult>>{}"+editNResult.toString());
		} catch (Exception e) {
			editNResult.setCode("0000002");
			editNResult.setMessage("调用jasper异常!");
			logger.error("--error---:{}",Exceptions.getStackTraceAsString(e));
		}finally {
			InterQueryJasperInfoDto interDto=new InterQueryJasperInfoDto();
			UpdateNetworkAccessConfigDto updateDto=new UpdateNetworkAccessConfigDto();
			updateDto.setIccid(newIccId);
			updateDto.setNacid(nacId);
			updateDto.setSerialnumber(messageId);
			updateDto.setReturnstate(editNResult.getHead().getCode());
			Map<String,String> map=Maps.newHashMap();
			map.put("iccId", newIccId);
			map.put("nacId", nacId);
			map.put("effectiveDate", effectiveDate);
			interDto.setReturntime(new Date());
			interDto.setSerialnumber(messageId);
			interDto.setQueryaskinterface(interfaceName);
			interDto.setQueryjasperinterface("editNetworkAccessConfig");
			interDto.setIccid(newIccId);
			interDto.setQueryservicename("EditingNetworkAccessService");
			interDto.setInputparameter(map.toString());
			interDto.setReturnparameter(editNResult.toString());
			interDto.setReturnstate(editNResult.getHead().getCode());
			//保存调用editNetworkAccessConfig接口记录
			if (this.saveInterQueryJasperInfoRecord(interDto,editNResult)&&this.saveUpdateNACRecord(updateDto, editNResult)) {
				logger.info("--成功--保存调用jasper信息");
			}
		}
		return editNResult;
	}
	/**
	 * 功能描述：保存调用信息到INTER_QUERY_JASPER_INFO表
	 * @author yufy
	 * @date 2016年8月1日 上午11:32:20
	 * @param @param iccId
	 * @param @param nacId
	 * @param @param interfaceName
	 * @param @param messageId
	 * @param @param resultDto
	 * @param @return 
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	private boolean saveInterQueryJasperInfoRecord(InterQueryJasperInfoDto interDto,ResultDto resultDto) {
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
	/**
	 * 功能描述：保存调用信息到UPDATE_NETWORK_ACCESS_CONFIG表
	 * @author yufy
	 * @date 2016年7月29日 下午3:43:37
	 * @param @param newIccId
	 * @param @param nacId
	 * @param @param interfaceName
	 * @param @param messageId
	 * @param @param editNetworkAccessConfigResult
	 * @param @return 
	 * @return UpdateNetworkAccessConfigDto
	 */
	@SuppressWarnings("rawtypes" )
	private boolean saveUpdateNACRecord(UpdateNetworkAccessConfigDto updateDto,ResultDto resultDto) {
		logger.info("--开始--保存更新SIM卡的通信计划表参数:updateDto:{},resultDto:{}",updateDto.toString(),resultDto.toString());
		try {
			updateNetworkAccessConfigPoMapper.insertSelective((UpdateNetworkAccessConfigPo) ConversionUtil.dto2po(updateDto, UpdateNetworkAccessConfigPo.class));
			logger.info("--成功--保存更新SIM卡的通信计划表参数:{}",updateDto.toString());
			return true;
		} catch (Exception e) {
			resultDto.setCode("0000003");
			resultDto.setMessage("存储日志表失败!");
			logger.error("--失败--保存更新SIM卡的通信计划表信息异常:"+Exceptions.getStackTraceAsString(e));
		}
		return false;
	}
}
