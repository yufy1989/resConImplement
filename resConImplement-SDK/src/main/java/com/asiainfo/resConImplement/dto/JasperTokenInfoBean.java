package com.asiainfo.resConImplement.dto;
/**
* 类说明：Jasper 账户认证信息, 对应不同的厂商对应不同的账户信息
* @author Baomz
* @date 2016年10月14日 下午3:54:18
*/
public class JasperTokenInfoBean {
	
	/**
	 * 账户名
	 */
	private String userName;
	/**
	 * 账户密码
	 */
	private String password;
	/**
	 * 密钥
	 */
	private String licenseKey;
	
	/**
	 * 功能描述：保留默认的构造器
	 * @author Baomz
	 * @date 2016年10月14日 下午4:23:14
	 * @param
	 */
	public JasperTokenInfoBean(){
		
	}
	
	/**
	 * 功能描述：根据所有的属性进行构造
	 * @author Baomz
	 * @date 2016年10月14日 下午4:22:35
	 * @param @param userName 账户名
	 * @param @param password 密码
	 * @param @param licenseKey 密钥
	 */
	public JasperTokenInfoBean(String userName, String password, String licenseKey){
		this.userName = userName;
		this.password = password;
		this.licenseKey = licenseKey;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLicenseKey() {
		return licenseKey;
	}
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	
}
