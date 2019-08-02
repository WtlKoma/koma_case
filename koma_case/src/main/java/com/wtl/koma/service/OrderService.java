package com.wtl.koma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtl.koma.enums.OrderTypeEnum;
import com.wtl.koma.handler.AOrderHandler;
import com.wtl.koma.handler.HandlerContext;

/**
 * <p>Description: 订单业务</p>
 * @author WTL
 * @date 2019年8月1日
 */
@Service
public class OrderService {
	
	@Autowired
	private HandlerContext handlerContext;
	
	/*
	 * 根据类型用相对应的订单处理类进行处理
	 */
	public String handle(Integer key){
		OrderTypeEnum typeEnum = OrderTypeEnum.getOrderType(key);
		AOrderHandler orderHandler = handlerContext.getInstance(typeEnum);
		return orderHandler.handle(typeEnum);
	}

}

