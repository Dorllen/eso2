package com.zhidian.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void defaultHandler(HttpServletRequest request,Exception e) throws Exception{
		if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
			throw e;
		}
//		ModelAndView model = new ModelAndView();
//		model.setViewName("error/default");
//		return model;
	}
}
