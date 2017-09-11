package com.asiainfo.resConImplement.common;

public class MsgHeader {
	/*
	 * 用户名
	 */
	private String username;
	/*
	 * 密码
	 */
	private String password;

	/*
	 * 服务名
	 */
	private String serName;
	/*
	 * 操作名
	 */
	private String operName;
	/*
	 * 请求时间
	 */
	private String reqTime;
	/*
	 * 流水号
	 */
	private String messageId;
	/*
	 * 渠道编码
	 */
	private String channel_Code;
	/*
	 * 接入方平台编码
	 */
	private String platform_Code;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSerName() {
		return serName;
	}

	public void setSerName(String serName) {
		this.serName = serName;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getChannel_Code() {
		return channel_Code;
	}

	public void setChannel_Code(String channel_Code) {
		this.channel_Code = channel_Code;
	}

	public String getPlatform_Code() {
		return platform_Code;
	}

	public void setPlatform_Code(String platform_Code) {
		this.platform_Code = platform_Code;
	}

	@Override
	public String toString() {
		return "MsgHeader [username=" + username + ", password=" + password + ", serName=" + serName + ", operName="
				+ operName + ", password=" + password + ", reqTime=" + reqTime + ", messageId=" + messageId
				+ ", channel_Code=" + channel_Code + ", platform_Code=" + platform_Code + "]";
	}
}
