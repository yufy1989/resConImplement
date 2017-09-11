package com.asiainfo.resConImplement.mapper;

import com.asiainfo.resConImplement.po.ProductPolicyConfigPo;

public interface ProductPolicyConfigPoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductPolicyConfigPo record);

    int insertSelective(ProductPolicyConfigPo record);

    ProductPolicyConfigPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductPolicyConfigPo record);

    int updateByPrimaryKey(ProductPolicyConfigPo record);
}