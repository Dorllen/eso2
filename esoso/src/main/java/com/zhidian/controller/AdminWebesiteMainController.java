package com.zhidian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidian.service.DataInfoAdminService;
import com.zhidian.views.ResultModel;
import com.zhidian.views.WebsitePageVO;
import com.zhidian.views.WormSettingsSearchResultVO;

@Controller
@RequestMapping("/admin/website")
public class AdminWebesiteMainController {

	@Autowired
	DataInfoAdminService dataService;

	@GetMapping("")
	public String index_() {
		return "redirect:/";
	}

	@GetMapping("/")
	@ResponseBody
	public String index(Model model) {

		return "";
	}

	@GetMapping("/info/part")
	@ResponseBody
	public Object websiteInforPart(@RequestParam("name") String name) {
		List<WormSettingsSearchResultVO> list = dataService.getWebsiteVersionListByName(name);
		ResultModel result = new ResultModel();
		if (list == null || list.size() == 0) {
			result.setMessage("未找到结果");
		} else {
			result.setTotal(list.size());
			result.setItems(list);
		}
		return result;
	}

	@GetMapping("/info/detail")
	@ResponseBody
	public Object websiteInforDetail(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		WebsitePageVO page = dataService.getWebsiteInforDetailByNameAndId(id, name);
		ResultModel result = new ResultModel();
		if (page == null) {
			result.setMessage("未找到结果");
		} else {
			result.setTotal(1);
			result.setItem(page);
		}
		return result;
	}
}
