package com.asiainfo.resConImplement.dto;

/**
 * 功能描述：tcu更换服务返回模型
 * @author yufy
 * @date 2016年7月27日
 */
public class TcuChangeDto extends BaseDto{
	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/
	private static final long serialVersionUID = -1506262947161725791L;
	private String code;
	private String state;
	private String oldIccId;
	private String newIccId;
	private String oldState;
	private String newState;
	private String timeStamp;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOldIccId() {
		return oldIccId;
	}
	public void setOldIccId(String oldIccId) {
		this.oldIccId = oldIccId;
	}
	public String getNewIccId() {
		return newIccId;
	}
	public void setNewIccId(String newIccId) {
		this.newIccId = newIccId;
	}
	public String getOldState() {
		return oldState;
	}
	public void setOldState(String oldState) {
		this.oldState = oldState;
	}
	public String getNewState() {
		return newState;
	}
	public void setNewState(String newState) {
		this.newState = newState;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "TcuChangeDto [code=" + code + ", state=" + state + ", oldIccId=" + oldIccId + ", newIccId=" + newIccId
				+ ", oldState=" + oldState + ", newState=" + newState + ", timeStamp=" + timeStamp + "]";
	}
	
}
