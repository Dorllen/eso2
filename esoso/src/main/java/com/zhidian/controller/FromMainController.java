/**
 * @Title: MainSearchController.java
 * @Package com.zhidian.interceptors
 * @Description: TODO(用一句话描述该文件做什么)
 * @author dongneng
 * @date 2017-3-19 下午8:15:39
 * @version V1.0
 */
package com.zhidian.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhidian.bases.worm.WormsService;

/**
 * @ClassName: MainSearchController
 * @Description: TODO(这个控制器的目的是：让搜索结果直接显示到页面，不经过数据处理，直接拿到搜索结果)
 * @author dongneng
 * @date 2017-3-19 下午8:15:39
 * 
 */
//@Controller
@RequestMapping("/from")
public class FromMainController {

	@Autowired
	WormsService wormsService;

	// 搜索服务
	@GetMapping(value = { "", "/" })
	public String index(@RequestParam(value = "q", required = false) String key, @RequestParam("t") String origin,
			@RequestParam("f") String from, Model model) {
		if (!StringUtils.isEmpty(key)) {
			if ("Origin".equals(origin)) {
				// 如果从来源，就直接在线获取数据，处理之后返回
				if(!StringUtils.isEmpty(from)){
					// 如果用户筛选了条件，即整个搜索的来源。
					
					
				}
			} else {
				// 从索引

				// 从数据库

				//
			}

		} else {
			// 提示 请输入关键词...

		}

		return "search/index";
	}

}
