package com.zhidian.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zhidian.bases.AppEnumDefine;
import com.zhidian.bases.ResourceEnumDefine;
import com.zhidian.bases.SearchEngineEnumDefine;
import com.zhidian.bases.SearchEngineEnumDefine.Type;
import com.zhidian.mapper.GlobalInfoMapper;
import com.zhidian.mapper.PullArticleMapper;
import com.zhidian.mapper.VersionMapper;
import com.zhidian.mapper.WebsiteMapper;
import com.zhidian.model.PullArticle;
import com.zhidian.model.Version;
import com.zhidian.model.Website;
import com.zhidian.model.sys.VersionBO;
import com.zhidian.util.RegExpUtils;
import com.zhidian.views.FormBarDTO;
import com.zhidian.views.IndexPageVO;
import com.zhidian.views.PullArticlePageVO;

/**
 * @ClassName: PageService
 * @Description: TODO(专门用作页面版本更新的服务器)
 * @author dongneng
 * @date 2017年4月22日 下午10:23:16
 *
 */
@Service
public class PageService {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	VersionMapper versionMapper;

	@Autowired
	WebsiteMapper websiteMapper;

	@Autowired
	PullArticleMapper pullArticleMapper;

	@Autowired
	GlobalInfoMapper globalInfoMapper;

	public IndexPageVO getIndexCurrentPageInfo() {
		IndexPageVO index = new IndexPageVO();
		index.setVersion(getIndexCurrentPageVersionInfo());
		index.setForm(getDefaultFormBarList());
		index.setDefaults(getDefaultFormBar());
		index.setForms(JSON.toJSONString(index.getForm()));
		return index;
	}

	private List<FormBarDTO> getDefaultFormBarList() {
		List<FormBarDTO> forms = new ArrayList<FormBarDTO>();
		Type[] types = SearchEngineEnumDefine.Type.values();
		for (Type t : types) {
			FormBarDTO f = new FormBarDTO();
			f.setFrom(t.getDefaults());
			f.setDefaults(t.getDefaults());
			f.setDefaultWebsites(t.getDefaultWebsites());
			f.setPlaceholder(t.getPlaceHolder());
			f.setType(t.getValue());
			forms.add(f);
		}
		return forms;
	}

	private FormBarDTO getDefaultFormBar() {
		FormBarDTO form = new FormBarDTO();
		Type type = SearchEngineEnumDefine.Type.问答;
		form.setFrom(type.getDefaults());
		form.setDefaults(type.getDefaults());
		form.setDefaultWebsites(type.getDefaultWebsites());
		form.setPlaceholder(type.getPlaceHolder());
		form.setType(type.getValue());
		return form;
	}

	private VersionBO getIndexCurrentPageVersionInfo() {
		Version version = versionMapper.queryVersionsForPageService01SimpleVersion(
				AppEnumDefine.SiteService.搜索.getValue(), ResourceEnumDefine.ResourceType.主页.getValue());
		if (version != null) {
			// id,name,version,type,defCss,defPage,defJs
			VersionBO v = new VersionBO();
			v.setId(version.getId());
			v.setDefPage(version.getDefPage());
			v.setName(version.getName());
			v.setType(version.getType());
			if (StringUtils.isNotEmpty(version.getDefCss())) {
				v.setDefCss(RegExpUtils.convertString2List2(version.getDefCss()));
			}
			if (StringUtils.isNotEmpty(version.getDefJs())) {
				v.setDefJs(RegExpUtils.convertString2List2(version.getDefJs()));
			}
			return v;
		}
		return null;
	}

	public PullArticlePageVO getPullArticle(String uuid) {
		PullArticle article = pullArticleMapper.queryPullArticlesForPullArticleService01SimplePullArticle(uuid);
		PullArticlePageVO pull = null;
		if (article != null) {
			// 数据检查
			List<String> css = RegExpUtils.convertString2List2(article.getCssPath());
			List<String> js = RegExpUtils.convertString2List2(article.getJsPath());
			String page = article.getPagePath();
			Version version = null;
			if (css == null || css.size() == 0 || js == null || js.size() == 0 || StringUtils.isEmpty(page)) {
				// css为空
				version = versionMapper.queryVersionsForPullArticleService01SimpleVersion(
						AppEnumDefine.SiteService.搜索.getValue(), article.getName(), article.getVersion());
				if (version == null) {
					log.warn("PullArticlePageVO无法获取，需处理异常....未找到PullArticle的版本信息，连默认版本都没有！ uuid -> {}",
							article.getUuid());
					return pull;
				}
				if (css == null || css.size() == 0) {
					article.setCssPath(version.getDefCss());
				}

				if (js == null || js.size() == 0) {
					// js为空
					article.setJsPath(version.getDefJs());
				}
				if (StringUtils.isEmpty(page)) {
					// page为空
					article.setPagePath(version.getDefPage());
				}
			}
			pull = new PullArticlePageVO();
			if (StringUtils.isNotEmpty(article.getContents())) {
				// 从websites表去获取对象信息，进行Class装载
				Website web = websiteMapper.queryWebsitesForPageService01SimpleWebsite(
						ResourceEnumDefine.ResourceType.内容详情页.getValue(), article.getName(), article.getVersion());
				if (web != null) {
					String receiveObj = web.getPageRObject();
					if (StringUtils.isNotEmpty(receiveObj)) {
						try {
							Class<?> clazz = Class.forName(receiveObj);
							if (clazz != null) {
								log.info("Contents Object Size is : " + article.getContents().length());
								Object obj = JSON.parseObject(article.getContents(), clazz);
								pull.setContents(obj);
							}
						} catch (ClassNotFoundException e) {
							log.info("内容详情页的接收对象装载失败.... 请检查...");
							e.printStackTrace();
						}
					}
				}
			}
			pull.setCssPaths(css);
			pull.setJsPaths(js);
			pull.setUrl(page);
			pull.setTitle(article.getTitle());
			pull.setUuid(article.getUuid());
			pull.setName(article.getName());
			if(article.getStartTime()!=null){
				pull.setTime(new SimpleDateFormat("yyyy-MM-dd").format(article.getStartTime()));
			}
			if(StringUtils.isNotEmpty(pull.getName())){
				pull.setFrom(pull.getName()+"网站");
			}
		}
		return pull;
	}

	public PullArticlePageVO getPullArticleBottomInfo(PullArticlePageVO result) {
		if (result != null) {
			// 可以是自动生成的，也可以是管理员写死，现在是管理员写死
			String info = globalInfoMapper.selectGlobalInfoForPageService01SimpleString(
					ResourceEnumDefine.ResourceType.内容详情页.getValue(), result.getName());
			result.setBottomInfo(info);
		}
		return result;
	}

}
