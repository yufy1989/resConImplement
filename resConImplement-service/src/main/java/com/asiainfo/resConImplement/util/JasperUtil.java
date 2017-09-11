package com.asiainfo.resConImplement.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类说明：读取 Jasper 配置的工具类
 * 
 * @author Baomz
 * @date 2016年7月20日 下午2:30:33
 */
public class JasperUtil {

	private static Logger logger = LoggerFactory.getLogger(JasperUtil.class); 
	private static String PREFIX;
	private static String NS_URL;
	private static String SERVICE_URL;
	private static Properties prop = new Properties();

	static {
		reLoad();
		logger.debug("load secure properties");
		PREFIX = prop.getProperty("jasper.prefix");
		NS_URL = prop.getProperty("jasper.ns.url");
		SERVICE_URL = prop.getProperty("jasper.service.url");
	}
	
     public static void reLoad(){
    	 try {
 			prop.load(JasperUtil.class.getClassLoader().getResourceAsStream("secure.properties"));
 		} catch (IOException e) {
 			logger.error("load secure error");
 			e.printStackTrace();
 		}
     }
     
     /**
      * 功能描述：获取特定厂商对应的用户名
      * @author Baomz
      * @date 2016年10月14日 下午4:13:44
      * @param @param token 厂商标记
      * @param @return 
      * @return String
      */
     public static String getUserName(String token){
    	 StringBuffer sb =  new StringBuffer();
    	 sb.append("jasper.").append(token).append(".username");
    	 return prop.getProperty(sb.toString());
     }
     
     /**
      * 功能描述：获取特定厂商对应的用户密码
      * @author Baomz
      * @date 2016年10月14日 下午4:13:44
      * @param @param token 厂商标记
      * @param @return 
      * @return String
      */
     public static String getPassword(String token){
    	 StringBuffer sb =  new StringBuffer();
    	 sb.append("jasper.").append(token).append(".password");
    	 return prop.getProperty(sb.toString());
     }
     
     
     /**
      * 功能描述：获取特定厂商对应的用户密码
      * @author Baomz
      * @date 2016年10月14日 下午4:13:44
      * @param @param token 厂商标记
      * @param @return 
      * @return String
      */
     public static String getLicenseKey(String token){
    	 StringBuffer sb =  new StringBuffer();
    	 sb.append("jasper.").append(token).append(".licenseKey");
    	 return prop.getProperty(sb.toString());
     }

	
	/**
	 * 功能描述：获取前缀
	 * @author Baomz
	 * @date 2016年7月20日 下午2:48:53
	 * @param @return 
	 * @return String
	 */
	public static String getPrefix() {
		return PREFIX;
	}

	/**
	 * 功能描述：获取命名空间URL
	 * @author Baomz
	 * @date 2016年7月20日 下午2:49:08
	 * @param @return 
	 * @return String
	 */
	public static String getNsUrl() {
		return NS_URL;
	}

	/**
	 * 功能描述：获取服务URL
	 * @author Baomz
	 * @date 2016年7月20日 下午2:49:35
	 * @param @return 
	 * @return String
	 */
	public static String getServiceUrl() {
		return SERVICE_URL;
	}

}
