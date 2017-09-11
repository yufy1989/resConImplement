package com.asiainfo.resConImplement.miniService.askMiniService;

import java.util.Date;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.common.util.ConversionUtil;
import com.asiainfo.resConImplement.dto.EditTerminalRequestParamGroupDto;
import com.asiainfo.resConImplement.dto.EditTerminalResponseParamGroupDto;
import com.asiainfo.resConImplement.dto.InterQueryJasperInfoDto;
import com.asiainfo.resConImplement.dto.ResultDto;
import com.asiainfo.resConImplement.dto.TerminalDto;
import com.asiainfo.resConImplement.dto.TransferSimsToAccountRequestDto;
import com.asiainfo.resConImplement.dto.UpdateTeminalConfigDto;
import com.asiainfo.resConImplement.dto.simTransferToAccountStatusListDto;
import com.asiainfo.resConImplement.jasper.TerminalAdapter;
import com.asiainfo.resConImplement.mapper.InterQueryJasperInfoPoMapper;
import com.asiainfo.resConImplement.mapper.UpdateTeminalConfigPoMapper;
import com.asiainfo.resConImplement.po.InterQueryJasperInfoPo;
import com.asiainfo.resConImplement.po.UpdateTeminalConfigPo;
import com.asiainfo.resConImplement.utils.Identities;

/**
 * 功能描述：资费计划微服务
 * @author zhaoxy9
 * @date 2016年7月29日
 */
@Service
public class MiniTerminalServiceImpl {

	private static Logger logger = LoggerFactory.getLogger(MiniTerminalServiceImpl.class); 
	@Autowired
	private InterQueryJasperInfoPoMapper InterQueryJasperInfoPoMapper;
	@Autowired
	private UpdateTeminalConfigPoMapper updateTeminalConfigPoMapper;
	/**
	 * 访问 Jasper 的Terminal 适配器
	 */
	@Autowired
	private TerminalAdapter terminalAdapter;
	/**
	 * 功能描述：查询资费计划微服务
	 * @author zhaoxy9
	 * @date 2016年7月29日 下午3:08:46
	 * @param @param iccId
	 * @param @param messageId
	 * @param @return 
	 * @return ResultDto<TerminalDto>
	 */
	@SuppressWarnings("unchecked")
	public ResultDto<TerminalDto> GetTerminalDetails(String iccId,String messageId,String interfaceName,String token) {
		logger.info("[start]MiniTerminalServiceImpl:GetTerminalDetails param is iccId:{},messageId:{},token:{}",iccId,messageId);
		ResultDto<TerminalDto> dto=new ResultDto<TerminalDto>();
		try {
			dto=terminalAdapter.GetTerminalDetails(iccId, messageId,token);
		} catch (Exception e) {
			dto.setCode("0000002");
			dto.setMessage("调用jasper异常!");
			logger.error("[terminalAdapter.GetTerminalDetails] Exception:"+e.getMessage());
		}
		InterQueryJasperInfoDto infoDto=new InterQueryJasperInfoDto();
		infoDto.setSerialnumber(messageId);
		infoDto.setQueryaskinterface(interfaceName);
		infoDto.setQueryjasperinterface("GetTerminalDetails");
		infoDto.setQueryservicename("GetTerminalDetailsService");
		infoDto.setIccid(iccId);
		infoDto.setInputparameter(iccId+","+messageId);
		infoDto.setReturnparameter(dto.toString());
		infoDto.setReturnstate(dto.getHead().getCode());
		infoDto.setReturntime(new Date());
		try {
			InterQueryJasperInfoPoMapper.insert((InterQueryJasperInfoPo)ConversionUtil.dto2po(infoDto, InterQueryJasperInfoPo.class));
		} catch (Exception e) {
			logger.error("[InterQueryJasperInfoPoMapper.insert] Exception:"+e.getMessage());
		}
		logger.info("[end]MiniTerminalServiceImpl:GetTerminalDetails param is ResultDto<TerminalDto>:{}",dto.toString());
		return dto;
	}
	/**
	 * 功能描述：更新资费计划微服务
	 * @author zhaoxy9
	 * @date 2016年7月29日 下午3:09:42
	 * @param @param dto
	 * @param @param messageId
	 * @param @return 
	 * @return ResultDto<EditTerminalRequestParamGroupDto>
	 */
	@SuppressWarnings("unchecked")
	public ResultDto<EditTerminalResponseParamGroupDto> EditTerminal(EditTerminalRequestParamGroupDto dto, String messageId,String interfaceName,String token) {
		logger.info("[start]MiniTerminalServiceImpl:EditTerminal param is EditTerminalRequestParamGroupDto:{},messageId:{}",dto.toString(),messageId);
		ResultDto<EditTerminalResponseParamGroupDto> resultDto=new ResultDto<EditTerminalResponseParamGroupDto>();
		try {
			resultDto=terminalAdapter.EditTerminal(dto, messageId,token);
		} catch (Exception e) {
			resultDto.setCode("0000002");
			resultDto.setMessage("调用jasper异常!");
			logger.error("[terminalAdapter.EditTerminal] Exception:"+e.getMessage());
		}
		InterQueryJasperInfoDto infoDto=new InterQueryJasperInfoDto();
		try {
			infoDto.setSerialnumber(messageId);
			infoDto.setQueryaskinterface(interfaceName);
			infoDto.setQueryjasperinterface("EditTerminal");
			infoDto.setQueryservicename("EditTerminalService");
			infoDto.setIccid(dto.getIccId());
			infoDto.setInputparameter(dto.toString());
			infoDto.setReturnparameter(resultDto.toString());
			infoDto.setReturnstate(resultDto.getHead().getCode());
			infoDto.setReturntime(new Date());
		} catch (Exception e2) {
			logger.error("InterQueryJasperInfoDto:{},error:{}",infoDto.toString(),e2.getMessage());
		}
		
		UpdateTeminalConfigDto configDto=new UpdateTeminalConfigDto();
		try {
			configDto.setSerialnumber(messageId);
			configDto.setIccid(dto.getIccId());
			configDto.setEffectivedate(new Date().toString());
			configDto.setTargetvalue(dto.getTargetValue());
			configDto.setChangetype(dto.getChangeType()+"");
			configDto.setReturnstate(resultDto.getHead().getCode());
		} catch (Exception e1) {
			logger.error("UpdateTeminalConfigDto:{},error:{}",configDto.toString(),e1.getMessage());
		}
		try {
			InterQueryJasperInfoPoMapper.insert((InterQueryJasperInfoPo)ConversionUtil.dto2po(infoDto, InterQueryJasperInfoPo.class));
		} catch (Exception e) {
			logger.error("InterQueryJasperInfoPoMapper.insert:{}",e.getMessage());
		}
		try {
			updateTeminalConfigPoMapper.insert((UpdateTeminalConfigPo)ConversionUtil.dto2po(configDto, UpdateTeminalConfigPo.class));
		} catch (Exception e) {
			logger.error("updateTeminalConfigPoMapper.insert:{}",e.getMessage());
		}
		logger.info("[end]MiniTerminalServiceImpl:EditTerminal param is ResultDto<EditTerminalRequestParamGroupDto>:{}",resultDto.toString());
		return resultDto;
	}
	
	/**
	 * 功能描述：子賬戶遷移
	 * @author zhaoxy9
	 * @date 2016年8月8日 下午3:17:27
	 * @param @param dto
	 * @param @param messageId
	 * @param @return 
	 * @return ResultDto<simTransferToAccountStatusListDto>
	 */
	@SuppressWarnings("unchecked")
	public ResultDto<simTransferToAccountStatusListDto> TransferSimstoAccount(TransferSimsToAccountRequestDto dto, String messageId,String interfaceName,String token) {

		logger.info("[start]MiniTerminalServiceImpl:TransferSimstoAccount param is TransferSimsToAccountRequestDto:{},messageId:{}",dto.toString(),messageId);
		ResultDto<simTransferToAccountStatusListDto> resultDto=new ResultDto<simTransferToAccountStatusListDto>();
		try {
			resultDto=terminalAdapter.TransferSimstoAccount(dto, messageId,token);
		} catch (Exception e) {
			resultDto.setCode("0000002");
			resultDto.setMessage("调用jasper异常!");
			logger.error("[terminalAdapter.TransferSimstoAccount] Exception:"+e.getMessage());
		}
		Iterator<String> iterator = dto.getIccidList().iterator();
		while (iterator.hasNext()) {
			String iccId = (String) iterator.next();
			InterQueryJasperInfoDto infoDto=new InterQueryJasperInfoDto();
//			infoDto.setId((int)Identities.randomLong());
			infoDto.setSerialnumber(messageId);
			infoDto.setQueryaskinterface(interfaceName);
			infoDto.setQueryjasperinterface("TransferSimstoAccount");
			infoDto.setQueryservicename("TransferSimstoAccountService");
			infoDto.setIccid(iccId);
			infoDto.setInputparameter(dto.toString()+","+messageId);
			infoDto.setReturnparameter(resultDto.toString());
			infoDto.setReturnstate(resultDto.getHead().getCode());
			infoDto.setReturntime(new Date());
			try {
				InterQueryJasperInfoPoMapper.insert((InterQueryJasperInfoPo)ConversionUtil.dto2po(infoDto, InterQueryJasperInfoPo.class));
			} catch (Exception e) {
				logger.error("[InterQueryJasperInfoPoMapper.insert] Exception:"+e.getMessage());
			}
		}
		logger.info("[end]MiniTerminalServiceImpl:TransferSimstoAccount param is ResultDto<simTransferToAccountStatusListDto>:{}",dto.toString());
		return resultDto;
	}
}
