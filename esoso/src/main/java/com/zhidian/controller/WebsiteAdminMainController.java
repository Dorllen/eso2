package com.zhidian.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.zhidian.bases.AppEnumDefine;
import com.zhidian.exception.PageArgumentsException;
import com.zhidian.service.AdminMainSupportService;
import com.zhidian.service.DataInfoAdminService;
import com.zhidian.util.BasicUtils;
import com.zhidian.util.FileUtils;
import com.zhidian.views.PullArticleUpdateModel;
import com.zhidian.views.ResultModel;
import com.zhidian.views.WebsiteMainUploadModel;
import com.zhidian.views.WebsitePostModel;
import com.zhidian.views.WebsitePostModel2;

@RestController
@RequestMapping("/admin/website/settings")
public class WebsiteAdminMainController {

	@Autowired
	DataInfoAdminService dataService;

	@Autowired
	AdminMainSupportService mainService;

	@PostMapping("/upload") // test.html测试上传
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

	@PostMapping("/updateWebsiteTest")
	public Object updateWebsiteTest(@ModelAttribute @Valid WebsitePostModel model, BindingResult error) {
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

	// 上面代码待验证

	@PostMapping("/setDefault")
	public Object setPullArticleDefaultUsing(@RequestParam("id") int id, @RequestParam("name") String name)
			throws PageArgumentsException {
		ResultModel result = new ResultModel();
		int num = mainService.setPullArticleDefaultUsing(id, name);
		if (num > 0) {
			result.setMessage("操作成功!");
		} else {
			result.setMessage("操作失败!");
		}
		return result;
	}

	@PostMapping("/deleteItem")
	public Object deletePullArticle(@RequestParam("id") int id, @RequestParam("name") String name)
			throws PageArgumentsException {
		ResultModel result = new ResultModel();
		int num = mainService.deletePullArticle(id, name);
		if (num > 0) {
			result.setMessage("操作成功!");
		} else {
			result.setMessage("操作失败!");
		}
		return result;
	}

	@PostMapping("/updateItemService")
	public Object updatePullArticleService(@RequestParam("id") int id, @RequestParam("name") String name,
			HttpServletRequest request) throws PageArgumentsException {
		ResultModel result = new ResultModel();
		Enumeration<String> map = request.getAttributeNames();
		// 取出有效的key，value
		if (map != null) {
			List<String> list = new ArrayList<String>();
			while (map.hasMoreElements()) {
				String key = map.nextElement();
				if (key != null && key.length() > 0) {
					// 校验 key 是否存在 AppEnumDefine.ConfigWebsiteService
					try {
						if (AppEnumDefine.ConfigWebsiteService.valueOf(key) != null) {
							// 记录key and value
							Integer value = (Integer) request.getAttribute(key);
							if (value != null && value == 0) {// 0代表关闭，关闭直接进入数据库
								list.add(key);
							}
						}
					} catch (Exception e) {
						// valueOf如果不是枚举类型的值会报错！
					}
				}
			}
			if (list.size() > 0) {
				int num = mainService.updateItemServiceByListKey(id, name, list);
				if (num > 0) {
					result.setMessage("操作成功!");
				} else {
					result.setMessage("操作失败!");
				}
			} else {
				// 没有更新的，就直接告知忽略了!
			}
		}
		return result;
	}

	@PostMapping("/updateItemInfo")
	public Object updatePullArticle(@ModelAttribute @Valid PullArticleUpdateModel article, BindingResult error)
			throws PageArgumentsException {
		ResultModel result = new ResultModel();
		if (error != null && error.getErrorCount() > 0) {
			result.setMessage("参数异常!");
		} else {
			int num = mainService.updateItemInfo(article, "Admin");
			if (num > 0) {
				result.setMessage("操作成功!");
			} else {
				result.setMessage("操作失败!");
			}
		}
		return result;
	}

	@PostMapping("/web/deleteWebsiteForce")
	public Object deleteWebsiteForceByWebsiteIdAndName(@RequestParam("id") String websiteId,
			@RequestParam("name") String name) throws PageArgumentsException {
		ResultModel result = new ResultModel();
		int num = mainService.deleteWebsiteForceByWebsiteIdAndName(websiteId, name);
		if (num > 0) {
			result.setCode("200");
			result.setMessage("操作成功!");
		} else {
			result.setCode("401");// 可能前置条件有
			result.setMessage("操作失败!");
		}
		return result;
	}

	@PostMapping("/web/deleteWebsite")
	public Object deleteWebsiteByWebsiteIdAndName(@RequestParam("id") String websiteId,
			@RequestParam("name") String name) throws PageArgumentsException {
		ResultModel result = new ResultModel();
		int num = mainService.deleteWebsiteByWebsiteIdAndName(websiteId, name);
		if (num > 0) {
			result.setCode("200");
			result.setMessage("操作成功!");
		} else {
			result.setCode("401");// 可能前置条件有
			result.setMessage("操作失败!");
		}
		return result;
	}

	@PostMapping("/web/setDefaultWebsite")
	public Object setDefaultWebsiteByWebsiteIdAndName(@RequestParam("id") String websiteId,
			@RequestParam("name") String name) throws PageArgumentsException {
		ResultModel result = new ResultModel();
		int num = mainService.updateWebsiteForSetDefaultByWebsiteIdAndName(websiteId, name);
		if (num > 0) {
			result.setCode("200");
			result.setMessage("操作成功!");
		} else {
			result.setCode("401");// 可能前置条件有
			result.setMessage("操作失败!");
		}
		return result;
	}

	@PostMapping("/updateWebsiteForce")
	public Object updateWebsiteForce(
			@RequestParam(value = "pageProcessorClass", required = false) MultipartFile pageProcessorClass,
			@RequestParam(value = "pagePipelineClass", required = false) MultipartFile pagePipelineClass,
			@RequestParam(value = "pageRObjectClass", required = false) MultipartFile pageRObjectClass,
			@RequestParam(value = "resultProcessorClass", required = false) MultipartFile resultProcessorClass,
			@RequestParam(value = "resultPipelineClass", required = false) MultipartFile resultPipelineClass,
			@RequestParam(value = "resultRObjectClass", required = false) MultipartFile resultRObjectClass,
			@ModelAttribute @Valid WebsiteMainUploadModel model, BindingResult error) throws PageArgumentsException {
		// 强制提交
		// 可能需要接收到上传的字节码文件。
		ResultModel result = new ResultModel();
		if (error != null && error.getErrorCount() > 0) {
			result.setMessage("检查参数!");
		} else {
			// 检查参数
			if (!FileUtils.checkIsSuffixForExit(pageProcessorClass, "class")
					|| !FileUtils.checkIsSuffixForExit(pagePipelineClass, "class")
					|| !FileUtils.checkIsSuffixForExit(pageRObjectClass, "class")
					|| !FileUtils.checkIsSuffixForExit(resultProcessorClass, "class")
					|| !FileUtils.checkIsSuffixForExit(resultPipelineClass, "class")
					|| !FileUtils.checkIsSuffixForExit(resultRObjectClass, "class")) {
				result.setMessage("文件格式不正确!");
				return result;
			}
			String root = System.getProperty("webapp.root");
			String baseDirectory = root + File.separator + "WEB-INF" + File.separator + "classes2" + File.separator
					+ "com" + File.separator + "zhidian" + File.separator;
			try {
				// 统一忽略文件存在，直接覆盖
				uploadFileHandler(pageProcessorClass, pagePipelineClass, pageRObjectClass, resultProcessorClass,
						resultPipelineClass, resultRObjectClass, model, baseDirectory, true);
			} catch (FileExistsException e) {
			}
			// 开始处理字节码文件.强制更新是忽略文件是否存在，而进行的直接覆盖
			int num = mainService.updateWebsiteForceForUpdataInfo(model, "Admin");
			if (num > 0) {
				result.setCode("200");
				result.setMessage("操作成功!");
			} else {
				result.setCode("401");// 可能前置条件有
				result.setMessage("操作失败!");
			}
		}
		return result;
	}

	private void uploadFileHandler(MultipartFile pageProcessorClass, MultipartFile pagePipelineClass,
			MultipartFile pageRObjectClass, MultipartFile resultProcessorClass, MultipartFile resultPipelineClass,
			MultipartFile resultRObjectClass, WebsiteMainUploadModel model, String baseDirectory, boolean igExit)
			throws FileExistsException {
		String name = FileUtils.keepMultipartFile(pageProcessorClass,
				baseDirectory + "bases" + File.separator + "worms" + File.separator + "processor", igExit);
		if (name != null) {
			model.setPageProcessor(name);
		}
		name = FileUtils.keepMultipartFile(pagePipelineClass,
				baseDirectory + "bases" + File.separator + "worms" + File.separator + "pipeline", igExit);
		if (name != null) {
			model.setPagePipeline(name);
		}
		name = FileUtils.keepMultipartFile(pageRObjectClass,
				baseDirectory + "bases" + File.separator + "worms" + File.separator + "model", igExit);
		if (name != null) {
			model.setPageRObject(name);
		}
		name = FileUtils.keepMultipartFile(resultProcessorClass,
				baseDirectory + "bases" + File.separator + "worms" + File.separator + "processor", igExit);
		if (name != null) {
			model.setResultProcessor(name);
		}
		name = FileUtils.keepMultipartFile(resultPipelineClass,
				baseDirectory + "bases" + File.separator + "worms" + File.separator + "pipeline", igExit);
		if (name != null) {
			model.setPagePipeline(name);
		}
		name = FileUtils.keepMultipartFile(resultRObjectClass,
				baseDirectory + "bases" + File.separator + "worms" + File.separator + "model", igExit);
		if (name != null) {
			model.setResultRObject(name);
		}
	}

	@PostMapping("/updateWebsite")
	public Object updateWebsite(
			@RequestParam(value = "pageProcessorClass", required = false) MultipartFile pageProcessorClass,
			@RequestParam(value = "pagePipelineClass", required = false) MultipartFile pagePipelineClass,
			@RequestParam(value = "pageRObjectClass", required = false) MultipartFile pageRObjectClass,
			@RequestParam(value = "resultProcessorClass", required = false) MultipartFile resultProcessorClass,
			@RequestParam(value = "resultPipelineClass", required = false) MultipartFile resultPipelineClass,
			@RequestParam(value = "resultRObjectClass", required = false) MultipartFile resultRObjectClass,
			@ModelAttribute @Valid WebsiteMainUploadModel model, BindingResult error) throws PageArgumentsException {
		// 可能需要接收到上传的字节码文件。
		ResultModel result = new ResultModel();
		if (error != null && error.getErrorCount() > 0) {
			result.setMessage("检查参数!");
		} else {
			// 检查参数
			// 检查参数
			if (!FileUtils.checkIsSuffixForExit(pageProcessorClass, "class")
					|| !FileUtils.checkIsSuffixForExit(pagePipelineClass, "class")
					|| !FileUtils.checkIsSuffixForExit(pageRObjectClass, "class")
					|| !FileUtils.checkIsSuffixForExit(resultProcessorClass, "class")
					|| !FileUtils.checkIsSuffixForExit(resultPipelineClass, "class")
					|| !FileUtils.checkIsSuffixForExit(resultRObjectClass, "class")) {
				result.setMessage("文件格式不正确!");
				return result;
			}
			String root = System.getProperty("webapp.root");
			String baseDirectory = root + File.separator + "WEB-INF" + File.separator + "classes2" + File.separator
					+ "com" + File.separator + "zhidian" + File.separator;
			try {
				uploadFileHandler(pageProcessorClass, pagePipelineClass, pageRObjectClass, resultProcessorClass,
						resultPipelineClass, resultRObjectClass, model, baseDirectory, false);
			} catch (FileExistsException e) {
				// 说明有文件存在
				result.setMessage("文件存在,不可覆盖!");
				return result;
			}
			// 非强制更新是不能覆盖存在的文件
			int num = mainService.updateWebsiteForUpdataInfo(model, "Admin");
			if (num > 0) {
				result.setCode("200");
				result.setMessage("操作成功!");
			} else {
				result.setCode("401");// 可能前置条件有
				result.setMessage("操作失败!");
			}
		}return result;

	}

	@PostMapping("/add")
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
}
