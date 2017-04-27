package com.zhidian.views;

// 想比较ResultPageView，该object是用来获得爬虫来的网页数据
public class ResultPageDTO {
	private String id; // uuid
	private String title;// 标题
	private String contents;// 内容部分，问题部分
	private String url;// 源地址，只是部分地址
	private String from;// 来的网站，如segmentfault

	public ResultPageDTO() {
	}

	public ResultPageDTO(String id, String title, String contents) {
		this.id = id;
		this.title = title;
		this.contents = contents;
	}

	public ResultPageDTO(String id, String title, String contents, String url) {
		this(id, title, contents);
		this.setUrl(url);
	}

	public ResultPageDTO(String id, String title, String contents, String url, String from) {
		this(id, title, contents, url);
		this.from = from;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
