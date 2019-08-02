package com.wtl.koma.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>Description: 在非Spring管理的类中获取spring注册的bean</p>
 * @author WTL
 * @date 2019年8月1日
 */
@SuppressWarnings("static-access")
@Component
public class BeanTool implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (this.applicationContext == null) {
			this.applicationContext = applicationContext;
		}
	}
	
	public static Object getBean(String name){
		return applicationContext.getBean(name);
	}
	
	public static <T> T getBean(Class<T> clazz){
		return applicationContext.getBean(clazz);
	}

}

