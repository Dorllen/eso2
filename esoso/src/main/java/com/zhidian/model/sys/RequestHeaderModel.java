package com.zhidian.model.sys;

public class RequestHeaderModel {
	public final static String CookieType = "cookie";
	private String name;
	private String value;
	private String type;// header或者cookie
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
