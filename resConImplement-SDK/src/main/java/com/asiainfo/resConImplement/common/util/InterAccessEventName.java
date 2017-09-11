package com.asiainfo.resConImplement.common.util;
/**
* 类说明：communication 
* @author baomz
* @date 2017年6月3日 下午12:13:25
*/
public enum InterAccessEventName {

	/**
	 * 更新SIM卡通信计划
	 */
	UPDATE_SIM_COMMUNICATION_PLAN("更新SIM卡通信计划");
	
	private String value;
	InterAccessEventName(String value){
		this.setValue(value);
	}
	
	/**
	 * 功能描述：返回事件名称
	 * @author baomz
	 * @date 2017年6月3日 下午12:19:47
	 * @param @return 
	 * @return String
	 */
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
