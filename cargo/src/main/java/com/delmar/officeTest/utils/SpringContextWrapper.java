package com.delmar.officeTest.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextWrapper implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextWrapper.applicationContext = applicationContext;
	}
	
	/**
	* 根据类型进行获取Bean的实例
	* @param clsType
	* @return
	*/
	public static <T> T getBean(Class<T> clsType){
		T obj = null;
		if (null != applicationContext){
			obj =  applicationContext.getBean(clsType);
		}
		return obj;
	}
}
