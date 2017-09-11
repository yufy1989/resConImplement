package com.asiainfo.resConImplement.mapper;

import com.asiainfo.resConImplement.po.ServiceTypeZoneRulePo;

public interface ServiceTypeZoneRulePoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ServiceTypeZoneRulePo record);

    int insertSelective(ServiceTypeZoneRulePo record);

    ServiceTypeZoneRulePo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ServiceTypeZoneRulePo record);

    int updateByPrimaryKey(ServiceTypeZoneRulePo record);
}