package com.zhidian.views;

import java.util.Date;
import java.util.List;

import com.zhidian.model.sys.CommendBO;
import com.zhidian.model.sys.ResultPageBO2;
import com.zhidian.model.sys.VersionBO;

// 为result.html的数据view模型.json传输不能使用该模型。因为传递了时间,
public class ResultPageVO {
	private String id; // uuid，该搜索结果的uuid
	private Date creTime;// 创建时间
	private String sort = "hot";// 热度hot、时间time、点击量cviews
	private String upOrdown = "up";// up down
	private String origin;// 来源类型
	private List<String> from;// 页面来源集
	private List<ResultPageBO2> results;// 页面结果集
	private List<String> tags;// 页面标签集
	// private List<TagResultBO> tags;// 页面标签集
	private List<CommendBO> commends;// 推荐结果
	private VersionBO version;
	private String from_;// segmentfault|github
	private String defaults;// segmentfault|github
	private String defaultWebsites;// segmentfault|github|cnblog|python
	private String design;
	private String type;// answer
	private String key;
	private String placeholder;
	private int page;// 当前页号
	private int lastPage;// 尾页，即下一页
	private String url;// 访问的链接
//	private String title; 暂用key替代

	// private // 用户信息
	// private // 搜索页面的值注入

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getUpOrdown() {
		return upOrdown;
	}

	public void setUpOrdown(String upOrdown) {
		this.upOrdown = upOrdown;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public List<String> getFrom() {
		return from;
	}

	public void setFrom(List<String> from) {
		this.from = from;
	}

	public List<ResultPageBO2> getResults() {
		return results;
	}

	public void setResults(List<ResultPageBO2> results) {
		this.results = results;
	}

	public List<CommendBO> getCommends() {
		return commends;
	}

	public void setCommends(List<CommendBO> commends) {
		this.commends = commends;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}

	public VersionBO getVersion() {
		return version;
	}

	public void setVersion(VersionBO version) {
		this.version = version;
	}

	public String getFrom_() {
		return from_;
	}

	public void setFrom_(String from_) {
		this.from_ = from_;
	}

	public String getDefaults() {
		return defaults;
	}

	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}

	public String getDefaultWebsites() {
		return defaultWebsites;
	}

	public void setDefaultWebsites(String defaultWebsites) {
		this.defaultWebsites = defaultWebsites;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
}
