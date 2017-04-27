/**
 * @Title: SimpleServlet.java
 * @Package com.zhidian.configs
 * @Description: TODO(用一句话描述该文件做什么)
 * @author dongneng
 * @date 2017-3-20 上午9:52:00
 * @version V1.0
 */
package com.zhidian.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


import com.zhidian.interceptor.SimpleCrossOriginFilter;

/**
 * @ClassName: SimpleServlet
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-20 上午9:52:00
 * 
 */
//@Component
public class SimpleServletConfigs{// implements ServletContextInitializer 

/*	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		servletContext.addServlet("SimpleServ", new SimpleServlet())
				.addMapping("/simple/*");
		servletContext.addFilter("SimpleCrossFilter",
				new SimpleCrossOriginFilter()).addMappingForUrlPatterns(
				EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST),
				true, "/simple/gg/*");// true 与 false并没有什么用。  
	}*/

}
