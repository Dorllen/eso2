package com.zhidian.bases.worms.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhidian.bases.WormEnumDefine;
import com.zhidian.model.sys.PullResultPageModel;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class ResultSimplePipeline<T extends PullResultPageModel> implements Pipeline {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public final static String STATUS = "RESULTSTATUS";
	
	public T obj;
	public T getObj() {
		return obj;
	}

	@Override
	public synchronized void process(ResultItems resultItems, Task task) {
		log.info("loading process....");
		if(resultItems!=null){
			String status = resultItems.get(STATUS);
			if(WormEnumDefine.Status.结束.name().equals(status)){
				log.info("status is current!");
				obj = resultItems.get(WormEnumDefine.Object.搜索结果页.name());
			}
		}
	}

}
