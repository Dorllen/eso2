package com.zhidian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminMainController {


	@GetMapping("")
	public String index_() {

		return "redirect:/admin/";
	}

	@GetMapping("/")
	public String index() {
		// 首页数据填充
		return "admin/index";
	}

	
	

}
