package com.zhidian.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhidian.bases.SearchEngineEnumDefine;
import com.zhidian.model.sys.ResultPageBO2;
import com.zhidian.model.sys.VersionBO;
import com.zhidian.service.SearchService;
import com.zhidian.views.ResultPageVO;

@Controller
public class TestMainController {

	@Autowired
	SearchService searchService;

	@RequestMapping("/index_")
	public String test() {
		return "index_";
	}

	@RequestMapping("/welcome")
	public String test2() {
		return "welcome";
	}

	@RequestMapping("/ttt")
	@ResponseBody
	public ResultPageVO ttt(@RequestParam(value = "t", required = false) long date) {
//		String str = "1492867587002";
		long l = new Long(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = sdf.format(l);
		System.out.println(s);
		ResultPageVO r = new ResultPageVO();
		r.setCreTime(new Date());
		r.setOrigin("origin");
		r.setSort("hot");
		return r;
	}
	public static void main(String[] args) {
//		System.out.println(new Date().getTime());
		String str = "1492867587002";
		long l = new Long(str);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String st = sdf.format(l);
		Date s;
		try {
			s = sdf.parse(st);
			System.out.println(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//1492867516560
	//1492867587002
	@RequestMapping("/segmentfault")
	public ModelAndView segmentfault() {
		ModelAndView model = new ModelAndView();
		ResultPageVO result = new ResultPageVO();
		result.setCreTime(new Date());
		VersionBO b = new VersionBO();
		List<String> css = new ArrayList<String>();
		css.add("css/results/answer/0.0.0.0/result.css");
		b.setDefCss(css);
		List<String> js = new ArrayList<String>();
		js.add("js/results/answer/0.0.0.0/result.js");
		b.setDefJs(js);
		List<ResultPageBO2> rs = new ArrayList<ResultPageBO2>();
		ResultPageBO2 e = new ResultPageBO2();
		e.setTitle("中国世界人民大家好，我是白斑病中国世界人民大家好，我是白斑病中国世界人民大家好，我是白斑病");
		e.setCreateTime(new Date());
		e.setFrom("segmentfault");
		e.setOriginUrl("http://www.segmentfault.com/xxxxxx");
		e.setTags("");
		e.setView(3 + "k");
		e.setScores(12.2 + "");
		e.setId("abcdefg456789");
		e.setMark("hot");
		e.setUrl("/article/" + e.getId());
		e.setContents(
				"中国式教育，但是既有python2.7.6，又有python3.4.0。但是默认的python命令是python2.7.6，我要想执行python3.4.0就必须输入python3。这是我的ubuntu自带的所有的python，如下图 所以我想问，怎么做...");

		rs.add(e);
		result.setResults(rs);
		result.setOrigin("origin");
		result.setVersion(b);
		result.setSort("hot");
		result.setKey("安眠药");
		result.setDefaultWebsites(SearchEngineEnumDefine.Type.问答.getDefaultWebsites());
		result.setDefaults(SearchEngineEnumDefine.Type.问答.getDefaults());
		result.setType(SearchEngineEnumDefine.Type.问答.getValue());
		result.setFrom_(SearchEngineEnumDefine.Type.问答.getDefaults());
		result.setPage(1);
		result.setUrl(searchService.handlerUrl("t=" + result.getType() + "&o=" + result.getOrigin() + "&q=安眠药&f="
				+ SearchEngineEnumDefine.Type.问答.getDefaults(), "hot", "up"));
		if (StringUtils.isNotEmpty(result.getOrigin())
				&& SearchEngineEnumDefine.From.源地址.getValue().equals(result.getOrigin())) {
			result.setLastPage(result.getPage() + 1);
		}
		model.addObject("Message", result);
		System.out.println("in......");
		model.setViewName("results/answer/0.0.0.0/index");
		return model;
	}
}
