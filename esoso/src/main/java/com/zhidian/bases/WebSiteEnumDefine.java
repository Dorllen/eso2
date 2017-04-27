package com.zhidian.bases;

import com.zhidian.bases.worms.pipeline.NullPipeline;
import com.zhidian.bases.worms.processor.SegmentFaultPageProcessor;
import com.zhidian.bases.worms.processor.SegmentFaultResultProcessor;

public enum WebSiteEnumDefine {
	github(null, null, null, null, null, null, null), segmentfault("http://segmentfault.com",
			"https://segmentfault.com/search?q=", SegmentFaultResultProcessor.class, NullPipeline.class,
			SegmentFaultPageProcessor.class, NullPipeline.class, "&page={0}");

	private String url;// 站点地址
	private String queryUrl;// 搜索地址
	private Class<?> resultProcessor;// 搜索结果页的处理器
	private Class<?> resultPipeline;
	private Class<?> pageProcessor;
	private Class<?> pagePipeline;
	private String pagination;

	private WebSiteEnumDefine(String url, String queryUrl, Class<?> resultProcessor, Class<?> resultPipeline,
			Class<?> pageProcessor, Class<?> pagePipeline, String pagination) {
		this.url = url;
		this.queryUrl = queryUrl;
		this.resultProcessor = resultProcessor;
		this.resultPipeline = resultPipeline;
		this.pageProcessor = pageProcessor;
		this.pagePipeline = pagePipeline;
		this.pagination = pagination;
	}

	public String getUrl() {
		return url;
	}

	public String getQueryUrl() {
		return queryUrl;
	}

	public Class<?> getResultProcessor() {
		return resultProcessor;
	}

	public Class<?> getResultPipeline() {
		return resultPipeline;
	}

	public Class<?> getPageProcessor() {
		return pageProcessor;
	}

	public Class<?> getPagePipeline() {
		return pagePipeline;
	}

	public String getPagination() {
		return pagination;
	}
}
