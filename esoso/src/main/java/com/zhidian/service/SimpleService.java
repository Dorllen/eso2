
/**
* @Title: SimpleService.java
* @Package com.zhidian.services
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-18 下午11:48:52
* @version V1.0
*/
package com.zhidian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhidian.mapper.UserMapper;
import com.zhidian.model.User;

/**
 * @ClassName: SimpleService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-18 下午11:48:52
 *
 */
@Service
public class SimpleService {
	@Autowired
	UserMapper userMapper;
	
	public List<User> getAllUser(){
		return userMapper.getAllUser();
	}
	
}
