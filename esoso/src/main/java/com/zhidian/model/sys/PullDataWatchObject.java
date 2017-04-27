package com.zhidian.model.sys;

import java.util.Date;

public class PullDataWatchObject {
	private String website;
	private String name;// 属性名
	private String xpathContent;// 解析内容
	private Date times;// 触发时间
	private String url;// 在做那个url的时候触发
	private String sign;// 来源描述
	private String type;// 爬虫类型。如：待改进，待处理Css

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXpathContent() {
		return xpathContent;
	}

	public void setXpathContent(String xpathContent) {
		this.xpathContent = xpathContent;
	}

	public Date getTimes() {
		return times;
	}

	public void setTimes(Date times) {
		this.times = times;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
