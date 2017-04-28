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
		爬虫服务("WormSercie"), 搜索服务("EngineSercie"), 系统服务("SysService");
		private String value;

		private ConfigType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
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
