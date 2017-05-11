package com.zhidian.views;

/**
* @ClassName: WebsiteDetailDTO2
* @Description: TODO(用于website-control-info.html)
* @author dongneng
* @date 2017年5月12日 上午4:21:20
*
*/
public class WebsiteDetailDTO2 extends WebsiteDetailDTO{
	private String unuseTime;
	private String unuseMan;
	private String alias;
	private String shortAddr;// 短地址，segmentfault.com,
	private String fullAddr;// 全地址
	public String getUnuseTime() {
		return unuseTime;
	}
	public void setUnuseTime(String unuseTime) {
		this.unuseTime = unuseTime;
	}
	public String getUnuseMan() {
		return unuseMan;
	}
	public void setUnuseMan(String unuseMan) {
		this.unuseMan = unuseMan;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getShortAddr() {
		return shortAddr;
	}
	public void setShortAddr(String shortAddr) {
		this.shortAddr = shortAddr;
	}
	public String getFullAddr() {
		return fullAddr;
	}
	public void setFullAddr(String fullAddr) {
		this.fullAddr = fullAddr;
	}
}
