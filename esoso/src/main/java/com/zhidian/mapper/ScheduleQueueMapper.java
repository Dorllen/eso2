
package com.zhidian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.ScheduleQueue;

@Mapper
public interface ScheduleQueueMapper {

	void insertScheduleQueuesForPullArticleService01SimpleVoid(@Param("list") List<ScheduleQueue> list);

	/**
	* @Title: queryScheduleQueuesForWormsService01ListScheduleQueue
	* @Description: TODO(获取的是可执行的ScheduleQueue。类别是非系统级别的。系统级别会自动处理)
	* @param @return    参数
	* @return List<ScheduleQueue>    返回类型
	* @throws
	*/
	List<ScheduleQueue> queryScheduleQueuesForWormsService01ListScheduleQueue();

}
