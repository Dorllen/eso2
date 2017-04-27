package com.zhidian.views;

import java.util.List;

public class PullArticlePageVO {
	// 还需要定制化
	private String uuid;
	private String title;
	private Object contents;
	private List<String> cssPaths;
	private List<String> jsPaths;
	private String bottomInfo;// 底部信息
	private String url;// 跳转页面地址
	private String name;// segmentfault
	private String time;// 时间2014-05-32
	private String from;// name + 网站
	
	private int loginStatus;// 做个登陆识别，页面通过验证input:hidden的这个值
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Object getContents() {
		return contents;
	}
	public void setContents(Object contents) {
		this.contents = contents;
	}
	public List<String> getCssPaths() {
		return cssPaths;
	}
	public void setCssPaths(List<String> cssPaths) {
		this.cssPaths = cssPaths;
	}
	public List<String> getJsPaths() {
		return jsPaths;
	}
	public void setJsPaths(List<String> jsPaths) {
		this.jsPaths = jsPaths;
	}
	public String getBottomInfo() {
		return bottomInfo;
	}
	public void setBottomInfo(String bottomInfo) {
		this.bottomInfo = bottomInfo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	
}
