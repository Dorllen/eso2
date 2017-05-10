package com.zhidian.views;

import org.hibernate.validator.constraints.NotEmpty;

/**
* @ClassName: PullArticleUpdateModel
* @Description: TODO(内容库内容修改接收模型)
* @author dongneng
* @date 2017年5月11日 上午5:44:34
*
*/
public class PullArticleUpdateModel {
	private int id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String websiteId;//
	@NotEmpty
	private String title;
	@NotEmpty
	private String resultContent;
	@NotEmpty
	private String url;
	private String tags;
	private String mark;
	private String pagePath;
	private String cssPath;
	private String jsPath;
	private String sign;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(String websiteId) {
		this.websiteId = websiteId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getResultContent() {
		return resultContent;
	}
	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
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
	public String getCssPath() {
		return cssPath;
	}
	public void setCssPath(String cssPath) {
		this.cssPath = cssPath;
	}
	public String getJsPath() {
		return jsPath;
	}
	public void setJsPath(String jsPath) {
		this.jsPath = jsPath;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
