package com.zhidian.model.sys;

import java.util.Date;

// 为result.html的数据view模型。但是将view,scores进行处理过的显示
public class ResultPageBO2 {
	private String id; // uuid
	private String title;// 标题
	private String contents;// 内容部分，问题部分
	private String url;// 链接. http://localhost:8080/esoso/article/(uuid)
	private String mark = "common";// 热度\高访问量\普通
	private String tags;// 标签
	private String from;// 来的网站，如segmentfault
	private String originUrl;// 源地址
	private Date createTime;
	private String view;
	private String scores; //评分
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getScores() {
		return scores;
	}
	public void setScores(String scores) {
		this.scores = scores;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
}
