package com.zhidian.views;

public class ServiceSettingsDTO {
	private int id;
	private String name;
	private boolean using = true;// 默认使用
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
	public boolean isUsing() {
		return using;
	}
	public void setUsing(boolean using) {
		this.using = using;
	}
}
