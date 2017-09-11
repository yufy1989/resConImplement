package com.asiainfo.resConImplement.dto;

import java.io.Serializable;

/**
* 类说明：JasperAPI 中的 NacIdType 类型对应
* @author Baomz
* @date 2016年7月26日 下午6:09:04
*/
public class NacIdTypeDto implements Serializable {

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/
	private static final long serialVersionUID = -5868999554930106171L;
	
	/**
	 * nacId 元素
	 */
	private String nacId;

	public String getNacId() {
		return nacId;
	}

	public void setNacId(String nacId) {
		this.nacId = nacId;
	}
	
	
}
