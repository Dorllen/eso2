package com.zhidian.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.zhidian.bases.CommonClassLoader;
import com.zhidian.bases.CustomClassLoader;
import com.zhidian.service.DataInfoAdminService;
import com.zhidian.util.BasicUtils;
import com.zhidian.views.ResultListModel;
import com.zhidian.views.ResultModel;
import com.zhidian.views.ResultSimpleModel;
import com.zhidian.views.WebsitePageVO;
import com.zhidian.views.WebsitePostModel;
import com.zhidian.views.WebsitePostModel2;
import com.zhidian.views.WormSettingsSearchResultVO;

@Controller
@RequestMapping("/admin/website")
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

	@GetMapping("/info/part")
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

	@GetMapping("/info/detail")
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

	@PostMapping("/info/updateWebsite")
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

	@PostMapping("/info/setDefaultWebsite")
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

	@GetMapping("/info/getVersions")
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

	@GetMapping("/info/getWebsites")
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

	@PostMapping("/add")
	@ResponseBody
	public Object addNewWebsite(@ModelAttribute @Valid WebsitePostModel2 model, MultipartHttpServletRequest request,
			BindingResult error) {
		ResultModel result = new ResultModel();
		if (error.getErrorCount() > 0) {
			result.setMessage("参数验证不通过!");
		} else {
			MultipartFile f1 = request.getFile("pageProcessorClass");
			MultipartFile f2 = request.getFile("pageRObjectClass");
			MultipartFile f3 = request.getFile("resultProcessorClass");
			if (f1 != null && f2 != null && f3 != null) {
				if (f1.getOriginalFilename().lastIndexOf(".class") <= 0
						|| f2.getOriginalFilename().lastIndexOf(".class") <= 0
						|| f3.getOriginalFilename().lastIndexOf(".class") <= 0) {
					result.setMessage("字节码文件的格式不正确!");
					return result;
				} else {
					// 校验其他的字节码文件是否上传
					MultipartFile f4 = request.getFile("pagePipelineClass");
					MultipartFile f5 = request.getFile("resultPipelineClass");
					MultipartFile f6 = request.getFile("resultRObjectClass");
					if (f4 != null) {
						if (f4.getOriginalFilename().lastIndexOf(".class") <= 0) {
							result.setMessage("字节码文件的格式不正确!");
							return result;
						}
					}
					if (f5 != null) {
						if (f5.getOriginalFilename().lastIndexOf(".class") <= 0) {
							result.setMessage("字节码文件的格式不正确!");
							return result;
						}
					}
					if (f6 != null) {
						if (f6.getOriginalFilename().lastIndexOf(".class") <= 0) {
							result.setMessage("字节码文件的格式不正确!");
							return result;
						}
					}
					// 校验上传的配置文件格式是否正确[待改进]

					// 所有文件上傳成功之後的操作...
					int i = dataService.addNewWebsite(model, "Admin");
					if (i == 1) {
						// 开始保存文件
						String root = System.getProperty("webapp.root");
						String r = root + File.separator + "WEB-INF" + File.separator + "classes2" + File.separator
								+ "com" + File.separator + "zhidian" + File.separator;
						File f = new File(
								r + "bases" + File.separator + "worms" + File.separator + "processor" + File.separator,
								f1.getOriginalFilename());
						try {
							BasicUtils.copyFromBytes(f1.getBytes(), f);// pageProcessor
							f = new File(r + "model" + File.separator + "websites" + File.separator + "answer"
									+ File.separator, f2.getOriginalFilename());// pageRObject
							BasicUtils.copyFromBytes(f2.getBytes(), f);
							f = new File(
									r + "bases" + File.separator + "worms" + File.separator + "processor"
											+ File.separator, // resultProcessor
									f3.getOriginalFilename());
							BasicUtils.copyFromBytes(f3.getBytes(), f);
							if (f4 != null) {
								f = new File(r + "bases" + File.separator + "worms" + File.separator + "pipeline"
										+ File.separator, f4.getOriginalFilename());// pagePipeline
								BasicUtils.copyFromBytes(f4.getBytes(), f);
							}
							if (f5 != null) {
								f = new File(r + "bases" + File.separator + "worms" + File.separator + "pipeline"
										+ File.separator, f5.getOriginalFilename());// resultPipeline
								BasicUtils.copyFromBytes(f5.getBytes(), f);
							}
							if (f6 != null) {
								f = new File(r + "model" + File.separator + "websites" + File.separator + "answer"
										+ File.separator, f6.getOriginalFilename());// resultRObject
								BasicUtils.copyFromBytes(f6.getBytes(), f);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						result.setMessage("新增成功!");
					} else if (i == -1) {
						result.setMessage("新增失败,参数异常");
					}
				}
			} else {
				result.setMessage("字节码文件不能为空!");
				return result;
			}
			System.out.println(JSON.toJSONString(model));
			result.setMessage("验证中...");
		}
		return result;
	}

	@DeleteMapping("/delete/{id:[0-9]*}")
	@ResponseBody
	public Object deleteWebsiteById(@PathVariable("id") int id) {
		ResultSimpleModel result = new ResultSimpleModel();
		int i = dataService.deleteWebsiteById(id);
		if (i == 1) {
			result.setMessage("删除成功!");
		} else if (i == 0) {
			result.setMessage("删除失败!");
		} else if (i == -1) {
			result.setMessage("参数有误!");
		} else if (i == -2) {
			result.setMessage("不可删除!");
		}
		return result;
	}

	@PostMapping("/upload")
	@ResponseBody
	public Object uploadTest(MultipartHttpServletRequest request) {
		MultipartFile f = request.getFile("file");
		if (f != null) {
			System.out.println(f.getOriginalFilename());
			System.out.println(f.getContentType());
			System.out.println(f.getSize());
			String root = System.getProperty("webapp.root");
			File f1 = new File(root + "/WEB-INF/classes2/com/common/", f.getOriginalFilename());// resultPipeline
			try {
				BasicUtils.copyFromBytes(f.getBytes(), f1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "yes";
		}
		return "no!";
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
