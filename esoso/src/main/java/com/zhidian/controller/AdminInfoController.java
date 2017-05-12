package com.zhidian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminInfoController {
	public final static String ADMINCODE = "ADMINCODE_2017";

	@GetMapping("")
	public String index_() {

		return "redirect:/admin/";
	}

	@GetMapping("/")
	public String index(HttpServletRequest request) {
		// 首页数据填充
		// 没有登陆跳转到登陆页面
		Object obj = request.getSession().getAttribute(ADMINCODE);
		if (obj == null) {
			return "redirect:login";
		} else {
			return "admin/index";
		}
	}

	@GetMapping("/login")
	public String login(HttpServletRequest request) {
//		String code = UUID.randomUUID().toString();
		System.out.println(" --/login controller --  ");
		return "admin/login";
	}
}
