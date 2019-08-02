package com.wtl.koma.handler;

import com.wtl.koma.enums.OrderTypeEnum;

/**
 * <p>Description: 订单处理抽象类</p>
 * @author WTL
 * @date 2019年8月1日
 */
public abstract class AOrderHandler {
	
	abstract public String handle(OrderTypeEnum type);

}

