package com.asiainfo.resConImplement.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：更新新SIM卡的资费计划入参
 * @author zhaoxy9
 * @date 2016年7月25日
 */
public class EditTerminalRequestParamGroupDto extends BaseDto {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -8516644615686450793L;
	/**
	 * 卡号
	 */
	private String iccId;
	/**
	 * 生效时间
	 */
	private String effectiveDate;
	/**
	 * 目标值
	 */
	private String targetValue;
	/**
	 * 变更类型
	 */
	private int changeType;

	public String getIccId() {
		return iccId;
	}

	public void setIccId(String iccId) {
		this.iccId = iccId;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}

	public int getChangeType() {
		return changeType;
	}

	public void setChangeType(int changeType) {
		this.changeType = changeType;
	}

	@Override
	public String toString() {
		return "EditTerminalRequestParamGroupDto [iccId=" + iccId
				+ ", effectiveDate=" + effectiveDate + ", targetValue="
				+ targetValue + ", changeType=" + changeType + "]";
	}
	
	

}
