package com.zhidian.model.sys;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.fastjson.JSON;

@PropertySource("classpath:config.properties")
@Configuration
public class ResultRoleBO {
	@Value("${ResultRole.default_:20}")
	private int default_;
	@Value("${ResultRole.websites:{\'segmentfault\':20}}")
	private String websites_;
	private Map<String, Object> websites;
	private Date start = new Date();// 应用时间
	@Value("${ResultRole.survivor:86400}")
	private long live;
	private Date end;// 结束时间
	@Value("${ResultRole.description:未定义}")
	private String descri;// 此次规则描述

	public int getDefault_() {
		return default_;
	}

	public void setDefault_(int default_) {
		this.default_ = default_;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getWebsites() {
		if (websites != null) {
			return websites;
		} else {
			if (websites_ != null && websites_.length() > 0) {
				return (Map<String, Object>) JSON.parse(websites_);
			}
			return null;
		}
	}

	public void setWebsites(Map<String, Object> websites) {
		this.websites = websites;
	}

	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		if (this.end == null) {
			if (this.start != null) {
				if (live > 0) {
					end = new Date(this.start.getTime() + live);
				}
			}
		}
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getWebsites_() {
		return websites_;
	}

	public void setWebsites_(String websites_) {
		this.websites_ = websites_;
	}
}
