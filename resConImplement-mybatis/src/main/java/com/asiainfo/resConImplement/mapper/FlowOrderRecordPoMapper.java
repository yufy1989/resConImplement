package com.asiainfo.resConImplement.mapper;

import com.asiainfo.resConImplement.po.FlowOrderRecordPo;

public interface FlowOrderRecordPoMapper {
    int insert(FlowOrderRecordPo record);

    int insertSelective(FlowOrderRecordPo record);
}