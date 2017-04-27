package com.zhidian.model.sys;

public class WebsiteCssConfigModel {
	private String url;// 下载css的全路径 http://segmentfault.con/static/css/qa.css
	private String name;// css的名称，包括后缀，如qa.css
	private String website;// segmentfault 站点名
	private String version;// 所对应的css的version版本地址。当前的css版本
	private String sign;// 描述
	private boolean useSearch;// 是否使用css/qa.css?v=1232后缀，默认false，拒绝
	private String uuid;//默认版本的uuid
	
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
