package com.zhidian.model;

import java.util.Date;

/**
* @ClassName: Message
* @Description: TODO(用户系统管理员登陆用户反馈的，同时也可以是系统向用户推送的消息。可以称呼为站内邮件)
* @author dongneng
* @date 2017年4月25日 下午9:23:35
*
*/
public class Message {
	private int id;
	private String toAccount;// 接收方对象
	private String toType;// 接收方对象类型。管理员、还是用户
	private String fromAccount;
	private String fromType;
	private String title;// 标题
	private String content;// 内容
	private Date createTime;// 发送方创建时间
	private Date sendTime;// 发送方发送时间
	private Date receTime;// 接收方点击时间
	private Date confirmTime;// 接收方确定时间
	private int status;// 消息的状态。0代表未初始化,1代表新建,2代表发送了,3代表接收方点开了,4代表接收方确定了
	private String type;// 消息的类型。
	private int using;// 是否显示，0代表显示，1代表不显示。即无论状态如何都不管
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getToType() {
		return toType;
	}
	public void setToType(String toType) {
		this.toType = toType;
	}
	public String getFromType() {
		return fromType;
	}
	public void setFromType(String fromType) {
		this.fromType = fromType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getReceTime() {
		return receTime;
	}
	public void setReceTime(Date receTime) {
		this.receTime = receTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public int getUsing() {
		return using;
	}
	public void setUsing(int using) {
		this.using = using;
	}
}
