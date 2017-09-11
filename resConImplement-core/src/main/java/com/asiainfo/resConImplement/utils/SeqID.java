package com.asiainfo.resConImplement.utils;


/**
* 类说明：
* @author Administrator
* @date 2016年5月26日 下午8:36:14
*/
public enum SeqID {
	USER_ID("USER_ID", "100"), 
	TRADE_ID("TRADE_ID", "101"), 
	ACCT_ID("ACCT_ID", "102"),
	CUST_ID("CUST_ID", "103"),
	CONTACT_ID("CONTACT_ID", "104"),
	BILLID_ID("BILLID_ID", "105"),
	SUBSCRIBE_ID("SUBSCRIBE_ID", "106"),
	SERIAL_NUM("SERIALNUM","107"),
	TF_F_RESOURCE_ID("TFFRESOURCE_ID","108"),
	RESOURCEINTERFACELOG_ID("RESOURCEINTERFACELOG_ID","109"),
    BILLINTERFACELOG_ID("BILLINTERFACELOG_ID","200"),
    PAYMENTINTERFACE_ID("tfFPaymentInterfacePo","201"),
    RESDISPLAYLOG_ID("RESDISPLAYLOG_ID","202"),
	RESDISPLAYLOG_BATHNUM("RESDISPLAYLOG_BATHNUM","203"),
    PAYLOG_ID("PAYLOG_ID","301"),
    ACCESSLOG_ID("ACCESSLOG_ID","302"),
    WRITEOFFLOG_ID("WRITEOFFLOG_ID","303"),
    WRITESNAPLOG_ID("WRITESNAPLOG_ID","304"),
    PAYMENTINTERFACELOG_ID("PAYMENTINTERFACELOG_ID","305"),
	ORG_ID("ORG_ID", "306"),
	SYSUSER_ID("SYSUSER_ID", "307"),
	ROLE_ID("ROLE_ID", "308"),
	ACCT_BALANCE_ID("ACCT_BALANCE_ID","309"),
	CAR_NUMBER("CAR_NUMBER","310"),
	SP_PRODUCT_ID("SP_PRODUCT_ID","400"),
	SP_DISCNT_INSTANCE("SP_DISCNT_INSTANCE","401"),//SP的实例标识
	DISCNT_DISCNT_INSTANCE("DISCNT_DISCNT_INSTANCE","402"),//优惠的实例标识
	TRANSFER_SIMS_TO_ACCOUNT_LOG_ID("TRANSFER_SIMS_TO_ACCOUNT_LOG_ID","403");//优惠的实例标识

	// 成员变量
	private String IdName;
	private String IdSeq;

	// 构造方法
	private SeqID(String IdName, String IdSeq) {
		this.IdName = IdName;
		this.IdSeq = IdSeq;
	}

	// 普通方法
	public static String getIdName(String IdSeq) {
		for (SeqID c : SeqID.values()) {
			if (c.getIdSeq().equals(IdSeq)) {
				return c.IdName;
			}
		}
		return null;
	}

	// 普通方法
	public static String getIdSeq(String IdName) {
		for (SeqID c : SeqID.values()) {
			if (c.getIdName().equals(IdName)) {
				return c.IdSeq;
			}
		}
		return null;
	}

	public String getIdName() {
		return IdName;
	}

	public void setIdName(String idName) {
		IdName = idName;
	}

	public String getIdSeq() {
		return IdSeq;
	}

	public void setIdSeq(String idSeq) {
		IdSeq = idSeq;
	}
	
}
