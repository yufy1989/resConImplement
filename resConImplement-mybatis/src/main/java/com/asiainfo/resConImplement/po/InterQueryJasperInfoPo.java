package com.asiainfo.resConImplement.po;

import java.io.Serializable;
import java.util.Date;

public class InterQueryJasperInfoPo implements Serializable {
    private Integer id;

    private String serialnumber;

    private String queryaskinterface;

    private String queryservicename;

    private String queryjasperinterface;

    private String iccid;

    private String inputparameter;

    private String returnparameter;

    private String returnstate;

    private Date returntime;

    private String reserve1;

    private String reserve2;

    private String reserve3;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
    }

    public String getQueryaskinterface() {
        return queryaskinterface;
    }

    public void setQueryaskinterface(String queryaskinterface) {
        this.queryaskinterface = queryaskinterface == null ? null : queryaskinterface.trim();
    }

    public String getQueryservicename() {
        return queryservicename;
    }

    public void setQueryservicename(String queryservicename) {
        this.queryservicename = queryservicename == null ? null : queryservicename.trim();
    }

    public String getQueryjasperinterface() {
        return queryjasperinterface;
    }

    public void setQueryjasperinterface(String queryjasperinterface) {
        this.queryjasperinterface = queryjasperinterface == null ? null : queryjasperinterface.trim();
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid == null ? null : iccid.trim();
    }

    public String getInputparameter() {
        return inputparameter;
    }

    public void setInputparameter(String inputparameter) {
        this.inputparameter = inputparameter == null ? null : inputparameter.trim();
    }

    public String getReturnparameter() {
        return returnparameter;
    }

    public void setReturnparameter(String returnparameter) {
        this.returnparameter = returnparameter == null ? null : returnparameter.trim();
    }

    public String getReturnstate() {
        return returnstate;
    }

    public void setReturnstate(String returnstate) {
        this.returnstate = returnstate == null ? null : returnstate.trim();
    }

    public Date getReturntime() {
        return returntime;
    }

    public void setReturntime(Date returntime) {
        this.returntime = returntime;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3 == null ? null : reserve3.trim();
    }
}