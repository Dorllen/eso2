package com.zhidian.bases;

public class ResourceEnumDefine {
	public enum ResourceType {
		内容详情页("websites"), 搜索结果页("results"), 主页("index");
		private String value;

		private ResourceType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
