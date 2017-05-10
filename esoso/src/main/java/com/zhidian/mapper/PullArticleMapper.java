
/**
* @Title: ArticleMapper.java
* @Package com.zhidian.mappers
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-20 下午10:40:43
* @version V1.0
*/
package com.zhidian.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.PullArticle;

/**
 * @ClassName: ArticleMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-20 下午10:40:43
 *
 */
@Mapper
public interface PullArticleMapper {
	// 增
	
	PullArticle queryPullArticlesForPullArticleService01SimplePullArticle(@Param("uuid") String uuid);

	void insertArticlesForWormsService02ListPullArticle(@Param("list") List<PullArticle> list);

	/**
	* @Title: selectPullArticlesForPullArticleService01MapObject
	* @Description: TODO(根据uuid，找出name,url,uuid)
	* @param @param uuid
	* @param @return    参数
	* @return Map<String,Object>    返回类型
	* @throws
	*/
	Map<String, String> selectPullArticlesForPullArticleService01MapObject(@Param("uuid") String uuid);
	
	String tee(Map<String,Object> map);
	// 删

	/**
	* @Title: selectPullArticlesForDataInfoAdminService01SimpleInt
	* @Description: TODO(用于查找，是否pullarticle对versions表的id的version有关联)
	* @param @param id
	* @param @return    参数
	* @return int    返回类型
	* @throws
	*/
	int selectPullArticlesForDataInfoAdminService01SimpleInt(@Param("id") int id);

	List<PullArticle> queryPullArticlesForAdminInfoSupportServcie01ListPullArticle();

	/**
	* @Title: updatePullArticlesForAdminMainSupportService01RetrunId
	* @Description: TODO(设置pullArticle的默认使用版本。通过name，id进行确定url是类型.对于同样type的数据进行using=0，并且数据都要是status=0，代表在用。只有在用的才有)
	* @param @param id
	* @param @param name
	* @param @param type
	* @param @return    参数
	* @return int    返回类型
	* @throws
	*/
	int updatePullArticlesForAdminMainSupportService01RetrunId(@Param("id") int id,@Param("id")  String name);

//	PullArticle selectPullArticleByIdAndName(@Param("id") int id,@Param("id")  String name);
	
	
	// 改
	
	
}
