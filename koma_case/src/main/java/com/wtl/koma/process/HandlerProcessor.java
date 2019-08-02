package com.wtl.koma.process;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import com.wtl.koma.annotations.OrderHandlerType;
import com.wtl.koma.constants.NormalConstants;
import com.wtl.koma.enums.OrderTypeEnum;
import com.wtl.koma.handler.HandlerContext;
import com.wtl.koma.utils.ClassScaner;

/**
 * <p>Description: 处理实体对象</p>
 * @author WTL
 * @date 2019年7月31日
 */
@SuppressWarnings("rawtypes")
@Component
public class HandlerProcessor implements BeanFactoryPostProcessor {

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		Map<OrderTypeEnum, Class> handlerOrderMap = new HashMap<>();
		ClassScaner.scan(NormalConstants.ORDERHANDLERPACKAGE, OrderHandlerType.class).forEach(clazz -> {
			//获取注解中的类型值
			OrderTypeEnum type = clazz.getAnnotation(OrderHandlerType.class).value();
			//将类型值做为key对应的类作为value
			handlerOrderMap.put(type, clazz);
		});
		//初始化HandlerContext,将其注册到Spring容器中
		HandlerContext context = new HandlerContext(handlerOrderMap);
		beanFactory.registerSingleton(HandlerContext.class.getName(), context);
	}

}

