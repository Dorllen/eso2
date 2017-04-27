
/**
* @Title: User.java
* @Package com.zhidian.entity
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-18 下午11:39:26
* @version V1.0
*/
package com.zhidian.model;

import java.util.Date;

/**
 * @ClassName: User
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-18 下午11:39:26
 *
 */
public class User {
	private int id;
	private String name;
	private String account;
	private String password;//md5密码值
	private Date createTime;
	private Date updateTime;
	private int authority;// 默认0代表所有可用的。
	private String designSearch;// 自定义搜索来源。默认搜索
	private String designButton;// 自定义button
	private String photo;
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public String getDesignSearch() {
		return designSearch;
	}
	public void setDesignSearch(String designSearch) {
		this.designSearch = designSearch;
	}
	public String getDesignButton() {
		return designButton;
	}
	public void setDesignButton(String designButton) {
		this.designButton = designButton;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
