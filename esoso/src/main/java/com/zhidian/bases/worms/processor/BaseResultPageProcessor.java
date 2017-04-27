package com.zhidian.bases.worms.processor;

import com.zhidian.model.sys.PullResultPageModel;

public abstract class BaseResultPageProcessor<T extends PullResultPageModel> extends BaseProcessor {
	private T obj;

	public BaseResultPageProcessor() {
	}

	public BaseResultPageProcessor(T obj) {
		this.obj = obj;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	/**
	 * @Title: removeUrlSearch @Description: TODO(去除?及后面字符) @param @param
	 * url @param @return 参数 @return String 返回类型 @throws
	 */
	public static String removeUrlSearch(String url) {
		// 现在的网站不会是通过传xxx.com?article=123456 这种方式
		if (url != null && url.length() > 0) {
			int i = url.indexOf('?');
			if (i != -1) {
				return url.substring(0, i);
			}
		}
		return url;
	}

	public static void main(String[] args) {
		//
		String str = "http://www.baidu.com?uod";
		System.out.println(removeUrlSearch(str));
	}
}
