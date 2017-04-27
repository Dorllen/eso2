
/**
* @Title: GlobalExceptionHandler.java
* @Package com.zhidian.exceptions
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-20 下午4:33:56
* @version V1.0
*/
package com.zhidian.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-20 下午4:33:56
 *
 */
//@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "not found")
	@ExceptionHandler(IOException.class)
	public void common(){
		System.out.println("Common Exception Handler ...");
	}
	
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error/404");
        System.out.println("defaultErrorHandler ...");
        return mav;
    }
}
