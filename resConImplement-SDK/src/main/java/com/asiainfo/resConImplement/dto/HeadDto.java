package com.asiainfo.resConImplement.dto;

import java.io.Serializable;

/**
 * 类说明： 头部信息
 * 
 * @author Baomz
 * @date 2016年7月26日 下午3:13:54
 */
public class HeadDto implements Serializable {

	private static final long serialVersionUID = 5116220736472630567L;

	/**
	 * 对应请求对象的 MessageId
	 */
	private String messageId;

	/**
	 * The version of the response payload schema
	 */
	private String version;

	/**
	 * Server build number
	 */
	private String build;

	/**
	 * The time when server processes the request, in UTC format
	 */
	private String timestamp;

	/**
	 * 消息代码
	 */
	private String code;

	/**
	 * 消息描述
	 */
	private String message;

	/**
	 * 错误返回标识
	 */
	private String faultRequestId;

	/**
	 * 错误信息
	 */
	private String faultError;

	/**
	 * 错误内容
	 */
	private String faultString;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFaultRequestId() {
		return faultRequestId;
	}

	public void setFaultRequestId(String faultRequestId) {
		this.faultRequestId = faultRequestId;
	}

	public String getFaultError() {
		return faultError;
	}

	public void setFaultError(String faultError) {
		this.faultError = faultError;
	}

	public String getFaultString() {
		return faultString;
	}

	public void setFaultString(String faultString) {
		this.faultString = faultString;
	}

	@Override
	public String toString() {
		return "messageId=" + messageId + ", version=" + version + ", build=" + build + ", timestamp="
				+ timestamp + ", code=" + code + ", message=" + message + ", faultRequestId=" + faultRequestId
				+ ", faultError=" + faultError + ", faultString=" + faultString;
	}
}
