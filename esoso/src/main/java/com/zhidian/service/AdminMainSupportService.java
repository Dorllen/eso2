package com.zhidian.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhidian.bases.SearchEngineEnumDefine;
import com.zhidian.mapper.VersionMapper;
import com.zhidian.model.Version;
import com.zhidian.util.BasicUtils;
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

	public int setVersionDefaultUsing(String versionId, String name) throws Exception {
		if(StringUtils.isNotEmpty(versionId)&&StringUtils.isNotEmpty(name)){
			int id = BasicUtils.version2Id(versionId);
			if(id>0){
				Version v = versionMapper.selectVersionsByIdAndName(id,name);
				if(v!=null){
					return versionMapper.updateVersionsForAdminMainSupportService01SimpleVersion(v.getId(),v.getName(),v.getType(),v.getType2());
				}else{
					return -1;// 可能是参悟有问题，可能是version处于禁用状态
				}
			}else{
				throw new Exception("参数有误,无法转换!");
			}
		}else{
			throw new Exception("参数为空..");
		}
	}
	
	public int setVersionStopUsing(String versionId, String name) throws Exception {
		if(StringUtils.isNotEmpty(versionId)&&StringUtils.isNotEmpty(name)){
			int id = BasicUtils.version2Id(versionId);
			if(id>0){
				return versionMapper.updateVersionsForAdminMainSupportService01ReturnId(id, name);
			}else{
				throw new Exception("参数有误,无法转换!");
			}
		}else{
			throw new Exception("参数为空..");
		}
	}
	
	public int setVersionUnStop(String versionId, String name) throws Exception {
		if(StringUtils.isNotEmpty(versionId)&&StringUtils.isNotEmpty(name)){
			int id = BasicUtils.version2Id(versionId);
			if(id>0){
				return versionMapper.updateVersionsForAdminMainSupportService02ReturnId(id, name);
			}else{
				throw new Exception("参数有误,无法转换!");
			}
		}else{
			throw new Exception("参数为空..");
		}
	}
	

}
