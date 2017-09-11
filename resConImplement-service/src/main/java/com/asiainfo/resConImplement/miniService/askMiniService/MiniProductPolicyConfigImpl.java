package com.asiainfo.resConImplement.miniService.askMiniService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.resConImplement.common.util.ConversionUtil;
import com.asiainfo.resConImplement.dto.ProductPolicyConfigDto;
import com.asiainfo.resConImplement.mapper.ext.ProductPolicyConfigPoExtMapper;
import com.asiainfo.resConImplement.po.ProductPolicyConfigPo;

/**
* 类说明：产品策略配置
* @author yufy
* @date 2016年7月20日 上午11:39:08
*/
@Service
public class MiniProductPolicyConfigImpl{
	
	@Autowired
	private ProductPolicyConfigPoExtMapper productPolicyConfigPoExtMapper;
	
	
	/**
	 * 功能描述：根据产品ID获得策略信息
	 * @author pankx
	 * @date 2016年8月9日 下午6:36:15
	 * @param @param productId
	 * @param @return 
	 * @return ProductPolicyConfigDto
	 */
	public ProductPolicyConfigDto getPolicyInfoByProductId(String productId){
	
		ProductPolicyConfigPo po = productPolicyConfigPoExtMapper.selectPolicyByProductId(productId);
		if(po!=null){
			return (ProductPolicyConfigDto) ConversionUtil.po2dto(po, ProductPolicyConfigDto.class);
		}
		return null;
	}
}
