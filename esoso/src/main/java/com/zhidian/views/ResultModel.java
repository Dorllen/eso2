package com.zhidian.views;

/**
* @ClassName: ResultModel
* @Description: TODO(异步请求包装类)
* @author dongneng
* @date 2017年5月2日 下午4:34:44
*
*/
public class ResultModel {
	private String status;
	private String code;
	private String message;
	private Object item;
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
	public Object getItem() {
		return item;
	}
	public void setItem(Object item) {
		this.item = item;
	}
}
