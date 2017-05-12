
package com.zhidian.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.Admin;

@Mapper
public interface AdminMapper {

	Admin queryAdminsForAdminService01SimpleAdmin(@Param("account") String account, @Param("password") String password);

}
