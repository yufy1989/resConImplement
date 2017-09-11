package com.asiainfo.resConImplement.jasper;

import java.util.Map;
import java.util.Set;

import com.asiainfo.resConImplement.dto.JasperTokenInfoBean;
import com.asiainfo.resConImplement.util.JasperUtil;

/**
* 类说明：
* @author Baomz
* @date 2016年10月17日 上午11:27:17
*/
public class ReloadAllTokens implements Runnable{
	
	private Map<String, JasperTokenInfoBean> tokenInfoMap;
	
	public ReloadAllTokens(Map<String, JasperTokenInfoBean> tokenInfoMap){
		this.tokenInfoMap = tokenInfoMap;
	}

	@Override
	public void run() {
		
		Set<String> tokens = tokenInfoMap.keySet();
		
		for(String token: tokens){
			String licenseKey = JasperUtil.getLicenseKey(token);
			String userName = JasperUtil.getUserName(token);
			String password = JasperUtil.getPassword(token);
			if(licenseKey != null || userName != null || password != null){
				JasperTokenInfoBean tokenInfo = tokenInfoMap.get(token);
				tokenInfo.setLicenseKey(licenseKey);
				tokenInfo.setUserName(userName);
				tokenInfo.setPassword(password);
			}
		}
	}

}
