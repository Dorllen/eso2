package com.zhidian.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultHandler(HttpServletRequest request,Exception e) throws Exception{
		if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
			throw e;
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("error/default");
		return model;
	}
}
