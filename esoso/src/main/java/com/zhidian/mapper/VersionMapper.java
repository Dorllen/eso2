
package com.zhidian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.Version;
import com.zhidian.model.sys.NameValueModel;

@Mapper
public interface VersionMapper {

	public Version queryVersionsForSearchService01SimpleVersion(@Param("type") String type, @Param("name") String name);

	public Version queryVersionsForPageService01SimpleVersion(@Param("type") String type, @Param("name") String name);

	
	public List<String> selectVersionsForDataInfoAdminService01ListString(@Param("name") String name);

	public Version queryVersionsForDataInfoAdminService01SimpleVersion(@Param("name") String name);

	public List<NameValueModel> queryVersionsForAdminInfoSupportService01ListVersionBO2(@Param("type") String type,@Param("type2") String type2);

	
	
	public int insertVersionsForAdminMainService01SimpleId(Version version);



}
