package com.zhidian.views;

import org.hibernate.validator.constraints.NotEmpty;

/**
* @ClassName: VersionAddViewMainDTO
* @Description: TODO(version-add的提交模型)
* @author dongneng
* @date 2017年5月9日 下午2:54:22
*
*/
public class VersionAddViewMainDTO {
	@NotEmpty
	private String type;// websites、results
	private String type2 ;// answer,blog【现默认只提供问答】
	private boolean isFirst;
	private String name;// 如果isFirst是true，则name的值可以接收
	private String defPage;
	private String defCss;
	private String defJs;
	private String sign;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isFirst() {
		return isFirst;
	}
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	
	
}
