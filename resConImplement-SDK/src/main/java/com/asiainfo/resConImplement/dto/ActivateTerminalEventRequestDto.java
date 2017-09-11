package com.asiainfo.resConImplement.dto;

/**
* 类说明：
* @author baomz
* @date 2017年6月2日 下午3:02:23
*/
public class ActivateTerminalEventRequestDto extends BaseDto{

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/
	private static final long serialVersionUID = 2046909426068962786L;
	
	private String iccid;
	private String eventName;
	private String  effectiveDate;
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	@Override
	public String toString() {
		return "ActivateTerminalEventDto [iccid=" + iccid + ", eventName=" + eventName + ", effectiveDate="
				+ effectiveDate + "]";
	}

}
