package com.zhidian.model.sys;


/**
 * @ClassName: VersionBO
 * @Description: TODO(Admin通过config管理version)
 * @author dongneng
 * @date 2017年4月21日 下午7:02:21
 *
 */
public class VersionBO2 {
	private int id;
	private String name;// 站点名，websites:segmentfault,results:answer,blog
	private String type;
	private String type2;
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
