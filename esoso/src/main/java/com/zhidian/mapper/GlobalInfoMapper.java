
package com.zhidian.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GlobalInfoMapper {

	/**
	* @Title: queryGlobalInfoForPageService01SimpleString
	* @Description: TODO(获取当前还能使用的global信息)
	* @param @param type
	* @param @param name
	* @param @return    参数
	* @return GlobalInfo    返回类型
	* @throws
	*/
	String selectGlobalInfoForPageService01SimpleString(@Param("type") String type,@Param("name")  String name);
	

}
