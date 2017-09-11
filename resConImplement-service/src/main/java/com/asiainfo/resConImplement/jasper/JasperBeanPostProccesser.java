package com.asiainfo.resConImplement.jasper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
* 类说明：
* @author Baomz
* @date 2016年7月25日 下午6:01:07
*/
@Component
public class JasperBeanPostProccesser implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		
		if(bean instanceof BaseAdapter){
			BaseAdapter base = (BaseAdapter) bean;
			base.initService(bean.getClass());
			base.initMethod(bean.getClass());
			System.out.println("init ...");
		}
		return bean;
	}

}
