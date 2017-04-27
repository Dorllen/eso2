package com.zhidian.bases;

public class SearchEngineEnumDefine {

	public enum Type {
		// segmentfault|github
		问答("answer", "查找关键字:python/java/ElasticSearch", "segmentfault", "segmentfault|github|cnblog"), 博客("blog", "",
				"", "");
		private String value;
		private String placeHolder;
		private String defaults;// 整个系统默认选择的俩元
		private String defaultWebsites;// 弹出面板狂默认提供可选择的来源，包括defaults

		private Type(String value, String placeHolder, String defaults, String defaultWebsites) {
			this.value = value;
			this.placeHolder = placeHolder;
			this.defaults = defaults;
			this.defaultWebsites = defaultWebsites;
		}

		public String getValue() {
			return value;
		}

		public String getPlaceHolder() {
			return placeHolder;
		}

		public String getDefaults() {
			return defaults;
		}

		public String getDefaultWebsites() {
			return defaultWebsites;
		}
	}

	public enum Sort {
		// 热度hot、时间time、点击量cviews
		热度("hot"), 时间("time"), 浏览量("cviews");
		private String value;

		private Sort(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	public enum UpOrDown {
		升序("up"), 降序("down");
		private String value;

		private UpOrDown(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	public enum From {
		资源库("db"), 源地址("origin");
		private String value;

		private From(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
