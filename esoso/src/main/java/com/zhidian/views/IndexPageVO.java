package com.zhidian.views;

import java.util.List;

import com.zhidian.model.sys.VersionBO;

public class IndexPageVO {

	private List<FormBarDTO> form;
	private FormBarDTO defaults;// 默认的form
	private VersionBO version;
	private List<RecentHistorySearchDTO> searches;
	private String forms;
//	private 
	// 用户
	public List<FormBarDTO> getForm() {
		return form;
	}
	public void setForm(List<FormBarDTO> form) {
		this.form = form;
	}
	public FormBarDTO getDefaults() {
		return defaults;
	}
	public void setDefaults(FormBarDTO defaults) {
		this.defaults = defaults;
	}
	public VersionBO getVersion() {
		return version;
	}
	public void setVersion(VersionBO version) {
		this.version = version;
	}
	public List<RecentHistorySearchDTO> getSearches() {
		return searches;
	}
	public void setSearches(List<RecentHistorySearchDTO> searches) {
		this.searches = searches;
	}
	public String getForms() {
		return forms;
	}
	public void setForms(String forms) {
		this.forms = forms;
	}
	
}
