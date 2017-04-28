
/**
* @Title: Article.java
* @Package com.zhidian.controllers
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-21 上午5:01:08
* @version V1.0
*/
package com.zhidian.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidian.exception.PageArgumentsException;
import com.zhidian.service.PageService;
import com.zhidian.views.PullArticlePageVO;

/**
 * @ClassName: Article
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-21 上午5:01:08
 *
 */
@Controller
public class PullArticleMainController {

	@Autowired
	PageService pageService;

	@GetMapping("/pa/{uuid}")
	public String index(@PathVariable(value = "uuid", required = false) String uuid, Model model) throws Exception {
		// 内容显示，直接从数据看。

		if (StringUtils.isEmpty(uuid)) {
			throw new PageArgumentsException();
		} else if (!uuid.matches("[0-9A-Za-z]{8,}")) {
			throw new PageArgumentsException();
		}
		// 获得基本信息
		PullArticlePageVO result = pageService.getPullArticle(uuid);
		// 创建页面信息，如底部信息
		result = pageService.getPullArticleBottomInfo(result);
		model.addAttribute("Message", result);
		if (result == null) {
			throw new PageArgumentsException();
		} else if (StringUtils.isNotEmpty(result.getUrl())) {
			return result.getUrl();// "//websites/answer/segmentfault/0.0.0.0/index";
		} else {
			return "websites/answer/segmentfault/0.0.0.0/index";
		}
	}

	@GetMapping("/pa/f/{uuid}") // 直接从来源获取数据
	@ResponseBody
	public String notSecVisit(@PathVariable(value = "uuid", required = false) String uuid, HttpServletRequest request)
			throws PageArgumentsException {
		System.out.println("uuid:" + uuid);
		// 手动开始爬虫
		if (StringUtils.isEmpty(uuid)) {
			throw new PageArgumentsException();
		} else if (!uuid.matches("[0-9A-Za-z]{8,}")) {
			throw new PageArgumentsException();
		}
		String account = "test";// 获取登陆的用户账号
		String from = request.getHeader("Referer");
		String ip = request.getRemoteAddr();
		String url = pageService.recordVisitedCount(uuid, account, from, ip);
//		 return "redirect:http://www.cnblogs.com/yaowen/p/3779284.html";
		return "redirect:" + url;
	}
}
