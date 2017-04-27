package com.zhidian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zhidian.bases.worm.WormsService;
import com.zhidian.model.sys.PullPageObjectModel;

@Controller
@RequestMapping("/admin")
public class AdminMainController {

	@Autowired
	WormsService wormsService;

	@GetMapping("")
	public String index_() {

		return "redirect:/admin/";
	}

	@GetMapping("/")
	public String index() {
		// 首页数据填充
		return "admin/index";
	}

	@GetMapping("/worm/start")
	@ResponseBody
	public List<PullPageObjectModel> startPullData() {
		List<PullPageObjectModel> list= wormsService.startPullDataFromScheduleByAdminTrigger();
		System.out.println(JSON.toJSONString(list));
		return list;
	}
}
