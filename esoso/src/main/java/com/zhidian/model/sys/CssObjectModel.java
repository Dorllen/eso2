package com.zhidian.model.sys;

import java.util.Date;

// 下载期间css的存储.其实就是放csstemps表的数据
public class CssObjectModel {
	private String uuid;// 内容的uuid
	private String url;// 下载的css的url
	private String name;// 文件名
	private String search;// 后缀的内容如qa.css?v=1.3.3  search是?v1.3.3
	private Date date;// 下载时间
	private boolean download;// 标识是否下载了
	private String cssPath;// 相对路径！ css/websites/0.0.0.0/xxx.css(包括文件名，不加temp后缀)
	private String downloadPath;// 下载保存地址, 本地路径.。是通过System.getProperty("") 获得项目根地址，再而获得文件的路径(包括文件名，加temp后缀)
	private String version;// 版本信息 
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
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isDownload() {
		return download;
	}
	public void setDownload(boolean download) {
		this.download = download;
	}
	public String getDownloadPath() {
		return downloadPath;
	}
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "\"CssObjectModel\":{\"uuid\":\"" + uuid + "\", \"url\":\"" + url + "\", \"search\":\"" + search
				+ "\", \"date\":\"" + date + "\", \"download\":\"" + download + "\", \"downloadPath\":\"" + downloadPath
				+ "\", \"version\":\"" + version + "\"}";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCssPath() {
		return cssPath;
	}
	public void setCssPath(String cssPath) {
		this.cssPath = cssPath;
	}
	
}
