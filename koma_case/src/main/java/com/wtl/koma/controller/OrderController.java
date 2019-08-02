package com.wtl.koma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wtl.koma.service.OrderService;

/**
 * <p>Description: 订单测试类</p>
 * @author WTL
 * @date 2019年8月1日
 */
@RestController
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("handle")
	public String handle(Integer key){
		return orderService.handle(key);
	}

}

