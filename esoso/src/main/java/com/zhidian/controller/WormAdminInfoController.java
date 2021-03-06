package com.zhidian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidian.bases.worm.WormsService;
import com.zhidian.model.sys.PullPageObjectModel;
import com.zhidian.views.ResultModel;

@Controller
@RequestMapping("/admin/worm")
public class WormAdminInfoController {

	@Autowired
	WormsService wormsService;

	@GetMapping("")
	public String index_(){
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String index(Model model){
		
		
		return "admin/worm-index";
	}
	
	@GetMapping("/s/start")
	@ResponseBody
	public List<PullPageObjectModel> startPullData() {
		List<PullPageObjectModel> list= wormsService.startPullDataFromScheduleByAdminTrigger();
		return list;
	}
	
	@GetMapping("/s/s")
	@ResponseBody
	public Object startPullData2() {
		ResultModel result = new ResultModel();
		new Thread(new Runnable() {
			public void run() {
				wormsService.startPullDataFromScheduleByAdminTrigger();				
			}
		}).start();;
		result.setMessage("执行成功!");
		return result;
	}
	
	@GetMapping("/s/start/{id}")
	@ResponseBody
	public List<PullPageObjectModel> startPullDataForId(@PathVariable("id") int id){
		return wormsService.startPullDataFromScheduleByAdminTriggerForId(id);
	}
	
	@GetMapping("/s/pass")
	@ResponseBody
	public Object pass(){
		ResultModel result = new ResultModel();
		wormsService.passAll();
		result.setMessage("执行成功!");
		return result;
	}
	
}
