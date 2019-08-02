package com.wtl.koma.service;

import org.springframework.stereotype.Service;

import com.wtl.koma.annotations.NoRepeatedSubmit;
import com.wtl.koma.exception.MissingParametersException;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Description: </p>
 * @author WTL
 * @date 2019年7月15日
 */
@Slf4j
@Service
public class TestService {
	
	/**
	 * 测试异常
	 */
	public void testE(){
		throw new MissingParametersException();
	}
	
	/**
	 * 测试重复提交
	 * @param userId
	 * @param mCode
	 * @throws InterruptedException
	 */
	@NoRepeatedSubmit
	public void testRepeatedSubmit(Integer userId, String mCode) throws InterruptedException{
		log.info("用户{}在{}机器上开始处理业务。", userId, mCode);
		Thread.sleep(1000);
		log.info("用户{}在{}机器上结束处理业务。", userId, mCode);
	}

}

