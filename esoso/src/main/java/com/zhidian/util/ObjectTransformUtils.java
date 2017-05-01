package com.zhidian.util;

import com.alibaba.fastjson.JSON;
import com.zhidian.model.sys.RequestHeaderModel;

public class ObjectTransformUtils {
	
	public static String trnsToString(Object o){
		return JSON.toJSONString(o);
	}
	
	public static void main(String[] args) {
		System.out.println(JSON.parseObject("", RequestHeaderModel.class)==null);
	}
}
