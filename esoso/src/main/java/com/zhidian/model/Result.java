package com.zhidian.model;

import java.util.Date;

/**
* @ClassName: Result
* @Description: TODO()
* @author dongneng
* @date 2017年4月22日 下午10:28:22
*
*/
public class Result {
	// 搜索结果保存，等同于es中缓存的对象
	private int id;
	private String uuid;
	private String title;
	private String name;// 站点名。segementfault
	private String content;// 搜索结果
	private String tags;//标签
	private int views;
	private double scroes;
	private int collets;
	private int using;// 是否在使用
	private Date newTime;// 最新时间。可能是最近更新时间或者是创建时间
	private String mark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public double getScroes() {
		return scroes;
	}
	public void setScroes(double scroes) {
		this.scroes = scroes;
	}
	public int getCollets() {
		return collets;
	}
	public void setCollets(int collets) {
		this.collets = collets;
	}
	public int getUsing() {
		return using;
	}
	public void setUsing(int using) {
		this.using = using;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Date getNewTime() {
		return newTime;
	}
	public void setNewTime(Date newTime) {
		this.newTime = newTime;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
}
