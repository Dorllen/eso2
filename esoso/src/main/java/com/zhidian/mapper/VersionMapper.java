
package com.zhidian.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.Version;

@Mapper
public interface VersionMapper {

	public Version queryVersionsForSearchService01SimpleVersion(@Param("type") String type, @Param("name") String name);

	public Version queryVersionsForPageService01SimpleVersion(@Param("type") String type, @Param("name") String name);

	/**
	* @Title: queryVersionsForPullArticleService01SimpleVersion
	* @Description: TODO(获取条件是type与name，并且version匹配，如果不匹配返回默认0.0.0.0的Version，)
	* @param @param type
	* @param @param name
	* @param @param version
	* @param @return    参数
	* @return Version    返回类型
	* @throws
	*/
	public Version queryVersionsForPullArticleService01SimpleVersion(@Param("type") String type,
			@Param("name") String name, @Param("version") String version);

}
