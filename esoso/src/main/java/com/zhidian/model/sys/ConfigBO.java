package com.zhidian.model.sys;

/**
 * @ClassName: Config
 * @Description: TODO(数据库存储的配置文件，接收需要用另一个模型来接收。
 *               这里做的是维护确定站点是否启用；是否启用在线爬虫等等。设置搜索结果页的数量级。)
 * @author dongneng
 * @date 2017年4月23日 下午12:45:05
 *
 */
public class ConfigBO {
	private int id;
	private String name;// 名字，如segmentfault.可以考虑将所有站点放到ResultRoleBO，并可以配置初始化
	private String value;// segmentfault的网站热度为1.1
	private String type;
	private String sign;
	private String cName;// 接收的数据模型

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

}
