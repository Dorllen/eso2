package com.zhidian.bases.worms.processor;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zhidian.bases.AppEnumDefine;
import com.zhidian.model.sys.PullDataWatchObject;
import com.zhidian.model.sys.PullResultPageModel;

import us.codecraft.webmagic.selector.Selectable;

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
	 *         url @param @return 参数 @return String 返回类型 @throws
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

	/**
	 * @Title: setWatcherForProperty @Description:
	 * TODO(监控表达式是否能取值，如果不能取值则记录，同样不会对同一属性表达式记录多次) @param @param
	 * select @param @param url @param @param nameDescri @param @param xpath
	 * 参数 @return void 返回类型 @throws
	 */
	public void setWatcherForProperty(Selectable select, String url, String nameDescri, String xpath) {
		if (select == null || StringUtils.isEmpty(select.get())) {
			if (exitInWatcherByPropertyName(nameDescri)) {
				PullDataWatchObject watcher = new PullDataWatchObject();
				watcher.setName(nameDescri);// 搜索結果的爬取属性的名
				watcher.setUrl(url);
				watcher.setTimes(new Date());
				watcher.setWebsite(this.getObj().getName());
				watcher.setXpathContent(xpath);
				watcher.setType(AppEnumDefine.WormLogType.结果页页面解析.name());
				this.getObj().addErrorWatcher(watcher);
			}
		}
	}

	private boolean exitInWatcherByPropertyName(String name) {
		List<PullDataWatchObject> list = this.getObj().getErrorWatcher();
		if (list != null && list.size() > 0) {
			for (PullDataWatchObject o : list) {
				if (o != null && o.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}
}
