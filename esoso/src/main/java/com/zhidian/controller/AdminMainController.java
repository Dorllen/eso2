package com.zhidian.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhidian.model.Admin;
import com.zhidian.service.AdminService;
import com.zhidian.views.RequestModel;

@Controller
@RequestMapping("/admin/up")
public class AdminMainController {

	@Autowired
	AdminService adminService;

	@PostMapping("/login")
	public Object login(
			HttpSession session,
			@RequestParam("username") String username, @RequestParam("password") String password) {
		RequestModel result = new RequestModel();
		Admin a = adminService.checkIsRightLogin(username, password);
		if (a != null) {
			session.setAttribute(AdminInfoController.ADMINCODE, a);
			result.setMessage("登陆成功!");
			result.setHref("admin/");
		} else {
			result.setHref("admin/");
			result.setMessage("用户名或密码错误!");
		}
		return result;
	}
}
