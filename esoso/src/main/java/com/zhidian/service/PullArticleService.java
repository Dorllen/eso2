package com.zhidian.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhidian.bases.AppEnumDefine;
import com.zhidian.bases.ResourceEnumDefine;
import com.zhidian.bases.SearchEngineEnumDefine;
import com.zhidian.bases.SearchEngineEnumDefine.Sort;
import com.zhidian.bases.SearchEngineEnumDefine.UpOrDown;
import com.zhidian.mapper.PullArticleMapper;
import com.zhidian.mapper.ResultMapper;
import com.zhidian.mapper.ScheduleQueueMapper;
import com.zhidian.mapper.VersionMapper;
import com.zhidian.mapper.WormLogMapper;
import com.zhidian.model.Result;
import com.zhidian.model.ScheduleQueue;
import com.zhidian.model.WormLog;
import com.zhidian.model.sys.PullDataWatchObject;
import com.zhidian.model.sys.PullResultBO;
import com.zhidian.model.sys.PullResultPageModel;
import com.zhidian.model.sys.ResultPageBO;

@Service
public class PullArticleService {
	// private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	ResultMapper resultMapper;

	@Autowired
	VersionMapper versionMapper;

	@Autowired
	PullArticleMapper pullArticleMapper;

	@Autowired
	ScheduleQueueMapper scheduleQueueMapper;

	@Autowired
	WormLogMapper wormLogMapper;

	public List<String> analyseKeyWord(String key) {
		// 分词器

		// 从数据库对比分词获取

		return null;
	}

	public void collectKeyWord() {
		// 收集用户点击后的文章，记录其关键词
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@SuppressWarnings("unchecked")
	public void analyseSearchResultDiff(List<PullResultPageModel> list) {
		// 分析搜索结果，记录与以前差别(如果from有，需要另再设计，因为是来自筛选网站的)
		// 与上一个分析结果不同，在于这个是要进入页面分析文章指数，如有必要更新当前文章数据（相同数据可能更新），并且是后台加入运行队列
		Map<String, Object> map = getPullPageModelSubMapObject(list);
		List<PullResultBO> recLists = (List<PullResultBO>) map.get("PullResultBO");
		List<String> recFrom = (List<String>) map.get("From");
		List<String> recUuids = (List<String>) map.get("Uuids");
		if (recLists != null && recLists.size() > 0) {
			List<Result> resLists = resultMapper.queryResultsForPullArticleService02ListResult(recFrom, recUuids);// 这里可以考虑从索引服务器中比较
			if (resLists != null && resLists.size() > 0) {
				// 取出不存在DB的数据，没有存在的代表，来源已经有更新了。马上更新！
				List<PullResultBO> noExits = getNotExitsDB(resLists, recLists);
				if (noExits != null && noExits.size() > 0) {
					// 放入爬虫队列
					// 首先比较爬虫队列有对应的数据没处理的否？如果有，则不加入.进行筛选
					List<ScheduleQueue> exitSQ = scheduleQueueMapper
							.queryScheduleQueuesForPullArticleService01ListScheduleQueue(noExits);
					if (exitSQ != null && exitSQ.size() > 0) {
						noExits = filterExitScheduleNameAndUrl(noExits, exitSQ);
					}
					List<ScheduleQueue> qLists = createScheduleQueueObj(noExits);
					if (qLists != null && qLists.size() > 0) {
						scheduleQueueMapper.insertScheduleQueuesForPullArticleService01SimpleVoid(qLists);
					}
				}
				// 比较数据的差别
				// 分析有差别的数据? 這個比较是无效用的，所以放弃。因为结果显示页，只捕获标题，实际作用并不多。
			} else {
				// 数据库没有数据。搜索的结果都加入爬虫队列中
				// 首先比较爬虫队列有对应的数据没处理的否？如果有，则不加入
				List<ScheduleQueue> exitSQ = scheduleQueueMapper
						.queryScheduleQueuesForPullArticleService01ListScheduleQueue(recLists);
				if (exitSQ != null && exitSQ.size() > 0) {
					recLists = filterExitScheduleNameAndUrl(recLists, exitSQ);
				}
				List<ScheduleQueue> qLists = createScheduleQueueObj(recLists);
				if (qLists != null && qLists.size() > 0) {
					scheduleQueueMapper.insertScheduleQueuesForPullArticleService01SimpleVoid(qLists);
				}
			}
		}
		// 分析解析错误异常！
		List<PullDataWatchObject> watchers = (List<PullDataWatchObject>) map.get("PullDataWatchObject");// 分析顺便把解析出问题的站点记录下来！
		if (watchers != null && watchers.size() > 0) {
			List<WormLog> wormLogs = createWormLogsFromPullDataWatchObject(watchers);
			if (wormLogs != null && wormLogs.size() > 0) {
				// 从数据库检索出存在的
				List<WormLog> exitLog = wormLogMapper.selectWormLogsForPullArticleService01ListWormLog(wormLogs);
				// 求异，将存在去除
				if (exitLog != null && exitLog.size() > 0) {
					wormLogs = filterExitWormLogsNameAndUrl(wormLogs, exitLog);
				}
				// 置入剩余的数据
				wormLogMapper.insertWormLogsForPullArticleService01ListWormLog(wormLogs);
			}
		}
	}

	private List<WormLog> filterExitWormLogsNameAndUrl(List<WormLog> wormLogs, List<WormLog> exitLog) {
		if (wormLogs != null && wormLogs.size() > 0 && exitLog != null && exitLog.size() > 0) {
			List<WormLog> lists = new ArrayList<WormLog>(wormLogs.size());
			for (WormLog w : wormLogs) {
				if (w != null) {
					lists.add(w);
					for (WormLog z : exitLog) {
						if (z != null && z.getPropertyName().equals(w.getPropertyName())
								&& z.getWebsite().equals(w.getWebsite()) && z.getType().equals(w.getType())) {
							lists.remove(w);
							break;
						}
					}
				}
			}
			return lists;
		}
		return wormLogs;
	}

	private List<WormLog> createWormLogsFromPullDataWatchObject(List<PullDataWatchObject> watchers) {
		if (watchers != null && watchers.size() > 0) {
			List<WormLog> w1 = new ArrayList<WormLog>(watchers.size());
			WormLog w = null;
			for (PullDataWatchObject p : watchers) {
				if (p != null) {
					w = new WormLog();
					w.setSign(p.getSign());
					w.setFromType("在线爬虫... 请检查问题!");
					w.setPropertyName(p.getName());
					w.setStatus(2);
					w.setTriggerTime(p.getTimes());
					w.setType(p.getType());// 问题的类型
					w.setUrl(p.getUrl());
					w.setUuid(DigestUtils.md5Hex(p.getUrl()));
					w.setWebsite(p.getWebsite());
					w.setXpathContent(p.getXpathContent());
					w1.add(w);
				}
			}
			return w1;
		}
		return null;
	}

	/**
	 * @Title: filterExitScheduleNameAndUrl @Description:
	 *         TODO(筛选不存在exitSQ的PullResultBO) @param @param
	 *         noExits @param @param exitSQ @param @return 参数 @return
	 *         List<PullResultBO> 返回类型 @throws
	 */
	private List<PullResultBO> filterExitScheduleNameAndUrl(List<PullResultBO> noExits, List<ScheduleQueue> exitSQ) {
		if (exitSQ != null && exitSQ.size() > 0 && noExits != null && noExits.size() > 0) {
			List<PullResultBO> results = new ArrayList<PullResultBO>(noExits.size());
			for (PullResultBO p : noExits) {
				if (p != null) {
					results.add(p);
					for (ScheduleQueue s : exitSQ) {
						if (s != null) {
							if (s.getName().equals(p.getName()) && s.getUrl().equals(p.getUrl())) {
								if (results.contains(p)) {
									results.remove(p);
								}
							}
						}
					}
				}
			}
			return results;
		}
		return noExits;
	}

	private List<ScheduleQueue> createScheduleQueueObj(List<PullResultBO> bos) {
		if (bos != null && bos.size() > 0) {
			List<ScheduleQueue> queues = new ArrayList<ScheduleQueue>();
			for (PullResultBO r : bos) {
				if (r != null) {
					ScheduleQueue queue = new ScheduleQueue();
					queue.setCreateMan(AppEnumDefine.AppUser.系统.getValue());
					// queue.setCreateTime(new Date());//
					// 时间采用了数据库的入库时间，即数据库内置函数【需注意】
					queue.setName(r.getName());
					queue.setType(AppEnumDefine.ScheduleQueuesType.系统自增.getValue());
					queue.setType2(SearchEngineEnumDefine.Type.问答.getValue());// 默认是搜索引擎的answer类型
					queue.setType3(ResourceEnumDefine.ResourceType.内容详情页.getValue());// 爬虫页面的类型
					queue.setUrl(r.getUrl());
					queue.setUuid(r.getUuid());
					queues.add(queue);
				}
			}
			return queues;
		}
		return null;
	}

	private List<PullResultBO> getNotExitsDB(List<Result> dbLists, List<PullResultBO> lists) {
		if (dbLists != null && lists != null && dbLists.size() > 0 && lists.size() > 0) {
			List<PullResultBO> noExits = new ArrayList<PullResultBO>();
			for (PullResultBO b : lists) {
				if (b != null) {
					for (Result r : dbLists) {
						if (r != null) {
							if (r.getUuid() != null && r.getUuid().equals(b.getUuid())) {
								if (noExits.contains(b)) {
									// 将值移除。
									noExits.remove(b);
								}
								break;
							} else if (r.getUuid() != null && b.getUuid() != null && !r.getUuid().equals(b.getUuid())) {
								// 入队
								if (noExits.contains(b)) {
									continue;
								}
								noExits.add(b);
							}
						}
					}
				}
			}
			return noExits;
		}
		return null;
	}

	private Map<String, Object> getPullPageModelSubMapObject(List<PullResultPageModel> list) {
		if (list != null && list.size() > 0) {
			List<PullResultBO> recList = new ArrayList<PullResultBO>();
			List<String> recFrom = new ArrayList<String>();
			List<String> recUuids = new ArrayList<String>();
			List<PullDataWatchObject> watchers = new ArrayList<PullDataWatchObject>();
			for (PullResultPageModel l : list) {
				if (l != null && l.getResults() != null && l.getResults().size() > 0) {
					for (PullResultBO p : l.getResults()) {
						if (p != null) {
							recList.add(p);
							if (!recFrom.contains(p.getName())) {
								recFrom.add(p.getName());
							}
							if (!recUuids.contains(p.getUuid())) {
								recUuids.add(p.getUuid());
							}
						}
					}
				}
				if (l != null && l.getErrorWatcher() != null && l.getErrorWatcher().size() > 0) {
					watchers.addAll(l.getErrorWatcher());
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("PullResultBO", recList);
			map.put("From", recFrom);
			map.put("Uuids", recUuids);
			map.put("PullDataWatchObject", watchers);
			return map;
		}
		return null;
	}

	public List<ResultPageBO> getHandlerResultsWithUsing(List<PullResultPageModel> list, String sort, String upOrdown) {
		// 该条数据补全
		if (list != null && list.size() > 0) {
			List<ResultPageBO> lists = new ArrayList<ResultPageBO>(list.size());
			List<String> uuidList = getUUIDListFromList(list);
			// 从数据库获取数据
			List<Result> resLists = resultMapper.queryResultsForPullArticleService01ListResult(uuidList);
			for (PullResultPageModel pull : list) {
				// 装载数据，补全
				for (PullResultBO pBo : pull.getResults()) {
					if (pBo != null && pBo.getUuid() != null && pBo.getUuid().length() > 0) {
						if (StringUtils.isEmpty(pBo.getUuid())) {
							continue;
						}
						ResultPageBO rBo = new ResultPageBO();
						rBo.setContents(pBo.getContent());
						rBo.setTitle(pBo.getTitle());
						rBo.setId(pBo.getUuid());
						rBo.setFrom(pBo.getName());
						rBo.setCreateTime(pBo.getDate());
						rBo.setUrl("/pa/" + pBo.getUuid());
						if (resLists != null && resLists.size() > 0) {
							for (Result r : resLists) {
								if (pBo.getUuid().equals(r.getUuid())) {
									rBo.setCreateTime(r.getNewTime());
									rBo.setScores(r.getScroes());
									rBo.setView(r.getViews());
									rBo.setTags(r.getTags());
									rBo.setMark(r.getMark());
									if (r.getUsing() > 0) {
										continue;
									}
								}
							}
						}
						lists.add(rBo);
					}
				}
			}
			if (SearchEngineEnumDefine.UpOrDown.升序.getValue().equals(upOrdown)) {
				// "up"
				lists.sort(new Comparator<ResultPageBO>() {
					public int compare(ResultPageBO o1, ResultPageBO o2) {
						if (o1 != null && o2 != null) {
							if (SearchEngineEnumDefine.Sort.时间.getValue().equals(o1.getMark())
									&& o1.getMark().equals(o2.getMark())) {
								if (o1.getCreateTime() != null) {
									if (o2.getCreateTime() != null) {
										return (int) (o1.getCreateTime().getTime() - o2.getCreateTime().getTime());
									} else {
										return 1;
									}
								} else if (o2.getCreateTime() != null) {
									return -1;
								}
								return 0;
							} else if (SearchEngineEnumDefine.Sort.浏览量.getValue().equals(o1.getMark())
									&& o1.getMark().equals(o2.getMark())) {
								return o1.getView() - o2.getView();
							} else if (SearchEngineEnumDefine.Sort.热度.getValue().equals(o1.getMark())
									&& o1.getMark().equals(o2.getMark())) {
								return (int) (o1.getScores() - o2.getScores());
							}
						}
						return 0;
					}
				});
			} else if (SearchEngineEnumDefine.UpOrDown.降序.getValue().equals(upOrdown)) {
				// "down"
				lists.sort(new Comparator<ResultPageBO>() {
					public int compare(ResultPageBO o1, ResultPageBO o2) {
						if (o1 != null && o2 != null) {
							if (SearchEngineEnumDefine.Sort.时间.getValue().equals(o1.getMark())
									&& o1.getMark().equals(o2.getMark())) {
								if (o1.getCreateTime() != null) {
									if (o2.getCreateTime() != null) {
										return (int) (o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
									} else {
										return 1;
									}
								} else if (o2.getCreateTime() != null) {
									return -1;
								}
								return 0;
							} else if (SearchEngineEnumDefine.Sort.浏览量.getValue().equals(o1.getMark())
									&& o1.getMark().equals(o2.getMark())) {
								return o2.getView() - o1.getView();
							} else if (SearchEngineEnumDefine.Sort.热度.getValue().equals(o1.getMark())
									&& o1.getMark().equals(o2.getMark())) {
								return (int) (o2.getScores() - o1.getScores());
							}
						}
						return 0;
					}
				});
			}
			return lists;
		}
		return null;
	}

	public List<ResultPageBO> getHandlerResults(List<PullResultPageModel> list, String sort, String upOrdown) {
		// 该条数据补全
		if (list != null && list.size() > 0) {
			List<ResultPageBO> lists = new ArrayList<ResultPageBO>(list.size());
			List<String> uuidList = getUUIDListFromList(list);
			// 从数据库获取数据
			List<Result> resLists = resultMapper.queryResultsForPullArticleService01ListResult(uuidList);
			for (PullResultPageModel pull : list) {
				// 装载数据，补全
				for (PullResultBO pBo : pull.getResults()) {
					if (pBo != null && pBo.getUuid() != null && pBo.getUuid().length() > 0) {
						if (StringUtils.isEmpty(pBo.getUuid())) {
							continue;
						}
						ResultPageBO rBo = new ResultPageBO();
						rBo.setContents(pBo.getContent());
						rBo.setTitle(pBo.getTitle());
						rBo.setId(pBo.getUuid());
						rBo.setFrom(pBo.getName());
						rBo.setCreateTime(pBo.getDate());
						rBo.setUrl("/pa/" + pBo.getUuid());
						rBo.setOriginUrl(pBo.getUrl());
						if (resLists != null && resLists.size() > 0) {
							for (Result r : resLists) {
								if (pBo.getUuid().equals(r.getUuid())) {
									rBo.setCreateTime(r.getNewTime());
									rBo.setScores(r.getScroes());
									rBo.setView(r.getViews());
									rBo.setTags(r.getTags());
									rBo.setMark(r.getMark());
								}
							}
						}
						lists.add(rBo);
					}
				}
			}
			if (SearchEngineEnumDefine.UpOrDown.升序.getValue().equals(upOrdown)) {
				// "up"
				lists.sort(new Comparator<ResultPageBO>() {
					public int compare(ResultPageBO o1, ResultPageBO o2) {
						if (o1 != null && o2 != null) {
							if (SearchEngineEnumDefine.Sort.时间.getValue().equals(o1.getMark())
									&& o1.getMark().equals(o2.getMark())) {
								if (o1.getCreateTime() != null) {
									if (o2.getCreateTime() != null) {
										return (int) (o1.getCreateTime().getTime() - o2.getCreateTime().getTime());
									} else {
										return 1;
									}
								} else if (o2.getCreateTime() != null) {
									return -1;
								}
								return 0;
							} else if (SearchEngineEnumDefine.Sort.浏览量.getValue().equals(o1.getMark())
									&& o1.getMark().equals(o2.getMark())) {
								return o1.getView() - o2.getView();
							} else if (SearchEngineEnumDefine.Sort.热度.getValue().equals(o1.getMark())
									&& o1.getMark().equals(o2.getMark())) {
								return (int) (o1.getScores() - o2.getScores());
							}
						}
						return 0;
					}
				});
			} else if (SearchEngineEnumDefine.UpOrDown.降序.getValue().equals(upOrdown)) {
				// "down"
				lists.sort(new Comparator<ResultPageBO>() {
					public int compare(ResultPageBO o1, ResultPageBO o2) {
						if (o1 != null && o2 != null) {
							if (SearchEngineEnumDefine.Sort.时间.getValue().equals(o1.getMark())
									&& o1.getMark().equals(o2.getMark())) {
								if (o1.getCreateTime() != null) {
									if (o2.getCreateTime() != null) {
										return (int) (o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
									} else {
										return 1;
									}
								} else if (o2.getCreateTime() != null) {
									return -1;
								}
								return 0;
							} else if (SearchEngineEnumDefine.Sort.浏览量.getValue().equals(o1.getMark())
									&& o1.getMark().equals(o2.getMark())) {
								return o2.getView() - o1.getView();
							} else if (SearchEngineEnumDefine.Sort.热度.getValue().equals(o1.getMark())
									&& o1.getMark().equals(o2.getMark())) {
								return (int) (o2.getScores() - o1.getScores());
							}
						}
						return 0;
					}
				});
			}
			return lists;
		}
		return null;
	}

	public List<ResultPageBO> getHandlerResultsForIndexCache(List<ResultPageBO> lists) {

		return null;
	}

	private List<String> getUUIDListFromList(List<PullResultPageModel> list) {
		List<String> strList = new ArrayList<String>();
		for (PullResultPageModel pull : list) {
			List<PullResultBO> lists = pull.getResults();
			if (lists != null && lists.size() > 0) {
				for (PullResultBO pBo : lists) {// pBO必选在数据爬取处校验不为null
					String str = pBo.getUuid();
					if (str != null && str.length() > 0) {// 基本也不会为空，除非异常了
						strList.add(str);
					}
				}
			}
		}
		return strList;
	}

	public List<ResultPageBO> getResultsByIndexCache(List<String> keyLists, String key, List<String> from) {
		return getResultsByIndexCache(keyLists, from, 1, 20, Sort.热度.getValue(), UpOrDown.升序.getValue());
	}

	public List<ResultPageBO> getResultsByIndexCache(List<String> keyLists, List<String> from, Integer page,
			Integer size) {
		return getResultsByIndexCache(keyLists, from, page, size, Sort.热度.getValue(), UpOrDown.升序.getValue());
	}

	public List<ResultPageBO> getResultsByIndexCache(List<String> keyLists, List<String> from, Integer page,
			Integer size, String sort, String upOrdown) {

		return null;
	}

	public List<ResultPageBO> getResultsByDBWays(List<String> keyLists, List<String> from) {
		return getResultsByDBWays(keyLists, from, 1, 20, Sort.热度.getValue(), UpOrDown.升序.getValue());
	}

	public List<ResultPageBO> getResultsByDBWays(List<String> keyLists, List<String> from, Integer page, Integer size) {
		return getResultsByDBWays(keyLists, from, page, size, Sort.热度.getValue(), UpOrDown.升序.getValue());
	}

	public List<ResultPageBO> getResultsByDBWays(List<String> keyLists, List<String> from, Integer page, Integer size,
			String sort, String upOrdown) {

		return null;
	}

}
