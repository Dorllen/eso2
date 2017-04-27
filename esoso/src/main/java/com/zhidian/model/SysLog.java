package com.zhidian.model;

import java.util.Date;

/**
* @ClassName: SysLog
* @Description: TODO(系统日志，包括：用户登录日志，管理员操作等日志。思考？日志数据是系统正常日志。这类日志放数据库好吗？还是直接采用Log4j的？)
* @author dongneng
* @date 2017年4月25日 下午8:45:09
*
*/
public class SysLog {
	private int id;
	private String type;// 日志的类型。登陆，更新，增加，删除
	private String type2;// 日志的类型再划分。loginStatus,updatePw,updateDesignButton,还有用户点赞，收藏，等等
	private String account;// 日志的用户名，account值。
	private String value;// 值：超级管理员，在12:37分进行了，爬虫新增计划。如：开启
	private int status;// 代表值的状态，到底是成功还是失败了。可以取:0成功（默认）,1有小问题,2有大问题,3严重
	private Date createTime;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}
