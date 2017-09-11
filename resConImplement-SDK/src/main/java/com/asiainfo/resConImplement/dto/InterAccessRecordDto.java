package com.asiainfo.resConImplement.dto;

import java.io.Serializable;
import java.util.Date;

public class InterAccessRecordDto implements Serializable {
    private Integer id;

    private String serialnumber;

    private String interfacename;

    private String eventname;

    private String channelname;

    private String channelcode;

    private String iccid;

    private String inputparameter;

    private String queryip;

    private String callingparty;

    private String platformcode;

    private String querystate;

    private String respinfo;

    private Date querytime;

    private Date updatetime;

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

    public String getInterfacename() {
        return interfacename;
    }

    public void setInterfacename(String interfacename) {
        this.interfacename = interfacename == null ? null : interfacename.trim();
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname == null ? null : eventname.trim();
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname == null ? null : channelname.trim();
    }

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode == null ? null : channelcode.trim();
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

    public String getQueryip() {
        return queryip;
    }

    public void setQueryip(String queryip) {
        this.queryip = queryip == null ? null : queryip.trim();
    }

    public String getCallingparty() {
        return callingparty;
    }

    public void setCallingparty(String callingparty) {
        this.callingparty = callingparty == null ? null : callingparty.trim();
    }

    public String getPlatformcode() {
        return platformcode;
    }

    public void setPlatformcode(String platformcode) {
        this.platformcode = platformcode == null ? null : platformcode.trim();
    }

    public String getQuerystate() {
        return querystate;
    }

    public void setQuerystate(String querystate) {
        this.querystate = querystate == null ? null : querystate.trim();
    }

    public String getRespinfo() {
        return respinfo;
    }

    public void setRespinfo(String respinfo) {
        this.respinfo = respinfo == null ? null : respinfo.trim();
    }

    public Date getQuerytime() {
        return querytime;
    }

    public void setQuerytime(Date querytime) {
        this.querytime = querytime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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