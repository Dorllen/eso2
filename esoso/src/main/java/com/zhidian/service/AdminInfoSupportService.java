package com.zhidian.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhidian.bases.SearchEngineEnumDefine;
import com.zhidian.exception.PageArgumentsException;
import com.zhidian.mapper.ConfigMapper;
import com.zhidian.mapper.VersionMapper;
import com.zhidian.model.Version;
import com.zhidian.model.sys.ConfigBO;
import com.zhidian.model.sys.NameValueModel;
import com.zhidian.util.BasicUtils;
import com.zhidian.views.ConfigDTO;
import com.zhidian.views.VersionControllerViewDTO;
import com.zhidian.views.VersionUpdateViewModel;

@Service
public class AdminInfoSupportService {

	@Autowired
	ConfigMapper configMapper;

	@Autowired
	VersionMapper versionMapper;

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

	public List<VersionControllerViewDTO> getVersionBySearch(String type, String value) throws Exception {
		if (StringUtils.isNotEmpty(type) && StringUtils.isNotEmpty(value)) {
			if ("version".equals(type)) {
				int id = BasicUtils.version2Id(value);
				if (id > 0) {
					List<Version> list = versionMapper.queryVersionsForAdminInfoSupportService01ListVersion(id);
					return createVersionControllerViewDTOList(list);
				} else {
					throw new PageArgumentsException("参数有误,无法转换!");
				}
			} else if ("websites".equals(type)) {
				List<Version> list = versionMapper.queryVersionsForAdminInfoSupportService02ListVersion(value);
				return createVersionControllerViewDTOList(list);
			}
			throw new PageArgumentsException("参数不符合预期...");
		} else {
			throw new PageArgumentsException("参数为空..");
		}
	}

	private List<VersionControllerViewDTO> createVersionControllerViewDTOList(List<Version> list) {
		if (list != null && list.size() > 0) {
			List<VersionControllerViewDTO> ls = new ArrayList<VersionControllerViewDTO>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			for (Version v : list) {
				if (v != null) {
					VersionControllerViewDTO d = new VersionControllerViewDTO();
					d.setId(v.getId());
					d.setName(v.getName());
					d.setUsing(v.getUsing());
					d.setCreateTime(sdf.format(v.getCreateTime()));
					d.setVersionId(BasicUtils.id2Version(v.getId()));
					ls.add(d);
				}
			}
			return ls;
		}
		return null;
	}

	public VersionUpdateViewModel getVersionInfoByVersionId(String versionId) throws PageArgumentsException {
		if(StringUtils.isNotEmpty(versionId)){
			int id = BasicUtils.version2Id(versionId);
			if(id>0){
				Version v = versionMapper.queryVersionsForAdminInfoSupportService01SimpleVersion(id);
				return createVersionUpdateViewModel(v);
			}else{
				throw new PageArgumentsException("参数不符合预期...");
			}
		}else{
			throw new PageArgumentsException("参数为空..");
		}
	}

	private VersionUpdateViewModel createVersionUpdateViewModel(Version version) {
		if(version!=null){
			VersionUpdateViewModel v = new VersionUpdateViewModel();
			v.setId(version.getId());
			v.setDefCss(version.getDefCss());
			v.setDefJs(version.getDefJs());
			v.setDefPage(version.getDefPage());
			v.setName(version.getName());
			v.setType(version.getType());// 需要转换
			v.setType2(version.getType2());
			v.setUsing(version.getUsing()>0?true:false);
			return v;
		}
		return null;
	}
}
