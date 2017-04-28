
package com.zhidian.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zhidian.model.PaCount;

@Mapper
public interface PaCountMapper {

	void insertPaCountsForPageService01SimpleVoid(PaCount pa);

}
