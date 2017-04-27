
/**
* @Title: SimpleCrossOriginFilter.java
* @Package com.zhidian.interceptors
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-20 上午12:36:48
* @version V1.0
*/
package com.zhidian.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;

/**
 * @ClassName: SimpleCrossOriginFilter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-20 上午12:36:48
 *
 */
//@Component
public class SimpleCrossOriginFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter in...");
		System.out.println(System.nanoTime());
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        String name = request.getParameter("name");
        if(!StringUtils.isNullOrEmpty(name)){
        	System.out.println("in ");
        	chain.doFilter(request, resp);
        	System.out.println("out");
        }else{
        	System.out.println("to demo");
        	resp.sendRedirect("/demo");
        }
        System.out.println(System.nanoTime());
        System.out.println("doFilter out...");
	}

	public void destroy() {
		
	}


}
