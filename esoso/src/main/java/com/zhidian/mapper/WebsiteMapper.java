
package com.zhidian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.Website;
import com.zhidian.model.sys.WebsiteBO;
import com.zhidian.model.sys.WebsiteBO2;

@Mapper
public interface WebsiteMapper {
	// 查
	/**
	* @Title: queryWebsitesForPageService01SimpleWebsite
	* @Description: TODO(通过三条件查询数据，如果通过version没有数据则选择默认version:0.0.0.0)
	* @param @param type
	* @param @param name
	* @param @param version
	* @param @return    参数
	* @return Website    返回类型
	* @throws
	*/
	Website queryWebsitesForPageService01SimpleWebsite(@Param("type") String type,@Param("name") String name,@Param("version") String version);
	
	
	// 增
	
	void addWebsite(Website website);


	/**
	* @Title: queryWebsitesForWormsService01ListWebsiteBO
	* @Description: TODO(查找类型的为name的website。不会获取0.0.0.0，只会获取using=0)
	* @param @param type
	* @param @param names
	* @param @return    参数
	* @return List<WebSiteBO>    返回类型
	* @throws
	*/
	List<WebsiteBO> queryWebsitesForWormsService01ListWebsiteBO(@Param("type") String type,@Param("names") List<String> names);


	List<Website> queryWebsitesForDataInfoAdminService01ListWebsite(@Param("name") String name);


	Website queryWebsitesForDataInfoAdminService01Website(@Param("id") int id,@Param("name") String name);


	void updateWebsitesForDataInfoAdminService01SmpleWebsiteBO2(WebsiteBO2 w);


	/**
	* @Title: updateWebsitesForDataInfoAdmin01SimpleWebsite
	* @Description: TODO(设置website使用的版本)
	* @param @param id
	* @param @param name    参数
	* @return void    返回类型
	* @throws
	*/
	void updateWebsitesForDataInfoAdmin01SimpleWebsite(@Param("id") String id,@Param("name")  String name);

	
	
	// 删
	
	
	// 改
	
	
}
