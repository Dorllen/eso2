package com.zhidian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhidian.exception.PageArgumentsException;
import com.zhidian.service.AdminInfoSupportService;
import com.zhidian.service.DataInfoAdminService;
import com.zhidian.views.ResultListModel;
import com.zhidian.views.ResultModel;
import com.zhidian.views.ResultMultiModel;
import com.zhidian.views.ServiceSettingsDTO;
import com.zhidian.views.VersionMainDTO;
import com.zhidian.views.WebsiteDetailDTO;
import com.zhidian.views.WebsiteMainDTO;
import com.zhidian.views.WebsitePaDTO;
import com.zhidian.views.WebsitePageVO;
import com.zhidian.views.WebsitePalistPullArticleDTO;
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
	// 以上待验证

	@GetMapping("/getItemService")
	public Object getItemService(@RequestParam("id") int id, @RequestParam("name") String name)
			throws PageArgumentsException {
		ResultListModel result = new ResultListModel();
		List<ServiceSettingsDTO> dto = infoService.getItemServiceByItemsIdAndName(id, name);
		if (dto != null) {
			result.setItems(dto);
			result.setTotal(dto.size());
		}
		return result;
	}

	@GetMapping("/getItemInfo")
	public Object getItemInfo(@RequestParam("id") int id, @RequestParam("name") String name)
			throws PageArgumentsException {
		ResultModel result = new ResultModel();
		WebsitePalistPullArticleDTO dto = infoService.getItemInfoByItemsIdAndName(id, name);
		result.setItem(dto);
		return result;
	}

	@GetMapping("/getItemInfo2")
	public Object getItemInfo2(@RequestParam("id") int id, @RequestParam("name") String name)
			throws PageArgumentsException {
		ResultMultiModel result = new ResultMultiModel();
		WebsitePalistPullArticleDTO dto = infoService.getItemInfoByItemsIdAndName(id, name);
		List<String> websiteList = infoService.getWebsitesListId(id, name);
		result.setItem(dto);
		result.setItem_(websiteList);
		return result;
	}

	@GetMapping("/getWebsiteAndVersionInfo")
	public Object getWebsiteAndVersionInfo(@RequestParam("websiteId") String websiteId) throws PageArgumentsException {
		ResultMultiModel result = new ResultMultiModel();
		WebsiteMainDTO website = infoService.getWebsiteMainDTOInfo(websiteId);
		VersionMainDTO version = infoService.getVersionMainDTOInfo(websiteId);
		result.setItem(website);
		result.setItem_(version);
		return result;
	}

	// @GetMapping("/getDefaultItem")
	// public Object getDefaultItemList()
	// throws PageArgumentsException {
	// ResultListModel result = new ResultListModel();
	// return result;
	// }

	@GetMapping("/pa/search")
	public Object getWebsitePaList(@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime, @RequestParam("type") String type,
			@RequestParam("value") String value) throws PageArgumentsException {
		ResultListModel result = new ResultListModel();
		List<WebsitePaDTO> list = infoService.getWebsitePaListList(startTime, endTime, type, value);
		if (list != null) {
			result.setItems(list);
			result.setTotal(list.size());
		}
		return result;
	}

	@GetMapping("/pa/search2")
	public Object getWebsitePaList2(@RequestParam("type") String type, @RequestParam("value") String value)
			throws PageArgumentsException {
		ResultListModel result = new ResultListModel();
		List<WebsitePaDTO> list = infoService.getWebsitePaListList(type, value);
		if (list != null) {
			result.setItems(list);
			result.setTotal(list.size());
		}
		return result;
	}

	// 以下是对Website表的主要操作
	@GetMapping("/web/search")// website-control.html
	public Object getWebsiteInfo(@RequestParam("type") String type, @RequestParam("value") String value) 
			throws PageArgumentsException {
		ResultListModel result = new ResultListModel();
		List<WebsiteDetailDTO> list = infoService.getWebsisteMainBySearch(type,value);
		if (list != null) {
			result.setItems(list);
			result.setTotal(list.size());
		}
		return result;
	}
	
	@GetMapping("/web/getVersions")
	public Object getWebsiteAllVersions(@RequestParam("name") String name) throws PageArgumentsException {
		ResultMultiModel result = new ResultMultiModel();
		List<String> list = infoService.getWebsiteAllVersionList(name);
		String checked = infoService.getWebsiteDefaultVersionId(name);
		if (list != null && list.size() > 0) {
			result.setItem(list);
			result.setItem_(checked);
		} else {
			result.setMessage("请求参数有误");
		}

		return result;
	}
	
	@GetMapping("/web/getWebsiteService")
	public Object getWebsiteAllService(@RequestParam("id") String websiteId,
			@RequestParam("name") String name)throws PageArgumentsException{
		ResultListModel result = new ResultListModel();
		List<ServiceSettingsDTO> dto = infoService.getWebsiteServiceByItemsIdAndName(websiteId, name);
		if (dto != null) {
			result.setItems(dto);
			result.setTotal(dto.size());
		}
		return result;
	}

	// @GetMapping("/getInfo") // test
	// public Object uploadTest2(@RequestParam("name") String name) {
	// try {
	// System.out.println("name:" + name);
	// Class<?> clz = Class.forName("com.zhidian.bases.worms.pipeline.Result");
	// Object obj = clz.newInstance();
	// System.out.println(obj.toString());
	// return obj.toString();
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// } catch (InstantiationException e) {
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// @GetMapping("/getInfo3") // test
	// @ResponseBody
	// public Object uploadTest3(@RequestParam("name") String name) {
	// CommonClassLoader cl = new CommonClassLoader();
	// try {
	// System.out.println(cl);
	// Class<?> claz = Class.forName("com.common.Result", true, cl);
	// Object o = claz.newInstance();
	// System.out.println(o);
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InstantiationException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// @Autowired
	// CommonClassLoader c;
	//
	// @GetMapping("/getInfo4") // test
	// @ResponseBody
	// public Object uploadTest4(@RequestParam("name") String name) {
	// try {
	// System.out.println(c);
	// Class<?> claz = Class.forName("com.common.Result", true, c);
	// Object o = claz.newInstance();
	// System.out.println(o);
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InstantiationException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// @GetMapping("/getInfo5") // test
	// @ResponseBody
	// public Object uploadTest5(@RequestParam("name") String name) {
	// System.out.println("name:" + name);
	// String root = System.getProperty("webapp.root");
	// CustomClassLoader claz = new CustomClassLoader(root + File.separator +
	// "WEB-INF" + File.separator + "classes"
	// + File.separator + "com" + File.separator + "zhidian" + File.separator +
	// "bases" + File.separator
	// + "worms" + File.separator + "pipeline" + File.separator +
	// "Result.class");
	// try {
	// Class<?> clz = claz.loadClass("com.common.Result");
	// Object o = clz.newInstance();
	// System.out.println(o);
	// return o;
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InstantiationException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return null;
	// }
}
