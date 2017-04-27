package com.zhidian.model.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 下载的网站页面
public class PullPageObjectModel {
	private String uuid;// uuid
	private String url;// 链接
	private String name;// 网站名。segmentfault
	private Date date;// 下载时间
	private String title;
	private String resultContent;
	private boolean changed;// 是否下载
	private Object model;// 页面数据模型。
	private String downloadPath;// 目录地址 通过项目根目录地址，拼接获得当前网站的css文件根地址
	private List<CssInfoModel> cssModel;// 当前model的css。注意cssModel的version不能为空。除非是第一次
	private List<CssObjectModel> cssPaths;
	private WebsiteConfigModel websiteConfig;// cookie等爬虫参数设置。
	private List<PullDataWatchObject> errorWatcher;
	private String sign;// 描述
	
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

	public List<CssObjectModel> getCssPaths() {
		return cssPaths;
	}

	public void setCssPaths(List<CssObjectModel> cssPaths) {
		this.cssPaths = cssPaths;
	}

	public void addCssPaths(CssObjectModel cssObj) {
		if (cssPaths == null) {
			this.cssPaths = new ArrayList<CssObjectModel>();
		}
		this.cssPaths.add(cssObj);
	}

	public void addCssPaths(List<CssObjectModel> cssPaths) {
		if (cssPaths == null) {
			this.cssPaths = cssPaths;
		} else {
			this.cssPaths.addAll(cssPaths);
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public List<CssInfoModel> getCssModel() {
		return cssModel;
	}

	public void setCssModel(List<CssInfoModel> cssModel) {
		this.cssModel = cssModel;
	}

	public WebsiteConfigModel getWebsiteConfig() {
		return websiteConfig;
	}

	public void setWebsiteConfig(WebsiteConfigModel websiteConfig) {
		this.websiteConfig = websiteConfig;
	}

	public String getResultContent() {
		return resultContent;
	}

	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<PullDataWatchObject> getErrorWatcher() {
		return errorWatcher;
	}

	public void setErrorWatcher(List<PullDataWatchObject> errorWatcher) {
		this.errorWatcher = errorWatcher;
	}
	
	public void addErrorWatcher(PullDataWatchObject watch){
		if(this.errorWatcher==null){
			this.errorWatcher = new ArrayList<PullDataWatchObject>();
		}
		this.errorWatcher.add(watch);
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
