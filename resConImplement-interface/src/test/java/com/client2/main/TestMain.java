package com.client2.main;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.client2.MsgHeader;
import com.client2.NetworkAccessConfigDto;
import com.client2.NetworkAccessSer;

public class TestMain {

	public static void main(String[] args) {
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(NetworkAccessSer.class);
		factory.setAddress("http://localhost:8080/resConImplement-interface/services/networkAccessSer");

		factory.getInInterceptors().add(new org.apache.cxf.interceptor.LoggingInInterceptor());

		//客户端授权拦截器
		factory.getOutInterceptors().add(new org.apache.cxf.interceptor.LoggingOutInterceptor());

		NetworkAccessSer hh = factory.create(NetworkAccessSer.class);
		
		MsgHeader msgHeader=new MsgHeader();
		
		NetworkAccessConfigDto ww =hh.getNetworkAccessConfig(msgHeader, "89860115623101107763", "zhen1001033");
		System.out.println(ww.getBuild());
		System.out.println(ww.getCorrelationId());
		System.out.println(ww.getIccId());
		
		for(String s:ww.getNacIds()){
			System.out.println("NacId:"+s);
		}

	}

}
