package com.zhidian.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhidian.bases.CommonClassLoader;
import com.zhidian.bases.CustomClassLoader;
import com.zhidian.exception.PageArgumentsException;
import com.zhidian.service.AdminInfoSupportService;
import com.zhidian.service.DataInfoAdminService;
import com.zhidian.views.ResultListModel;
import com.zhidian.views.ResultModel;
import com.zhidian.views.ServiceSettingsDTO;
import com.zhidian.views.WebsitePageVO;
import com.zhidian.views.WormSettingsSearchResultVO;

//@Controller
@RestController
@RequestMapping("/admin/website/info")
public class WebsiteAdminInfoController {

	@Autowired
	DataInfoAdminService dataService;

	@Autowired
	AdminInfoSupportService infoService;

	/*
	 * @GetMapping("") public String index_() { return "redirect:/"; }
	 * 
	 * @GetMapping("/") public String index(Model model) {
	 * 
	 * return ""; }
	 */

	@GetMapping("/part")
	public Object websiteInforPart(@RequestParam("name") String name) {
		List<WormSettingsSearchResultVO> list = dataService.getWebsiteVersionListByName(name);
		ResultListModel result = new ResultListModel();
		if (list == null || list.size() == 0) {
			result.setMessage("未找到结果");
		} else {
			result.setTotal(list.size());
			result.setItems(list);
		}
		return result;
	}

	@GetMapping("/detail")
	public Object websiteInforDetail(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		WebsitePageVO page = dataService.getWebsiteInforDetailByNameAndId(id, name);
		ResultModel result = new ResultModel();
		if (page == null) {
			result.setMessage("未找到结果");
		} else {
			result.setItem(page);
		}
		return result;
	}

	@GetMapping("/getVersions")
	public Object getWebsiteAllVersions(@RequestParam("name") String name) {
		ResultListModel result = new ResultListModel();
		List<String> list = dataService.getWebsiteAllVersionList(name);
		if (list != null && list.size() > 0) {
			result.setItems(list);
		} else {
			result.setMessage("请求参数有误");
		}

		return result;
	}

	@GetMapping("/getWebsites")
	public Object getAllWebsitesName() {
		ResultListModel result = new ResultListModel();
		List<String> list = dataService.getAllWebsites();
		if (list != null && list.size() > 0) {
			result.setItems(list);
		} else {
			result.setMessage("请求有误");
		}
		return result;
	}

	@GetMapping("/getItemService")
	public Object getItemsService(@RequestParam("id") int id, @RequestParam("name") String name)
			throws PageArgumentsException {
		ResultListModel result = new ResultListModel();
		List<ServiceSettingsDTO> dto = infoService.getItemServiceByItemsIdAndName(id, name);
		if (dto != null) {
			result.setItems(dto);
			result.setTotal(dto.size());
		}
		return result;
	}

	@GetMapping("/getInfo") // test
	public Object uploadTest2(@RequestParam("name") String name) {
		try {
			System.out.println("name:" + name);
			Class<?> clz = Class.forName("com.zhidian.bases.worms.pipeline.Result");
			Object obj = clz.newInstance();
			System.out.println(obj.toString());
			return obj.toString();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/getInfo3") // test
	@ResponseBody
	public Object uploadTest3(@RequestParam("name") String name) {
		CommonClassLoader cl = new CommonClassLoader();
		try {
			System.out.println(cl);
			Class<?> claz = Class.forName("com.common.Result", true, cl);
			Object o = claz.newInstance();
			System.out.println(o);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Autowired
	CommonClassLoader c;

	@GetMapping("/getInfo4") // test
	@ResponseBody
	public Object uploadTest4(@RequestParam("name") String name) {
		try {
			System.out.println(c);
			Class<?> claz = Class.forName("com.common.Result", true, c);
			Object o = claz.newInstance();
			System.out.println(o);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/getInfo5") // test
	@ResponseBody
	public Object uploadTest5(@RequestParam("name") String name) {
		System.out.println("name:" + name);
		String root = System.getProperty("webapp.root");
		CustomClassLoader claz = new CustomClassLoader(root + File.separator + "WEB-INF" + File.separator + "classes"
				+ File.separator + "com" + File.separator + "zhidian" + File.separator + "bases" + File.separator
				+ "worms" + File.separator + "pipeline" + File.separator + "Result.class");
		try {
			Class<?> clz = claz.loadClass("com.common.Result");
			Object o = clz.newInstance();
			System.out.println(o);
			return o;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
