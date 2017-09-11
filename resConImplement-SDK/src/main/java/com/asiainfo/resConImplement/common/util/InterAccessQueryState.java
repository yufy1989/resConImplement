package com.asiainfo.resConImplement.common.util;
/**
* 类说明：返回状态
* @author baomz
* @date 2017年6月3日 上午11:54:00
*/
public enum InterAccessQueryState {

	SUCCESS("000000");
	private String value;
	InterAccessQueryState(String value){
		this.setValue(value);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
