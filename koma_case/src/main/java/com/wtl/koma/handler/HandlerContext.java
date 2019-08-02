package com.wtl.koma.handler;

import java.util.Map;


import com.wtl.koma.enums.OrderTypeEnum;
import com.wtl.koma.utils.BeanTool;

/**
 * <p>Description: </p>
 * @author WTL
 * @date 2019年8月1日
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class HandlerContext {
	
	private Map<OrderTypeEnum, Class> handlerMap;
	
	public HandlerContext(Map<OrderTypeEnum, Class> handlerMap) {
		this.handlerMap = handlerMap;
	}

	public AOrderHandler getInstance(OrderTypeEnum type) {
		Class clazz = handlerMap.get(type);
		if (clazz == null) {
			throw new IllegalArgumentException("没有找到此订单类型，请确认订单类型：");
		}
		
		return (AOrderHandler) BeanTool.getBean(clazz);
	}

}

