package com.zhidian.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zhidian.bases.AppEnumDefine;
import com.zhidian.mapper.PullArticleMapper;
import com.zhidian.mapper.VersionMapper;
import com.zhidian.mapper.WebsiteMapper;
import com.zhidian.model.Version;
import com.zhidian.model.Website;
import com.zhidian.model.sys.WebsiteBO2;
import com.zhidian.views.WebsitePageVO;
import com.zhidian.views.WebsitePostModel;
import com.zhidian.views.WebsitePostModel2;
import com.zhidian.views.WormSettingsSearchResultVO;

/**
 * @ClassName: DataInfoService
 * @Description: TODO(用于页面的表格，图形的数据获取。使用对象管理员)
 * @author dongneng
 * @date 2017年5月2日 下午2:25:19
 *
 */
@Service
public class DataInfoAdminService {

	@Autowired
	PullArticleMapper pullArticleMapper;

	@Autowired
	WebsiteMapper websiteMapper;

	@Autowired
	VersionMapper versionMapper;

	public List<WormSettingsSearchResultVO> getWebsiteVersionListByName(String name) {
		if (StringUtils.isNotEmpty(name)) {
			List<Website> webList = websiteMapper.queryWebsitesForDataInfoAdminService01ListWebsite(name);
			System.out.println(JSON.toJSONString(webList));
			if (webList != null && webList.size() > 0) {
				List<WormSettingsSearchResultVO> list = new ArrayList<WormSettingsSearchResultVO>(webList.size());
				for (Website w : webList) {
					if (w != null) {
						WormSettingsSearchResultVO v = createWormSettingsSearchResultVOFronWebsite(w);
						list.add(v);
					}
				}
				return list;
			}
		}
		return null;
	}

	private WormSettingsSearchResultVO createWormSettingsSearchResultVOFronWebsite(Website w) {
		if (w != null) {
			WormSettingsSearchResultVO v = new WormSettingsSearchResultVO();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if (w.getCreateTime() != null) {
				v.setCreateTime(sdf.format(w.getCreateTime()));
			}
			if (w.getUpdateTime() != null) {
				v.setUpdateTime(sdf.format(w.getUpdateTime()));
			}
			v.setCreateMan(w.getCreateMan());
			v.setUpdateMan(w.getUpdateMan());
			if (w.getUsing() == 0) {
				v.setUsing(false);
			} else {
				v.setUsing(true);
			}
			v.setName(w.getName());
			v.setVersion(""+w.getVersionId());
			v.setSign(w.getSign());
			v.setId(String.valueOf(w.getId()));
			return v;
		}
		return null;
	}

	public WebsitePageVO getWebsiteInforDetailByNameAndId(Integer id, String name) {
		if (id != null && id > 0 && StringUtils.isNotEmpty(name)) {
			Website web = websiteMapper.queryWebsitesForDataInfoAdminService01Website(id, name);
			if (web != null) {
				return createWebsitePageVOFromWebsite(web);
			}
		}
		return null;
	}

	private WebsitePageVO createWebsitePageVOFromWebsite(Website w) {
		if (w != null) {
			WebsitePageVO v = new WebsitePageVO();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			v.setId(String.valueOf(w.getId()));
			v.setAlias(w.getAlias());
			v.setCreateMan(v.getCreateMan());
			if (w.getCreateTime() != null) {
				v.setCreateTime(sdf.format(w.getCreateTime()));
			}
			v.setDefaultPageCss(w.getDefaultPageCss());
			v.setDefPageConfig(w.getDefPageConfig());
			v.setDefPageCss(w.getDefPageCss());
			v.setDefRequestHeader(w.getDefRequestHeader());
			v.setDefResultConfig(w.getDefResultConfig());
			v.setFullAddr(w.getFullAddr());
			v.setName(w.getName());
			v.setPagePipeline(w.getPagePipeline());
			v.setPageProcessor(w.getPageProcessor());
			v.setPageRObject(w.getPageRObject());
			v.setPagination(w.getPagination());
			v.setResultPipeline(w.getResultPipeline());
			v.setResultProcessor(w.getResultProcessor());
			v.setResultRObject(w.getResultRObject());
			v.setSearchAddr(w.getSearchAddr());
			v.setShortAddr(w.getShortAddr());
			v.setSign(w.getSign());
			v.setType(w.getType());
			v.setUnuseMan(w.getUnuseMan());
			if (w.getUnuseTime() != null) {
				v.setUnuseTime(sdf.format(w.getUnuseTime()));
			}
			v.setUpdateMan(w.getUpdateMan());
			if (w.getUpdateTime() != null) {
				v.setUpdateTime(sdf.format(w.getUpdateTime()));
			}
			v.setUseSearch(w.isUseSearch());
			v.setUsing(w.getUsing() == 0 ? false : true);
			v.setVersion(""+w.getVersionId());
			return v;
		}
		return null;
	}

	public void updateWebsiteFromPostObject(WebsitePostModel model, String account) {
		WebsiteBO2 w = createWebsiteBO2FromWebsitePostModel(model, account);
		if (w != null) {
			websiteMapper.updateWebsitesForDataInfoAdminService01SmpleWebsiteBO2(w);// 更新数据库
		}
	}

	private WebsiteBO2 createWebsiteBO2FromWebsitePostModel(WebsitePostModel m, String account) {
		if (m != null) {
			WebsiteBO2 w = new WebsiteBO2();
			w.setDefRequestHeader(m.getDefRequestHeader());
			w.setDefResultConfig(m.getDefResultConfig());
			w.setId(Integer.parseInt(m.getId()));
			w.setName(m.getName());
			w.setPagePipeline(m.getPagination());
			w.setPageProcessor(m.getPageProcessor());
			w.setPageRObject(m.getPageRObject());
			w.setSearchAddr(m.getSearchAddr());
			w.setSign(m.getSign());
			w.setUseSearch(m.isUseSearch());
			w.setUpdateMan(account);
			w.setUpdateTime(new Date());
			return w;
		}
		return null;
	}

	public void setWebisteDefaultUsing(String id, String name) {
		if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(name)) {
			websiteMapper.updateWebsitesForDataInfoAdminService01SimpleWebsite(id, name);
		}
	}

	/**
	 * @Title: addNewWebsite @Description: TODO(增加一個新站點的版本信息) @param @param
	 *         mdoel 参数 @return void 返回类型 @throws
	 */
	@Transactional
	public int addNewWebsite(WebsitePostModel2 model, String account) {
		if (model != null) {
			Version version = versionMapper.queryVersionsForDataInfoAdminService01SimpleVersion(model.getName());
			if (version != null) {// 如果版本库不存在version，则说明该站点不存在，则不允许创建
				Website w = createWebsiteFromWebsitePostModel2(model, account);
				if (w != null) {
					if (model.isCheck1()) {
						// 设置为默认版本.即当前站点默认使用版本
						w.setUsing(1);
						if (model.isCheck2()) {
							// 使用默认版的css,就不将version的defcss放入
							w.setVersionId(version.getId());
							websiteMapper.insertWebsitesForDataInfoAdminService01SimpleWebsite(w);// 将其他的website制为using=0
							if (w.getId() > 0) {
								websiteMapper.updateWebsitesForDataInfoAdminService01SimpleWebsite("" + w.getId(),
										w.getName());
							}
						} else {
							w.setVersionId(model.getCheck2Version());// 获得默认version,确定version是否存在数据库中
							w.setDefPageCss(model.getDefPageCss());
							websiteMapper.insertWebsitesForDataInfoAdminService02SimpleWebsite(w);// 将其他的website置为using=0,并且将model.getVersion验证
							if (w.getId() > 0) {
								websiteMapper.updateWebsitesForDataInfoAdminService01SimpleWebsite("" + w.getId(),
										w.getName());
							}
						}
					} else {
						w.setUsing(0);// 第一次不自动使用，需再设置
						if (model.isCheck2()) {
							// 使用默认版的css
							w.setVersionId(version.getId());
							websiteMapper.insertWebsitesForDataInfoAdminService01SimpleWebsite(w);
						} else {
							w.setVersionId(model.getCheck2Version());// 获得默认version,确定version是否存在数据库中
							w.setDefPageCss(model.getDefPageCss());
							websiteMapper.insertWebsitesForDataInfoAdminService02SimpleWebsite(w);
						}
					}
					return 1;
				}
			}
		}
		return -1;
	}

	private Website createWebsiteFromWebsitePostModel2(WebsitePostModel2 m, String account) {
		if (m != null) {
			Website w = new Website();
			w.setAlias(m.getAlias());
			w.setCreateMan(account);
			w.setCreateTime(new Date());
			w.setDefaultPageCss(m.getDefaultPageCss());// 校验
			w.setDefPageConfig(m.getDefPageConfig());
			w.setDefRequestHeader(m.getDefRequestHeader());
			w.setDefResultConfig(m.getDefResultConfig());// 校验
			w.setFullAddr(m.getFullAddr());
			w.setName(m.getName());// 检验来自数据库定义.
			w.setPagePipeline(m.getPagePipeline());
			w.setPageProcessor(m.getPageProcessor());
			w.setPageRObject(m.getPageRObject());
			w.setPagination(m.getPagination());
			w.setResultPipeline(m.getResultPipeline());
			w.setResultProcessor(m.getResultProcessor());
			w.setResultRObject(m.getResultRObject());
			w.setSearchAddr(m.getSearchAddr());
			w.setShortAddr(m.getShortAddr());
			w.setSign(m.getSign());
			w.setType(AppEnumDefine.SiteService.搜索.getValue());// engine搜索类型
			w.setUseSearch(m.isUseSearch());
			return w;
		}
		return null;

	}

	public List<String> getWebsiteAllVersionList(String name) {
		if (StringUtils.isNotEmpty(name)) {
			return versionMapper.selectVersionsForDataInfoAdminService01ListString(name);
		}
		return null;
	}

	public List<String> getAllWebsites() {
		return websiteMapper.selectWebsitesForDataInfoAdminService01ListString();
	}

	public int deleteWebsiteById(int id) {
		if(id>0){
			// 删除之前查找，是否数据version被使用
			int num = pullArticleMapper.selectPullArticlesForDataInfoAdminService01SimpleInt(id);
			if(num>0){
				return -2;
			}else{
				return websiteMapper.deleteWebistesForDataInfoAdminService01SimpleId(id);
			}
		}
		return -1;
	}

}
