package com.zhidian.service;

import java.text.SimpleDateFormat;
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
import com.zhidian.model.PullArticle;
import com.zhidian.model.Version;
import com.zhidian.model.sys.ConfigBO;
import com.zhidian.model.sys.NameValueModel;
import com.zhidian.model.sys.PullArticleBO;
import com.zhidian.model.websites.config.ConfigWebsiteItemModel;
import com.zhidian.util.BasicUtils;
import com.zhidian.views.ConfigDTO;
import com.zhidian.views.ServiceSettingsDTO;
import com.zhidian.views.VersionAddVO;
import com.zhidian.views.VersionControlDTO;
import com.zhidian.views.VersionControlViewDTO;
import com.zhidian.views.VersionUpdateVO;
import com.zhidian.views.WebsitePalistDTO;

@Service
public class AdminInfoSupportService {

	@Autowired
	ConfigMapper configMapper;

	@Autowired
	VersionMapper versionMapper;

	@Autowired
	PullArticleMapper pullMapper;

	public List<ConfigDTO> getConfigForVersionList() {
		List<ConfigBO> list = configMapper.queryConfigsForAdminInfoSupportService01ListConfigBO();
		List<ConfigDTO> configs = null;
		if (list != null && list.size() > 0) {
			configs = new ArrayList<ConfigDTO>(list.size());
			for (ConfigBO c : list) {
				if (c != null) {
					ConfigDTO d = new ConfigDTO(c);
					configs.add(d);
				}
			}
		}
		return configs;
	}

	public List<NameValueModel> getWebsitesFromVersionByType(String type) {
		String type2 = SearchEngineEnumDefine.Type.问答.getValue();
		return versionMapper.queryVersionsForAdminInfoSupportService01ListVersionBO2(type, type2);
	}

	public List<VersionControlViewDTO> getVersionBySearch(String type, String value) throws Exception {
		if (StringUtils.isNotEmpty(type) && StringUtils.isNotEmpty(value)) {
			if ("version".equals(type)) {
				int id = BasicUtils.version2Id(value);
				if (id > 0) {
					List<Version> list = versionMapper.queryVersionsForAdminInfoSupportService01ListVersion(id);
					return createVersionControlViewDTOList(list);
				} else {
					throw new PageArgumentsException("参数有误,无法转换!");
				}
			} else if ("websites".equals(type)) {
				List<Version> list = versionMapper.queryVersionsForAdminInfoSupportService02ListVersion(value);
				return createVersionControlViewDTOList(list);
			}
			throw new PageArgumentsException("参数不符合预期...");
		} else {
			throw new PageArgumentsException("参数为空..");
		}
	}

	private List<VersionControlViewDTO> createVersionControlViewDTOList(List<Version> list) {
		if (list != null && list.size() > 0) {
			List<VersionControlViewDTO> ls = new ArrayList<VersionControlViewDTO>(list.size());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (Version v : list) {
				if (v != null) {
					VersionControlViewDTO d = new VersionControlViewDTO();
					d.setId(v.getId());
					d.setName(v.getName());
					d.setUsing(v.getUsing() > 0 ? true : false);
					d.setNmp(v.getNmp() > 0 ? true : false);
					if (v.getCreateTime() != null) {
						d.setCreateTime(sdf.format(v.getCreateTime()));
					}
					d.setVersionId(BasicUtils.id2Version(v.getId()));
					ls.add(d);
				}
			}
			return ls;
		}
		return null;
	}

	public VersionUpdateVO getVersionInfoByVersionId(String versionId) throws PageArgumentsException {
		if (StringUtils.isNotEmpty(versionId)) {
			int id = BasicUtils.version2Id(versionId);
			if (id > 0) {
				Version v = versionMapper.queryVersionsForAdminInfoSupportService01SimpleVersion(id);
				return createVersionUpdateViewModel(v);
			} else {
				throw new PageArgumentsException("参数不符合预期...");
			}
		} else {
			throw new PageArgumentsException("参数为空..");
		}
	}

	private VersionUpdateVO createVersionUpdateViewModel(Version version) {
		if (version != null) {
			VersionUpdateVO v = new VersionUpdateVO();
			v.setId(version.getId());
			v.setDefCss(version.getDefCss());
			v.setDefJs(version.getDefJs());
			v.setDefPage(version.getDefPage());
			v.setName(version.getName());
			v.setType(version.getType());// 需要转换
			v.setType2(version.getType2());
			v.setUsing(version.getUsing() > 0 ? true : false);
			v.setVersionId(BasicUtils.id2Version(version.getId()));
			return v;
		}
		return null;
	}

	public List<VersionControlDTO> getVersionInfoUsingList() {
		List<Version> list = versionMapper.queryVersionsForAdminInfoSupportService03ListVersion();
		return createVersionControlVO(list);
	}

	private List<VersionControlDTO> createVersionControlVO(List<Version> list) {
		if (list != null && list.size() > 0) {
			List<VersionControlDTO> vos = new ArrayList<VersionControlDTO>(list.size());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (Version v : list) {
				if (v != null) {
					VersionControlDTO vo = new VersionControlDTO();
					if (v.getCreateTime() != null) {
						vo.setCreateTime(sdf.format(v.getCreateTime()));
					}
					vo.setName(v.getName());
					vo.setUsing(v.getUsing() > 0 ? true : false);
					vo.setId(v.getId());
					vo.setVersionId(BasicUtils.id2Version(v.getId()));
					vos.add(vo);
				}
			}
			return vos;
		}
		return null;
	}

	public VersionAddVO getVersionAddInfoUsing() {
		List<ConfigBO> list = configMapper.queryConfigsForAdminInfoSupportService01ListConfigBO();
		VersionAddVO vo = new VersionAddVO();
		vo.setItems(list);
		return vo;
	}

	public List<WebsitePalistDTO> getWebsitesPaList() {
		// 获取每个站点特有的一条正使用的数据，现获取最近记录入库的
		List<PullArticle> list = pullMapper.queryPullArticlesForAdminInfoSupportServcie01ListPullArticle();
		return createWebsitePalistDTO(list);
	}

	private List<WebsitePalistDTO> createWebsitePalistDTO(List<PullArticle> list) {
		if (list != null && list.size() > 0) {
			List<WebsitePalistDTO> dtos = new ArrayList<WebsitePalistDTO>(list.size());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (PullArticle p : list) {
				if (p != null) {
					WebsitePalistDTO d = new WebsitePalistDTO();
					// 装载数据
					d.setId(p.getId());
					d.setCollets(p.getCollets());
					if (p.getStartTime() != null) {
						d.setCreateTime(sdf.format(p.getStartTime()));
					}
					d.setMark(p.getMark());
					d.setName(p.getName());
					d.setRelyVersionId(BasicUtils.id2Version(p.getWebsiteId()));
					d.setTags(p.getTags());
					d.setTitle(p.getTitle());
					d.setUrl(p.getUrl());
					d.setCollets(p.getCollets());
					d.setScores(p.getScores());
					d.setViews(p.getViews());
					d.setUuid(p.getUuid());
					dtos.add(d);
				}
			}
			return dtos;
		}
		return null;
	}

	public List<ServiceSettingsDTO> getItemServiceByItemsIdAndName(int id, String name) throws PageArgumentsException {
		if (id > 0) {
			// 通过字符串模糊查询。如果有数据，则取出字符串。
			PullArticleBO p = pullMapper.queryPullArticlesForAdminInfoSupportServcie01SimplePullArticleBO(id, name);
			if (p != null) {
				String sql = createSqlForWebsiteItem(p);
				List<ConfigBO> configs = configMapper.queryConfigsForAdminInfoSupportService02ListConfigBO(sql);
				if (configs != null && configs.size() > 0) {
					return createServiceSettingsDTOList(configs);
				} else {
					return null;
				}
			} else {
				throw new PageArgumentsException("参数异常，数据无该记录...");
			}
		} else {
			throw new PageArgumentsException();
		}
	}

	private List<ServiceSettingsDTO> createServiceSettingsDTOList(List<ConfigBO> configs) {
		if (configs != null && configs.size() > 0) {
			List<ServiceSettingsDTO> dtos = new ArrayList<ServiceSettingsDTO>(configs.size());
			for (ConfigBO c : configs) {
				if(c!=null){
					ServiceSettingsDTO s = new ServiceSettingsDTO();
					s.setId(c.getId());
					s.setName(c.getName());
					s.setUsing(false);
					dtos.add(s);
				}
			}
			return dtos;
		}
		return null;
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
}
