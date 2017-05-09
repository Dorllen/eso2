package com.zhidian.views;

import java.util.List;

import com.zhidian.model.sys.ConfigBO;

/**
* @ClassName: VersionAddVO
* @Description: TODO(version-add.html)
* @author dongneng
* @date 2017年5月10日 上午2:12:50
*
*/
public class VersionAddVO {
	List<ConfigBO> items;// 现在只有类型选项列表
	public List<ConfigBO> getItems() {
		return items;
	}

	public void setItems(List<ConfigBO> items) {
		this.items = items;
	}
	
}
