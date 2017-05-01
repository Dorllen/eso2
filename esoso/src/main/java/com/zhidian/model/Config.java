package com.zhidian.model;

import java.util.Date;

/**
 * @ClassName: Config
 * @Description: TODO(数据库存储的配置文件，接收需要用另一个模型来接收。
 *               这里做的是维护确定站点是否启用；是否启用在线爬虫等等。设置搜索结果页的数量级。)
 * @author dongneng
 * @date 2017年4月23日 下午12:45:05
 *
 */
public class Config {
	private int id;
	private String name;// 名字，如segmentfault.可以考虑将所有站点放到ResultRoleBO，并可以配置初始化
	private String value;// segmentfault的网站热度为1.1
	private String type;// 配置的类型。 AppEnumDefine.ConfigType
	// Type: EngineSercie Name: defaultEngine(默认搜索来源)  engineSort类型(代表热度等设置),onlineEngine(代表当前站点的在线搜索是否开启),engineType代表搜索界面的默认值,
	// Type: SysService Name:  pageSnapshot(页面快照) resultSnapshot(结果快照) recVersion(最近版本),
	// Type: WormSercie Name: stopWorm(哪些站点停止爬虫，无论进行的）
	// Type: WebsiteService Name:preventVisit(不允许访问，报找不到),allowVisit(允许访问),pageSecVisit安全访问(可以设置那些网站的安全访问是开启的),pageSecClose(设置哪些页面的安全访问关闭，比secVisit级别低)
	private Date createTime;
	private Date updateTime;
	private String updateMan;
	private int using;
	private Date unuseTime;
	private String unuseMan;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getUsing() {
		return using;
	}

	public void setUsing(int using) {
		this.using = using;
	}

	public Date getUnuseTime() {
		return unuseTime;
	}

	public void setUnuseTime(Date unuseTime) {
		this.unuseTime = unuseTime;
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

	public String getUnuseMan() {
		return unuseMan;
	}

	public void setUnuseMan(String unuseMan) {
		this.unuseMan = unuseMan;
	}

	public String getUpdateMan() {
		return updateMan;
	}

	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}
}
