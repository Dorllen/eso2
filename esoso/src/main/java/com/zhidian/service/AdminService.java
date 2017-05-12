package com.zhidian.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhidian.mapper.AdminMapper;
import com.zhidian.model.Admin;

@Service
public class AdminService {
	
	@Autowired
	AdminMapper adminMapper;
	
	public Admin checkIsRightLogin(String username,String password){
		if(StringUtils.isNotEmpty(username)&&StringUtils.isNotEmpty(password)){
			return adminMapper.queryAdminsForAdminService01SimpleAdmin(username,password);
		}else{
			return null;
		}
	}
	
}
