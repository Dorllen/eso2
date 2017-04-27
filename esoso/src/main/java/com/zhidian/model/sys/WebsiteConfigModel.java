package com.zhidian.model.sys;

public class WebsiteConfigModel {
//	.setUserAgent(userAgent).setCharset(charset).setCycleRetryTimes(cycleTime)
//	.setSleepTime(sleepTime).setTimeOut(timeout).setUseGzip(useGzip);
	
	private String userAgent;
	private String charset;
	private int cycyleTime;// 循环次数,0拒绝设置
	private int sleepTime;//ms 0决绝设置
	private int timeout;// 0 拒绝设置。
	private boolean useGzip;// 默认false
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public int getCycyleTime() {
		return cycyleTime;
	}
	public void setCycyleTime(int cycyleTime) {
		this.cycyleTime = cycyleTime;
	}
	public int getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public boolean isUseGzip() {
		return useGzip;
	}
	public void setUseGzip(boolean useGzip) {
		this.useGzip = useGzip;
	}
}
