package com.zhidian.model.websites.answer;

/**
* @ClassName: SegmentfaultPageRObject
* @Description: TODO(用于当前正在使用的websites/answer/segmentfault的数据模型。非VO)
* @author dongneng
* @date 2017年4月23日 下午1:25:38
*
*/
public class SegmentfaultPageRObject extends PageControlObject{
	private String id;
	private String name;
	private String title;
	private String originUrl;
	private String main;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOriginUrl() {
		return originUrl;
	}
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
}
