package com.zhidian.model.sys;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.zhidian.model.Version;

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
//	private String version;// 版本，每个结果详情页可能需要对应的版本来管理维护数据传输。
	private Version version;
	private String defaultPageCss;//
	private String defPageCss;// 系统样式的css文件。
	private String defPageConfig;// 默认詳情頁配置 WebsiteConfigModel
	private String defResultConfig;// 默認結果頁爬取規則 WebsiteConfigModel
	private String defRequestHeader;// 定义请求头 RequestHeaderModel

	private List<WebsiteCssConfigModel> pageCssLists;
	private List<RequestHeaderModel> requestHeaders;
	private WebsiteConfigModel pageConfig;
	private WebsiteConfigModel resultConfig;

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

	public List<WebsiteCssConfigModel> getPageCssLists() {
		if (pageCssLists == null) {
			if (defaultPageCss != null && defaultPageCss.length() > 0) {
				try {
					this.pageCssLists = JSON.parseArray(defaultPageCss, WebsiteCssConfigModel.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return pageCssLists;
	}

	public WebsiteConfigModel getPageConfig() {
		if (pageConfig == null) {
			if (defPageConfig != null && defPageConfig.length() > 0) {
				try {
					this.pageConfig = JSON.parseObject(defPageConfig, WebsiteConfigModel.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return pageConfig;
	}

	public WebsiteConfigModel getResultConfig() {
		if (resultConfig == null) {
			if (defResultConfig != null && defResultConfig.length() > 0) {
				try {
					this.resultConfig = JSON.parseObject(defResultConfig, WebsiteConfigModel.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return resultConfig;
	}

	public List<RequestHeaderModel> getRequestHeaders() {
		if (requestHeaders == null) {
			if (defRequestHeader != null && defRequestHeader.length() > 0) {
				this.requestHeaders = JSON.parseArray(defRequestHeader, RequestHeaderModel.class);
			}
		}
		return requestHeaders;
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

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public String getDefPageCss() {
		return defPageCss;
	}

	public void setDefPageCss(String defPageCss) {
		this.defPageCss = defPageCss;
	}

}
