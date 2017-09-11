package com.asiainfo.resConImplement.po;

import java.io.Serializable;

public class UpdateTeminalConfigPo implements Serializable {
    private String serialnumber;

    private String returnstate;

    private String iccid;

    private String effectivedate;

    private String targetvalue;

    private String changetype;

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

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid == null ? null : iccid.trim();
    }

    public String getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(String effectivedate) {
        this.effectivedate = effectivedate == null ? null : effectivedate.trim();
    }

    public String getTargetvalue() {
        return targetvalue;
    }

    public void setTargetvalue(String targetvalue) {
        this.targetvalue = targetvalue == null ? null : targetvalue.trim();
    }

    public String getChangetype() {
        return changetype;
    }

    public void setChangetype(String changetype) {
        this.changetype = changetype == null ? null : changetype.trim();
    }
}