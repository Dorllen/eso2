package com.zhidian.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
* @ClassName: RegExpUtils
* @Description: TODO(这里用一句话描述这个类的作用)
* @author dongneng
* @date 2017年3月29日 上午12:48:45
*
*/
public class RegExpUtils {
	
	
	/**
	* @Title: convertString2List
	* @Description: TODO(用于将segmentfault|github|cnblog转换成数组)
	* @param @param str
	* @param @return    参数
	* @return List<String>    返回类型
	* @throws
	*/
	public static List<String> convertString2List(String str){
		// regexp is : \\|
		// |arr 2  内容自动忽略null值
		// | 0 
		// seg|arr 2
		// seg|arr|   2
		// |seg| 2
		
		List<String> list = null;
		if(!StringUtils.isEmpty(str)){
			// 需要去重，去非法参数
			String[] strArr = str.split("\\|");
			if(strArr!=null&&strArr.length>0){
				list = new ArrayList<String>();
				for(String s : strArr){
					if(!StringUtils.isEmpty(s)){
						if(!list.contains(s)){
							list.add(s);
						}
					}
				}
			}
			
			// 失败的正则取值
//			Matcher matcher = Pattern.compile("[^\\|?\\w+\\-(?:\\w+\\|?$)]{1,}").matcher(str);//([^''+]\\w*)\\|([^''+]\\w*)
//			if(matcher.find()){
//				list = new ArrayList<String>();
//				for(int i=1;i<=matcher.groupCount();i++){
//					if(StringUtils.isEmpty(matcher.group(i))){
//						continue;
//					}
//					if(!list.contains(matcher.group(i))){
//						list.add(matcher.group(i));
//					}
//				}
//			}
		}
		return list;
	}
	
	/**
	* @Title: convertString2List2
	* @Description: TODO(对","进行正则转换)
	* @param @param str
	* @param @return    参数
	* @return List<String>    返回类型
	* @throws
	*/
	public static List<String> convertString2List2(String str){
		List<String> list = null;
		if(!StringUtils.isEmpty(str)){
			// 需要去重，去非法参数
			String[] strArr = str.split(",");
			if(strArr!=null&&strArr.length>0){
				list = new ArrayList<String>();
				for(String s : strArr){
					if(!StringUtils.isEmpty(s)){
						if(!list.contains(s)){
							list.add(s);
						}
					}
				}
			}
		}
		return list;
	}
	
	public static List<String> convertString2List2(String str,String prefix){
		List<String> list = null;
		if(!StringUtils.isEmpty(str)){
			// 需要去重，去非法参数
			String[] strArr = str.split(",");
			if(strArr!=null&&strArr.length>0){
				list = new ArrayList<String>();
				for(String s : strArr){
					if(!StringUtils.isEmpty(s)){
						if(!list.contains(s)){
							list.add(prefix+s);
						}
					}
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		String str = "seg|arr";
		System.out.println("string split:"+str.split("\\|").length);
		//  (?:^\\|+(\\w+))|(?:(\\w+)\\|$)
//		boolean b = str.matches("\\|*(\\w*)\\|*");//[(^\\|+\\w*)|([^''+]\\w*\\|+$)]
//		System.out.println(b);
		List<String> list = convertString2List(str);
		System.out.println(list!=null?list.size():"is null!");
		if(list!=null){
			for(String s: list){
				System.out.println(s);
			}
		}
	}
}
