package com.wtl.koma.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wtl.koma.exception.MissingParametersException;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Description: Controller异常处理</p>
 * @author WTL
 * @date 2019年7月15日
 */
@Slf4j
@ControllerAdvice
public class controllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public String errorHandler(HttpServletRequest request, Exception e){
		log.info("errorHandler:", e);
		return StringUtils.isBlank(e.getMessage()) ? "您的网络异常，请稍后……" : e.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(value = MissingParametersException.class)
	public String errorHandlerMissingParametersException(HttpServletRequest request, MissingParametersException e){
		log.info("errorHandlerMissingParametersException:", e);
		return StringUtils.isBlank(e.getMessage()) ? "您的传参异常，请重试" : e.getMessage();
	}

//	@Override
//	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//		log.info("进入处理异常");
//		if (ex instanceof MissingParametersException) {
//			return errorHandler((MissingParametersException) ex);
//		}
//		return super.handleExceptionInternal(ex, body, headers, status, request);
//	}
	
	

}

