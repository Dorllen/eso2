
/**
* @Title: MainIndexController.java
* @Package com.zhidian.controllers
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-20 下午11:20:23
* @version V1.0
*/
package com.zhidian.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.zhidian.model.sys.VersionBO;
import com.zhidian.service.PageService;
import com.zhidian.views.IndexPageVO;

/**
 * @ClassName: MainIndexController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-20 下午11:20:23
 *
 */
@Controller
public class IndexMainController {
	
	@Autowired
	PageService pageService;
	
	@GetMapping(value={"","/"})
	public String index(
			HttpServletRequest request,
			Model model){
		// 首页准备
		// 如何首页的搜索框要提供更多的服务，需要到SearchEnumDefine.type，以及维护好WebSiteEnumDefine的爬虫对象
		IndexPageVO index = pageService.getIndexCurrentPageInfo();
		model.addAttribute("Message", index);
		if(index!=null){
			VersionBO version =index.getVersion();
			if(version!=null){
				String url = version.getDefPage();
				if(StringUtils.isNotEmpty(url)){
					System.out.println(url);
					return url;
				}
			}
		}
		return "index";
	}
}
