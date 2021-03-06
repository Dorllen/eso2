package com.zhidian.model.sys;

import java.util.List;

/**
 * @ClassName: VersionBO
 * @Description: TODO(Version类的精简版，用于page页面的资源加载。我直接從数据库加载页面，创建VersionBO)
 * @author dongneng
 * @date 2017年4月21日 下午7:02:21
 *
 */
public class VersionBO {
	private int id;
	private String name;// 站点名，websites:segmentfault,results:answer,blog
//	private String version = "0.0.0.0";// 版本号、0.0.0.1 默认版本0.0.0.0
	private String type;
	private String type2;
	private List<String> defCss;// //
								// 默认的css地址。css/websites/segmentfault/0.0.0.1/index.css,segmentfault/css/0.0.0.1/main.css
	private String defPage;// 默认的page的地址。websites/segmentfault/0.0.0.1/index.html
	public List<String> getDefCss() {
		return defCss;
	}

	public void setDefCss(List<String> defCss) {
		this.defCss = defCss;
	}

	public String getDefPage() {
		return defPage;
	}

	public void setDefPage(String defPage) {
		this.defPage = defPage;
	}

	public List<String> getDefJs() {
		return defJs;
	}

	public void setDefJs(List<String> defJs) {
		this.defJs = defJs;
	}

	private List<String> defJs; // 默认js地址
								// js/websites/segmentfault/0.0.0.1/xx.js,js/websites/segmentfault/0.0.0.1/xx2.js

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}
}
