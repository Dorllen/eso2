package com.zhidian.model.sys;

import java.util.Date;

// 用于爬取搜索结果页数据
public class PullResultBO {
	private String uuid; // uuid处理，需判断是否取出url?后数据
	private String url; // segnemtfault.com/a/11231654
	private String name;// 网站名。segmentfault
	private String title;// 文章名
	private String content;
	private Date date;// 爬虫时间
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
