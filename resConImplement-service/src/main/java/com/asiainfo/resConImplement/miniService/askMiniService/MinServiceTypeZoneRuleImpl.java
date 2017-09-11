package com.asiainfo.resConImplement.miniService.askMiniService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.mapper.ext.ServiceTypeZoneRulePoExtMapper;
import com.asiainfo.resConImplement.po.ServiceTypeZoneRulePo;

@Service
public class MinServiceTypeZoneRuleImpl {

	private static Logger logger = LoggerFactory.getLogger(MinServiceTypeZoneRuleImpl.class);
	
	@Autowired
	private ServiceTypeZoneRulePoExtMapper serviceTypeZoneRuleMapper;
	
	public ServiceTypeZoneRulePo selectByServiceType(String serviceType){
		logger.info("selectByServiceType param:"+serviceType);
		return serviceTypeZoneRuleMapper.selectByServiceType(serviceType);
	}
}
