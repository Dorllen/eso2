package com.zhidian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhidian.model.sys.AdminBO;
import com.zhidian.service.AdminService;
import com.zhidian.views.RequestModel;

@RestController
@RequestMapping("/admin/up")
public class AdminMainController {

	@Autowired
	AdminService adminService;

	@PostMapping("/login")
	public Object login(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		RequestModel result = new RequestModel();
		AdminBO a = adminService.checkIsRightLogin(username, password);
		if (a != null) {
			request.getSession().setAttribute(AdminInfoController.ADMINCODE, a);
			result.setCode("200");
			result.setMessage("登陆成功!");
			result.setHref(request.getContextPath() + "/admin/");
		} else {
			System.out.println(request.getContextPath());
			result.setMessage("用户名或密码错误!");
		}
		return result;
	}

}
