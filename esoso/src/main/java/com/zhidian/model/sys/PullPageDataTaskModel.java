package com.zhidian.model.sys;

public class PullPageDataTaskModel {
	private String website;// 站点名称
	private String pageProcess; // 处理对象
	private String pipeLine;// 接收对象
	private String from;// http://segmentfault.com/a/123456
	private PullPageObjectModel pom;// 页面数据模型 PullPageObjectModel

	public PullPageDataTaskModel() {
	}

	public PullPageDataTaskModel(String website, String pageProcess, String pipeLine) {
		this.website = website;
		this.pageProcess = pageProcess;
		this.pipeLine = pipeLine;
	}

	public PullPageDataTaskModel(String website, String pageProcess, String pipeLine, String from) {
		this(website, pageProcess, pipeLine);
		this.from = from;
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

	public Object getPom() {
		return pom;
	}

	public void setPom(PullPageObjectModel pom) {
		this.pom = pom;
	}
}
