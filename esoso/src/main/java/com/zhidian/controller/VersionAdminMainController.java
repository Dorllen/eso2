package com.zhidian.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zhidian.service.AdminInfoSupportService;
import com.zhidian.service.AdminMainSupportService;
import com.zhidian.util.BasicUtils;
import com.zhidian.util.FileUtils;
import com.zhidian.views.ResultModel;
import com.zhidian.views.VersionAddViewMainDTO;

@RestController
@RequestMapping("/admin/version/settings")
public class VersionAdminMainController {
	// private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	AdminInfoSupportService infoService;

	@Autowired
	AdminMainSupportService mainService;

	@PostMapping("/addNew")
	public Object addNewVersion(@RequestParam("pageFile") MultipartFile pageFile,
			@RequestParam("cssFiles") MultipartFile[] cssFiles, @RequestParam("jsFiles") MultipartFile[] jsFiles,
			MultipartHttpServletRequest request, @ModelAttribute @Valid VersionAddViewMainDTO version,
			BindingResult error) throws IllegalStateException, IOException {
		ResultModel result = new ResultModel();
		if (error != null && error.getErrorCount() > 0) {
			// 有异常
			result.setMessage("操作失败,参数异常");
		} else {
			Date d = new Date();
			String root = System.getProperty("webapp.root");
			// 有文件则上传。有文件 有错误则拒绝
			Map<String, List<String>> fileTempPath = new HashMap<String, List<String>>();
			if (pageFile != null) {
				String pName = pageFile.getOriginalFilename();
				if (pName == null || pName.lastIndexOf(".html") <= 0) {
					result.setMessage("文件格式不正确!");
					return result;
				} else {
					File tF1 = new File(root + File.separator + "tempPage" + File.separator + d.getTime()
							+ File.separator + pageFile.getOriginalFilename());
					FileUtils.mkdirs(tF1);
					List<String> arr = new ArrayList<String>();
					pageFile.transferTo(tF1);
					arr.add(tF1.getAbsolutePath());
					fileTempPath.put("page", arr);
					version.setDefPage(pName);
				}
			}
			if (cssFiles != null && cssFiles.length > 0) {
				for (MultipartFile f : cssFiles) {
					if (f != null) {
						String pName = f.getOriginalFilename();
						if (pName == null || pName.lastIndexOf(".css") <= 0) {
							result.setMessage("文件格式不正确!");
							return result;
						} else {
							version.setDefCss(StringUtils.isNotEmpty(version.getDefCss()) ? version.getDefCss()
									: "" + "," + pName);
						}
					}
				}
				String tempPath = root + File.separator + "tempCss" + File.separator + d.getTime();
				List<String> arr = new ArrayList<String>(cssFiles.length);
				for (MultipartFile f : cssFiles) {
					if (f != null) {
						File tF2 = new File(tempPath, f.getOriginalFilename());
						FileUtils.mkdirs(tF2);
						f.transferTo(tF2);
						arr.add(tF2.getAbsolutePath());
					}
				}
				fileTempPath.put("css", arr);
			}
			if (jsFiles != null && jsFiles.length > 0) {
				for (MultipartFile f : jsFiles) {
					if (f != null) {
						String pName = f.getOriginalFilename();
						if (pName == null || pName.lastIndexOf(".js") <= 0) {
							result.setMessage("文件格式不正确!");
							return result;
						} else {
							version.setDefJs(
									StringUtils.isNotEmpty(version.getDefJs()) ? version.getDefJs() : "" + "," + pName);
						}
					}
				}
				String tempPath = root + File.separator + "tempJs" + File.separator + d.getTime();
				List<String> arr = new ArrayList<String>(jsFiles.length);
				for (MultipartFile f : jsFiles) {
					if (f != null) {
						File tF2 = new File(tempPath, f.getOriginalFilename());
						FileUtils.mkdirs(tF2);
						f.transferTo(tF2);
						arr.add(tF2.getAbsolutePath());
					}
				}
				fileTempPath.put("js", arr);
			}
			int num = mainService.addNewVersionByVersionViewModel(version);
			if (num > 0) {
				// 文件保存
				String versionId = BasicUtils.id2Version(num);
				String type = version.getType();// websites
				String name = version.getName();// segmentfault
				String keepPath = root + File.separator;
				List<String> pageArr = fileTempPath.get("page");
				if (pageArr != null && pageArr.size() > 0) {
					String pagePath = keepPath + "WEB-INF" + File.separator + type + File.separator + "answer"
							+ File.separator + name + File.separator + versionId;
					for (String p : pageArr) {
						if (p != null && p.length() > 0) {
							File f = new File(p);
							File f2 = new File(pagePath, f.getName());
							FileUtils.mkdirs(f2);
							FileCopyUtils.copy(f, f2);
							f.deleteOnExit();
							pageArr.remove(p);
						}
					}
				}
				List<String> cssArr = fileTempPath.get("css");
				if (cssArr != null && cssArr.size() > 0) {
					String cssPath = keepPath + "WEB-INF" + File.separator + "css" + File.separator + type
							+ File.separator + name + File.separator + versionId;
					for (String p : cssArr) {
						if (p != null && p.length() > 0) {
							File f = new File(p);
							File f2 = new File(cssPath, f.getName());
							FileUtils.mkdirs(f2);
							FileCopyUtils.copy(f, f2);
							f.deleteOnExit();
							cssArr.remove(p);
						}
					}
				}
				List<String> jsArr = fileTempPath.get("js");
				if (jsArr != null && jsArr.size() > 0) {
					String jsPath = keepPath + "WEB-INF" + File.separator + "js" + File.separator + type
							+ File.separator + name + File.separator + versionId;
					for (String p : jsArr) {
						if (p != null && p.length() > 0) {
							File f = new File(p);
							File f2 = new File(jsPath, f.getName());
							FileUtils.mkdirs(f2);
							FileCopyUtils.copy(f, f2);
							f.deleteOnExit();
							jsArr.remove(p);
						}
					}
				}
				result.setMessage("操作成功!");
			} else {
				result.setMessage("操作失败!");
			}
			// 数据清理
			List<String> pageArr = fileTempPath.get("page");
			if (pageArr != null && pageArr.size() > 0) {
				for (String str : pageArr) {
					if (StringUtils.isNotEmpty(str)) {
						File f = new File(str);
						if (f.exists()) {
							f.deleteOnExit();
						}
					}
				}
			}
			List<String> cssArr = fileTempPath.get("css");
			if (cssArr != null && cssArr.size() > 0) {
				for (String str : cssArr) {
					if (StringUtils.isNotEmpty(str)) {
						File f = new File(str);
						if (f.exists()) {
							f.deleteOnExit();
						}
					}
				}
			}
			List<String> jsArr = fileTempPath.get("js");
			if (jsArr != null && jsArr.size() > 0) {
				for (String str : jsArr) {
					if (StringUtils.isNotEmpty(str)) {
						File f = new File(str);
						if (f.exists()) {
							f.deleteOnExit();
						}
					}
				}
			}
		}
		return result;
	}

	@PostMapping("/setDefault")
	public Object setDefaultByVersionId(@RequestParam("id") String id, @RequestParam("name") String name)
			throws Exception {
		ResultModel result = new ResultModel();
		int num = mainService.setVersionDefaultUsing(id, name);
		if (num > 0) {
			result.setMessage("操作成功!");
		} else {
			result.setMessage("操作失败!");
		}
		return result;
	}
	
	@PostMapping("/setStop")
	public Object setStopByVersionId(@RequestParam("id") String id, @RequestParam("name") String name)
			throws Exception {
		ResultModel result = new ResultModel();
		int num = mainService.setVersionStopUsing(id, name);
		if (num > 0) {
			result.setMessage("操作成功!");
		} else {
			result.setMessage("操作失败!");
		}
		return result;
	}
	
	@PostMapping("/setUnStop")
	public Object setUnStopByVersionId(@RequestParam("id") String id, @RequestParam("name") String name)
			throws Exception {
		ResultModel result = new ResultModel();
		int num = mainService.setVersionUnStop(id, name);
		if (num > 0) {
			result.setMessage("操作成功!");
		} else {
			result.setMessage("操作失败!");
		}
		return result;
	}
}
