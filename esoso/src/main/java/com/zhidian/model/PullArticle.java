
/**
* @Title: Ariticle.java
* @Package com.zhidian.mappers
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-20 下午9:54:03
* @version V1.0
*/
package com.zhidian.model;

import java.util.Date;

/**
 * @ClassName: Ariticle
 * @Description: TODO(爬取的内容文章。不允许覆盖，因为要做时间戳显示旧版本)
 * @author dongneng
 * @date 2017-3-20 下午9:54:03
 *
 */
public class PullArticle {

	private int id;
	private String uuid;
	private String tags;// 标签
	private String title;// 标题
	private String contents;// 内容.json数据字符串
	private String resultContent;// 问题部分内容。搜索显示的结果页内容部分，需要自行截取
	private String pagePath;// 没有","划分，只有一个
	private String jsPath;// 0.0.0.1
	private String cssPath;// css/segment/0.0.0.1/xxx.css,css/segment/0.0.0.1/xxx.css
	private String name;// 站点名 segmentfault
	private String sign;// 描述
	private Date startTime;// 开始时间
	private Date updateTime;// 更新时间
	private String updateMan;// 更新人
	private Date updateManTime;// 更新人时间
	private int using;// 是否使用
	private int views;// 访问量
	private double scores;// 热点
	private int collets;// 收集量
//	private String version;// 内容版本，如果内容版本改变了。内容的默认css也是改变的。关联的是version表的
	private int websiteId;
	private Date unuseTime;
	private String unuseMan;// 禁用人
	private String url;// 源地址
	private String mark;// 标记，可以管理员修改。hot,time,cviews。也可以自动。管理员修改不会触发updateManTime
	private String cuuid;// contents的uuid值
	private int status;// 爬虫置入的数据，如1,待处理的！，0代表已处理，可以直接访问 -1代表已删除
	private String type;// 爬虫的类型。answer|blog
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
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * @param contents
	 *            the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * @return the cssPath
	 */
	public String getCssPath() {
		return cssPath;
	}

	/**
	 * @param cssPath
	 *            the cssPath to set
	 */
	public void setCssPath(String cssPath) {
		this.cssPath = cssPath;
	}

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign
	 *            the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
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
	 * @param updateMan
	 *            the updateMan to set
	 */
	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}

	/**
	 * @return the updateManTime
	 */
	public Date getUpdateManTime() {
		return updateManTime;
	}

	/**
	 * @param updateManTime
	 *            the updateManTime to set
	 */
	public void setUpdateManTime(Date updateManTime) {
		this.updateManTime = updateManTime;
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
	 * @return the views
	 */
	public int getViews() {
		return views;
	}

	/**
	 * @param views
	 *            the views to set
	 */
	public void setViews(int views) {
		this.views = views;
	}

	/**
	 * @return the scores
	 */
	public double getScores() {
		return scores;
	}

	/**
	 * @param scores
	 *            the scores to set
	 */
	public void setScores(double scores) {
		this.scores = scores;
	}

	/**
	 * @return the collets
	 */
	public int getCollets() {
		return collets;
	}

	/**
	 * @param collets
	 *            the collets to set
	 */
	public void setCollets(int collets) {
		this.collets = collets;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResultContent() {
		return resultContent;
	}

	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}

	public Date getUnuseTime() {
		return unuseTime;
	}

	public void setUnuseTime(Date unuseTime) {
		this.unuseTime = unuseTime;
	}

	public String getUnuseMan() {
		return unuseMan;
	}

	public void setUnuseMan(String unuseMan) {
		this.unuseMan = unuseMan;
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

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public String getJsPath() {
		return jsPath;
	}

	public void setJsPath(String jsPath) {
		this.jsPath = jsPath;
	}

	public String getCuuid() {
		return cuuid;
	}

	public void setCuuid(String cuuid) {
		this.cuuid = cuuid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
