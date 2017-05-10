package com.zhidian.views;

/**
* @ClassName: VersionMainDTO
* @Description: TODO(同于WebsiteMainDTO的作用)
* @author dongneng
* @date 2017年5月11日 上午5:18:43
*
*/
public class VersionMainDTO {
	private int id;
	private String versionId;
	private String name;// 站点名，websites:segmentfault,results:answer,blog
	private String defJs;
	private String defCss;
	private String defPage;
	private String sign;
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
	public String getDefJs() {
		return defJs;
	}
	public void setDefJs(String defJs) {
		this.defJs = defJs;
	}
	public String getDefCss() {
		return defCss;
	}
	public void setDefCss(String defCss) {
		this.defCss = defCss;
	}
	public String getDefPage() {
		return defPage;
	}
	public void setDefPage(String defPage) {
		this.defPage = defPage;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
