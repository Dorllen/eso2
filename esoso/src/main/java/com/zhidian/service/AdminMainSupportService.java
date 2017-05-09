package com.zhidian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhidian.bases.SearchEngineEnumDefine;
import com.zhidian.mapper.VersionMapper;
import com.zhidian.model.Version;
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

}
