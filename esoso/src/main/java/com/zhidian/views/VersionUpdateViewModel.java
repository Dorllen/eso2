package com.zhidian.views;

public class VersionUpdateViewModel {
	private int id;
	private String versionId;
	private String name;
	private String type;
	private String type2;
	private String defPage;
	private String defCss;
	private String defJs;
	private String sign;
	private boolean using;
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
	public String getDefPage() {
		return defPage;
	}
	public void setDefPage(String defPage) {
		this.defPage = defPage;
	}
	public String getDefCss() {
		return defCss;
	}
	public void setDefCss(String defCss) {
		this.defCss = defCss;
	}
	public String getDefJs() {
		return defJs;
	}
	public void setDefJs(String defJs) {
		this.defJs = defJs;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public boolean isUsing() {
		return using;
	}
	public void setUsing(boolean using) {
		this.using = using;
	}
}
