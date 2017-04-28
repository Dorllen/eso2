
package com.zhidian.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.PaCount;

@Mapper
public interface PaCountMapper {

	void insertPaCountsForPageService01SimplePaCount(PaCount pa);

	/**
	 * @param originIp 
	 * @param originUrl 
	* @Title: queryPaCountsForPageService01SimplePaCount
	* @Description: TODO(10分钟以内的访问无效,status = 0有效的。)
	* @param @param string
	* @param @param string2
	* @param @param uuid
	* @param @param account
	* @param @param ordinal
	* @param @return    参数
	* @return PaCount    返回类型
	* @throws
	*/
	PaCount queryPaCountsForPageService01SimplePaCount(@Param("website") String website,@Param("url") String url,@Param("uuid") String uuid,
			@Param("account") String account,@Param("type") int type,@Param("originUrl")  String originUrl,@Param("originIp")  String originIp);

}
