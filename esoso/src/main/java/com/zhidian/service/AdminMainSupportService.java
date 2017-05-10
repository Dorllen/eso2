package com.zhidian.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zhidian.bases.SearchEngineEnumDefine;
import com.zhidian.exception.PageArgumentsException;
import com.zhidian.mapper.ConfigMapper;
import com.zhidian.mapper.PullArticleMapper;
import com.zhidian.mapper.VersionMapper;
import com.zhidian.mapper.WebsiteMapper;
import com.zhidian.model.Version;
import com.zhidian.model.sys.ConfigBO;
import com.zhidian.model.sys.PullArticleBO;
import com.zhidian.model.websites.config.ConfigWebsiteItemModel;
import com.zhidian.util.BasicUtils;
import com.zhidian.views.PullArticleUpdateModel;
import com.zhidian.views.VersionAddViewMainDTO;

/**
 * @ClassName: AdminMainSupportService
 * @Description: TODO(数据接入数据库的Admin服务)
 * @author dongneng
 * @date 2017年5月9日 下午3:07:49
 *
 */
@Service
public class AdminMainSupportService {

	@Autowired
	VersionMapper versionMapper;

	@Autowired
	WebsiteMapper websiteMapper;

	@Autowired
	ConfigMapper configMapper;

	@Autowired
	PullArticleMapper pullArticleMapper;

	public int addNewVersionByVersionViewModel(VersionAddViewMainDTO version) {
		if (version != null) {
			Version v = new Version();
			v.setName(version.getName());
			v.setDefCss(version.getDefCss());
			v.setDefPage(version.getDefPage());
			v.setDefJs(version.getDefJs());
			v.setSign(version.getSign());
			v.setType(version.getType());
			v.setType2(SearchEngineEnumDefine.Type.问答.getValue());// type默认是问答，可拓展。现只提供问答搜索服务
			return versionMapper.insertVersionsForAdminMainService01SimpleId(v);
		}
		return 0;
	}

	public int setVersionDefaultUsing(String versionId, String name) throws Exception {
		if (StringUtils.isNotEmpty(versionId) && StringUtils.isNotEmpty(name)) {
			int id = BasicUtils.version2Id(versionId);
			if (id > 0) {
				return versionMapper.updateVersionsForAdminMainSupportService01SimpleVersion(id, name);
			} else {
				throw new Exception("参数有误,无法转换!");
			}
		} else {
			throw new Exception("参数为空..");
		}
	}

	public int setVersionStopUsing(String versionId, String name) throws Exception {
		if (StringUtils.isNotEmpty(versionId) && StringUtils.isNotEmpty(name)) {
			int id = BasicUtils.version2Id(versionId);
			if (id > 0) {
				return versionMapper.updateVersionsForAdminMainSupportService01ReturnId(id, name);
			} else {
				throw new Exception("参数有误,无法转换!");
			}
		} else {
			throw new Exception("参数为空..");
		}
	}

	public int setVersionUnStop(String versionId, String name) throws Exception {
		if (StringUtils.isNotEmpty(versionId) && StringUtils.isNotEmpty(name)) {
			int id = BasicUtils.version2Id(versionId);
			if (id > 0) {
				return versionMapper.updateVersionsForAdminMainSupportService02ReturnId(id, name);
			} else {
				throw new Exception("参数有误,无法转换!");
			}
		} else {
			throw new Exception("参数为空..");
		}
	}

	public int setPullArticleDefaultUsing(int id, String name) throws PageArgumentsException {
		if (id > 0 && StringUtils.isNotEmpty(name)) {
			return pullArticleMapper.updatePullArticlesForAdminMainSupportService01RetrunId(id, name);
		} else {
			throw new PageArgumentsException("参数为空..");
		}
	}

	public int deletePullArticle(int id, String name) throws PageArgumentsException {
		if (id > 0 && StringUtils.isNotEmpty(name)) {
			return pullArticleMapper.deletePullArticlesForAdminMainSupportService01IdName(id, name);
		}
		throw new PageArgumentsException("参数为空..");
	}

	public int updateItemServiceByListKey(int id, String name, List<String> list) throws PageArgumentsException {
		if (id > 0 && StringUtils.isNotEmpty(name) && list != null && list.size() > 0) {
			// 再数据后面追加,首先校验数据是否存在
			PullArticleBO p = pullArticleMapper.queryPullArticlesForAdminMainSupportServiceSimplePullArticleBO(id,
					name);
			if (p != null) {
				// 再进行数据检验是否存在list中
				String sql = createSqlForWebsiteItem(p);
				List<ConfigBO> configs = configMapper.queryConfigsForAdminMainSupportService01ListConfigBO(sql);
				System.out.println(JSON.toJSONString(configs));
				if (configs != null && configs.size() > 0) {
					// 找出与list中同name的configBo
					List<ConfigBO> cofs = new ArrayList<ConfigBO>(configs.size());
					for (ConfigBO c : configs) {
						if (c != null) {
							for (String s : list) {
								if (StringUtils.isNotEmpty(s)) {
									if (s.equals(c.getName())) {
										// 入队、等待改正
										if (cofs.contains(c)) {
											continue;
										} else {
											cofs.add(c);
										}
									}
								}
							}
						}
					}
					// 拿到需要修改数据的ConfigBO
					for(ConfigBO c : cofs){
						// 修改数据
						if(c!=null){
							c.setValue(createWebsiteServiceValueString(c.getValue(),sql));
						}
					}
					// 更新入库
					return configMapper.updateConfigsForAdminMainSupportService01ReturnId(cofs);
				} else {
					// 说明数据库不存在，可以直接拒绝此次操作
					return 1;// 告知操作成功
				}
			} else {
				throw new PageArgumentsException();
			}
		} else {
			throw new PageArgumentsException();
		}
	}

	private String createWebsiteServiceValueString(String value, String sql) {
		if(StringUtils.isNotEmpty(sql)){
			if(value!=null&&value.trim().length()>0){
				// 插入最后一个]之前,这里需要判断一种情况是:[]情况，或[  ]情况
				int f = value.indexOf('[');
				int l = value.lastIndexOf(']');
				if(l-f==1){
					value = "["+sql+"]";
				}else{
					String str = value.substring(f+1, l);
					if(str.trim().length()>0){// 说明里面还有其他值
						value = value.substring(0, value.length()-1)+","+sql+"]";
					}else{
						value = "["+sql+"]";
					}
				}
			}else{
				value = "["+sql+"]";
			}
		}
		return value;
	}

	private String createSqlForWebsiteItem(PullArticleBO p) {
		// 强制依赖，p肯定不为空
		ConfigWebsiteItemModel config = new ConfigWebsiteItemModel();
		config.setId(p.getId());
		config.setName(p.getName());
		config.setType(p.getType());
		config.setUrl(p.getUrl());
		config.setUuid(p.getUuid());
		return JSON.toJSONString(config);
	}

	public int updateItemInfo(PullArticleUpdateModel article) {
		if(article!=null){
			return pullArticleMapper.updatePullArticlesForAdminMainSupportService02RetrunId(article.getId(), article.getCssPath(),)
		}
		return 0;
	}
}
