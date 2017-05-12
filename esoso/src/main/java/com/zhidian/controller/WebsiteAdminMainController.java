package com.zhidian.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zhidian.bases.AppEnumDefine;
import com.zhidian.exception.FileExistException;
import com.zhidian.exception.PageArgumentsException;
import com.zhidian.service.AdminMainSupportService;
import com.zhidian.service.DataInfoAdminService;
import com.zhidian.util.BasicUtils;
import com.zhidian.util.FileUtils;
import com.zhidian.views.PullArticleUpdateModel;
import com.zhidian.views.ResultModel;
import com.zhidian.views.WebsiteMainAddModel;
import com.zhidian.views.WebsiteMainUploadModel;
import com.zhidian.views.WebsitePostModel;

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
			String baseDirectory = root + File.separator + "WEB-INF" + File.separator + "classes2";
			List<String> tempPath = null;
			String code = "" + new Date().getTime();
			try {
				// 统一忽略文件存在，直接覆盖
				// baseDiectory:d:/..../WEB-INF/classes2
				// 需要考虑如果数据更新入数据库失败需要还原，删除文件
				tempPath = uploadFileHandler(pageProcessorClass, pagePipelineClass, pageRObjectClass,
						resultProcessorClass, resultPipelineClass, resultRObjectClass, model, baseDirectory, true,
						code);
			} catch (FileExistException e) {
			}
			try {// 内部错误需要捕获，并且清除临时文件
				// 开始处理字节码文件.强制更新是忽略文件是否存在，而进行的直接覆盖
				int num = mainService.updateWebsiteForceForUpdateInfo(model, "Admin");
				if (num > 0) {
					// 文件转移
					if (tempPath != null && tempPath.size() > 0) {
						for (String s : tempPath) {
							FileUtils.copyFileToPath(
									baseDirectory + File.separator + FileUtils.TempFileName + File.separator + code, s,
									baseDirectory);
						}
					}
					result.setCode("200");
					result.setMessage("操作成功!");
				} else {
					result.setCode("401");// 可能前置条件有
					result.setMessage("操作失败!");
				}
			} catch (PageArgumentsException e) {
				throw e;
			}finally{
				if (tempPath != null && tempPath.size() > 0) {
					for (String s : tempPath) {
						FileUtils.deleteFileOnExist(
								baseDirectory + File.separator + FileUtils.TempFileName + File.separator + code, s);
					}
				}
			}
		}
		return result;
	}

	private List<String> uploadFileHandler(MultipartFile pageProcessorClass, MultipartFile pagePipelineClass,
			MultipartFile pageRObjectClass, MultipartFile resultProcessorClass, MultipartFile resultPipelineClass,
			MultipartFile resultRObjectClass, WebsiteMainUploadModel model, String root, boolean igExit, String code)
			throws FileExistException {
		String fPath = "com" + File.separator + "zhidian" + File.separator; // 文件名
		List<String> filePath = new ArrayList<String>();
		String name = FileUtils.keepMultipartFileToTemp(pageProcessorClass, root,
				fPath + "bases" + File.separator + "worms" + File.separator + "processor", igExit, code);
		if (name != null) {
			filePath.add(name);// com/sere/dsdf/vvv.class
			model.setPageProcessor(FileUtils.fileNameHandlerWithOutSuffix(name));
		}
		name = FileUtils.keepMultipartFileToTemp(pagePipelineClass, root,
				fPath + "bases" + File.separator + "worms" + File.separator + "pipeline", igExit, code);
		if (name != null) {
			filePath.add(name);
			model.setPagePipeline(FileUtils.fileNameHandlerWithOutSuffix(name));
		}
		name = FileUtils.keepMultipartFileToTemp(pageRObjectClass, root,
				fPath + "bases" + File.separator + "worms" + File.separator + "model", igExit, code);
		if (name != null) {
			filePath.add(name);
			model.setPageRObject(FileUtils.fileNameHandlerWithOutSuffix(name));
		}
		name = FileUtils.keepMultipartFileToTemp(resultProcessorClass, root,
				fPath + "bases" + File.separator + "worms" + File.separator + "processor", igExit, code);
		if (name != null) {
			filePath.add(name);
			model.setResultProcessor(FileUtils.fileNameHandlerWithOutSuffix(name));
		}
		name = FileUtils.keepMultipartFileToTemp(resultPipelineClass, root,
				fPath + "bases" + File.separator + "worms" + File.separator + "pipeline", igExit, code);
		if (name != null) {
			filePath.add(name);
			model.setResultPipeline(FileUtils.fileNameHandlerWithOutSuffix(name));
		}
		name = FileUtils.keepMultipartFileToTemp(resultRObjectClass, root,
				fPath + "bases" + File.separator + "worms" + File.separator + "model", igExit, code);
		if (name != null) {
			filePath.add(name);
			model.setResultRObject(FileUtils.fileNameHandlerWithOutSuffix(name));
		}
		return filePath;
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
			String baseDirectory = root + File.separator + "WEB-INF" + File.separator + "classes2";
			List<String> tempPath = new ArrayList<String>();
			String code = "" + new Date().getTime();
			try {
				tempPath = uploadFileHandler(pageProcessorClass, pagePipelineClass, pageRObjectClass,
						resultProcessorClass, resultPipelineClass, resultRObjectClass, model, baseDirectory, false,
						code);
			} catch (FileExistException e) {
				// 说明有文件存在
				result.setMessage("文件存在,不可覆盖!");
				return result;
			}
			try {
				// 非强制更新是不能覆盖存在的文件
				int num = mainService.updateWebsiteForUpdateInfo(model, "Admin");
				if (num > 0) {
					// 文件转移
					if (tempPath != null && tempPath.size() > 0) {
						for (String s : tempPath) {
							FileUtils.copyFileToPath(
									baseDirectory + File.separator + FileUtils.TempFileName + File.separator + code, s,
									baseDirectory);
						}
					}
					result.setCode("200");
					result.setMessage("操作成功!");
				} else {
					result.setCode("401");// 可能前置条件有
					result.setMessage("操作失败!");
				}
			} catch (PageArgumentsException e) {
				throw e;
			}finally{
				if (tempPath != null && tempPath.size() > 0) {
					for (String s : tempPath) {
						FileUtils.deleteFileOnExist(
								baseDirectory + File.separator + FileUtils.TempFileName + File.separator + code, s);
					}
				}
			}
		}
		return result;

	}

	@PostMapping("/addWebsite")
	public Object addWebsite(
			@RequestParam(value = "pageProcessorClass", required = false) MultipartFile pageProcessorClass,
			@RequestParam(value = "pagePipelineClass", required = false) MultipartFile pagePipelineClass,
			@RequestParam(value = "pageRObjectClass", required = false) MultipartFile pageRObjectClass,
			@RequestParam(value = "resultProcessorClass", required = false) MultipartFile resultProcessorClass,
			@RequestParam(value = "resultPipelineClass", required = false) MultipartFile resultPipelineClass,
			@RequestParam(value = "resultRObjectClass", required = false) MultipartFile resultRObjectClass,
			@ModelAttribute @Valid WebsiteMainAddModel model, BindingResult error) throws PageArgumentsException {
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
			String baseDirectory = root + File.separator + "WEB-INF" + File.separator + "classes2";
			List<String> tempPath = new ArrayList<String>();
			String code = "" + new Date().getTime();
			try {
				// 统一忽略文件存在，直接覆盖
				// baseDiectory:d:/..../WEB-INF/classes2
				// 需要考虑如果数据更新入数据库失败需要还原，删除文件
				tempPath = uploadFileHandler(pageProcessorClass, pagePipelineClass, pageRObjectClass,
						resultProcessorClass, resultPipelineClass, resultRObjectClass, model, baseDirectory, false,
						code);
			} catch (FileExistException e) {
				result.setMessage("文件存在,不可覆盖!");
				return result;
			}
			// 开始处理字节码文件.强制更新是忽略文件是否存在，而进行的直接覆盖
			try {
				int num = mainService.addWebsiteInfo(model, "Admin");
				if (num > 0) {
					// 文件转移
					if (tempPath != null && tempPath.size() > 0) {
						for (String s : tempPath) {
							FileUtils.copyFileToPath(
									baseDirectory + File.separator + FileUtils.TempFileName + File.separator + code, s,
									baseDirectory);
						}
					}
					result.setCode("200");
					result.setMessage("操作成功!");
				} else {
					result.setCode("401");// 可能前置条件有
					result.setMessage("操作失败!");
				}
			} catch (PageArgumentsException e) {
				throw e;
			}finally{
				if (tempPath != null && tempPath.size() > 0) {
					for (String s : tempPath) {
						FileUtils.deleteFileOnExist(
								baseDirectory + File.separator + FileUtils.TempFileName + File.separator + code, s);
					}
				}
			}
		}
		return result;
	}

	private List<String> uploadFileHandler(MultipartFile pageProcessorClass, MultipartFile pagePipelineClass,
			MultipartFile pageRObjectClass, MultipartFile resultProcessorClass, MultipartFile resultPipelineClass,
			MultipartFile resultRObjectClass, WebsiteMainAddModel model, String root, boolean igExit, String code)
			throws FileExistException {
		String fPath = "com" + File.separator + "zhidian" + File.separator; // 文件名
		List<String> filePath = new ArrayList<String>();
		String name = FileUtils.keepMultipartFileToTemp(pageProcessorClass, root,
				fPath + "bases" + File.separator + "worms" + File.separator + "processor", igExit, code);
		if (name != null) {
			filePath.add(name);// com/sere/dsdf/vvv.class
			model.setPageProcessor(FileUtils.fileNameHandlerWithOutSuffix(name));
		}
		name = FileUtils.keepMultipartFileToTemp(pagePipelineClass, root,
				fPath + "bases" + File.separator + "worms" + File.separator + "pipeline", igExit, code);
		if (name != null) {
			filePath.add(name);
			model.setPagePipeline(FileUtils.fileNameHandlerWithOutSuffix(name));
		}
		name = FileUtils.keepMultipartFileToTemp(pageRObjectClass, root,
				fPath + "bases" + File.separator + "worms" + File.separator + "model", igExit, code);
		if (name != null) {
			filePath.add(name);
			model.setPageRObject(FileUtils.fileNameHandlerWithOutSuffix(name));
		}
		name = FileUtils.keepMultipartFileToTemp(resultProcessorClass, root,
				fPath + "bases" + File.separator + "worms" + File.separator + "processor", igExit, code);
		if (name != null) {
			filePath.add(name);
			model.setResultProcessor(FileUtils.fileNameHandlerWithOutSuffix(name));
		}
		name = FileUtils.keepMultipartFileToTemp(resultPipelineClass, root,
				fPath + "bases" + File.separator + "worms" + File.separator + "pipeline", igExit, code);
		if (name != null) {
			filePath.add(name);
			model.setResultPipeline(FileUtils.fileNameHandlerWithOutSuffix(name));
		}
		name = FileUtils.keepMultipartFileToTemp(resultRObjectClass, root,
				fPath + "bases" + File.separator + "worms" + File.separator + "model", igExit, code);
		if (name != null) {
			filePath.add(name);
			model.setResultRObject(FileUtils.fileNameHandlerWithOutSuffix(name));
		}
		return filePath;
	}

}
