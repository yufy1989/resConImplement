package com.asiainfo.resConImplement.mapper;

import com.asiainfo.resConImplement.po.TransferSimsToAccountConfigPo;

public interface TransferSimsToAccountConfigPoMapper {
    int deleteByPrimaryKey(String id);

    int insert(TransferSimsToAccountConfigPo record);

    int insertSelective(TransferSimsToAccountConfigPo record);

    TransferSimsToAccountConfigPo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TransferSimsToAccountConfigPo record);

    int updateByPrimaryKey(TransferSimsToAccountConfigPo record);
}