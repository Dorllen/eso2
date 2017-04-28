
package com.zhidian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.WormLog;

@Mapper
public interface WormLogMapper {

	/**
	* @Title: insertWormsLogForWormsService01ListWormLog
	* @Description: TODO(增加内容详情页的WormLog入数据库)
	* @param @param list    参数
	* @return void    返回类型
	* @throws
	*/
	void insertWormsLogForWormsService01ListWormLog(@Param("list") List<WormLog> list);

	/**
	 * @Title: selectWormLogsForPullArticleService01ListWormLog @Description:
	 * TODO(找出type,propertyName,website，并且status不为0) @param @param
	 * list @param @return 参数 @return List<WormLog> 返回类型 @throws
	 */
	List<WormLog> selectWormLogsForPullArticleService01ListWormLog(@Param("list") List<WormLog> list);

	/**
	* @Title: insertWormLogsForPullArticleService01ListVoid
	* @Description: TODO(增加在綫爬虫的WormLog入数据库)
	* @param @param list    参数
	* @return void    返回类型
	* @throws
	*/
	void insertWormLogsForPullArticleService01ListWormLog(@Param("list") List<WormLog> list);

}
