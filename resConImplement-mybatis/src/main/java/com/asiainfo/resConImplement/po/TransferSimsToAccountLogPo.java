package com.asiainfo.resConImplement.po;

import java.io.Serializable;
import java.util.Date;

public class TransferSimsToAccountLogPo implements Serializable {
    private String id;

    private String iccid;

    private String vin;

    private String maker;

    private String model;

    private String year;

    private String feature;

    private String bundle;

    private String code;

    private String message;

    private Date createTime;

    private Date updateTime;

    private String revcol;

    private String revcol2;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid == null ? null : iccid.trim();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle == null ? null : bundle.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRevcol() {
        return revcol;
    }

    public void setRevcol(String revcol) {
        this.revcol = revcol == null ? null : revcol.trim();
    }

    public String getRevcol2() {
        return revcol2;
    }

    public void setRevcol2(String revcol2) {
        this.revcol2 = revcol2 == null ? null : revcol2.trim();
    }
}