package com.zhidian.model.sys;

/**
 * @ClassName: CommIDKNameValueModel
 * @Description: TODO(通用id,name,value模型)
 * @author dongneng
 * @date 2017年5月9日 下午2:23:01
 *
 */
public class NameValueModel {
	public NameValueModel() {
	}

	public NameValueModel(String name, String value) {
		this.name = name;
		this.value = value;
	}

	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
