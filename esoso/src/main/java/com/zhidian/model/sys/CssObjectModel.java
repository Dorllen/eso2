package com.zhidian.model.sys;

import java.util.Date;

// 下载期间css的存储.其实就是放csstemps表的数据
public class CssObjectModel {
	private String uuid;
	private String url;
	private String name;// 文件名
	private String search;// 
	private Date date;
	private boolean download;
	private String downloadPath;// 下载保存地址, 本地路径
	private String version;// 版本信息 
	private int checked;// 如果遇到版本冲突，需要管理员检查，默认0,代表需要管理员，-1代表旧版本相同，1代表管理员检查，2代表检查后结果
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
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
