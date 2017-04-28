/**
 * @Title: c.java
 * @Package com.zhidian
 * @Description: TODO(用一句话描述该文件做什么)
 * @author dongneng
 * @date 2017-3-20 下午3:33:36
 * @version V1.0
 */
package com.zhidian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//@Controller
public class FileUploadController {

	// private static final Logger log = LoggerFactory
	// .getLogger(FileUploadController.class);
	public static final String ROOT = "data/avatar";
	private final ResourceLoader resourceLoader;

	@Autowired
	public FileUploadController(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	// @RequestMapping(method = RequestMethod.GET, value = "/")
	// public String provideUploadInfo(Model model) throws IOException {
	// model.addAttribute("files", Files.walkFileTree(Paths.get(ROOT))
	// .filter(path -> !path.equals(Paths.get(ROOT)))
	// .map(path -> Paths.get(ROOT).relativize(path))
	// .map(path -> linkTo(methodOn(FileUploadController.class)
	// .getFile(path.toString()).withRel(path.toString()))
	// .collect(Collections.toList()));
	// return "uploadForm";
	// }

	// 显示图片的方法关键 匹配路径像 localhost:8080/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png
	@RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
	@ResponseBody
	public ResponseEntity<?> getFile(@PathVariable String filename) {
//		try {
//			return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
//		} catch (Exception e) {
//			return ResponseEntity.notFound().build();
//		}
		return null;
	}

	// 上传的方法
	@RequestMapping(method = RequestMethod.POST, value = "/up")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		System.out.println(request.getParameter("member"));
		if (!file.isEmpty()) {
//			try {
//				Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		return "index";
	}
}