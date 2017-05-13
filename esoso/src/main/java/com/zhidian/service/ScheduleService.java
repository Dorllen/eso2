package com.zhidian.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhidian.bases.worm.WormsService;

@Service("scheduleService")
public class ScheduleService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	WormsService wormsService;

	public void refreshCacheArticle() {
		// 定时更新缓存数据

	}

	public void autoDownloadFromSheduleQueue() {
		try {
			wormsService.startPullDataFromScheduleByAdminTrigger();
		} catch (Exception e) {
			log.warn("定时任务出现异常-> {}", e.getMessage());
		}
	}

}
