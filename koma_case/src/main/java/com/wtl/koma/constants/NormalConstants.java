package com.wtl.koma.constants;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Description: 一般常量类</p>
 * @author WTL
 * @date 2019年7月18日
 */
public class NormalConstants {
	
	/**
	 * 防止用户连续重复提交的记录容器（可以用内存数据库如redis进行替换）
	 * key 用户ID-请求的类的全限定名-方法名
	 * value 随意值（主要为key）
	 */
	public static ConcurrentHashMap<String, String> userRequest = new ConcurrentHashMap<String, String>();
	
	public static AtomicInteger ai = new AtomicInteger();
	
	/**
	 * 订单处理的包
	 */
	public static final String ORDERHANDLERPACKAGE = "com.wtl.koma.handler.order";

}

