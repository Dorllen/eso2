
package com.zhidian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.Website;
import com.zhidian.model.sys.WebsiteBO;
import com.zhidian.model.sys.WebsiteBO2;
import com.zhidian.model.sys.WebsiteBO3;

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
	* @return WebsiteBO    返回类型
	* @throws
	*/
	WebsiteBO queryWebsitesForPageService01SimpleWebsiteBO(@Param("type") String type,@Param("name") String name,@Param("id") int id);
	
	


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

	/**
	* @Title: queryWebsitesForWormsService01SimpleWebsiteBO
	* @Description: TODO(查找类型的为name的website。不会获取0.0.0.0，只会获取using=0)
	* @param @param type
	* @param @param name
	* @param @return    参数
	* @return WebsiteBO    返回类型
	* @throws
	*/
	WebsiteBO queryWebsitesForWormsService01SimpleWebsiteBO(@Param("type") String type,@Param("name") String name);

	List<Website> queryWebsitesForDataInfoAdminService01ListWebsite(@Param("name") String name);


	Website queryWebsitesForDataInfoAdminService01Website(@Param("id") int id,@Param("name") String name);


	void updateWebsitesForDataInfoAdminService01SmpleWebsiteBO2(WebsiteBO2 w);



	List<String> selectWebsitesForDataInfoAdminService01ListString();




	int insertWebsitesForDataInfoAdminService01SimpleWebsite(Website w);




	int insertWebsitesForDataInfoAdminService02SimpleWebsite(Website w);




	List<Integer> selectWebsitesForAdminInfoSupport01ListId(@Param("id") int id,@Param("name") String name);




	/**
	* @Title: querywebsitesForAdminSupportService01SimpleWebsiteBO2
	* @Description: TODO(nmp=1)
	* @param @param id
	* @param @return    参数
	* @return WebsiteBO2    返回类型
	* @throws
	*/
	WebsiteBO2 queryWebsitesForAdminSupportService01SimpleWebsiteBO2(@Param("id") int id);




	List<WebsiteBO3> queryWebsitesForAdminSupportService01ListWebsiteBO3();




	List<WebsiteBO3> queryWebsitesForAdminSupportService02ListWebsiteBO3(@Param("name") String name);




	/**
	* @Title: queryWebsitesForAdminSupportService03ListWebsiteBO3
	* @Description: TODO(站点版本id)
	* @param @param id
	* @param @return    参数
	* @return List<WebsiteBO3>    返回类型
	* @throws
	*/
	List<WebsiteBO3> queryWebsitesForAdminSupportService03ListWebsiteBO3(@Param("id") int id);




	/**
	* @Title: queryWebsitesForAdminSupportService04ListWebsiteBO3
	* @Description: TODO(依赖版本id，及Version表的id)
	* @param @param id
	* @param @return    参数
	* @return List<WebsiteBO3>    返回类型
	* @throws
	*/
	List<WebsiteBO3> queryWebsitesForAdminSupportService04ListWebsiteBO3(@Param("id") int id);




	int deleteWebsitesForAdminMainSupportService01ReturnId(@Param("id") int id,@Param("name") String name);




	int deleteWebsitesForAdminMainSupportService02ReturnId(@Param("id") int id,@Param("name") String name);


	/**
	* @Title: updateWebsitesForAdminMainSupportService01ReturnId
	* @Description: TODO(设置website使用的版本,nmp必须为1)
	* @param @param id
	* @param @param name    参数
	* @return void    返回类型
	* @throws
	*/
	int updateWebsitesForAdminMainSupportService01ReturnId(@Param("id") int id,@Param("name")  String name);




	WebsiteBO3 queryWebsitesForAdminSupportService01SimpleWebsiteBO3(@Param("id") int id);




	int updateWebsitesForAdminMainSupportService02ReturnId(Website w);



	int updateWebsitesForAdminMainSupportService03ReturnId(Website w);




	/**
	* @Title: insertWebsitesForAdminMainSupportService01ReturnId
	* @Description: TODO(type:engine要存在,type2:answer等要存在，nmp=1,versionId存在。暂将type,type2写死.[待改进])
	* @param @param w
	* @param @return    参数
	* @return int    返回类型
	* @throws
	*/
	int insertWebsitesForAdminMainSupportService01ReturnId(Website w);




	Website queryWebsitesForAdminInfoSupportService01SimpleWebsite(@Param("id") int id,@Param("name")  String name);




	Website queryWebsitesForAdminMainSupportService01SimpleWebsite(@Param("id") int id,@Param("name")  String name);




	Website queryWebsitesForPullArticle01SimpleWebsite(@Param("name") String name);







	
	
	// 删
	
	
	// 改
	
	
}
