package com.asiainfo.resConImplement.dto;

import java.io.Serializable;

/**
 * 类说明：统一格式的 传输对象
 * 
 * @author Baomz
 * @date 2016年7月26日 下午3:52:23
 */
public class ResultDto< T extends BaseDto> implements Serializable{

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/
	private static final long serialVersionUID = -5394347595112561829L;

	
	public ResultDto(){
		
	}
	
	/**
	 * 响应头
	 */
	private HeadDto head = new HeadDto();
	
	/**
	 * 响应体
	 */
	private T body;
	
	public ResultDto(T body){
		this.setBody(body);
	}
	
	

	public HeadDto getHead() {
		return head;
	}

	public void setHead(HeadDto head) {
		this.head = head;
	}


	public void setMessageId(String messageId) {
		this.head.setMessageId(messageId);
	}
	
	public void setVersion(String version) {
		this.head.setVersion(version);
	}
	
	public void setBuild(String build) {
		this.head.setBuild(build);
	}
	
	public void setTimestamp(String timestamp) {
		this.head.setTimestamp(timestamp);
	}

	public void setCode(String code) {
		this.head.setCode(code);
	}
	
	public void setMessage(String message) {
		this.head.setMessage(message);
		
	}
	
	public void setFaultRequestId(String faultRequestId) {
		this.head.setFaultRequestId(faultRequestId);
	}
	
	public void setFaultError(String faultError) {
		this.head.setFaultError(faultError);
	}
	
	public void setFaultString(String faultString) {
		this.head.setFaultString(faultString);
	}

	public T getBody() {
		return body;
	}



	public void setBody(T body) {
		this.body = body;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("head=[");
			if(head != null){
				sb.append(head.toString());
			}
		sb.append("], body=["); 
		if(body != null){
			sb.append(body.toString());
		}
		sb.append("]");
		return sb.toString();
	}
}
