package com.zhidian.model.sys;

public class PullResultDataTaskModel {
	private String website;// 站点名称
	private String pageProcess; // 处理对象
	private String pipeLine;// 接收对象
	private String from;// http://segmentfault.com/search?q=xxx
	private String pagination;// &page=2
	private int size;// 获取长度
	private Object pom;// 页面数据模型 PullResultPageModel

	public PullResultDataTaskModel() {
	}

	public PullResultDataTaskModel(String website, String pageProcess, String pipeLine) {
		this.website = website;
		this.pageProcess = pageProcess;
		this.pipeLine = pipeLine;
	}

	public PullResultDataTaskModel(String website, String pageProcess, String pipeLine, String from) {
		this(website, pageProcess, pipeLine);
		this.from = from;
	}

	public PullResultDataTaskModel(String website, String pageProcess, String pipeLine, String from, String pagination) {
		this(website, pageProcess, pipeLine, from);
		this.pagination = pagination;
	}

	public PullResultDataTaskModel(String website, String pageProcess, String pipeLine, String from, String pagination,
			int size) {
		this(website, pageProcess, pipeLine, from, pagination);
		this.size = size;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPageProcess() {
		return pageProcess;
	}

	public void setPageProcess(String pageProcess) {
		this.pageProcess = pageProcess;
	}

	public String getPipeLine() {
		return pipeLine;
	}

	public void setPipeLine(String pipeLine) {
		this.pipeLine = pipeLine;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Object getPom() {
		return pom;
	}

	public void setPom(Object pom) {
		this.pom = pom;
	}
}
