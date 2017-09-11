package com.asiainfo.resConImplement.mapper;

import com.asiainfo.resConImplement.po.InterAccessRecordPo;

public interface InterAccessRecordPoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InterAccessRecordPo record);

    int insertSelective(InterAccessRecordPo record);

    InterAccessRecordPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InterAccessRecordPo record);

    int updateByPrimaryKey(InterAccessRecordPo record);
}