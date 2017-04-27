package com.zhidian.model.sys;

import java.util.ArrayList;
import java.util.List;

// 用于爬取搜索结果页的配置设置
public class PullResultPageModel {
	private String name;// 站点名
	private String url;// 站点爬虫链接
	private String pagination;// 爬虫分页
	private boolean useSearch;//使用后缀？ 默认false,代表去除搜索结果页捕获的链接后缀，如：?..&..。
	private int size;// 爬虫数目
	private int page;// 页号
	private List<PullResultBO> results;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public boolean isUseSearch() {
		return useSearch;
	}

	public void setUseSearch(boolean useSearch) {
		this.useSearch = useSearch;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<PullResultBO> getResults() {
		if (this.results == null) {
			this.results = new ArrayList<PullResultBO>();
		}
		return results;
	}

	public void setResults(List<PullResultBO> results) {
		this.results = results;
	}

	public void addResults(PullResultBO result) {
		if (this.results == null) {
			this.results = new ArrayList<PullResultBO>();
		}
		this.results.add(result);
	}

	public void addResults(List<PullResultBO> results) {
		if (this.results == null) {
			this.results = results;
		}
		this.results.addAll(results);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
