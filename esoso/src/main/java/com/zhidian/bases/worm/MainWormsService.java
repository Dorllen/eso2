/**
 * @Title: MainWormsService.java
 * @Package com.zhidian.services
 * @Description: TODO(用一句话描述该文件做什么)
 * @author dongneng
 * @date 2017-3-20 下午11:37:11
 * @version V1.0
 */
package com.zhidian.bases.worm;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.Scheduler;

/**
 * @ClassName: MainWormsService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-20 下午11:37:11
 * 
 */
public class MainWormsService implements MainWormsServiceImpl {

	public void test(){
		Spider spider = Spider.create(null);
	}
	
	
	public void runWormsTemplate(PageProcessor pageProcessor, String url,
			Pipeline pipeLine){
		Spider.create(pageProcessor).addUrl(url).addPipeline(pipeLine);
	}
	
	public void runWormsTemplate(PageProcessor pageProcessor, String url,
			Pipeline pipeLine, Scheduler scheduler){
		Spider.create(pageProcessor).addUrl(url).addPipeline(pipeLine)
		.setScheduler(scheduler);
	}
	
	public void runWormsTemplate(PageProcessor pageProcessor, String url,
			Pipeline pipeLine, Scheduler scheduler, Request requests) {
		Spider.create(pageProcessor).addUrl(url).addPipeline(pipeLine)
				.addRequest(requests).setScheduler(scheduler);
	}
	public void runWormsTemplate(PageProcessor pageProcessor, String url,
			Pipeline pipeLine, Scheduler scheduler, Downloader downloader,Request requests) {
		Spider.create(pageProcessor).addUrl(url).addPipeline(pipeLine)
				.setDownloader(downloader).setScheduler(scheduler).addRequest(requests);
	}
}
