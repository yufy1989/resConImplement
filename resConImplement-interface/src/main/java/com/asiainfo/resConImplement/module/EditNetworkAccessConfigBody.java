package com.asiainfo.resConImplement.module;
/**
* 类说明：
* @author Administrator
* @date 2016年7月30日 下午12:20:20
*/
public class EditNetworkAccessConfigBody {
	private String iccId;
	private String nacId;
	public String getIccId() {
		return iccId;
	}
	public void setIccId(String iccId) {
		this.iccId = iccId;
	}
	public String getNacId() {
		return nacId;
	}
	public void setNacId(String nacId) {
		this.nacId = nacId;
	}
	@Override
	public String toString() {
		return "EditNetworkAccessConfigBody [iccId=" + iccId + ", nacId=" + nacId + "]";
	}
}
