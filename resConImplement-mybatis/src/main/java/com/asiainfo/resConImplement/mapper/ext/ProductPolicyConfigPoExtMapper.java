package com.asiainfo.resConImplement.mapper.ext;

import org.apache.ibatis.annotations.Param;

import com.asiainfo.resConImplement.po.ProductPolicyConfigPo;

public interface ProductPolicyConfigPoExtMapper {
	
	/**
	 * 功能描述：根据产品ID获得策略名称
	 * @author pankx
	 * @date 2016年8月4日 下午7:44:50
	 * @param @param productId
	 * @param @return 
	 * @return ProductPolicyConfigPo
	 */
	ProductPolicyConfigPo selectPolicyByProductId(@Param("productId")String productId);
   
}