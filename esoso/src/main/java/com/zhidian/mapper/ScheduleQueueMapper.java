
package com.zhidian.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.ScheduleQueue;
import com.zhidian.model.sys.PullResultBO;

@Mapper
public interface ScheduleQueueMapper {

	void insertScheduleQueuesForPullArticleService01SimpleVoid(@Param("list") List<ScheduleQueue> list);

	/**
	 * @Title:
	 * queryScheduleQueuesForWormsService01ListScheduleQueue @Description:
	 * TODO(获取的是可执行的ScheduleQueue。类别是非系统级别的。系统级别会自动处理。并且状态是1的) @param @return
	 * 参数 @return List<ScheduleQueue> 返回类型 @throws
	 */
	List<ScheduleQueue> queryScheduleQueuesForWormsService01ListScheduleQueue();

	
	/**
	* @Title: queryScheduleQueuesForWormsService01SimpleScheduleQueue
	* @Description: TODO(获取的是可执行的ScheduleQueue。类别是非系统级别的。系统级别会自动处理。并且状态是1的.id是确定的)
	* @param @param id
	* @param @return    参数
	* @return ScheduleQueue    返回类型
	* @throws
	*/
	ScheduleQueue queryScheduleQueuesForWormsService01SimpleScheduleQueue(@Param("id") int id);

	
	/**
	 * @Title: updateScheduleQueuesForWormsServiceListInteger @Description:
	 * TODO(ScheduleQueues的id集合，用户下载内容详情页面之后，状态从1->2，等待管理员处理) @param @param
	 * queues 参数 @return void 返回类型 @throws
	 */
	void updateScheduleQueuesForWormsServiceListInteger(@Param("list") List<Integer> queues);

	/**
	 * @Title:
	 * queryScheduleQueuesForPullArticleService01ListScheduleQueue @Description:
	 * TODO(通过PullResultBO的url,name来查找是否存在，并且要求type为：System,answer,webistes。无论status是如何。因为这是用户搜索触发，不需要每次搜索都加入) @param @param
	 * list @param @return 参数 @return List<ScheduleQueue> 返回类型 @throws
	 */
	List<ScheduleQueue> queryScheduleQueuesForPullArticleService01ListScheduleQueue(
			@Param("list") List<PullResultBO> list);

	/**
	* @Title: selectScheduleQueuesForPullArticleService01MapObject
	* @Description: TODO(获得name,url,uuid。属于在线爬取搜索结果页的数据，数据status!=0，即不为已经处理了的ScheduleQueue，type:System,answer,websites)
	* @param @param uuid
	* @param @return    参数
	* @return Map<String,Object>    返回类型
	* @throws
	*/
	Map<String,String> selectScheduleQueuesForPullArticleService01MapObject(@Param("uuid") String uuid);


}
