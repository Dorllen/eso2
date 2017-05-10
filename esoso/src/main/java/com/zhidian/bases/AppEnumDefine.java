package com.zhidian.bases;

public class AppEnumDefine {
	public enum SiteService {
		搜索("engine"), 博客("blog"), 网盘("net");
		private String value;

		private SiteService(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum PageControllType {// 不设置value是为了更好取值
		访问, 点赞, 收藏, 差评
	}

	public enum WormLogType {
		详情页页面解析, 详情页CSS变动, 结果页页面解析;
	}

	public enum ConfigType {
		爬虫服务("WormService"), 搜索服务("EngineService"), 系统服务("SysService"), 版本控制("VersionController"), 站点服务(
				"WebsiteService"), 服务定义("ServiceType");
		private String value;

		private ConfigType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	public enum ConfigServiceType{// 用于定义，站点有哪些服务
		Wesbsites,Articles
	}
	
	public enum ConfigWormService{
		OnlinePullData,// 在线爬取数据禁止
		SchedulePullData,// 定时爬取
		CustomPullData// 自定义爬取
	}
	
	public enum ConfigEngineService{
		SearchService, // 能够直接搜索到。指搜数据库
		OnlineSearchService// 在线搜索
	}
	
	public enum ConfigSysService{
		Visit,
		SecVisit,
		PageShot,// 页面快照
		VersionService,// 版本服务，如果有设置，则只提供一个版本，最新版本
		WebsiteAnalysis// 站点是否有过服务功能
	}
	
	public enum ConfigWebsiteService{// ConfigType 的WesbitesService 用于控制单条数据. 都是黑名单，默认是通过的
		Visit,// 直接访问黑名单
		SecVisit,// 安全访问黑名单
		SearchService, // 能够直接搜索到
		OnlinePullData,// 在线爬取数据禁止
		SchedulePullData,// 定时爬取
		CustomPullData,// 自定义爬取
		WebsiteAnalysis,// 是否加入站点分析
		SortService,// 排名服务，是否加入排名算法队列中
		PageShot,// 页面快照
		VersionService// 版本服务
	}

	public enum SysLogType {
		登陆类日志("LoginLog"), 更新类日志("UpdateLog"), 新增类日志("InsertLog"), 删除类日志("DeleteLog");
		private String value;

		private SysLogType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum SysLogSubType {
		爬虫服务管理类日志("WormServiceControlrLog");

		private String value;

		private SysLogSubType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum AppUser {
		系统("System"), 管理员("Admin"), 用户("User");
		private String value;

		private AppUser(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum MessageType {
		用户2用户("UserToUser"), 用户2管理员("UserToAdmin"), 管理员2用户("AdminToUser"), 管理员2管理员("AdminToAdmin");
		private String value;

		private MessageType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum ScheduleQueuesType {
		// 系统自增考虑的是：在线爬虫自动增加
		// 定时计划指的是：定时任务向数据库添加爬虫对象
		// 注意.xml中有依赖
		系统自增("System"), 管理员自增("AdminHandler"), 定时计划("ScheduleAuto");
		private String value;

		private ScheduleQueuesType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
