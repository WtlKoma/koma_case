package com.wtl.koma.aop;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.wtl.koma.annotations.NoRepeatedSubmit;
import com.wtl.koma.constants.NormalConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Description: 重复提交注解处理</p>
 * @author WTL
 * @date 2019年7月18日
 */
@Slf4j
@Aspect
@Component
public class NRSAop {
	
	@Around("@annotation(nrs)")
	public void handlerNPS(ProceedingJoinPoint pjp, NoRepeatedSubmit nrs){
		String className = pjp.getSignature().getDeclaringTypeName();
		String methodName = pjp.getSignature().getName();
		Object[] args = pjp.getArgs();
		//用户ID-请求的类的全限定名-方法名
		String key = args[0] + "-" + className + "-" + methodName;
		log.info("{}类调用{}方法进入AOP", className, methodName);
		try {
			//判断当前是否存在用户在请求相同的接口
			while (!JudgeSUE(key)) {
				log.info("等待：" + key);
			}
			log.info("开始执行：" + key);
			pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}finally {
			log.info("第"+ NormalConstants.ai.get() +"个用户执行结束：" + key);
			NormalConstants.userRequest.put(key, "0");
		}
		log.info("离开AOP");
	}
	
	/**
	 *  判断是否包含本用户的相同请求
	 * @param args
	 * @param className
	 * @param methodName
	 * @return
	 */
	private Boolean JudgeSUE(String key){
		synchronized(key){
			if (NormalConstants.userRequest.get(key) == null || NormalConstants.userRequest.get(key).equals("0")) {
				NormalConstants.userRequest.put(key, "1");
				return true;
			}
			return false;
		}
	}

}

