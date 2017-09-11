package com.asiainfo.resConImplement.mapper;

import com.asiainfo.resConImplement.po.InterQueryJasperInfoPo;

public interface InterQueryJasperInfoPoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InterQueryJasperInfoPo record);

    int insertSelective(InterQueryJasperInfoPo record);

    InterQueryJasperInfoPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InterQueryJasperInfoPo record);

    int updateByPrimaryKey(InterQueryJasperInfoPo record);
}