/**
 * @Title: SimpleController.java
 * @Package com.zhidian.controllers
 * @Description: TODO(用一句话描述该文件做什么)
 * @author dongneng
 * @date 2017-3-18 下午9:58:21
 * @version V1.0
 */
package com.zhidian.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zhidian.model.User;
import com.zhidian.service.SimpleService;
import com.zhidian.views.UserVO;

/**
 * @ClassName: SimpleController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-18 下午9:58:21
 * 
 */
//@Controller
public class SimpleController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	SimpleService simpleService;

	@RequestMapping("/demo")
	// @ModelAttribute
	public String demo(Model model) {
		model.addAttribute("name", "dongneng");
		System.out.println("in demo!");
		System.out.println(System.nanoTime());
		return "demo";
	}

	@RequestMapping("/demo2")
	public String demo2() {
		return "demo2";
	}

	@GetMapping("/tt/")
	public String t() {
		System.out.println("T1");
		return null;
	}

	@PostMapping("/tt/")
	public String t2() {
		System.out.println("T2");
		return null;
	}

	@RequestMapping("/demo3")
	public String demo3() {
		return "jDemo";
	}

	@ResponseBody
	@RequestMapping("/user")
	public List<User> allUser() {
		log.info("info: select all user..");
		log.debug("debug: select all user..");
		log.error("error: select all user..");
		List<User> list = simpleService.getAllUser();
		return list;
	}

	@RequestMapping("/error2")
	@ResponseStatus(code = HttpStatus.CONFLICT, reason = "not not not")
	@ExceptionHandler(Exception.class)
	public void error(Exception ex, BindingResult bind) {
		System.out.println("----");
		System.out.println(ex);
		if (ex != null) {
			System.out.println(ex.getMessage());
		}
		System.out.println("in error2...");
		System.out.println(bind);
		if (bind != null) {
			System.out.println("----");
			System.out.println(bind.getTarget());
			System.out.println(bind.getObjectName());
			System.out.println(bind.getFieldError().getDefaultMessage());
			System.out.println(bind.getFieldError().getRejectedValue());
			System.out.println(bind.getFieldError().getCode());
			System.out.println("----");
		}
		// return "error";
	}

	@RequestMapping("/t")
	public ModelAndView modelView(ModelAndView model) {
		model.addObject(1);
		model.setViewName("demo");
		return model;
	}

	@RequestMapping("/u/{id}")
	public String Id(@PathVariable int id) {
		System.out.println(id);
		return "id:" + id;
	}

	@RequestMapping("/uname/{name:[a-zA-Z]+}")
	public String Name(@PathVariable String name) {
		System.out.println(name);
		return "name:" + name;
	}

	@RequestMapping("/r1")
	public String re1() {
		System.out.println("r1");
		return "redirect:/demo";
	}

	@RequestMapping("/r2")
	public String re2() {
		System.out.println("r2");
		return "redirect:demo";
	}

	@RequestMapping("/f1")
	public String fo1() {
		System.out.println("fo1");
		return "forward:/demo";
	}

	@RequestMapping("/f2")
	public String fo2() {
		System.out.println("fo2");
		return "forward:demo";
	}

	@RequestMapping("/f/f1")
	public String f11() {
		System.out.println("f11");
		return "forward:/demo";
	}

	@RequestMapping("/f/f2")
	public String f12() {
		System.out.println("f12");
		return "forward:demo";
	}

	@RequestMapping("/f/demo")
	@ResponseBody
	public String fdemo() {
		System.out.println("fdemo");
		return "f/demo";
	}

	// 表单测试

	@RequestMapping("/form")
	public String form() {
		return "form";
	}

	@RequestMapping("/form/set")
	public String formSet(@Valid @ModelAttribute UserVO user, BindingResult bind) {
		if (bind.hasErrors()) {
			System.out.println(user);
			return "form";
		}
		System.out.println(user != null ? user.toString() : "user is null?!!!");
		return "设置成功!";
	}

	@RequestMapping("/model")
	public ModelAndView dt(ModelAndView model) {
		model.addObject("name", "123");
		model.setViewName("demo");
		return model;
	}

	@RequestMapping("/model2")
	public String dt2(Model model) {
		model.addAttribute("name", "456");
		return "demo";
	}

	@RequestMapping("/model3")
	public String dt3(Model model) {
		model.addAttribute("name", "456");
		return "forward:/demo";
	}

	public static final String ROOT = "/data/avatar/";

	private final ResourceLoader resourceLoader;

	@Autowired
	public SimpleController(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@GetMapping(value = "/avatar/i/{filename:.+}")
	@ResponseBody
	public ResponseEntity<?> getFile(@PathVariable String filename,
			HttpServletRequest request) {
		try {
//			String temp = request.getSession().getServletContext().getRealPath(Paths.get(ROOT, filename).toString());
//			System.out.println(temp);
//			return ResponseEntity.ok(resourceLoader.getResource(temp));
//			return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return null;
	}

	
	
	
	
	@RequestMapping("/img")
	public String img(@ModelAttribute("name") String name) {
		return "img";
	}

	@PostMapping("/img/set")
	@ResponseBody
	public String imgUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request)
			throws IOException {
		// 先遍历文章
//		String rootPath = request.getSession().getServletContext().getRealPath("/data/avatar");
//		File f = new File(rootPath);
//		System.out.println(f);
//		if(f.isDirectory()){
//			System.out.println("dir");
//			for(File ff : f.listFiles()){
//				System.out.println(ff.getName());
//			}
//		}
//		if(f.isFile()){
//			System.out.println("is file");
//			System.out.println(f.getName());
//		}
//		
//		
//		System.out.println(rootPath);
//		file.transferTo(new File(rootPath+"1.jpg"));
//		Files.copy(file.getInputStream(), Paths.get(ROOT, "1.jpg"));
		return "ok!";
	}
}
