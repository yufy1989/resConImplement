package com.asiainfo.resConImplement.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：更新新SIM卡的资费计划出参
 * @author zhaoxy9
 * @date 2016年7月25日
 */
public class EditTerminalResponseParamGroupDto extends BaseDto {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -8516644615686450793L;

	private String iccId;

	private Date effectiveDate;
	
	private String any;
	
	public String getIccId() {
		return iccId;
	}

	public void setIccId(String iccId) {
		this.iccId = iccId;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getAny() {
		return any;
	}

	public void setAny(String any) {
		this.any = any;
	}


}
