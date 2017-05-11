package com.zhidian.views;

/**
* @ClassName: WebsitePalistDTO
* @Description: TODO(website-pa-list.html,Website-pa-version.html(add type、sign property))
* @author dongneng
* @date 2017年5月10日 上午3:30:16
*
*/
public class WebsitePaDTO {
	private int id;
	private String name;
	private String title;
	private String tags;
	private String createTime;
	private String url;
//	private String versionId;//  PullArticle在数据显示是没有versionId的，直接使用id
	private String type;
	private String sign;
	private String relyVersionId;
	private String uuid;
	private String mark;
	private int views;
	private int collets;
	private double scores;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRelyVersionId() {
		return relyVersionId;
	}
	public void setRelyVersionId(String relyVersionId) {
		this.relyVersionId = relyVersionId;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getCollets() {
		return collets;
	}
	public void setCollets(int collets) {
		this.collets = collets;
	}
	public double getScores() {
		return scores;
	}
	public void setScores(double scores) {
		this.scores = scores;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
