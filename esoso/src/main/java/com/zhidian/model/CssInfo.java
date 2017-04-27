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
 * @Description: TODO(css的数据所存储的表。如果业务拓展，尽量与搜索引擎的爬取页面的css表分开。沒有其他需求，就用來存储信息的)
 * @author dongneng
 * @date 2017-3-21 上午1:13:08
 * 
 */
public class CssInfo {
	private int id;
	private String uuid;// 内容的uuid，非url的uuid
	private String url;// 下载css的全路径 http://segmentfault.con/static/css/qa.css
	private String name;// css的名称，包括后缀，如qa.css
	private String website;// segmentfault 站点名
	private String version;// 所存储的版本地址。与Version的版本无关
	private String path;// 存放路徑，实际路径 css/websites/segmentfault/0.0.0.1/
	private int using;// 是否在用。这个无关页面显示，相关在于爬取页面的选择
	private Date unuseTime;// 不使用时间
	private Date createTime;// 创建时间
	private String unuseMan;// 禁用人
	private String sign;// 描述
	private String valMan;// 验证人
	private Date valTime;// 验证时间
	private String type;// 下载的类型，如后台管理员单个触发、定时计划触发、在线爬虫触发。
	private int status;// 文件的状态。0代表正常,1代表管理员未处理,2代表文件当前文件夹有存在，是否覆盖。这里的意思，遇到下载模板如果处理不了，就先确定css再确定文件如数据库
	private boolean useSearch;// 是否使用css/qa.css?v=1232后缀，默认false，拒绝
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the using
	 */
	public int getUsing() {
		return using;
	}

	/**
	 * @param using
	 *            the using to set
	 */
	public void setUsing(int using) {
		this.using = using;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUnuseTime() {
		return unuseTime;
	}

	public void setUnuseTime(Date unuseTime) {
		this.unuseTime = unuseTime;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUnuseMan() {
		return unuseMan;
	}

	public void setUnuseMan(String unuseMan) {
		this.unuseMan = unuseMan;
	}

	public String getValMan() {
		return valMan;
	}

	public void setValMan(String valMan) {
		this.valMan = valMan;
	}

	public Date getValTime() {
		return valTime;
	}

	public void setValTime(Date valTime) {
		this.valTime = valTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isUseSearch() {
		return useSearch;
	}

	public void setUseSearch(boolean useSearch) {
		this.useSearch = useSearch;
	}
}
