package com.zhidian.model.websites.config;

/**
* @ClassName: ConfigWebsiteModel
* @Description: TODO(用于Config的站点进行过滤)
* @author dongneng
* @date 2017年5月10日 下午2:22:24
*
*/
public class ConfigWebsiteModel {
	private int id;
	private String name;
	private String type;
	private String type2;
	private String versionId;
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
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
}
