package com.asiainfo.resConImplement.po;

import java.io.Serializable;
import java.util.Date;

public class TransferSimsToAccountConfigPo implements Serializable {
    private String id;

    private String iccid;

    private String vin;

    private String maker;

    private String model;

    private String year;

    private String feature;

    private String bundle;

    private String subAccountName;

    private String commPlan;

    private String ratePlan;

    private Date createTime;

    private String state;

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

    public String getSubAccountName() {
        return subAccountName;
    }

    public void setSubAccountName(String subAccountName) {
        this.subAccountName = subAccountName == null ? null : subAccountName.trim();
    }

    public String getCommPlan() {
        return commPlan;
    }

    public void setCommPlan(String commPlan) {
        this.commPlan = commPlan == null ? null : commPlan.trim();
    }

    public String getRatePlan() {
        return ratePlan;
    }

    public void setRatePlan(String ratePlan) {
        this.ratePlan = ratePlan == null ? null : ratePlan.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}