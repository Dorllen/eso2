package com.zhidian.views;

public class FormBarDTO {
	private String from;// segmentfault|github
	private String defaults;// segmentfault|github
	private String defaultWebsites;// segmentfault|github|cnblog|python
	private String type;// answer
	private String placeholder;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	public String getDefaults() {
		return defaults;
	}
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}
	public String getDefaultWebsites() {
		return defaultWebsites;
	}
	public void setDefaultWebsites(String defaultWebsites) {
		this.defaultWebsites = defaultWebsites;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
