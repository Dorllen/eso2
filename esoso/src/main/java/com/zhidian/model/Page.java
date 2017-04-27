
/**
* @Title: Page.java
* @Package com.zhidian.entity
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-18 下午11:27:45
* @version V1.0
*/
package com.zhidian.model;

import java.util.Date;

/**
* @ClassName: Page
* @Description: TODO(代表内容详情页？)
* @author dongneng
* @date 2017年4月21日 下午8:56:29
*
*/
public class Page {
	private int id;
	private String uuid;
	private String name;
	private String sign;// 描述
	private String path;// segmentfault/page/0.0.0.1/index.html
	private Date createTime;
	private Date updateTime;
	private String updateMan;// 更新人
	private Date updateManTime;// 更新人时间
	private int using;
	private Date unuseTime;
	private String unuseMan;// 禁用人
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateMan() {
		return updateMan;
	}
	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}
	public Date getUpdateManTime() {
		return updateManTime;
	}
	public void setUpdateManTime(Date updateManTime) {
		this.updateManTime = updateManTime;
	}
	public int getUsing() {
		return using;
	}
	public void setUsing(int using) {
		this.using = using;
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
}
