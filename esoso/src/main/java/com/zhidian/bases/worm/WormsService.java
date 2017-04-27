package com.zhidian.bases.worm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhidian.bases.AppEnumDefine;
import com.zhidian.mapper.CssInfoMapper;
import com.zhidian.mapper.ScheduleQueueMapper;
import com.zhidian.mapper.WebsiteMapper;
import com.zhidian.model.ScheduleQueue;
import com.zhidian.model.sys.CssInfoModel;
import com.zhidian.model.sys.PullPageDataTaskModel;
import com.zhidian.model.sys.PullPageObjectModel;
import com.zhidian.model.sys.PullResultDataTaskModel;
import com.zhidian.model.sys.PullResultPageModel;
import com.zhidian.model.sys.ResultRoleBO;
import com.zhidian.model.sys.WebsiteBO;
import com.zhidian.model.sys.WebsiteCssConfigModel;

@Service
public class WormsService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	// @Autowired
	// SegmentFaultSearchPipeline pipeline;

	@Autowired
	PullBaseService pullService;

	@Autowired
	ScheduleQueueMapper scheduleMapper;

	@Autowired
	ResultRoleBO resultRole;

	@Autowired
	WebsiteMapper websiteMapper;

	@Autowired
	CssInfoMapper cssInfoMapper;

	public List<PullResultPageModel> getResultsByOnlineSearch(String key, List<String> from, Integer page,
			Integer size) {
		if (page == null || page <= 0) {
			page = 1;
		}
		if (size == null || size <= 0) {
			size = 20;
		}
		// 从网络上获取，拼接
		// from 格式: segmentfault|cnblog|ibmdeveloper
		List<PullResultDataTaskModel> models = null;
		List<WebsiteBO> websites = websiteMapper
				.queryWebsitesForWormsService01ListWebsiteBO(AppEnumDefine.SiteService.搜索.getValue(), from);
		Map<String, WebsiteBO> wMap = getMapWebsiteBOFromListWebsiteBO(websites);
		if (from != null && from.size() > 0) {
			// 正则验证，从缓存/数据库中拿去segmentfault对应的class【暂定写死】
			models = new ArrayList<PullResultDataTaskModel>();
			PullResultDataTaskModel model = null;
			Map<String, Integer> authority = getAuthorityMap(from);
			for (String str : from) {
				if (!StringUtils.isEmpty(str)) {
					// 取出处理器
					model = createPullResultDataTaskModel(authority, str, key, page, size, wMap);
					if (model != null) {
						models.add(model);
					}

				}
			}
		} else {
			// 定义规则，哪些页面是默认取数据的来源。可以从数据库定义。可以从缓存/数据库中取
			models = new ArrayList<PullResultDataTaskModel>();
			int num = getAuthorityValue("segmentfault");
			PullResultDataTaskModel model = createPullResultDataTaskModel(num, "segmentfault", key, page, size, wMap);
			if (model != null) {
				models.add(model);
			}
		}
		if (models != null && models.size() > 0) {
			// 进入数据爬去队列
			return pullService.startPullPageDataFromList(models);
		}
		return null;
	}

	private Map<String, Integer> getAuthorityMap(List<String> from) {
		if (from != null) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			if (from.size() == 1) {
				String website = from.get(0);
				Object temp = resultRole.getWebsites().get(website);
				int num = resultRole.getDefault_() != 0 ? resultRole.getDefault_() : 20;
				num = temp != null ? (int) temp : num;
				map.put(website, num);
			} else {
				map.put("default_", resultRole.getDefault_() != 0 ? resultRole.getDefault_() : 20);
				map.put("total", 0);
				for (String website : from) {
					int t = map.get("total");
					Object temp = resultRole.getWebsites().get(website);
					int num = resultRole.getDefault_() != 0 ? resultRole.getDefault_() : 20;
					num = temp != null ? (int) temp : num;
					map.put(website, num);
					map.put("total", t + num);
				}
			}
		}
		return null;
	}

	private Integer getAuthorityValue(String website) {
		if (website != null) {
			Object temp = resultRole.getWebsites().get(website);
			int num = resultRole.getDefault_() != 0 ? resultRole.getDefault_() : 20;
			num = temp != null ? (int) temp : num;
			return num;
		}
		return null;
	}

	private PullResultDataTaskModel createPullResultDataTaskModel(Map<String, Integer> authority, String website,
			String key, Integer page, Integer size, Map<String, WebsiteBO> wMap) {
		// 默认一张页面显示20
		WebsiteBO web = wMap.get(website);
		if (web != null) {
			int num = 20;
			if (authority != null && authority.size() > 1) {
				num = authority.get(website) / authority.get("total") - authority.get("default_");
			}
			PullResultDataTaskModel model = new PullResultDataTaskModel(website, web.getResultProcessor(),
					web.getResultPipeline(), web.getSearchAddr() + key, web.getPagination(), num);
			if (model != null && num > 0) {
				// 扒搜索结果页，PullDataTaskModel的部分数据是无效的。如:pagination,num，但是起初url是有效的
				PullResultPageModel pull = new PullResultPageModel();// 从数据库获取爬虫数据模型
				pull.setName(model.getWebsite());
				pull.setSize(num);
				pull.setUrl(web.getSearchAddr() + key);
				pull.setPagination(web.getPagination());
				pull.setPage(page);// 页号
				pull.setUseSearch(false);// 不允许后缀
				model.setPom(pull);
				return model;
			}
		}
		return null;
	}

	private PullResultDataTaskModel createPullResultDataTaskModel(int num, String website, String key, Integer page,
			Integer size, Map<String, WebsiteBO> wMap) {
		WebsiteBO web = wMap.get(website);
		if (web != null) {
			PullResultDataTaskModel model = new PullResultDataTaskModel(website, web.getResultProcessor(),
					web.getResultPipeline(), web.getSearchAddr() + key, web.getPagination(), num);
			if (model != null && num > 0) {
				// 扒搜索结果页，PullDataTaskModel的部分数据是无效的。如:url,pagination,num
				PullResultPageModel pull = new PullResultPageModel();// 从数据库获取爬虫数据模型
				pull.setName(model.getWebsite());
				pull.setSize(num);
				pull.setUrl(web.getSearchAddr() + key);
				pull.setPagination(web.getPagination());
				pull.setPage(page);// 页号
				pull.setUseSearch(false);// 不允许后缀
				model.setPom(pull);
				return model;
			}
		}
		return null;
	}

	public List<PullResultPageModel> getResultsByOnlineSearch(String key, List<String> from) {
		return getResultsByOnlineSearch(key, from, 1, 20);
	}

	public void handlerAfterPullPageData(List<PullPageObjectModel> list) {
		// 处理获得的日志

		// 处理异常监听

		// 处理爬虫队列的数据

		// SysLog表处理

	}

	public List<PullPageObjectModel> startPullDataFromScheduleByAdminTrigger() {
		List<ScheduleQueue> list = scheduleMapper.queryScheduleQueuesForWormsService01ListScheduleQueue();
		if (list != null && list.size() > 0) {
			// 开始处理
			List<String> names = getScheduleQueueNemesNotSame(list);
			if (names != null && names.size() > 0) {
				List<WebsiteBO> websites = websiteMapper
						.queryWebsitesForWormsService01ListWebsiteBO(AppEnumDefine.SiteService.搜索.getValue(), names);
				// 获取站点的爬虫配置信息
				// 觉得把websites转list换为map更加棒
				Map<String, WebsiteBO> wMap = getMapWebsiteBOFromListWebsiteBO(websites);
				String webRoot = System.getProperty("webapp.root");
				List<PullPageDataTaskModel> models = createPullPageDataTaskModel(webRoot + "WEB-INF/css/websites", list,
						wMap);
				return pullService.startPullDataFromMapCompleteScheduleQueues(models);
			}
		}
		return null;
	}

	private List<PullPageDataTaskModel> createPullPageDataTaskModel(String cssRootPath, List<ScheduleQueue> queues,
			Map<String, WebsiteBO> wMap) {
		if (queues != null && queues.size() > 0) {
			List<PullPageDataTaskModel> models = new ArrayList<PullPageDataTaskModel>(queues.size());
			PullPageDataTaskModel model = null;
			WebsiteBO web = null;
			log.info("CssRootPath is -> {}", cssRootPath);
			for (ScheduleQueue q : queues) {
				if (q != null && q.getName() != null && q.getName().length() > 0) {
					web = wMap.get(q.getName());
					if (web == null) {
						log.info("出现了数据为空的情况！Queue_Value ->{}, WebsiteBO ->{}", q, web);
						continue;
					}
					model = new PullPageDataTaskModel(q.getName(), web.getPageProcessor(), web.getPagePipeline(),
							q.getUrl());
					PullPageObjectModel pom = new PullPageObjectModel();// 页面数据模型创建，从数据库获取数据

					pom.setName(q.getName());
					pom.setUrl(q.getUrl());
					pom.setUuid(org.apache.commons.codec.digest.DigestUtils.md5Hex(q.getUrl()));
					pom.setDownloadPath(cssRootPath);// 当前css的项目的根地址.E:\esoso\eso2\esoso\src\main\webapp\WEB-INF\css\websites
					pom.setSign(q.getSign());
					// pom.setChanged("");

					List<CssInfoModel> cLists = createCssInfoModelFromWebsiteDefaultCssConfig(pom.getDownloadPath(),
							web.getCssLists());
					pom.setCssModel(cLists);

					pom.setWebsiteConfig(web.getConfig());

					model.setPom(pom);

					models.add(model);
				}
			}
			return models;
		}
		return null;
	}

	private List<CssInfoModel> createCssInfoModelFromWebsiteDefaultCssConfig(String path,
			List<WebsiteCssConfigModel> cssLists) {
		if (cssLists != null && cssLists.size() > 0) {
			List<CssInfoModel> list = new ArrayList<CssInfoModel>(cssLists.size());
			CssInfoModel model = null;
			for (WebsiteCssConfigModel css : cssLists) {
				if (css != null) {
					model = new CssInfoModel();
					model.setCssPath(path);// 获得项目的保存文件的地址。到css文件夹就行。如:E:\esoso\eso2\esoso\src\main\webapp\WEB-INF\css\websites
					model.setName(css.getName());
					model.setUrl(css.getUrl());
					model.setUuid(css.getUuid());
					model.setUseSearch(css.isUseSearch());
					model.setVersion(css.getVersion());
					model.setWebSite(css.getWebsite());
					list.add(model);
				}
			}
			return list;
		}
		return null;

	}

	private Map<String, WebsiteBO> getMapWebsiteBOFromListWebsiteBO(List<WebsiteBO> websites) {
		// 思考，这部分数据很少改变，可以考虑放入缓存
		if (websites != null && websites.size() > 0) {
			Map<String, WebsiteBO> map = new HashMap<String, WebsiteBO>();
			for (WebsiteBO b : websites) {
				if (b != null && b.getName() != null && b.getName().length() > 0) {
					if (!map.containsKey(b.getName())) {
						map.put(b.getName(), b);
					}
				}
			}
			return map;
		}
		return null;
	}

	private List<String> getScheduleQueueNemesNotSame(List<ScheduleQueue> list) {
		List<String> strArr = new ArrayList<String>(list.size());
		for (ScheduleQueue sc : list) {
			if (sc != null && sc.getName() != null && sc.getName().length() > 0) {
				if (!strArr.contains(sc)) {
					strArr.add(sc.getName());
				}
			}
		}
		return strArr;
	}
}
