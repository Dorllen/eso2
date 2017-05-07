package com.zhidian.views;

/**
* @ClassName: ResultModel
* @Description: TODO(异步请求包装类)
* @author dongneng
* @date 2017年5月2日 下午4:34:44
*
*/
public class ResultMultiModel {
	private String status;
	private String code;
	private String message;
	private int total;
	private Object item;
	private Object item_;
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
	public Object getItem() {
		return item;
	}
	public void setItem(Object item) {
		this.item = item;
	}
	public Object getItem_() {
		return item_;
	}
	public void setItem_(Object item_) {
		this.item_ = item_;
	}
}
