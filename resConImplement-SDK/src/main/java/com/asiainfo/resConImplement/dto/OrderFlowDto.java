package com.asiainfo.resConImplement.dto;


/**
* 类说明：流量订购返回参数
* @author pankx
* @date 2016年7月27日 下午3:33:39
*/
public class OrderFlowDto extends BaseDto{
	
	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/
	private static final long serialVersionUID = 1L;
	
	private String imsi; //
	public String getImsi() {
		return imsi;
	}

	public void setImsi(String iccid) {
		this.imsi = iccid;
	}

	@Override
	public String toString() {
		return "OrderFlowDto [imsi=" + imsi + "]";
	}
	
	
	
	
}
