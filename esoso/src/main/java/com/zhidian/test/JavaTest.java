
/**
* @Title: JavaTest.java
* @Package com.zhidian.test
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-21 上午1:37:55
* @version V1.0
*/
package com.zhidian.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

import com.alibaba.fastjson.JSON;
import com.zhidian.model.sys.WebsiteCssConfigModel;
import com.zhidian.model.websites.answer.SegmentfaultPageRObject;

/**
 * @ClassName: JavaTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-21 上午1:37:55
 *
 */
public class JavaTest {
	public static void main(String[] args) {

		//https://segmentfault.com/q/1010000007838751
		System.out.println(DigestUtils.md5Hex("https://segmentfault.com/q/1010000007838751"));
		
		//[{"name":"qa.css","url":"http://segmentfault.con/static/css/qa.css","useSearch":false,"version":"0.0.0.0","website":"segmentfault"},{"name":"qa.css","url":"http://segmentfault.con/static/css/global.css","useSearch":false,"version":"0.0.0.0","website":"seg
//		exchangeDataWebsiteCssConfig();
//		String str = "[{\"name\":\"qa.css\",\"url\":\"http://segmentfault.con/static/css/qa.css\",\"useSearch\":false,\"version\":\"0.0.0.0\",\"website\":\"segmentfault\"},{\"name\":\"qa.css\",\"url\":\"http://segmentfault.con/static/css/global.css\",\"useSearch\":false,\"version\":\"0.0.0.0\",\"website\":\"seg";
//		System.out.println(str.length());
		
		// 数据转换工具。将segmentfault的数据，从title、content表转到main.txt中。json格式
		// exchangeDataFromFile();

		// t1();
		// t2();
		// outstream();
		// time();
		// time2();
		// listSort();
		// jsonObject();

	}

	private static void exchangeDataWebsiteCssConfig() {
		// 为WebsiteCssConfigModel做初始的json转换
		List<WebsiteCssConfigModel> list = new ArrayList<WebsiteCssConfigModel>();
		WebsiteCssConfigModel css = new WebsiteCssConfigModel();
		css.setName("qa.css");
		css.setUrl("http://segmentfault.con/static/css/qa.css");
		css.setUseSearch(false);
		css.setVersion("0.0.0.0");
		css.setWebsite("segmentfault");
		list.add(css);
		css = new WebsiteCssConfigModel();
		css.setName("qa.css");
		css.setUrl("http://segmentfault.con/static/css/global.css");
		css.setUseSearch(false);
		css.setVersion("0.0.0.0");
		css.setWebsite("segmentfault");
		list.add(css);
		System.out.println(JSON.toJSONString(list));
		System.out.println(JSON.toJSONString(list).length());
	}

	private static void exchangeDataFromFile() {
		// 数据转换工具。将segmentfault的数据，从title、content表转到main.txt中。json格式
		String f = getOrignData("https://segmentfault.com/q/1010000003713912");// "https://segmentfault.com/q/1010000003709420"
		System.out.println(f);
		writeData(f);// to main.txt

	}

	private static void writeData(String data) {
		try {
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File("c://main.txt")), "utf-8"));
			bw.write(data);
			bw.flush();
			if (bw != null) {
				bw.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private static String getOrignData(String sr) {
		SegmentfaultPageRObject r = new SegmentfaultPageRObject();
		r.setId(DigestUtils.md5Hex(sr));
		r.setOriginUrl(sr);
		File f = new File("c://title.txt");
		// bufferedReader(f);

		// inputStream(f);
		r.setTitle(bufferedReader(f));
		System.out.println(r.getTitle());
		r.setName("segmentfault");
		r.setMain(bufferedReader(new File("c://content.txt")));

		System.out.println(r.getTitle().length());
		System.out.println(r.getMain().length());
		String str = JSON.toJSONString(r);
		// System.out.println(str);
		System.out.println(str.length());
		return str;
	}

	private static void inputStream(File f) {
		try {
			String str = "";
			InputStream br = new BufferedInputStream(new FileInputStream(f));
			OutputStream out = new FileOutputStream(new File("c://content.txt"));
			byte[] c = new byte[1024];
			while (br.read(c) != -1) {
				// str = str + new String(c,"utf-8");
				out.write(c);
			}
			System.out.println(str);
			if (br != null) {
				br.close();
			}
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String bufferedReader(File f) {
		String str = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "gbk"));
			// char[] c = new char[1024]; // 都字符数组是有问题的！
			// while (br.read(c) >0) {
			// str = str + new String(c);
			//
			// }
			String temp;
			while ((temp = br.readLine()) != null) {
				str = str + temp;

			}
			// System.out.println(str);
			if (br != null) {
				br.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	private static void jsonObject() {
		try {
			Class<?> claz = Class.forName("com.zhidian.model.User");
			Object u = JSON.parseObject("{\"address\":\"sh\",\"age\":17,\"id\":2}", claz);
			System.out.println(claz);
			System.out.println(JSON.toJSONString(u));
			// Object o = claz.newInstance();
			// Object e = JSON.parse("");
			// System.out.println(o);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void listSort() {
		List<String> list = new ArrayList<String>();
		list.add("name");
		list.add("ggg");
		list.add("bbbb");
		list.add("2");
		list.sort(new Comparator<String>() {
			public int compare(String o1, String o2) {
				if (o1 != null) {
					if (o2 != null) {
						return o1.length() - o2.length();
					} else {
						return 1;
					}
				} else if (o2 != null) {
					return -1;
				}
				return 0;
			}
		});
		for (String str : list) {
			System.out.println(str);
		}
	}

	private static void time2() {
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 2);
		Date d2 = c.getTime();
		System.out.println(d2.getTime() - d.getTime());
	}

	private static void time() {
		// String str = "{'name':'dongneng','age':15}";
		// Map<String,Object> map = (Map<String, Object>) JSON.parse(str);
		// for(Map.Entry<String, Object> entry : map.entrySet()){
		// System.out.println(entry.getKey()+"-"+entry.getValue());
		// }
		// Date d = new Date();
		// long l = d.getTime();
		// Calendar c = Calendar.getInstance();
		// c.add(Calendar.DATE, 2);
		// Date d3 = c.getTime();
		// System.out.println(d3.getTime()-l);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		// String r1 = sdf.format(d);
		// System.out.println(r1);
		// String r2 = sdf.format(d3);
		// System.out.println(r2);
		// Date d4 = new Date(l+60*60*8*1000);
		// System.out.println(sdf.format(d4));// 8小时

		Timestamp t = new Timestamp(new Date().getTime());
		Date d = t;
		System.out.println(t);
	}

	private static void outstream() {
		File f = new File("d:/css" + "/" + "0.0.0.2" + "/" + "pa" + ".css");// 取当前站点在当前项目的css路径
		if (!f.exists()) {
			if (!f.getParentFile().exists()) {
				boolean b = f.getParentFile().mkdir();
				System.out.println(b);
			}
			if (f.isFile()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			OutputStream out = new FileOutputStream(f);
			try {
				out.write("my name is dongneng".getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void t2() {
		// <link rel='stylesheet'
		// href='https://static.segmentfault.com/v-58f1f176/global/css/global.css'/>
		// <link
		// rel='stylesheet'!href='https://static.segmentfault.com/v-58f1f176/global/css/responsive.css'
		// tiele='sfdf'/>
		// <link rel='stylesheet'
		// href='https://static.segmentfault.com/v-58f1f176/qa/css/qa.css'/>
		// <link rel='stylesheet'
		// href='https://static.segmentfault.com/v-58f1f176/global/css/global.css'/>
		String str = "<link rel='apple-touch-icon' href='https://static.segmentfault.com/v-58f1f176/global/img/touch-icon.png'>"
				+ "<meta name='msapplication-TileColor' content='#009a61'/>"
				+ "<meta name='msapplication-square150x150logo' content='https://static.segmentfault.com/v-58f1f176/global/img/touch-icon.png'/>"
				+ "<link rel='alternate' type='application/atom+xml' title='SegmentFault 最新问题' href='/feeds/questions'><link rel='alternate' type='application/atom+xml' title='SegmentFault 最新文章' href='/feeds/blogs'>"
				+ "<link rel='stylesheet' href='https://static.segmentfault.com/v-58f1f176/global/css/global.css'/>"
				+ "<link rel='stylesheet'  href='https://static.segmentfault.com/v-58f1f176/qa/css/qa.css'/>"
				+ "<link rel='stylesheet' href='https://static.segmentfault.com/v-58f1f176/global/css/responsive.css' title='safa'  />";
		// 考虑顺序，考虑'"，考虑空格
		Matcher matcher = Pattern.compile("<link.*rel='stylesheet'.*href=['\"]([0-9A-Za-z\\-\\:\\/\\.]*?)['\"]\\s*/>")
				.matcher(str);
		while (matcher.find()) {
			System.out.println(matcher.group(1));
			// System.out.println(matcher.group(2));
		}

		// <(\w+)[^>]*>(.*?)</\1>
		String regEx = "<a\\s*>\\s*(.*?)</a>";
		String s = "<a> 123</a><a> 456</a><a>789</a>";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(s);
		while (mat.find()) {
			System.out.println(mat.group(1));
		}
		// String source = "<a title=中国体育报 href=''>aaa</a><a title='北京日报'
		// href=''>bbb</a>";
		// String reg = "<a>\\s*title=['\"]?(.*?)['\"]?(\\s.*?)?>";
		// Matcher m = Pattern.compile(reg).matcher(source);
		// while (m.find()) {
		// System.out.println(m.group(1));
		// }
	}

	private static void t1() {
		String url = "http://demo.com/js/ss/ee.css?ddd=123";
		Pattern pattern = Pattern.compile("^.*\\.css.*$");
		Matcher matcher = pattern.matcher(url);
		System.out.println(matcher.matches());
	}
}
