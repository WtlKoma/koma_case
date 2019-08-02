package com.wtl.koma.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wtl.koma.enums.OrderTypeEnum;

/**
 * <p>Description: 订单处理类型注解</p>
 * @author WTL
 * @date 2019年8月1日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface OrderHandlerType {
	
	OrderTypeEnum value();

}

