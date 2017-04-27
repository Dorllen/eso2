package com.zhidian.model.sys;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class WebsiteBO {
	private int id;
	private String name;// segmentfault
	private String searchAddr;// 搜索地址 http://segmentfault.com?q=
	private String pagination;// 分页 &page={0}
	private String sign;// 描述
	private String resultProcessor;// 文件名
	private String resultPipeline;// 文件名
	private String pageProcessor;
	private String pagePipeline;
	private String resultRObject;// 搜索结果接收对象
	private String pageRObject;// 数据模型接收对象。如：内容详情页的接收模型
	private boolean useSearch; // 使用后缀？ 默认false,代表去除搜索结果页捕获的链接后缀，如：?..&..。
	private String type;// 类型。AppEnumDefine.SiteService
	private String version;// 版本，每个结果详情页可能需要对应的版本来管理维护数据传输。
	private String defaultCss;//
	private List<WebsiteCssConfigModel> cssLists;
	private String defConfig;
	private WebsiteConfigModel config;

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

	public boolean isUseSearch() {
		return useSearch;
	}

	public void setUseSearch(boolean useSearch) {
		this.useSearch = useSearch;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<WebsiteCssConfigModel> getCssLists() {
		if (cssLists == null) {
			if (defaultCss != null && defaultCss.length() > 0) {
				try {
					this.cssLists = JSON.parseArray(defaultCss, WebsiteCssConfigModel.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return cssLists;
	}

	public WebsiteConfigModel getConfig() {
		if (config == null) {
			if (defConfig != null && defConfig.length() > 0) {
				try {
					this.config = JSON.parseObject(defConfig, WebsiteConfigModel.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return config;
	}

	public String getDefaultCss() {
		return defaultCss;
	}

	public void setDefaultCss(String defaultCss) {
		this.defaultCss = defaultCss;
	}

	public String getDefConfig() {
		return defConfig;
	}

	public void setDefConfig(String defConfig) {
		this.defConfig = defConfig;
	}

}
