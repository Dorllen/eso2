package com.zhidian.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.zhidian.model.PullArticle;
import com.zhidian.model.Version;
import com.zhidian.model.Website;
import com.zhidian.model.sys.ConfigBO;
import com.zhidian.model.sys.PullArticleBO;
import com.zhidian.model.sys.PullArticleBO2;
import com.zhidian.model.websites.config.ConfigWebsiteItemModel;
import com.zhidian.util.BasicUtils;
import com.zhidian.views.PullArticleUpdateModel;
import com.zhidian.views.VersionAddViewMainDTO;
import com.zhidian.views.WebsiteMainUploadModel;

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
			return versionMapper.insertVersionsForAdminMainSupportService01SimpleId(v);
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
					for (ConfigBO c : cofs) {
						// 修改数据
						if (c != null) {
							c.setValue(createWebsiteServiceValueString(c.getValue(), sql));
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
		if (StringUtils.isNotEmpty(sql)) {
			if (value != null && value.trim().length() > 0) {
				// 插入最后一个]之前,这里需要判断一种情况是:[]情况，或[ ]情况
				int f = value.indexOf('[');
				int l = value.lastIndexOf(']');
				if (l - f == 1) {
					value = "[" + sql + "]";
				} else {
					String str = value.substring(f + 1, l);
					if (str.trim().length() > 0) {// 说明里面还有其他值
						value = value.substring(0, value.length() - 1) + "," + sql + "]";
					} else {
						value = "[" + sql + "]";
					}
				}
			} else {
				value = "[" + sql + "]";
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

	public int updateItemInfo(PullArticleUpdateModel article, String account) throws PageArgumentsException {
		if (article != null) {
			PullArticle pa = pullArticleMapper
					.queryPullArticlesForAdminMainSupportServiceSimplePullArticle(article.getId(), article.getName());
			if (pa != null) {
				PullArticleBO2 p = createPullArticleFromUploadData(article, pa);
				if (p == null) {
					// 说明没有更新
					return 1;// 说明操作成功!
				} else {
					p.setUpdateMan(account);
					return pullArticleMapper.updatePullArticlesForAdminMainSupportService01SimplePullArticleBO2(p);
				}
			} else {
				// 异常请求
				throw new PageArgumentsException();
			}
		}
		return 0;
	}

	private PullArticleBO2 createPullArticleFromUploadData(PullArticleUpdateModel a, PullArticle p)
			throws PageArgumentsException {
		if (a != null && p != null) {
			int code = 0;
			PullArticleBO2 b = new PullArticleBO2();
			b.setId(a.getId());
			if (!BasicUtils.compareSame(a.getCssPath(), p.getCssPath(), false)) {
				code++;
			}
			b.setCssPath(a.getCssPath());
			if (!BasicUtils.compareSame(a.getMark(), p.getMark(), false)) {
				code++;
			}
			b.setMark(a.getMark());
			if (!BasicUtils.compareSame(a.getPagePath(), p.getPagePath(), false)) {
				code++;
			}
			b.setPagePath(a.getPagePath());
			if (!BasicUtils.compareSame(a.getResultContent(), p.getResultContent(), false)) {
				code++;
			}
			b.setResultContent(a.getResultContent());
			if (!BasicUtils.compareSame(a.getSign(), p.getSign(), false)) {
				code++;
			}
			b.setSign(a.getSign());
			if (!BasicUtils.compareSame(a.getTags(), p.getTags(), false)) {
				code++;
			}
			b.setTags(a.getTags());
			if (!BasicUtils.compareSame(a.getTitle(), p.getTitle(), false)) {
				code++;
			}
			b.setTitle(a.getTitle());
			if (!BasicUtils.compareSame(a.getUrl(), p.getUrl(), false)) {
				code++;
				b.setUrl(a.getUrl());
				if (a.getUrl() != null && a.getUrl().trim().length() > 0) {
					b.setUuid(DigestUtils.md5Hex(a.getUrl()));
				}
			} else {
				b.setUrl(p.getUrl());
				b.setUuid(p.getUuid());
			}
			int nu = BasicUtils.version2Id(a.getWebsiteId());
			if (nu > 0) {
				if (p.getWebsiteId() != nu) {
					code++;
				}
				b.setWebsiteId(nu);
			} else {
				throw new PageArgumentsException();
			}
			if (code == 0) {
				return null;
			} else {
				// b.setUpdateMan("");// 放到外部更新
				b.setUpdateTime(new Date());// 没有使用数据库内置函数了【待思考】
				return b;
			}
		}
		return null;
	}

	public int deleteWebsiteForceByWebsiteIdAndName(String websiteId, String name) throws PageArgumentsException {
		// 强制删除
		if (StringUtils.isNotEmpty(websiteId) && StringUtils.isNotEmpty(name)) {
			int id = BasicUtils.version2Id(websiteId);
			if (id > 0) {
				return websiteMapper.deleteWebsitesForAdminMainSupportService01ReturnId(id, name);
			} else {
				throw new PageArgumentsException();
			}
		} else {
			throw new PageArgumentsException();
		}
	}

	public int deleteWebsiteByWebsiteIdAndName(String websiteId, String name) throws PageArgumentsException {
		// 非强制删除
		if (StringUtils.isNotEmpty(websiteId) && StringUtils.isNotEmpty(name)) {
			int id = BasicUtils.version2Id(websiteId);
			if (id > 0) {
				return websiteMapper.deleteWebsitesForAdminMainSupportService02ReturnId(id, name);
			} else {
				throw new PageArgumentsException();
			}
		} else {
			throw new PageArgumentsException();
		}
	}

	public int updateWebsiteForSetDefaultByWebsiteIdAndName(String websiteId, String name)
			throws PageArgumentsException {
		// call websitesp1(#{id},#{name});
		if (StringUtils.isNotEmpty(websiteId) && StringUtils.isNotEmpty(name)) {
			int id = BasicUtils.version2Id(websiteId);
			if (id > 0) {
				return websiteMapper.updateWebsitesForAdminMainSupportService01ReturnId(id, name);
			} else {
				throw new PageArgumentsException();
			}
		} else {
			throw new PageArgumentsException();
		}
	}

	public int updateWebsiteForceForUpdateInfo(WebsiteMainUploadModel model, String account)
			throws PageArgumentsException {
		if (model != null) {
			Website w = createWebsiteFromWebsiteMainUploadModel(model, account);
			if (w != null) {
				try {
					// 这里出现异常的原因就是versionId是不存在的，或者是参数错误的（nmp=0，获取versionId失败）
					return websiteMapper.updateWebsitesForAdminMainSupportService02ReturnId(w);
				} catch (Exception e) {
					throw new PageArgumentsException();
				}
			} else {
				throw new PageArgumentsException();
			}
		} else {
			throw new PageArgumentsException();
		}
	}

	private Website createWebsiteFromWebsiteMainUploadModel(WebsiteMainUploadModel model, String account)
			throws PageArgumentsException {
		if (model != null) {
			Website w = new Website();
			int id = BasicUtils.version2Id(model.getWebsiteId());
			if (id <= 0) {
				throw new PageArgumentsException();
			}
			w.setId(id);
			id = BasicUtils.version2Id(model.getVersionId());
			if (id <= 0) {
				throw new PageArgumentsException();
			}
			w.setVersionId(id);
			w.setAlias(model.getAlias());
			w.setDefaultPageCss(model.getDefaultPageCss());
			w.setDefPageConfig(model.getDefPageConfig());
			w.setDefPageCss(model.getDefPageCss());
			w.setDefRequestHeader(model.getDefRequestHeader());
			w.setDefResultConfig(model.getDefResultConfig());
			w.setFullAddr(model.getFullAddr());
			w.setPagePipeline(model.getPagePipeline());
			w.setPageProcessor(model.getPageProcessor());
			w.setPageRObject(model.getPageRObject());
			w.setPagination(model.getPagination());
			w.setResultPipeline(model.getResultPipeline());
			w.setResultProcessor(model.getResultProcessor());
			w.setResultRObject(model.getResultRObject());
			w.setSearchAddr(model.getSearchAddr());
			w.setShortAddr(model.getShortAddr());
			w.setSign(model.getSign());
			w.setUpdateTime(new Date());
			w.setUpdateMan(account);
			w.setUseSearch(model.isUseSearch());
			return w;
		}
		return null;
	}

	public int updateWebsiteForUpdateInfo(WebsiteMainUploadModel model, String account) throws PageArgumentsException {
		if (model != null) {
			Website w = createWebsiteFromWebsiteMainUploadModel(model, account);
			if (w != null) {
				try {
					return websiteMapper.updateWebsitesForAdminMainSupportService03ReturnId(w);
				} catch (Exception e) {
					throw new PageArgumentsException();
				}
			} else {
				throw new PageArgumentsException();
			}
		} else {
			throw new PageArgumentsException();
		}
	}
}
