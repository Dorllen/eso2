
package com.zhidian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.Version;
import com.zhidian.model.sys.NameValueModel;
import com.zhidian.model.sys.VersionBO2;

@Mapper
public interface VersionMapper {

	public Version queryVersionsForSearchService01SimpleVersion(@Param("type") String type,@Param("type2") String type2, @Param("name") String name);

	public Version queryVersionsForPageService01SimpleVersion(@Param("type") String type,@Param("type2") String type2, @Param("name") String name);


	public Version queryVersionsForDataInfoAdminService01SimpleVersion(@Param("name") String name);

	public List<NameValueModel> queryVersionsForAdminInfoSupportService01ListVersionBO2(@Param("type") String type,@Param("type2") String type2);

	
	
	public int insertVersionsForAdminMainSupportService01SimpleId(Version version);

	public List<Version> queryVersionsForAdminInfoSupportService01ListVersion(@Param("id") int id);

	public List<Version> queryVersionsForAdminInfoSupportService02ListVersion(@Param("name") String name);

	/**
	* @Title: selectVersionsByIdAndName
	* @Description: TODO(通过id，name找出非禁用的version.不管using)
	* @param @param id
	* @param @param name
	* @param @return    参数
	* @return Version    返回类型
	* @throws
	*/
	public Version selectVersionsByIdAndName(@Param("id") int id,@Param("name") String name);
	

	/**
	* @Title: updateVersionsForAdminMainSupportService01SimpleVersion
	* @Description: TODO(设置version的using为1。version必须是nmp=1才可以)
	* @param @param id
	* @param @param name
	* @param @param type
	* @param @param type2    参数
	* @return void    返回类型
	* @throws
	*/
	public int updateVersionsForAdminMainSupportService01SimpleVersion(@Param("id") int id,@Param("name") String name);

	/**
	* @Title: updateVersionsForAdminMainSupportService02ReturnId
	* @Description: TODO(禁用version,该version必须是using=0)
	* @param @param id
	* @param @param name
	* @param @return    参数
	* @return int    返回类型
	* @throws
	*/
	public int updateVersionsForAdminMainSupportService01ReturnId(@Param("id") int id,@Param("name") String name);

	public Version queryVersionsForAdminInfoSupportService01SimpleVersion(@Param("id") int id);

	public List<Version> queryVersionsForAdminInfoSupportService03ListVersion();

	public int updateVersionsForAdminMainSupportService02ReturnId(@Param("id") int id,@Param("name") String name);

	public VersionBO2 queryVersionsForAdminSupportService01SimpleVerionBO2(@Param("id") int id);

	public List<Integer> selectVersionsForAdminInforSupportService01ListString(@Param("id") int id);

	public List<String> selectVersionsForAdminInfoSupportService01ListString();

	public List<Integer> selectVersionsForAdminInfoSupportService02ListInteger(@Param("name") String name);

	public Integer selectVersionsForAdminInfoSupportService02SimpleInteger(String name);
	
//	public void selectTest(@Param("id") int id,@Param("name") String name);
//	public int updateTest(@Param("id") int id,@Param("name") String name);
}
