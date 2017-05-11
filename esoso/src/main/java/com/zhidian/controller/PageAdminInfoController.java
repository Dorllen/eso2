package com.zhidian.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhidian.exception.PageArgumentsException;
import com.zhidian.service.AdminInfoSupportService;
import com.zhidian.views.VersionAddVO;
import com.zhidian.views.VersionControlDTO;
import com.zhidian.views.VersionUpdateVO;
import com.zhidian.views.WebsitePaDTO;

/**
 * @ClassName: PageAdminInfoController
 * @Description: TODO(对部分页面跳转进行控制)
 * @author dongneng
 * @date 2017年5月9日 下午10:13:22
 *
 */
@Controller
public class PageAdminInfoController {

	@Autowired
	AdminInfoSupportService infoService;
	
	@GetMapping("/admin/version-control-info.html")
	public String versionControlInfoPage(@RequestParam("id") String versionId, Model model) throws Exception {
		// 放入数据模型
		if (StringUtils.isNotEmpty(versionId)) {
			VersionUpdateVO view = infoService.getVersionInfoByVersionId(versionId);
			model.addAttribute("Message", view);
			return "admin/version-control-info";
		} else {
			throw new PageArgumentsException();
		}
	}

	@GetMapping("/admin/version-control-info-update.html")
	public String versionControlInfoUpdatePage(@RequestParam("id") String versionId, Model model) throws PageArgumentsException {
		// 放入数据模型
		if (StringUtils.isNotEmpty(versionId)) {
			VersionUpdateVO view = infoService.getVersionInfoByVersionId(versionId);
			model.addAttribute("Message", view);
			return "admin/version-control-info-update";
		} else {
			throw new PageArgumentsException();
		}
	}
	
	@GetMapping("/admin/version-control.html")
	public String versionControlPage(Model model){
		List<VersionControlDTO> list = infoService.getVersionInfoUsingList();
		model.addAttribute("Message", list);
		return "admin/version-control";
	}
	
	@GetMapping("/admin/version-add.html")
	public String versionAddPage(Model model){
		VersionAddVO view = infoService.getVersionAddInfoUsing();
		model.addAttribute("Message", view);
		return "admin/version-add";
	}
	
	@GetMapping("/admin/website-pa-list.html")
	public String websitePalistlistPage(Model model){
		List<WebsitePaDTO> list = infoService.getWebsitesPaList();
		model.addAttribute("Message", list);
		return "admin/website-pa-list";
	}
	
	@GetMapping("/admin/website-pa-version.html")
	public String websitePalistVersionPage(@RequestParam(value ="page",required= false) Integer page,Model model){
		List<WebsitePaDTO> list = infoService.getWebsitesPaList(page);
		model.addAttribute("Message", list);
		return "admin/website-pa-version";
	}
	
}
