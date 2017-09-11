package com.asiainfo.resConImplement.mappings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* 类说明：用于注解 Jasper 方法
* @author Baomz
* @date 2016年7月21日 下午1:40:16
*/
@Target({ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME) 
public @interface JasperMethod {

	/**
	 * 功能描述：方法的名称
	 * @author Baomz
	 * @date 2016年7月21日 下午1:41:55
	 * @param @return 
	 * @return String
	 */
	String method();
	
	/**
	 * 功能描述：请求节点
	 * @author Baomz
	 * @date 2016年7月21日 下午1:42:33
	 * @param @return 
	 * @return String
	 */
	String requestEl();
	
	/**
	 * 功能描述：响应节点
	 * @author Baomz
	 * @date 2016年7月21日 下午1:42:48
	 * @param @return 
	 * @return String
	 */
	String responseEl();
}
