package com.zhidian.controller;

import java.io.File;
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

import com.zhidian.bases.CommonClassLoader;
import com.zhidian.bases.CustomClassLoader;
import com.zhidian.service.DataInfoAdminService;
import com.zhidian.views.ResultListModel;
import com.zhidian.views.ResultModel;
import com.zhidian.views.WebsitePageVO;
import com.zhidian.views.WebsitePostModel;
import com.zhidian.views.WormSettingsSearchResultVO;

@Controller
@RequestMapping("/admin/website/info")
public class WebsiteAdminInfoController {

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

	@GetMapping("/part")
	@ResponseBody
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
	@ResponseBody
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

	// 提交

	@PostMapping("/updateWebsite")
	@ResponseBody
	public Object updateWebsite(@ModelAttribute @Valid WebsitePostModel model, BindingResult error) {
		// 可能需要接收到上传的字节码文件。
		ResultModel result = new ResultModel();
		if (error != null && error.getErrorCount() > 0) {
			result.setMessage("检查参数!");
		} else {
			dataService.updateWebsiteFromPostObject(model, "Admin");
			result.setCode("200");
			result.setMessage("更新成功!");
		}
		return result;
	}

	@PostMapping("/setDefaultWebsite")
	@ResponseBody
	public Object setWebsiteDefault(@RequestParam("id") String id, @RequestParam("name") String name) {
		ResultModel result = new ResultModel();
		if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(name)) {
			dataService.setWebisteDefaultUsing(id, name);
			result.setMessage("更新成功!");
		} else {
			result.setMessage("请求参数有误!");
		}
		return result;
	}

	@GetMapping("/getVersions")
	@ResponseBody
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
	@ResponseBody
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

	@GetMapping("/getInfo")
	@ResponseBody
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

	@GetMapping("/getInfo3")
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

	@GetMapping("/getInfo4")
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

	@GetMapping("/getInfo5")
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
