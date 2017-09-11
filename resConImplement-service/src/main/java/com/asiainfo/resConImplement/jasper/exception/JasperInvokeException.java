package com.asiainfo.resConImplement.jasper.exception;
/**
* 类说明：
* @author Baomz
* @date 2016年12月22日 上午11:33:00
*/
public class JasperInvokeException extends RuntimeException{

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)    
	*/
	private static final long serialVersionUID = 3156171556451276786L;
	

	public JasperInvokeException() {
		
	}

	public JasperInvokeException(String message) {
		super(message);
	}
	
	public JasperInvokeException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
