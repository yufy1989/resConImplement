package com.asiainfo.resConImplement.module;

public class NetworkAccessConfigBody {
	private String iccId;

	public String getIccId() {
		return iccId;
	}

	public void setIccId(String iccId) {
		this.iccId = iccId;
	}

	@Override
	public String toString() {
		return "NetworkAccessConfigBody [iccId=" + iccId + "]";
	}
}
