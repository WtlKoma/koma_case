package com.wtl.koma.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wtl.koma.constants.NormalConstants;
import com.wtl.koma.pojo.ConfigurationValuePojo;
import com.wtl.koma.service.TestService;

/**
 * <p>Description: </p>
 * @author WTL
 * @date 2019年7月15日
 */
@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private ConfigurationValuePojo cvp;
	
	/**
	 * 测试全局exception
	 * @return
	 */
	@PostMapping("testE")
	public String testException(){
		testService.testE();
		return "成功";
	}
	
	/**
	 * 测试重复提交
	 * @param userId
	 * @throws InterruptedException
	 */
	@PostMapping("testRS")
	public void testRepeatedSubmit(Integer userId) throws InterruptedException{
		//用的Jemeter按照时间模拟用户ID
		SimpleDateFormat df = new SimpleDateFormat("ss");
		userId = Integer.valueOf(df.format(new Date()));
		NormalConstants.ai.incrementAndGet();
		System.out.println("请求用户为：" + userId);
		testService.testRepeatedSubmit(userId, cvp.getMachineCode());
	}

}

