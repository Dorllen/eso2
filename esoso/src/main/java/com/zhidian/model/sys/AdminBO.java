package com.zhidian.model.sys;

import java.util.Date;

public class AdminBO {
	private int id;
	private String name;
	private String levelName;
	private String account;// 必须以邮箱格式xxx@eso.me
	private Date createTime;
	private int authority;// 权限类别。0代表超级管理员，1代表技术管理员，2代表普通管理员(默认)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
}
