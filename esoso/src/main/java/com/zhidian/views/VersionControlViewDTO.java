package com.zhidian.views;

public class VersionControlViewDTO {
	private int id;
	private String versionId;
	private String name;
	private String createTime;
	private boolean using;
	private boolean nmp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public boolean getUsing() {
		return using;
	}
	public void setUsing(boolean using) {
		this.using = using;
	}
	public boolean isNmp() {
		return nmp;
	}
	public void setNmp(boolean nmp) {
		this.nmp = nmp;
	}
}
