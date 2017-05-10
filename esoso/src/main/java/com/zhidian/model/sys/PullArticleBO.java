
/**
* @Title: Ariticle.java
* @Package com.zhidian.mappers
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-20 下午9:54:03
* @version V1.0
*/
package com.zhidian.model.sys;

/**
 * @ClassName: Ariticle
 * @Description: TODO(爬取的内容文章。不允许覆盖，因为要做时间戳显示旧版本)
 * @author dongneng
 * @date 2017-3-20 下午9:54:03
 *
 */
public class PullArticleBO {

	private int id;
	private String uuid;
	private String name;// 站点名 segmentfault
	private int using;// 是否使用
	private String url;// 源地址
	private String mark;// 标记，可以管理员修改。hot,time,cviews。也可以自动。管理员修改不会触发updateManTime
	private String cuuid;// contents的uuid值
	private int status;// 爬虫置入的数据，如1,待处理的！，0代表已处理，可以直接访问 -1代表已删除
	private String type;// 爬虫的类型。answer|blog
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUsing() {
		return using;
	}
	public void setUsing(int using) {
		this.using = using;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getCuuid() {
		return cuuid;
	}
	public void setCuuid(String cuuid) {
		this.cuuid = cuuid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
