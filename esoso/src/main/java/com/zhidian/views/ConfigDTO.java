package com.zhidian.views;

import com.zhidian.model.sys.ConfigBO;

public class ConfigDTO {
	public ConfigDTO() {
	}

	public ConfigDTO(ConfigBO config) {
		if (config != null) {
			this.setName(config.getcName());
			this.setValue(config.getValue());
		}
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

	private String name;
	private String value;
}
