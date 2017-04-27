
/**
* @Title: WebLoadingConfigs.java
* @Package com.zhidian.interceptors
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-18 下午11:16:33
* @version V1.0
*/
package com.zhidian.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName: WebLoadingConfigs
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-18 下午11:16:33
 *
 */
@Configuration
public class WebMvcConfigs extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new SimpleInterceptor()).addPathPatterns("/**").addPathPatterns("/simple/**");
		super.addInterceptors(registry);
	}
}
