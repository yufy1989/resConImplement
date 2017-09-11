package com.asiainfo.resConImplement.dto;
/**
* 类说明：
* @author baomz
* @date 2017年6月2日 下午3:09:09
*/
public class ActivateTerminalEventResponseDto extends BaseDto{

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/
	private static final long serialVersionUID = 6844610509752452510L;
	
	private String iccid;
	
	private String status;

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ActivateTerminalEventResponseDto [iccid=" + iccid + ", status=" + status + "]";
	}
	
	

}
