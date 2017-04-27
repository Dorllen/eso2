/**
 * @Title: Mybatis.java
 * @Package com.zhidian.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author dongneng
 * @date 2017-3-18 下午9:14:26
 * @version V1.0
 */
package com.zhidian.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @ClassName: Mybatis
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-18 下午9:14:26
 * 
 */
public class Mybatis {

	public final static String PATH = "classpath:mybatis.xml";

	private static SqlSessionFactory sqlSessionFactory;

	public static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			InputStream inputStream = null;
			try {
				inputStream = Resources.getResourceAsStream(PATH);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);
		}
		return sqlSessionFactory;
	}

}
