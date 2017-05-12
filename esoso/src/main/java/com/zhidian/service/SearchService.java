package com.zhidian.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhidian.bases.ResourceEnumDefine;
import com.zhidian.bases.SearchEngineEnumDefine;
import com.zhidian.mapper.VersionMapper;
import com.zhidian.model.Version;
import com.zhidian.model.sys.ResultPageBO;
import com.zhidian.model.sys.ResultPageBO2;
import com.zhidian.model.sys.VersionBO;
import com.zhidian.util.BasicUtils;
import com.zhidian.util.RegExpUtils;
import com.zhidian.views.ResultPageVO;

// 并不关联其他service，只是对数据的处理
@Service
public class SearchService {
	@Autowired
	VersionMapper versionMapper;

	public ResultPageVO exchangeObjectOnlyPullPageModel(List<ResultPageBO> lists) {
		if (lists != null && lists.size() > 0) {
			ResultPageVO result = new ResultPageVO();
			Date d = new Date();
			result.setResults(dataHandler(lists));
			result.setCreTime(d);
			result.setOrigin(SearchEngineEnumDefine.From.源地址.getValue());
			result.setFrom(getFromNameFromResultPageBO(lists));
			result.setTags(getTagsFromResultPageBO(lists));

			String uuid = "" + d.getTime();// 暂定时间
			result.setId(uuid);// 当前页面的id

			// 内容加载文件
			result.setVersion(exchangeVersionBOfromVersion());
			// 推荐内容，需从推荐服务中获取
			result.setCommends(null);
			return result;
		}
		return null;
	}

	/**
	 * @Title: dataHandler @Description: TODO(对数据进行再处理) @param @param
	 *         lists @param @return 参数 @return List<ResultPageBO> 返回类型 @throws
	 */
	private List<ResultPageBO2> dataHandler(List<ResultPageBO> lists) {
		if (lists != null && lists.size() > 0) {
			List<ResultPageBO2> list = new ArrayList<ResultPageBO2>(lists.size());
			for (ResultPageBO b : lists) {
				// contents
				ResultPageBO2 r = new ResultPageBO2();
				r.setCreateTime(b.getCreateTime());
				r.setFrom(b.getFrom());
				r.setId(b.getId());
				r.setMark(b.getMark());
				r.setOriginUrl(b.getOriginUrl());
				r.setTags(b.getTags());
				r.setTitle(dataHandler39(b.getTitle()));
				r.setUrl(b.getUrl());
				// contents
				r.setContents(dataHandler246(b.getContents()));
				// views
				r.setView(dataHandlerView(b.getView()));
				// scores
				r.setScores(dataHandlerScore(b.getScores()));
				list.add(r);
			}
			return list;
		}
		return null;
	}

	public String handlerUrl(String prefix, String sort, String upOrdown) {
		if (prefix != null && StringUtils.isNotEmpty(sort)) {
			prefix = prefix + "&s=" + sort;
			if (StringUtils.isNotEmpty(upOrdown)) {
				prefix = prefix + "&s2=" + upOrdown;
			}
		}
		return prefix;
	}

	/**
	 * @Title: dataHandler142 @Description: TODO(数据截取) @param @param
	 *         contents @param @return 参数 @return String 返回类型 @throws
	 */
	private static String dataHandler246(String contents) {
		if (StringUtils.isNotEmpty(contents)) {
			if (contents.length() > 249) {
				contents = contents.substring(0, 246) + "...";
			}
		}
		return contents;
	}

	private static String dataHandlerView(int views) {
		if (views > 1000) {
			int i = views / 1000;
			return i + "k";
		}
		return "" + views;
	}

	private static String dataHandlerScore(double scores) {
		return String.format("%.2f", scores);// 可能会四舍五入
	}

	private static String dataHandler39(String contents) {
		if (StringUtils.isNotEmpty(contents)) {
			if (contents.length() > 42) {
				contents = contents.substring(0, 39) + "...";
			}
		}
		return contents;
	}

	private VersionBO exchangeVersionBOfromVersion() {
		Version version = versionMapper.queryVersionsForSearchService01SimpleVersion(
				ResourceEnumDefine.ResourceType.搜索结果页.getValue(), SearchEngineEnumDefine.Type.问答.getValue(),
				SearchEngineEnumDefine.Type.问答.getValue());
		if (version != null) {
			// id,name,version,type,defCss,defPage,defJs
			VersionBO v = new VersionBO();
			String versionId = BasicUtils.id2Version(version.getId());
			v.setId(version.getId());
			v.setDefPage("results/" + version.getName() + "/" + versionId + "/" + version.getDefPage());
			v.setName(version.getName());
			v.setType(version.getType());
			if (StringUtils.isNotEmpty(version.getDefCss())) {
				// 参考PageServuce getIndexCurrentPageVersionInfo
				v.setDefCss(RegExpUtils.convertString2List2(version.getDefCss(),
						"css/" + ResourceEnumDefine.ResourceType.搜索结果页.getValue() + "/" + version.getName() + "/"
								+ versionId + "/"));
			}
			if (StringUtils.isNotEmpty(version.getDefJs())) {
				v.setDefJs(RegExpUtils.convertString2List2(version.getDefJs(),
						"js/" + ResourceEnumDefine.ResourceType.搜索结果页.getValue() + "/" + version.getName() + "/"
								+ versionId + "/"));
			}
			return v;
		}
		return null;
	}

	private List<String> getFromNameFromResultPageBO(List<ResultPageBO> lists) {
		if (lists != null && lists.size() > 0) {
			List<String> fromName = new ArrayList<String>(lists.size());
			for (ResultPageBO r : lists) {
				if (r != null && r.getFrom() != null && r.getFrom().length() > 0) {
					if (!fromName.contains(r.getFrom())) {
						fromName.add(r.getFrom());
					}
				}
			}
			return fromName;
		}
		return null;
	}

	private List<String> getTagsFromResultPageBO(List<ResultPageBO> lists) {
		if (lists != null && lists.size() > 0) {
			List<String> tags = new ArrayList<String>(lists.size());
			for (ResultPageBO r : lists) {
				if (r != null && r.getTags() != null && r.getTags().length() > 0) {
					String[] strArr = r.getTags().split("\\|");
					if (strArr != null) {
						for (String str : strArr) {
							if (str != null && str.length() > 0 && !tags.contains(str)) {
								tags.add(r.getFrom());
							}
						}
					}
				}
			}
			return tags;
		}
		return null;
	}

	// 待放弃
	public ResultPageBO2 exchangeObjectOnlyPullPageModel2(List<ResultPageBO> lists, String sort, String upOrdown) {
		// 当爬虫之后，调用该处理器，处理排序问题。
		// 查询网站热度，排序数据
		// 没有定义筛选条件，就调用拼接器，处理数据
		if (lists != null && lists.size() > 0) {
			// 校验sort
			if (sort == null || "".equals(sort) || "hot".equals(sort)) {// 热度

			} else if ("time".equals(sort)) {// 时间

			} else if ("cviews".equals(sort)) {// 浏览量

			}

			// 校验upOrdown
			if (upOrdown != null && upOrdown.length() > 0 && "down".equals(upOrdown)) {

			} else {

			}

		}

		return null;
	}

	public ResultPageVO exchangeObjectPullPageModelFromDB(List<ResultPageBO> lists) {
		ResultPageVO result = new ResultPageVO();
		Date d = new Date();
		result.setResults(dataHandler(lists));
		result.setCreTime(d);
		result.setOrigin(SearchEngineEnumDefine.From.资源库.getValue());
		result.setFrom(getFromNameFromResultPageBO(lists));
		result.setTags(getTagsFromResultPageBO(lists));
		String uuid = "" + d.getTime();// 暂定时间
		result.setId(uuid);// 当前页面的id
		// 内容加载文件
		result.setVersion(exchangeVersionBOfromVersion());
		// 推荐内容，需从推荐服务中获取
		result.setCommends(null);
		return result;
	}

	public ResultPageVO exchangeObjectOnlyPullPageModelFromIndex(List<ResultPageBO> lists) {
		// TODO Auto-generated method stub
		return null;
	}

}
