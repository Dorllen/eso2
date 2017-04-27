
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
	
	void addArticle(PullArticle article);

	PullArticle queryPullArticlesForPullArticleService01SimplePullArticle(@Param("uuid") String uuid);

	void insertArticlesForWormsService02ListPullArticle(@Param("list") List<PullArticle> list);
	
	
	// 删
	
	
	// 改
	
	
}
