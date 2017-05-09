package com.zhidian.views;

import java.util.List;

/**
* @ClassName: ResultModel
* @Description: TODO(异步请求包装类)
* @author dongneng
* @date 2017年5月2日 下午4:34:44
*
*/
public class ResultListModel {
	private String status;
	private String code;
	private String message;
	private int total;
	private List<?> items;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getItems() {
		return items;
	}
	public void setItems(List<?> items) {
		this.items = items;
	}
}
