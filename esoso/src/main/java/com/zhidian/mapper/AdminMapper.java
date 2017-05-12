
package com.zhidian.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhidian.model.sys.AdminBO;

@Mapper
public interface AdminMapper {

	AdminBO queryAdminsForAdminService01SimpleAdminBO(@Param("account") String account, @Param("password") String password);

}
