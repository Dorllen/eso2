package com.zhidian.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	public static void main(String[] args) {
		cssUrl();
//		String url = "https://static.segmentfault.com/v-58d65f3a/global/css/global.css?id=sdfdf";
//		Pattern pattern = Pattern.compile("(^.*\\.)(?:css$|css(\\?.*)$)");
//		Matcher matcher = pattern.matcher(url);
//		System.out.println(matcher.find());
//		System.out.println(matcher.groupCount());
//		System.out.println(matcher.group(1));
//		System.out.println(matcher.group(2));
	}

	private static void cssUrl() {
		String path= "<link rel='stylesheet' href='https://static.segmentfault.com/v-58d65f3a/global/css/global.css'>";
		boolean b = path.matches("<link\\s*rel='stylesheet'\\s*href='(.*\\.css)'\\s*>");
		System.out.println(b);
//		Pattern p = Pattern.compile("<link\\s*rel='stylesheet'\\s*href='(^.*(?:\\.css$|\\.css\\?.*$))'\\s*>");
//		Matcher m = p.matcher(path);
//		if(m.matches()){
//			for(int i = 0;i<=m.groupCount();i++){
//				System.out.println(i+":"+m.group(i));
//			}
//			System.out.println(m.groupCount());
//			System.out.println(m.group(0));
//		}else{
//			System.out.println(m.groupCount());
//		}
	}
}
