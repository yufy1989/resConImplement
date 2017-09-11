package com.asiainfo.resConImplement.mapper.ext;

import com.asiainfo.resConImplement.po.ServiceTypeZoneRulePo;

public interface ServiceTypeZoneRulePoExtMapper {

    ServiceTypeZoneRulePo selectByServiceType(String serviceType);

}