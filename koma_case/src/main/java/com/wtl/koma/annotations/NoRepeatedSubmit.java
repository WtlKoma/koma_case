package com.wtl.koma.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Description: 防止重复进行提交注解</p>
 * @author WTL
 * @date 2019年7月18日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface NoRepeatedSubmit {

}

