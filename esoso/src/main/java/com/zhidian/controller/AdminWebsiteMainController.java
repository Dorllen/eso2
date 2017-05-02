package com.zhidian.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidian.service.DataInfoAdminService;
import com.zhidian.views.ResultModel;
import com.zhidian.views.WebsitePageVO;
import com.zhidian.views.WebsitePostModel;
import com.zhidian.views.WormSettingsSearchResultVO;

@Controller
@RequestMapping("/admin/website")
public class AdminWebsiteMainController {

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

	// 提交

	@PostMapping("/info/updateWebsite")
	@ResponseBody
	public Object updateWebsite(@ModelAttribute @Valid WebsitePostModel model, BindingResult error) {
		// 可能需要接收到上传的字节码文件。
		ResultModel result = new ResultModel();
		if (error != null && error.getErrorCount() > 0) {
			result.setMessage("检查参数!");
		} else {
			dataService.updateWebsiteFromPostObject(model,"Admin");
			result.setCode("200");
			result.setMessage("更新成功!");
		}
		return result;
	}
	
	@PostMapping("/info/setDefaultWebsite")
	@ResponseBody
	public Object setWebsiteDefault(@RequestParam("id") String id, @RequestParam("name") String name){
		ResultModel result = new ResultModel();
		if(StringUtils.isNotEmpty(id)&&StringUtils.isNotEmpty(name)){
			dataService.setWebisteDefaultUsing(id,name);
			result.setMessage("更新成功!");
		}else{
			result.setMessage("请求参数有误!");
		}
		return result;
	}
}
