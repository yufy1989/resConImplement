package com.asiainfo.resConImplement.dto;

import java.io.Serializable;

public class UpdateNetworkAccessConfigDto implements Serializable {
    private String serialnumber;

    private String returnstate;

    private String effectivedate;

    private String nacid;

    private String iccid;

    private static final long serialVersionUID = 1L;

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
    }

    public String getReturnstate() {
        return returnstate;
    }

    public void setReturnstate(String returnstate) {
        this.returnstate = returnstate == null ? null : returnstate.trim();
    }

    public String getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(String effectivedate) {
        this.effectivedate = effectivedate == null ? null : effectivedate.trim();
    }

    public String getNacid() {
        return nacid;
    }

    public void setNacid(String nacid) {
        this.nacid = nacid == null ? null : nacid.trim();
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid == null ? null : iccid.trim();
    }

	@Override
	public String toString() {
		return "UpdateNetworkAccessConfigDto [serialnumber=" + serialnumber + ", returnstate=" + returnstate
				+ ", effectivedate=" + effectivedate + ", nacid=" + nacid + ", iccid=" + iccid + "]";
	}
}