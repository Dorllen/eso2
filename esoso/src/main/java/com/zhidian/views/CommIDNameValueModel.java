package com.zhidian.views;

/**
 * @ClassName: CommIDKNameValueModel
 * @Description: TODO(通用id,name,value模型)
 * @author dongneng
 * @date 2017年5月9日 下午2:23:01
 *
 */
public class CommIDNameValueModel {
	public CommIDNameValueModel() {
	}

	public CommIDNameValueModel(int id, String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	private int id;
	private String name;
	private String value;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
