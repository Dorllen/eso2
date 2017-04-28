package com.zhidian.bases.worms.pipeline;

import com.zhidian.bases.WormEnumDefine;
import com.zhidian.model.sys.PullPageObjectModel;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class BasePagePipeline implements Pipeline {
	// private Logger log = LoggerFactory.getLogger(getClass());

	public PullPageObjectModel pageObj;
	public final static String STATUS = "RESULTSTATUS";

	public synchronized void process(ResultItems resultItems, Task task) {
		// log.info("pageObj->{}", obj);
		if (resultItems != null) {
			String status = resultItems.get(STATUS);
			if (WormEnumDefine.Status.结束.name().equals(status)) {
				pageObj = resultItems.get(WormEnumDefine.Object.内容详情页.name());
			}
		}
	}

	public PullPageObjectModel getModel() {
		return pageObj;
	}

}
