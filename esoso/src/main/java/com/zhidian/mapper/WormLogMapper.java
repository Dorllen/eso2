
package com.zhidian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.WormLog;

@Mapper
public interface WormLogMapper {

	void insertWormsLogForWormsService01ListWormLog(@Param("list") List<WormLog> list);
	
}
