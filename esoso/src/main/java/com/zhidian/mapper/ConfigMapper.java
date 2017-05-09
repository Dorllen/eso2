
package com.zhidian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhidian.model.sys.ConfigBO;

@Mapper
public interface ConfigMapper {

	List<ConfigBO> queryConfigsForAdminInfoSupportService01ListConfigBO();

}
