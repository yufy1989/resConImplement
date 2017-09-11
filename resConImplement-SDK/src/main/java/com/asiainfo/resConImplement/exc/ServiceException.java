/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.asiainfo.resConImplement.exc;

/**
 * Service层公用的Exception.
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 * @author calvin
 */
public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 3583566093089790852L;
	private String msgCode;
	
	public ServiceException() {
		super();
	}

	public ServiceException(String message,String msgCode) {
		super(message);		
		this.msgCode=msgCode;
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
}
