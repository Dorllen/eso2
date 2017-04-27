package com.zhidian.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	
	
	@ExceptionHandler(value=PageArgumentsException.class)
	public ModelAndView NotFoundPage(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		System.out.println("请求参数有误！");
		model.addObject("StatusCode", 403);
		model.addObject("From", request.getRequestURL());
//		model.addObject("Message", "");
		model.setViewName("error/template");
		return model;
	}
	
}
