package com.zhidian.model.websites.config;

/**
* @ClassName: ConfigWebsiteItemModel
* @Description: TODO(用于Config的站点数据项进行过滤)
* @author dongneng
* @date 2017年5月10日 下午2:22:24
*
*/
public class ConfigWebsiteItemModel {
	private int id;
	private String name;
	private String url;
	private String uuid;
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
}
