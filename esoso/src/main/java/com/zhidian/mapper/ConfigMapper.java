
package com.zhidian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.sys.ConfigBO;

@Mapper
public interface ConfigMapper {

	List<ConfigBO> queryConfigsForAdminInfoSupportService01ListConfigBO();

	/**
	* @Title: queryConfigsForAdminInfoSupportService02ListConfigBO
	* @Description: TODO(通过组装模糊sql的字符串，来获取Website数据项的数据)
	* @param @param sql
	* @param @return    参数
	* @return List<ConfigBO>    返回类型
	* @throws
	*/
	List<ConfigBO> queryConfigsForAdminInfoSupportService02ListConfigBO(@Param("sql") String sql);

	/**
	* @Title: queryConfigsForAdminMainSupportService01ListConfigBO
	* @Description: TODO(找出不存有sql的属于WebsiteService的name的ConfigBO)
	* @param @param sql
	* @param @return    参数
	* @return List<ConfigBO>    返回类型
	* @throws
	*/
	List<ConfigBO> queryConfigsForAdminMainSupportService01ListConfigBO(@Param("sql") String sql);

	int updateConfigsForAdminMainSupportService01ReturnId(@Param("configs") List<ConfigBO> configs);

}
