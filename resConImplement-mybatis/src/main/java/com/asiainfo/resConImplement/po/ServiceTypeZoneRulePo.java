package com.asiainfo.resConImplement.po;

import java.io.Serializable;

public class ServiceTypeZoneRulePo implements Serializable {
    private String id;

    private String zone;

    private String serviceType;

    private String revStr1;

    private String revStr2;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone == null ? null : zone.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getRevStr1() {
        return revStr1;
    }

    public void setRevStr1(String revStr1) {
        this.revStr1 = revStr1 == null ? null : revStr1.trim();
    }

    public String getRevStr2() {
        return revStr2;
    }

    public void setRevStr2(String revStr2) {
        this.revStr2 = revStr2 == null ? null : revStr2.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}