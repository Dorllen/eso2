package com.zhidian.bases.worms.processor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.zhidian.bases.WormEnumDefine;
import com.zhidian.bases.worms.pipeline.ResultSimplePipeline;
import com.zhidian.model.sys.PullResultBO;
import com.zhidian.model.sys.PullResultPageModel;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

public class SegmentFaultResultProcessor extends BaseResultPageProcessor<PullResultPageModel> {
	// private Logger log = LoggerFactory.getLogger(getClass());

	public SegmentFaultResultProcessor(PullResultPageModel pullPageModel) {
		super(pullPageModel);
	}

	public void process(Page page) { // long s = System.currentTimeMillis();
		Selectable select = page.getHtml().xpath("//section[@class^='widget-question']");
		setWatcherForProperty(select, page.getUrl().toString(), "内容主体部分",
				"xpath(\"//section[@class^='widget-question']\")");
		List<Selectable> resultList = select.nodes();
		Date d = new Date();
		if (resultList != null && resultList.size() > 0) {
			int temp = this.getObj().getPage() * this.getObj().getSize() - this.getObj().getResults().size();// 总共要取的数据
			if (temp > 0) {
				List<PullResultBO> results = this.getObj().getResults();
				PullResultBO search;
				for (int i = 0; i < resultList.size() && i < temp; i++) {
					try {
						Selectable result = resultList.get(i);
						if (result == null) {
							continue;
						}
						// 如果没法解析就跳出
						search = new PullResultBO();
						// 解析tile
						Selectable title = result.xpath("//h2[@class='h4']/a[1]/text()");
						search.setTitle(title.toString());
						// 监听是否异常
						setWatcherForProperty(title, page.getUrl().toString(), "title",
								"xpath(\"//h2[@class='h4']/a[1]/text()\")");
						// 解析content
						Selectable content = result.xpath("//p[@class='excerpt']/text()");
						search.setContent(content.toString());
						setWatcherForProperty(content, page.getUrl().toString(), "content",
								"xpath(\"//p[@class='excerpt']/text()\")");
						// 解析link
						Selectable link = result.xpath("//h2[@class='h4']/a[1]").links();
						String tempUrl = link.get();
						setWatcherForProperty(link, page.getUrl().toString(), "link",
								"xpath(\"//h2[@class='h4']/a[1]\").links()");

						if (!this.getObj().isUseSearch()) {
							tempUrl = removeUrlSearch(tempUrl);
						}
						search.setUrl(tempUrl);
						search.setDate(d);
						search.setUuid(DigestUtils.md5Hex(tempUrl));
						search.setName(this.getObj().getName());
						// 内容筛选，去除重复url
						if (results.size() > 0) {
							for (PullResultBO r : results) {
								if (r != null && r.getUuid().equals(search.getUuid())) {
									search = null;
									break;
								}
							}
							if (search != null) {
								results.add(search);
							}
						} else {
							results.add(search);
						}
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
				this.getObj().setResults(results);
				if ((this.getObj().getPage() * this.getObj().getSize()) - this.getObj().getResults().size() > 0) {// 收集数据到达几页
					// 放入请求schedule中
					// log.info("Again to Get Data , and want to get size ->
					// {}",
					// this.getObj().getSize() -
					// this.getObj().getResults().size());
					List<String> requests = new ArrayList<String>(1);
					if (this.getObj().getPagination() != null) {// 如果系统未设置分页符
						String url = this.getObj().getUrl() + this.getObj().getPagination();
						// this.getObj().setPage(this.getObj().getPage() + 1);
						this.currentPage++;// 当前访问页号
						url = MessageFormat.format(url, this.currentPage);
						if (url != null && url.length() > 0) {
							requests.add(url);
							page.addTargetRequests(requests);
						}
					} else {
						// 没有分页符，即直接结束请求
					}
				}
				if (page.getTargetRequests() == null || page.getTargetRequests().size() == 0) {
					// 筛选结果集
					List<PullResultBO> list = this.getObj().getResults();
					if (list != null && list.size() > 0) {
						if (list.size() > ((this.getObj().getPage() - 1) * this.getObj().getSize())) {// 是否获取了有当前页号的数据。
							// 超过了需要采取的值，则取超出的部分
							this.getObj().setResults(list
									.subList((this.getObj().getPage() - 1) * this.getObj().getSize() + 1, list.size()));
						} else {
							this.getObj().setResults(null);// 没有超出置空
						}
					}
					page.putField(WormEnumDefine.Object.搜索结果页.name(), this.getObj());
					page.putField(ResultSimplePipeline.STATUS, WormEnumDefine.Status.结束.name());
				} else {
					page.setSkip(true);
				}
			}
		}
		// long e = System.currentTimeMillis();
		// log.info("{} spend time is -> {} s", page.getUrl().toString(), (e -
		// s) / 1000);
	}

	public static void main(String[] args) {
		// Spider.create(new SegmentFaultProcessor())
		// .addPipeline(new SegmentFaultSearchPipeline())
		// .get("https://segmentfault.com/search?q=python");

		List<String> list = new ArrayList<String>(26);
		list.add("!");
		list.add("2");
		System.out.println(list.size());
	}
}
