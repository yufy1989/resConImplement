package com.asiainfo.resConImplement.dto;

import java.io.Serializable;
import java.util.List;
/**
 * 订单同步实体类
 * @author liangdl
 *
 */
public class OrderAmountDto implements Serializable{
	/**
	 * V100	-备注 -订单号 
	 */
	private String orderNo;
	/**
	 * 订单金额
	 */
	private String amount;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	
}
