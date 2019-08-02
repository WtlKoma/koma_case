package com.wtl.koma.handler.order;

import org.springframework.stereotype.Component;

import com.wtl.koma.annotations.OrderHandlerType;
import com.wtl.koma.enums.OrderTypeEnum;
import com.wtl.koma.handler.AOrderHandler;

/**
 * <p>Description:  订单处理类</p>
 * @author WTL
 * @date 2019年8月1日
 */
@Component
@OrderHandlerType(OrderTypeEnum.THREE)
public class ThreeHandler extends AOrderHandler {

	@Override
	public String handle(OrderTypeEnum type) {
		return type.getKey() + "类型，走了订单第三种处理";
	}

}

