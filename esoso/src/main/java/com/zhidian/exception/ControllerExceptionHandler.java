package com.zhidian.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
public class ControllerExceptionHandler {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	
	@ExceptionHandler(value=PageArgumentsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView NotFoundPage(HttpServletRequest request){
		log.info("参数传递错误...");
		ModelAndView model = new ModelAndView();
		model.addObject("StatusCode", 400);
		model.addObject("From", request.getRequestURL());
//		model.addObject("Message", "");
		model.setViewName("error/template");
		return model;
	}
	
}
