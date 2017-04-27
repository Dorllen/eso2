
package com.zhidian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhidian.model.User;

@Mapper
public interface UserMapper {
	List<User> getAllUser();
	User getUser(int id);
	void addUser(User u);
}
