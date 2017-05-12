package com.zhidian.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhidian.controller.AdminInfoController;

public class LoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandler");
		String url = request.getRequestURI();
		if(url.indexOf("/admin")!=-1){
			Object o = request.getSession().getAttribute(AdminInfoController.ADMINCODE);
			if(o==null){
				response.sendRedirect(request.getContextPath()+"/admin/login");
				return false;
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
	}

}
