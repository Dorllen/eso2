
/**
* @Title: SegmentFaultPageProcessor.java
* @Package com.zhidian.bases.worms.processors
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-21 上午12:42:15
* @version V1.0
*/
package com.zhidian.bases.worms.processor;

import com.zhidian.bases.WormEnumDefine;
import com.zhidian.bases.worms.pipeline.BasePagePipeline;
import com.zhidian.model.sys.PullPageObjectModel;
import com.zhidian.model.sys.SegmentfaultPageBO;
import com.zhidian.model.websites.answer.SegmentfaultPageRObject;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @ClassName: SegmentFaultPageProcessor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-21 上午12:42:15
 *
 */
public class SegmentFaultPageProcessor extends BasePageProcessor<SegmentfaultPageBO> {
	// private Logger log = LoggerFactory.getLogger(getClass());

	public SegmentFaultPageProcessor(PullPageObjectModel pom) {
		super(pom);
	}

	// public static void main2(String[] args) {
	// PullPageObjectModel<SegmentfaultPageBO> pom = new
	// PullPageObjectModel<SegmentfaultPageBO>();
	// pom.setUuid(org.apache.commons.codec.digest.DigestUtils.md5Hex("http://segmentfault.com/q/1010000003713912"));
	// pom.setName("segmentfault");
	// pom.setDownloadPath("d:/css/segmentfault");// 当前css的项目的根地址，不能加/
	// List<CssInfoModel> list = new ArrayList<CssInfoModel>();
	// CssInfoModel mo = new CssInfoModel();
	// mo.setName("qa");
	// mo.setUuid("95ef7bb5b85546e6e3f05c7a1f3f8f41");
	// mo.setUrl("https://static.segmentfault.com/v-58f1f176/qa/css/qa.css");
	// mo.setVersion("0.0.1.0");
	// mo.setCssPath("d:/css/segmentfault/0.0.1.0");
	// list.add(mo);
	// pom.setCssModel(list);
	// SegmentFaultPageProcessor page = new SegmentFaultPageProcessor(pom);
	// page.getSite().addHeader("Origin", "https://segmentfault.com");
	// Spider.create(page).addUrl("http://segmentfault.com/q/1010000003713912").addPipeline(new
	// NullPipeline()).run();
	// System.out.println(JSON.toJSONString(pom));
	// }

	@Override
	public int pageHandler(Page page) {
		try {
			// 页面解析开始
			SegmentfaultPageBO sbo = new SegmentfaultPageBO();
			SegmentfaultPageRObject handler = new SegmentfaultPageRObject();
			Selectable title = page.getHtml().$("div.post-topheader");
			// 手动网站取值监控
			setWatcherForProperty(title, page.getUrl().toString(), "div's title", "$(\"div.post-topheader\")");
			
			Selectable content = page.getHtml().$("div.main");
			setWatcherForProperty(content,page.getUrl().toString(),"div's content","$(\"div.post-topheader\")");
			
			// 基本处理区域
			Selectable tTitle = title.xpath("//h1[@id='questionTitle']//a/allText()");
			setWatcherForProperty(tTitle,page.getUrl().toString(),"only title","xpath(\"//h1[@id='questionTitle']//a/allText()\")");
			this.getObj().setTitle(tTitle.get());
			
			// question fmt
			Selectable tContent = content.$("div.question.fmt").xpath("/allText()");
			setWatcherForProperty(tContent,page.getUrl().toString(),"only content","$(\"div.question.fmt\").xpath(\"/allText()\")");
			this.getObj().setResultContent(tContent.get());

			// 内容处理区域.放PageBO的子文件夾
			handler.setId(this.getObj().getUuid());// uuid
			handler.setOriginUrl(page.getUrl().toString());// 页面源地址
			handler.setTitle(title.get());
			handler.setMain(content.get());
			sbo.setContents(handler);
			this.getObj().setModel(sbo);
			page.putField(BasePagePipeline.STATUS, WormEnumDefine.Status.结束.name());
			page.putField(WormEnumDefine.Object.内容详情页.name(), this.getObj());

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
