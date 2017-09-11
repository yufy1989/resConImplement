package com.asiainfo.resConImplement.dto;

import java.io.Serializable;
import java.util.Date;

public class ProductPolicyConfigDto implements Serializable {
    private Integer id;

    private String callingparty;

    private String platformcode;

    private Integer productid;

    private String productname;

    private Integer policyid;

    private String policyname;

    private String status;

    private Date createtime;

    private Date updatetime;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public Integer getPolicyid() {
        return policyid;
    }

    public void setPolicyid(Integer policyid) {
        this.policyid = policyid;
    }

    public String getPolicyname() {
        return policyname;
    }

    public void setPolicyname(String policyname) {
        this.policyname = policyname == null ? null : policyname.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	@Override
	public String toString() {
		return "ProductPolicyConfigPo [id=" + id + ", callingparty=" + callingparty + ", platformcode=" + platformcode
				+ ", productid=" + productid + ", productname=" + productname + ", policyid=" + policyid
				+ ", policyname=" + policyname + ", status=" + status + ", createtime=" + createtime + ", updatetime="
				+ updatetime + ", remark=" + remark + "]";
	}
    
    
}