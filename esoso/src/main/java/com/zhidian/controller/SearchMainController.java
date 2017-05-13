package com.zhidian.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zhidian.bases.SearchEngineEnumDefine;
import com.zhidian.bases.worm.WormsService;
import com.zhidian.model.sys.PullResultPageModel;
import com.zhidian.model.sys.ResultPageBO;
import com.zhidian.service.PullArticleService;
import com.zhidian.service.SearchService;
import com.zhidian.util.RegExpUtils;
import com.zhidian.views.ResultPageVO;

/**
 * @ClassName: MainSearchController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-19 下午8:15:39
 * 
 */
@Controller
@RequestMapping("/s")
public class SearchMainController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	WormsService wormsService;

	@Autowired
	PullArticleService articleService;

	@Autowired
	SearchService searchService;

	// 搜索服务,这是直接访问链接，不是json数据接口！
	@GetMapping(value = { "", "/" })
	// q代表查询内容，t代表筛选类型，o代表搜寻类型，f代表查询筛选网站，d代表用戶定义查询网站（需检查数据库）
	public String index(@RequestParam(value = "q", required = false) String key,
			@RequestParam(value = "t", required = false, defaultValue = "answer") String type, // 搜索类型，answer,blog..
			@RequestParam(value = "o", required = false, defaultValue = "db") String origin, // 从资源库or来源
			// @RequestParam(value = "size", required = false) Integer size, //
			// 页数
			@RequestParam(value = "page", required = false) Integer page, // 页号
			@RequestParam(value = "d", required = false) String design, // 用户定义搜索来源
			@RequestParam(value = "s", required = false, defaultValue = "hot") String sort, // 排序方式
			@RequestParam(value = "s2", required = false, defaultValue = "up") String upOrdown, // 增or减
			@RequestParam(value = "f", required = false, defaultValue = "segmentfault") String from_, // 搜索的来源
			@RequestParam(value = "t2", required = false) String time, // 时间戳.【待完善，让收藏更方便】
			Model model) {
		long start = System.currentTimeMillis();
		try {
			key  = new String(key.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "redirect:null";
		}
		if (!StringUtils.isEmpty(key)) {
			// ResultPageDTO => ResultPageVO . 同时要连通数据库，找出当前用户的信息，如：收藏，访问量
			if (SearchEngineEnumDefine.Type.问答.getValue().equals(type)) {
				ResultPageVO result = null;
				List<ResultPageBO> lists = null;
				String emptyMessage = "";
				// 合并from与design
				List<String> from = RegExpUtils.convertString2List(from_);
				if (SearchEngineEnumDefine.From.源地址.getValue().equals(origin)) {
					// 如果从来源，就直接在线获取数据，处理之后返回
					// from 格式: segmentfault|cnblog|ibmdeveloper
					// 如果用户筛选了条件，即定义了搜索的来源。
					List<PullResultPageModel> list_ = wormsService.getResultsByOnlineSearch(key, from, page, 20);
					lists = articleService.getHandlerResults(list_, sort, upOrdown);
					// 分析list，分析与以前爬虫结果的差别
					articleService.analyseSearchResultDiff(list_);
					// 查询网站热度，排序数据
					// 没有定义筛选条件，就调用拼接器，处理数据
					result = searchService.exchangeObjectOnlyPullPageModel(lists);
				} else {
					lists = articleService.getResultsByDBWays(key, from, page, 20, sort, upOrdown);
					result = searchService.exchangeObjectPullPageModelFromDB(lists);
//					Date gTime = null;
//					try {
//						long timez = new Long(time);
//						gTime = new Date(timez);
//					} catch (Exception e) {
//						log.info("时间转换有问题，传值有误! 快照方式访问取消!");
//						e.printStackTrace();
//					}
//					// 关键词分词
//					List<String> keyLists = articleService.analyseKeyWord(key);
//					// 获取数据：从索引或数据库
//					if (gTime != null) {
//						// 用时间去数据库查找
//
//					} else {
//						lists = articleService.getResultsByIndexCache(keyLists, from, page, 20, sort, upOrdown);
//					}
//					if (lists == null || lists.size() == 0) {
//						// 当索引服务中为空，就去数据库查找
//						if (gTime != null) {
//							// 用时间去数据库查找
//						} else {
//							lists = articleService.getResultsByDBWays(keyLists, from, page, 20, sort, upOrdown);
//						}
//						if (lists != null && lists.size() > 0) {
//							result = searchService.exchangeObjectPullPageModelFromDB(lists);
//						} else {
//							// DB也没有数据。.
//							emptyMessage = "资源库未有收录站点的关键搜索，赶快切换搜索类型吧! ";
//							log.info("IndeDB and DB without data!!! key:{} from:{}", key, from);
//						}
//					} else {
//						lists = articleService.getHandlerResultsForIndexCache(lists);
//						result = searchService.exchangeObjectOnlyPullPageModelFromIndex(lists);
//					}
				}
				if (result == null) {
					result = new ResultPageVO();
				}
				result.setFrom_(from_);
				result.setDesign(design);
				result.setDefaultWebsites(SearchEngineEnumDefine.Type.问答.getDefaultWebsites());
				result.setDefaults(SearchEngineEnumDefine.Type.问答.getDefaults());
				result.setType(SearchEngineEnumDefine.Type.问答.getValue());
				result.setKey(key);
				result.setPlaceholder(SearchEngineEnumDefine.Type.问答.getPlaceHolder());
				result.setPage(page == null || page == 0 ? 1 : page);
				result.setUrl(searchService.handlerUrl(
						"t=" + result.getType() + "&o=" + result.getOrigin() + "&q=" + key + "&f=" + from_, sort,
						upOrdown));
				if (StringUtils.isNotEmpty(result.getOrigin())
						&& SearchEngineEnumDefine.From.源地址.getValue().equals(result.getOrigin())) {
					result.setLastPage(result.getPage() + 1);
				}
				
				if (result.getResults() != null && result.getResults().size() > 0) {
					// 制作模板，放入数据
					model.addAttribute("Message", result);
				} else {
					// 获取的数据是空的，告诉页面未找到结果！
					if (StringUtils.isEmpty(emptyMessage)) {
						emptyMessage = "未找到数据，请您重试! 或反馈我们!";
					}
					model.addAttribute("Message", result);
					model.addAttribute("EmptyMessage", emptyMessage);
				}
				String url = result.getVersion().getDefPage();
				if (StringUtils.isNotEmpty(url)) {
					log.info("SearchMainController mapping : s,s/; and tag is 0 , spend time is -> {}",
							(System.currentTimeMillis() - start) / 1000);
					return url;// results/segmentfault/0.0.0.0/index
				}
			} else if (SearchEngineEnumDefine.Type.博客.getValue().equals(type)) {
				log.warn("博客搜索服务暂未开启");
			}
		} else {
			// 提示 请输入关键词...
			model.addAttribute("Data", "请输入搜索内容!");
		}
		log.info("SearchMainController mapping : s,s/; and tag is last , spend time is -> {}",
				(System.currentTimeMillis() - start) / 1000);
		return "error/404";
	}

	// 显示搜索页结果
	@GetMapping("/j")
	@ResponseBody
	public Object json(@RequestParam(value = "f", required = false, defaultValue = "segmentfault") String from,
			@RequestParam("q") String query) {
		// 直接开始爬取
		// return wormsService.pullOnlineData(from, query);
		return null;
	}

	// 多线程爬虫显示搜索页结果
	@GetMapping("/json2")
	@ResponseBody
	public Object json2(@RequestParam(value = "f", required = false, defaultValue = "segmentfault") String from,
			@RequestParam("q") String query) {
		// 直接开始爬取
		// return wormsService.pullOnlineDataWithThreading(from, query);
		return null;
	}
	
	@GetMapping("/null")
	@ResponseBody
	public Object getNullInfor(){
		return null;
	}
}
