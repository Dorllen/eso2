package com.zhidian.model.sys;

import java.util.List;

public class ConfigVersionBO {
	private String name;
	private String value;
	private String website;
	private List<String> websites;
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
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public List<String> getWebsites() {
		return websites;
	}
	public void setWebsites(List<String> websites) {
		this.websites = websites;
	}
}
