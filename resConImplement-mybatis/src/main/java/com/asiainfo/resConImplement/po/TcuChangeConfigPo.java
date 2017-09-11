package com.asiainfo.resConImplement.po;

import java.io.Serializable;
import java.util.Date;

public class TcuChangeConfigPo implements Serializable {
    private Integer id;

    private String carpricesname;

    private String carmodelsname;

    private String carname;

    private String ifLive;

    private String state;

    private Date createtime;

    private Date updatetime;

    private String reserve1;

    private String reserve2;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarpricesname() {
        return carpricesname;
    }

    public void setCarpricesname(String carpricesname) {
        this.carpricesname = carpricesname == null ? null : carpricesname.trim();
    }

    public String getCarmodelsname() {
        return carmodelsname;
    }

    public void setCarmodelsname(String carmodelsname) {
        this.carmodelsname = carmodelsname == null ? null : carmodelsname.trim();
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname == null ? null : carname.trim();
    }

    public String getIfLive() {
        return ifLive;
    }

    public void setIfLive(String ifLive) {
        this.ifLive = ifLive == null ? null : ifLive.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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
}