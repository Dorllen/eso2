package com.zhidian.views;

import org.hibernate.validator.constraints.NotEmpty;

public class WebsiteMainAddModel {

	@NotEmpty
	private String name;
	@NotEmpty
	private String type;

	private String versionId;
	private String alias;// 别名。存储：segmentfault,segementfault网站
	private String shortAddr;// 短地址，segmentfault.com,
	private String fullAddr;// 全地址
							// http://segmentfault.com,http://www.segmentfault.com
	@NotEmpty
	private String searchAddr;// 搜索地址 http://segmentfault.com?q=
	private String pagination;// 分页 &page={0}
	private String sign;// 描述
	private String resultProcessor;// 文件名
	private String resultPipeline;// 文件名.结果页是使用的是：ResultSimplePipeline
	private String resultRObject;// 搜索结果接收对象
	private String pageProcessor;
	private String pagePipeline;// 详情页使用的是：BasePagePipeline
	private String pageRObject;// 数据模型接收对象。如：内容详情页的接收模型
	
	private boolean useSearch; // 使用后缀？
	private String defaultPageCss;// json对象。css的配置 WebsiteCssConfigModel
								// 接收。意思是：默认爬虫爬取的css的json字符串。即要爬取的css文件
	private String defPageCss;// 系统样式的css文件。
	private String defPageConfig;// 默认詳情頁配置 WebsiteConfigModel
	private String defResultConfig;// 默認結果頁爬取規則 WebsiteConfigModel
	private String defRequestHeader;//定义请求头 RequestHeaderModel
	
	private boolean check1;// 是否设置为默认
	private boolean check2;// 是否使用默认版本的css
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getShortAddr() {
		return shortAddr;
	}
	public void setShortAddr(String shortAddr) {
		this.shortAddr = shortAddr;
	}
	public String getFullAddr() {
		return fullAddr;
	}
	public void setFullAddr(String fullAddr) {
		this.fullAddr = fullAddr;
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
	public String getResultRObject() {
		return resultRObject;
	}
	public void setResultRObject(String resultRObject) {
		this.resultRObject = resultRObject;
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
	public String getDefaultPageCss() {
		return defaultPageCss;
	}
	public void setDefaultPageCss(String defaultPageCss) {
		this.defaultPageCss = defaultPageCss;
	}
	public String getDefPageCss() {
		return defPageCss;
	}
	public void setDefPageCss(String defPageCss) {
		this.defPageCss = defPageCss;
	}
	public String getDefPageConfig() {
		return defPageConfig;
	}
	public void setDefPageConfig(String defPageConfig) {
		this.defPageConfig = defPageConfig;
	}
	public String getDefResultConfig() {
		return defResultConfig;
	}
	public void setDefResultConfig(String defResultConfig) {
		this.defResultConfig = defResultConfig;
	}
	public String getDefRequestHeader() {
		return defRequestHeader;
	}
	public void setDefRequestHeader(String defRequestHeader) {
		this.defRequestHeader = defRequestHeader;
	}
	public boolean isCheck1() {
		return check1;
	}
	public void setCheck1(boolean check1) {
		this.check1 = check1;
	}
	public boolean isCheck2() {
		return check2;
	}
	public void setCheck2(boolean check2) {
		this.check2 = check2;
	}
}
