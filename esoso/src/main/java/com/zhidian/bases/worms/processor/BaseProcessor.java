package com.zhidian.bases.worms.processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public abstract class BaseProcessor implements PageProcessor {
	private String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv/:51.0) Gecko/20100101 Firefox/51.0";

	private int timeout = 1000;

	private String charset = "UTF-8";

	private int sleepTime = 1000;

	private int cycleTime = 0;

	private boolean useGzip = false;

	public Site getSite() {
		return Site.me().setUserAgent(userAgent).setCharset(charset).setCycleRetryTimes(cycleTime)
				.setSleepTime(sleepTime).setTimeOut(timeout).setUseGzip(useGzip);
	}

	public static boolean isCss(String url) {
		return Pattern.matches("^.*\\.(?:css$|css\\?.*$)", url);
	}

	public static Matcher cssMatcher(String url) {
		Pattern pattern = Pattern.compile("(^.*\\.)(?:css$|css(\\?.*)$)");
		return pattern.matcher(url);
	}
}
