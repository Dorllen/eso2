
/**
* @Title: Website.java
* @Package com.zhidian.entities
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-21 上午1:46:57
* @version V1.0
*/
package com.zhidian.model;

import java.util.Date;

/**
 * @ClassName: Website
 * @Description: TODO(维护当前系统的所有加入的站点信息)
 * @author dongneng
 * @date 2017-3-21 上午1:46:57
 *
 */
public class Website {
	private int id;
	private String name;// segmentfault
	private String alias;// 别名。存储：segmentfault,segementfault网站
	private String shortAddr;// 短地址，segmentfault.com,
	private String fullAddr;// 全地址 http://segmentfault.com,http://www.segmentfault.com
	private String searchAddr;// 搜索地址 http://segmentfault.com?q=
	private String pagination;// 分页 &page={0}
	private Date createTime;
	private String createMan;
	private Date updateTime;
	private String updateMan;
	private String sign;// 描述
	private String resultProcessor;//文件名
	private String resultPipeline;// 文件名
	private String pageProcessor;
	private String pagePipeline;
	private String resultRObject;// 搜索结果接收对象
	private String pageRObject;// 数据模型接收对象。如：内容详情页的接收模型
	private boolean useSearch; // 使用后缀？ 默认false,代表去除搜索结果页捕获的链接后缀，如：?..&..。
	private String type;// 类型。AppEnumDefine.SiteService 。 如果获取Version的值，只需要知道type为engine就对应version's type是websites就可以了
	private String version;// 版本，每个结果详情页可能需要对应的版本来管理维护数据传输。对应的是Version的version
	private int using;// 除了0.0.0.0是最终默认版，如果有using代表当前页面是默认版。1代表在使用，0代表禁用（默认）
	private String unuseMan;// 禁用人
	private Date unuseTime;// 禁止时间
	private String defaultCss;// json对象。css的配置 WebsiteCssConfigModel 接收。意思是：默认爬虫爬取的css的json字符串
	private String defCss;// 系统样式的css文件。
	private String defConfig;// 默认配置 WebsiteConfigModel
	public String getResultProcessor() {
		return resultProcessor;
	}
	public void setResultProcessor(String resultProcessor) {
		this.resultProcessor = resultProcessor;
	}
	public String getResultPipeline() {
		return resultPipeline;
	}
	public void setResultPipeline(String resultPipeline) {
		this.resultPipeline = resultPipeline;
	}
	public String getPageProcessor() {
		return pageProcessor;
	}
	public void setPageProcessor(String pageProcessor) {
		this.pageProcessor = pageProcessor;
	}
	public String getPagePipeline() {
		return pagePipeline;
	}
	public void setPagePipeline(String pagePipeline) {
		this.pagePipeline = pagePipeline;
	}
	public String getResultRObject() {
		return resultRObject;
	}
	public void setResultRObject(String resultRObject) {
		this.resultRObject = resultRObject;
	}
	public String getPageRObject() {
		return pageRObject;
	}
	public void setPageRObject(String pageRObject) {
		this.pageRObject = pageRObject;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the shortAddr
	 */
	public String getShortAddr() {
		return shortAddr;
	}
	/**
	 * @param shortAddr the shortAddr to set
	 */
	public void setShortAddr(String shortAddr) {
		this.shortAddr = shortAddr;
	}
	/**
	 * @return the fullAddr
	 */
	public String getFullAddr() {
		return fullAddr;
	}
	/**
	 * @param fullAddr the fullAddr to set
	 */
	public void setFullAddr(String fullAddr) {
		this.fullAddr = fullAddr;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the createMan
	 */
	public String getCreateMan() {
		return createMan;
	}
	/**
	 * @param createMan the createMan to set
	 */
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the updateMan
	 */
	public String getUpdateMan() {
		return updateMan;
	}
	/**
	 * @param updateMan the updateMan to set
	 */
	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getSearchAddr() {
		return searchAddr;
	}
	public void setSearchAddr(String searchAddr) {
		this.searchAddr = searchAddr;
	}
	public String getPagination() {
		return pagination;
	}
	public void setPagination(String pagination) {
		this.pagination = pagination;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public boolean isUseSearch() {
		return useSearch;
	}
	public void setUseSearch(boolean useSearch) {
		this.useSearch = useSearch;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getUsing() {
		return using;
	}
	public void setUsing(int using) {
		this.using = using;
	}
	public String getUnuseMan() {
		return unuseMan;
	}
	public void setUnuseMan(String unuseMan) {
		this.unuseMan = unuseMan;
	}
	public Date getUnuseTime() {
		return unuseTime;
	}
	public void setUnuseTime(Date unuseTime) {
		this.unuseTime = unuseTime;
	}
	public String getDefaultCss() {
		return defaultCss;
	}
	public void setDefaultCss(String defaultCss) {
		this.defaultCss = defaultCss;
	}
	public String getDefCss() {
		return defCss;
	}
	public void setDefCss(String defCss) {
		this.defCss = defCss;
	}
	public String getDefConfig() {
		return defConfig;
	}
	public void setDefConfig(String defConfig) {
		this.defConfig = defConfig;
	}
}
