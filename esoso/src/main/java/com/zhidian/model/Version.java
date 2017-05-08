package com.zhidian.model;

import java.util.Date;

/**
 * @ClassName: Version
 * @Description: TODO(页面的版本控制。这个Version数据库就是为爬虫准备的。用来显示页面的，进行默认调用的。当升级站点的时候可用到
 *               一、搜索结果页主要依靠这个，而内容详情页主要是依赖本身的css,如果未找到才进行version表搜索。
 *               注意：SearchEnumDefine.Type)
 * Type: ResourceEnumDefine.ResourceType的websites  Name: ResourceEnumDefine.ResourceType的segmentfault、github
 * Type: ResourceEnumDefine.ResourceType的results   Name: SearchEnumDefine.Type 的 answer,blog
 * Type: AppEnumDefine.SiteService的engine    Name: ResourceEnumDefine.ResourceType的index
 * ResourceEnumDefine.ResourceType的index是name，不是type
 * 
 * type:results - > answer|blog
 * type:engine -> index
 * type:websites ->segmentfault|github
 * @author dongneng
 * @date 2017年4月21日 下午6:31:34
 *
 */
public class Version {
	private int id;
	private String name;// 站点名，websites:segmentfault,results:answer,blog。
						// 如果是results只有一个是using状态的。0.0.0.0也是非using状态的。
//	private String version;// 版本号、0.0.0.1
	private Date createTime;
	private Date unuseTime;
	private int using;// 是否在使用 ArticleEnumDefine.UsingLevel
	private String defCss;// //
							// 默认的css地址。
							// css/websites/segmentfault/0.0.0.1/index.css,segmentfault/css/0.0.0.1/main.css
							// css/index/engine/0.0.0.0/index.css
							// css/results/answer/0.0.0.0/result.css
	private String defPage;// 默认的page的地址。
							// websites/segmentfault/0.0.0.1/index
							// results/answer/0.0.0.0/index
							// index/engine/0.0.0.0/index
	private String defJs; // 默认js地址
							// js/websites/segmentfault/0.0.0.1/xx.js,js/websites/segmentfault/0.0.0.1/xx2.js
							// js/results/answer/0.0.0.0/xx.js
							// js/index/engine/0.0.0.1/xx.js,js/index/engine/0.0.0.1/xx2.js
	private String sign;// 备注
	private String type;// 类型。websites,results。
	private int nmp;// 同于Website的nmp，意义是一样的

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUnuseTime() {
		return unuseTime;
	}

	public void setUnuseTime(Date unuseTime) {
		this.unuseTime = unuseTime;
	}

	public int getUsing() {
		return using;
	}

	public void setUsing(int using) {
		this.using = using;
	}

	public String getDefCss() {
		return defCss;
	}

	public void setDefCss(String defCss) {
		this.defCss = defCss;
	}

	public String getDefPage() {
		return defPage;
	}

	public void setDefPage(String defPage) {
		this.defPage = defPage;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDefJs() {
		return defJs;
	}

	public void setDefJs(String defJs) {
		this.defJs = defJs;
	}

	public int getNmp() {
		return nmp;
	}

	public void setNmp(int nmp) {
		this.nmp = nmp;
	}
}
