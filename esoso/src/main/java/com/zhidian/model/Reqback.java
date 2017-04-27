package com.zhidian.model;

import java.util.Date;

/**
* @ClassName: Reqback
* @Description: TODO(反馈)
* @author dongneng
* @date 2017年4月25日 下午9:24:59
*
*/
public class Reqback {
	private int id;
	private String account;// 用户名。
	private String type;// 反馈类型。搜索引擎反馈，内容库内容反馈，管理间反馈（代表其他管理员反馈）。
	private Date createTime;// 创建时间
	private String orignIp;// 源ip地址
	private String originUrl;// 源网页来源
	private String value;// 反馈内容
	private int status;//0代表已未处理，1代表丢弃处理，2代表待审视，3代表处理成功
	private Date hTime;//处理时间
	private String hMan;// 处理人
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getOrignIp() {
		return orignIp;
	}
	public void setOrignIp(String orignIp) {
		this.orignIp = orignIp;
	}
	public String getOriginUrl() {
		return originUrl;
	}
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date gethTime() {
		return hTime;
	}
	public void sethTime(Date hTime) {
		this.hTime = hTime;
	}
	public String gethMan() {
		return hMan;
	}
	public void sethMan(String hMan) {
		this.hMan = hMan;
	}
}
