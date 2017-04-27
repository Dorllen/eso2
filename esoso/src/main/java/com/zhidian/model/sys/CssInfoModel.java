package com.zhidian.model.sys;

// 主要用于下载的时候确定当前网站所需要的css信息
public class CssInfoModel {
	private String uuid;// 内容的uuid
	private String name;// 文件名
	private String webSite;// 网站名 segmentfault
	private String version;// 自己的css版本，与Version，与pullarticle也无关系
	private String url;// 链接名。https://static.segmentfault.com/v-58f1f176/qa/css/qa.css
	private String cssPath;// 相对于项目的路径 css/index/1.1
	private String abCssPath;// 绝对路径 d://....WEB-INF/css/segmentfault
	private boolean useSearch;// 是否使用css/qa.css?v=1232后缀，默认false，拒绝
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCssPath() {
		return cssPath;
	}
	public void setCssPath(String cssPath) {
		this.cssPath = cssPath;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public boolean getUseSearch() {
		return useSearch;
	}
	public void setUseSearch(boolean useSearch) {
		this.useSearch = useSearch;
	}
	public String getAbCssPath() {
		return abCssPath;
	}
	public void setAbCssPath(String abCssPath) {
		this.abCssPath = abCssPath;
	}
}
