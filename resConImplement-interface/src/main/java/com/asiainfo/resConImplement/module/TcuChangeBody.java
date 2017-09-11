package com.asiainfo.resConImplement.module;

import java.io.Serializable;

/**
 * 功能描述：tcu更换服务模型
 * @author yufy
 * @date 2016年7月27日
 */
public class TcuChangeBody implements Serializable{

	private static final long serialVersionUID = -1506262947161725791L;
	private String imsi;
	private String oldIccId;
	private String newIccId;
	private String carPricesName;
	private String carModelsName;
	private String carName;
	
	
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
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
	public String getCarPricesName() {
		return carPricesName;
	}
	public void setCarPricesName(String carPricesName) {
		this.carPricesName = carPricesName;
	}
	public String getCarModelsName() {
		return carModelsName;
	}
	public void setCarModelsName(String carModelsName) {
		this.carModelsName = carModelsName;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	@Override
	public String toString() {
		return "TcuChangeBody [imsi=" + imsi + ", oldIccId=" + oldIccId + ", newIccId=" + newIccId + ", carPricesName="
				+ carPricesName + ", carModelsName=" + carModelsName + ", carName=" + carName + "]";
	}
}
