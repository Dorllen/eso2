/**
 * @Title: AbstractProcessor.java
 * @Package com.zhidian.bases.worms.processors
 * @Description: TODO(用一句话描述该文件做什么)
 * @author dongneng
 * @date 2017-3-21 上午12:06:48
 * @version V1.0
 */
package com.zhidian.bases.worms.processor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.zhidian.bases.AppEnumDefine;
import com.zhidian.model.sys.CssInfoModel;
import com.zhidian.model.sys.CssObjectModel;
import com.zhidian.model.sys.PullDataWatchObject;
import com.zhidian.model.sys.PullPageObjectModel;
import com.zhidian.model.sys.RequestHeaderModel;
import com.zhidian.model.sys.WebsiteBO;
import com.zhidian.model.sys.WebsiteConfigModel;
import com.zhidian.util.BasicUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @ClassName: AbstractProcessor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-21 上午12:06:48
 * 
 */
public abstract class BasePageProcessor extends BaseProcessor {
	private PullPageObjectModel obj;

	public BasePageProcessor() {
		this.obj = new PullPageObjectModel();
	}

	public BasePageProcessor(PullPageObjectModel pom) {
		this.obj = pom;
	}

	public PullPageObjectModel getObj() {
		return obj;
	}

	public void setObj(PullPageObjectModel obj) {
		this.obj = obj;
	}

	public void process(Page page) {
		if (isCss(page.getUrl().toString())) {
			try {
//				System.out.println("進入Css處理識別中....");
				cssHandler(page);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			int i = pageHandler(page);
			if (i == 1) {
				// 正常执行
				List<String> cssPaths = page.getHtml().xpath("//link[contains(@rel, 'stylesheet')]").$("link", "href")
						.all();
				cssPaths.toString();// cssPaths不調用會出錯
//				System.out.println("CSS:" + JSON.toJSONString(cssPaths));
//				System.out.println("CSS Model:" + JSON.toJSONString(this.getObj().getCssModel()));
				if (cssPaths != null && cssPaths.size() > 0) {
					if (this.getObj() != null && this.getObj().getCssModel() != null) {
						List<String> temp = new ArrayList<String>(cssPaths.size());
						for (CssInfoModel css : this.getObj().getCssModel()) {
							for (String str : cssPaths) {
								if (cssUrlEquals(str, css)) {
									temp.add(str);
								}
							}
						}
						page.addTargetRequests(temp);
					} else {
						page.addTargetRequests(cssPaths);
					}
				}
			}
		}
	}

	public abstract int pageHandler(Page page);

	public void cssHandler(Page page) throws Exception {
		if (this.getObj().getCssModel() != null) {
			for (CssInfoModel css : this.getObj().getCssModel()) {
				// 比较
				if (cssUrlEquals(page.getUrl().toString(), css)) {
					// 比较内容
					String html = page.getRawText();
					if (StringUtils.isNotEmpty(html)) {
						String code = DigestUtils.md5Hex(html);
						if (code != null) {
							if (code.equals(css.getUuid())) {
								// 相等则忽略
							} else {
								// 准备下载
								CssObjectModel csModel = new CssObjectModel();
								csModel.setVersion(BasicUtils.newVersion(css.getVersion()));
								csModel.setDownloadPath(css.getCssPath() + "/" + css.getWebSite() + "/"
										+ csModel.getVersion() + "/" + css.getName() + ".css.temp");// 以.temp存储
								File f = new File(csModel.getDownloadPath());// 取当前站点在当前项目的css路径
								while (true) {// 循环建立，直到建立成功。创建正确的版本文件目录
									if (!f.exists()) {
										f.mkdirs();
										if (f.isFile()) {
											try {
												f.createNewFile();
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
										break;
									} else {
										// 存在文件则版本+1
										csModel.setVersion(BasicUtils.newVersion(csModel.getVersion()));
										String f_ = "/" + css.getWebSite() + "/" + csModel.getVersion() + "/"
												+ css.getName() + ".css";
										csModel.setCssPath(css.getCssPath() + f_);
										csModel.setDownloadPath(css.getAbCssPath() + f_);// 存储当前站点在当前项目的css路径
										f = new File(csModel.getDownloadPath() + ".temp");// 以.temp存储
									}
								}
								csModel.setUuid(code);
								csModel.setDate(new Date());
								csModel.setUrl(page.getUrl().toString());
								csModel.setSearch(BasicUtils.urlSearchPart(page.getUrl().toString()));
								csModel.setName(css.getName());

								OutputStream out = new FileOutputStream(f);
								try {
									out.write(html.getBytes());
									csModel.setDownload(true);// 確定下載
									this.getObj().addCssPaths(csModel);// 增加入
									this.getObj().setChanged(true);// 代表页面变动了
									setWatcherForCss(csModel);
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
							}
						}
					}
				}
			}
		}
	}

	/**
	 * @Title: cssUrlEquals @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @param pageUrl
	 *            page.getUrl
	 * @param @param
	 *            dbCssUrl 数据库css的url @param @return 参数 @return boolean
	 *            返回类型 @throws
	 */
	public static boolean cssUrlEquals(String pageUrl, CssInfoModel dbCss) {// dbCss可能是https://static.segmentfault.com/v-590a963a/global/css/global.css|
		if (StringUtils.isNotEmpty(pageUrl) && pageUrl != null) {
			if (pageUrl.equals(dbCss.getUrl())) {
				return true;
			} else {
				if (StringUtils.isNotEmpty(dbCss.getUrl())) {
					// http://wwww.baidu.com/css/abc.css
					// www.baidu.com/css/abc.css?v=123456
					if (!pageUrl.contains(".css")) {
						return false;
					}
					if (dbCss.getUseSearch()) {
						// 使用abc.css与abc.css?v=13文件区别
						String[] urls = dbCss.getUrl().split("\\|");
						if (urls != null) {
							for (String s : urls) {
								if (s.length() > 0) {
									if (s.equals(pageUrl)) {
										return true;
									} else {
										return false;
									}
								}
							}
						}
					} else {// 不使用后缀
						int i = pageUrl.lastIndexOf(".css");
						if (i <= 0) {
							return false;
						}
						String name_ = pageUrl.substring(pageUrl.lastIndexOf("/") + 1, i);
						if (name_.equals(dbCss.getName())) {
							String f1 = pageUrl.substring(0, pageUrl.lastIndexOf("/"));
							String[] urls = dbCss.getUrl().split("\\|");
							if (urls != null) {
								for (String s : urls) {
									if (s.length() > 0) {
										String f2 = s.substring(0, s.lastIndexOf("/"));
										if (BasicUtils.checkUrlEquals(f1, f2)) {
											return true;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean computeChange(String content, String template) {
		// 校验标签变动幅度

		return false;
	}

	@Override
	public Site getSite() {
		return loadingConfig(super.getSite());
	}

	public Site loadingConfig(Site site) {
		// WebsiteConfigModel WebsiteConfigModel
		if (this.getObj().getWebsiteConfig() != null) {
			WebsiteConfigModel config = this.getObj().getWebsiteConfig();
			if (config.getCharset() != null && config.getCharset().length() > 0) {
				site.setCharset(config.getCharset());
			}
			if (config.getCycyleTime() != 0) {
				site.setCycleRetryTimes(config.getCycyleTime());
			}
			if (config.getUserAgent() != null && config.getUserAgent().length() > 0) {
				site.setUserAgent(config.getUserAgent());
			}
			if (config.isUseGzip()) {
				site.setUseGzip(true);
			}
			if (config.getSleepTime() / 1000 > 0) {
				site.setSleepTime(config.getSleepTime());
			}
			if (config.getTimeout() > 0) {
				site.setTimeOut(config.getTimeout());
			}
		}
		// RequestHeaderModel
		if (this.getObj().getWebsite() != null) {
			WebsiteBO web = this.getObj().getWebsite();// 站點信息.用於增加請求頭。
			if (web.getRequestHeaders() != null && web.getRequestHeaders().size() > 0) {
				for (RequestHeaderModel r : web.getRequestHeaders()) {
					if (r != null) {
						if (r.getType() != null && RequestHeaderModel.CookieType.equals(r.getType())) {// 請求类型
							site.addCookie(r.getName(), r.getValue());
						} else {
							site.addHeader(r.getName(), r.getValue());
						}
					}
				}
			}
		}
		return site;
	}

	public void setWatcherForProperty(Selectable select, String url, String nameDescri, String xpath) {
		if (select == null || StringUtils.isEmpty(select.get())) {
			PullDataWatchObject watcher = new PullDataWatchObject();
			watcher.setName(nameDescri);// segmentfault页面是div的字符串title
			watcher.setUrl(url);
			watcher.setTimes(new Date());
			watcher.setSign(this.getObj().getSign());
			watcher.setWebsite(this.getObj().getName());
			watcher.setXpathContent(xpath);
			watcher.setType(AppEnumDefine.WormLogType.详情页页面解析.name());
			this.getObj().addErrorWatcher(watcher);
		}
	}

	public void setWatcherForCss(CssObjectModel csModel) {
		if (csModel != null) {
			PullDataWatchObject watcher = new PullDataWatchObject();
			watcher.setName(csModel.getName());// css则是css的名字
			watcher.setUrl(this.getObj().getUrl());// 存页面的地址，不要是css的地址
			watcher.setTimes(new Date());
			watcher.setSign(this.getObj().getSign());
			watcher.setWebsite(this.getObj().getName());
			watcher.setXpathContent(csModel.getUrl());// css的地址
			watcher.setType(AppEnumDefine.WormLogType.详情页CSS变动.name());
			this.getObj().addErrorWatcher(watcher);
		}
	}
}
