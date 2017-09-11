package com.asiainfo.resConImplement.common.util;
/**
* 类说明：平台编码
* @author baomz
* @date 2017年6月3日 上午11:47:37
*/
public enum PlatFormCode {

	JASPER("JASPER");
	
	private String value;
	
	PlatFormCode(String value){
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
