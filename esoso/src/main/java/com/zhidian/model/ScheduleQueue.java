package com.zhidian.model;

import java.util.Date;

public class ScheduleQueue {
	private int id;
	private String type;// 是定时爬虫，还是管理员添加爬虫计划  AppEnumDefine.ScheduleQueuesType
	private String type2;// 爬虫的类型。现在是默认answer  SearchEngineEnumDefine.Type.问答
	private String type3;// 爬虫的页面类型。结果页还是详情页。默认详情页 ResourceEnumDefine.ResourceType.内容详情页
	private String name;// 爬虫的站点名称，如:segmentfault。会去websites找对应的爬虫
	private Date createTime;
	private String createMan;
	private String sign;
	private int status;// 1 代表未处理，0代表处理完，2代表需上级处理，3代表无法处理
	private String url;// 下载地址：http://segmentfault.con/a/45648797978。或者是http://segmentfault.com?q=ss&page=2(这一种服务暂时不提供)
	private String uuid;// 下载地址的uuid。提供给在线获取页面
	private int mark;// 排序级别。默认0，越大越有限执行。【待改进：执行的时候】
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public String getType3() {
		return type3;
	}
	public void setType3(String type3) {
		this.type3 = type3;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
}
