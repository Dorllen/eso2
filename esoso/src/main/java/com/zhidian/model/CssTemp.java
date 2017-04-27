/**
 * @Title: Css.java
 * @Package com.zhidian.entities
 * @Description: TODO(用一句话描述该文件做什么)
 * @author dongneng
 * @date 2017-3-21 上午1:13:08
 * @version V1.0
 */
package com.zhidian.model;

import java.util.Date;

/**
 * @ClassName: Css
 * @Description: TODO(下载的Css临时存储的表)
 * @author dongneng
 * @date 2017-3-21 上午1:13:08
 * 
 */
public class CssTemp {
	private int id;
	private String uuid;// 内容的uuid，非url的uuid.
	private String url;// 下载css的全路径 http://segmentfault.con/static/css/qa.css
	private String name;// css的名称，包括后缀，如qa.css
	private String website;// segmentfault 站点名
	private String version;// 下载所存储的版本地址
	private String path;// 存放路徑，临时路径 css/websites/segmentfault/0.0.0.1/qa.css.temp
	private Date createTime;// 创建时间
	private String  type;// 下载的类型，如后台管理员单个触发、定时计划触发、在线爬虫触发、管理員覆盖
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
