package com.asiainfo.resConImplement.mapper;

import com.asiainfo.resConImplement.po.TcuChangeConfigPo;

public interface TcuChangeConfigPoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TcuChangeConfigPo record);

    int insertSelective(TcuChangeConfigPo record);

    TcuChangeConfigPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TcuChangeConfigPo record);

    int updateByPrimaryKey(TcuChangeConfigPo record);
}