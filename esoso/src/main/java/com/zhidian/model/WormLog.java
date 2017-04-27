package com.zhidian.model;

import java.util.Date;

public class WormLog {
	private int id;
	private String propertyName;// 属性名。如segmentfault的页面对象的区域块
	private String website;// 爬虫站点名称
	private String xpathContent;// 解析表达式
	private String url;// 爬虫页面的url
	private String sign;// 来自爬虫队列的对爬虫的描述
	private String type;// 爬虫类型。如：待改进，待处理Css
	private String fromType;// 爬虫类型。记录来自爬虫队列的类型。可能css存儲來自QueueSchedule的会用字符串拼接：如type,type2,type3
	private String uuid;// 爬虫页面的uuid
	private Date triggerTime;// 触发时间
	private int status;// 代表审视的状态。0代表已处理，1代表未处理。默认1。2代表需上级处理，3代表根本无法处理
	private String hMan;// 处理人
	private Date hTime;// 处理时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getXpathContent() {
		return xpathContent;
	}
	public void setXpathContent(String xpathContent) {
		this.xpathContent = xpathContent;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getTriggerTime() {
		return triggerTime;
	}
	public void setTriggerTime(Date triggerTime) {
		this.triggerTime = triggerTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String gethMan() {
		return hMan;
	}
	public void sethMan(String hMan) {
		this.hMan = hMan;
	}
	public Date gethTime() {
		return hTime;
	}
	public void sethTime(Date hTime) {
		this.hTime = hTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFromType() {
		return fromType;
	}
	public void setFromType(String fromType) {
		this.fromType = fromType;
	}
}
