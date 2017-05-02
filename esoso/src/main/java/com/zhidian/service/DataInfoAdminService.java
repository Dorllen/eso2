package com.zhidian.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zhidian.mapper.PullArticleMapper;
import com.zhidian.mapper.WebsiteMapper;
import com.zhidian.model.Website;
import com.zhidian.views.WebsitePageVO;
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
			v.setVersion(w.getVersion());
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
		if(w!=null){
			WebsitePageVO v = new WebsitePageVO();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			v.setAlias(w.getAlias());
			v.setCreateMan(v.getCreateMan());
			if(w.getCreateTime()!=null){
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
			if(w.getUnuseTime()!=null){
				v.setUnuseTime(sdf.format(w.getUnuseTime()));
			}
			v.setUpdateMan(w.getUpdateMan());
			if(w.getUpdateTime()!=null){
				v.setUpdateTime(sdf.format(w.getUpdateTime()));
			}
			v.setUseSearch(w.isUseSearch());
			v.setUsing(w.getUsing()==0?false:true);
			v.setVersion(w.getVersion());
			return v;
		}
		return null;
	}

}
