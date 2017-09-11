package com.asiainfo.resConImplement.dto;

import java.io.Serializable;

/**
* 类说明：专项政策 -The addtional policy
* @author pankx
* @date 2016年7月25日 下午4:43:02
*/
public class AdditionalPolicyTypeDto implements Serializable{

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/
	private static final long serialVersionUID = -8153574453108503087L;

	//实体是有序的。name,value这样封装到wsdl参数中
	private String name; //策略名称
	
	private String value; //默认传true

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "AdditionalPolicyTypeDto [name=" + name + ", value=" + value + "]";
	}
	
	
	
	
	

}
