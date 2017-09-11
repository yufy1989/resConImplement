package com.asiainfo.resConImplement.mapper;

import com.asiainfo.resConImplement.po.TransferSimsToAccountLogPo;

public interface TransferSimsToAccountLogPoMapper {
    int deleteByPrimaryKey(String id);

    int insert(TransferSimsToAccountLogPo record);

    int insertSelective(TransferSimsToAccountLogPo record);

    TransferSimsToAccountLogPo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TransferSimsToAccountLogPo record);

    int updateByPrimaryKey(TransferSimsToAccountLogPo record);
}