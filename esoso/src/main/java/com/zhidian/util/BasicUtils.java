package com.zhidian.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class BasicUtils {
	public static String newVersion(String oldVersion) throws Exception {
		if (StringUtils.isNotEmpty(oldVersion)) {
			String[] sArr = oldVersion.split("\\.");
			if (sArr != null && sArr.length == 4) {
				// 9999.9999.9999
				// 长度为4
				int i0 = Integer.parseInt(sArr[3]);
				int i1 = Integer.parseInt(sArr[2]);
				int i2 = Integer.parseInt(sArr[1]);
				int i3 = Integer.parseInt(sArr[0]);
				if (i0 >= 9999) {
					i0 = 0;
					if (i1 >= 9999) {
						i1 = 0;
						if (i2 >= 9999) {
							i2 = 0;
							if (i3 >= 9999) {
								throw new Exception("版本超出最大版本:9999.9999.9999.9999");
							} else {
								i3++;
							}
						} else {
							i2++;
						}
					} else {
						i1++;
					}
				} else {
					i0++;
				}
				return i3 + "." + i2 + "." + i1 + "." + i0;

			} else {
				throw new Exception("旧version有误：" + oldVersion);
			}
		} else {
			return "0.0.0.1";// 新建一个
		}
	}

	public static String urlSearchPart(String url) {
		if (StringUtils.isNotEmpty(url)) {
			Matcher matcher = Pattern.compile("(?:http:\\/\\/|http://www\\.|www\\.)?.*\\?([0-9A-Za-z&!=\\-]*)")
					.matcher(url);
			if (matcher.matches()) {
				return matcher.group(1);
			}
		}
		return null;
	}

	public static boolean checkUrlEquals(String url1, String url2) {
		// http://www.baidu.com/css
		// www.baidu.com/css
		// baidu.com/css
		if (StringUtils.isNotEmpty(url1) && StringUtils.isNotEmpty(url2)) {
			url1 = fullUrl(url1);
			url2 = fullUrl(url2);
			if (url1.equals(url2)) {
				return true;
			}
		}
		return false;
	}

	public static String fullUrl(String url) {
		if (url != null && url.indexOf("www.") == -1) {// baidu.com/www.abc/css/abc.css
														// ok?
			url = "www." + url;
		}
		if (url.indexOf("http://") == -1) {
			url = "http://" + url;
		}
		return url;
	}

	public static void main(String[] args) {
		// String s = "www.baidu.com/css";
		// String s2 = "baidu.com/css";
		// if (checkUrlEquals(s, s2)) {
		// System.out.println(true);
		// }

		// String s = "0.12.13.15";
		// try {
		// System.out.println(newVersion(s));
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		String str = "http://baidu.com?id=123&name=dongneng";
		// System.out.println(urlSearchPart(str));
		Matcher matcher = Pattern.compile("(?:http:\\/\\/|http://www\\.|www\\.)?.*\\?([0-9A-Za-z&!=\\-]*)")
				.matcher(str);
		if (matcher.find()) {
			System.out.println(matcher.group(1));
		}
	}
}
