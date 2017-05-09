package com.zhidian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhidian.model.sys.NameValueModel;
import com.zhidian.service.AdminInfoSupportService;
import com.zhidian.views.ConfigDTO;
import com.zhidian.views.ResultListModel;
import com.zhidian.views.VersionControllerViewDTO;

@RestController
@RequestMapping("/admin/version/info")
public class VersionAdminInfoController {

	@Autowired
	AdminInfoSupportService infoService;

	@GetMapping("/getDefaultsType")
	public Object getDefaultVersionTypeInfo() {
		ResultListModel result = new ResultListModel();
		List<ConfigDTO> list = infoService.getConfigForVersionList();
		if(list!=null){
			result.setTotal(list.size());
			result.setItems(list);
		}
		return result;
	}
	
	@GetMapping("/getVersionWebsites")
	public Object getVersionListByTypeName(@RequestParam("type") String type){
		List<NameValueModel> list = infoService.getWebsitesFromVersionByType(type);
		ResultListModel result = new ResultListModel();
		if(list!=null){
			result.setTotal(list.size());
			result.setItems(list);
		}
		return result;
	}
	
	@GetMapping("/getVersionInfo")
	public Object getVersionListBySearch(
			@RequestParam("type") String type,
			@RequestParam("value") String value) throws Exception{
		ResultListModel result = new ResultListModel();
		List<VersionControllerViewDTO> list = infoService.getVersionBySearch(type,value);
		if(list!=null){
			result.setTotal(list.size());
			result.setItems(list);
		}
		return result;
	}
	
}
