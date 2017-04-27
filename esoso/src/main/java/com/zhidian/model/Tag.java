package com.zhidian.model;

import java.util.Date;

/**
* @ClassName: Tag
* @Description: TODO(tags表，此表是一個字典表。不建议更新该表，不建议有同名的)
* @author dongneng
* @date 2017年4月21日 下午5:00:23
*
*/
public class Tag {
	private int id;
	private String name;// python 标签名。
	private String value;// python 标签名
	private int supId;// 默认null
	private Date createTime;
	private String createMan;// 创建人
	private Date unuseTime;// 禁用时间
	private String unuseMan;// 禁用人
	private int using;// 是否在使用
	private String sign;// 创建理由
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getSupId() {
		return supId;
	}
	public void setSupId(int supId) {
		this.supId = supId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUnuseTime() {
		return unuseTime;
	}
	public void setUnuseTime(Date unuseTime) {
		this.unuseTime = unuseTime;
	}
	public String getUnuseMan() {
		return unuseMan;
	}
	public void setUnuseMan(String unuseMan) {
		this.unuseMan = unuseMan;
	}
	public int getUsing() {
		return using;
	}
	public void setUsing(int using) {
		this.using = using;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
}
