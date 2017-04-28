package com.zhidian.model;

import java.util.Date;

/**
 * @ClassName: PaCount
 * @Description: TODO(用于统计页面的操作类型)
 * @author dongneng
 * @date 2017年4月28日 下午4:15:49
 *
 */
public class PaCount {
	private int id;
	private String uuid;// 页面的uuid。
	private String name;// 站点名
	private String url;// 操作的地址
						// 如果url找不到，则可以向ScheduleQueue去寻找,有可能在这里（但是没关系，这个可以不记录url）,但是由于每次记录入该表，需要验证信息，故此url顺带入表
	private Date createTime;// 操作时间，操作时间。
	private String createMan;// 操作人，如果是非注册，只可能是访问
	private int type;// 类型，用来区分是。PageControllType，有点赞，访问，差评等。存的是该type的顺序。
	private int status;// status默认0。0代表有效。1代表无效
	private String originUrl;// 来源地址
	private String originIp;// 来源ip

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	public String getOriginIp() {
		return originIp;
	}

	public void setOriginIp(String originIp) {
		this.originIp = originIp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
