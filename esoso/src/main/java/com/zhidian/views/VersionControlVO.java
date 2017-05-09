package com.zhidian.views;

/**
* @ClassName: VersionControlVO
* @Description: TODO(version-control.html)
* @author dongneng
* @date 2017年5月10日 上午1:14:43
*
*/
public class VersionControlVO {
	private int id;
	private String name;
	private String versionId;
	private String createTime;
	private boolean using;
	private boolean nmp;// 是否禁用了。如果是需要恢复
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
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public boolean isUsing() {
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
