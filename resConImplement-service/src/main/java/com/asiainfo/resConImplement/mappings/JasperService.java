package com.asiainfo.resConImplement.mappings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* 类说明：用于注解 Jasper服务
* @author Baomz
* @date 2016年7月21日 下午1:37:46
*/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JasperService {

	/**
	 * 功能描述：服务的名称
	 * @author Baomz
	 * @date 2016年7月21日 下午1:41:32
	 * @param @return 
	 * @return String
	 */
	String value();
}
