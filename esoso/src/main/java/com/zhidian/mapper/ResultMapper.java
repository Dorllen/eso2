
package com.zhidian.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.Result;

@Mapper
public interface ResultMapper {
	// 增
	
	
	
	// 删
	
	
	// 改
	
	// 查
	
	List<Result> queryResultsFor01ListResult(@Param("list") List<String> list);
	
	List<Result> queryResultsFor02ListResult(@Param("from") List<String> from,@Param("uuids")  List<String> uuids);

	Map<String,Object> testDD(int id);

	
}
