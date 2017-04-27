package com.zhidian.views;

// ResultPageView是对ResultPageDTO的再处理，将contents部分处理掉，originUrl处理
public class ResultPageView {
	private String id; // uuid
	private String title;// 标题
	private String contents;// 内容部分，可能问答部分
	private String originUrl;// 源地址，只是部分地址
	private int views;//浏览量
	private boolean collected;// 是否已收藏
	
	public ResultPageView(){}
	public ResultPageView(String id,String title,String contents){
		this.id = id;
		this.title = title;
		this.contents = contents;
	}
	public ResultPageView(String id,String title,String contents,String originUrl){
		this(id,title,contents);
		this.originUrl = originUrl;
	}
	public ResultPageView(String id,String title,String contents,String originUrl,int views){
		this(id,title,contents,originUrl);
		this.views = views;
	}
	public ResultPageView(String id,String title,String contents,String originUrl,int views,boolean collected){
		this(id,title,contents,originUrl,views);
		this.collected = collected;
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
	public String getOriginUrl() {
		return originUrl;
	}
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public boolean isCollected() {
		return collected;
	}
	public void setCollected(boolean collected) {
		this.collected = collected;
	}
}
