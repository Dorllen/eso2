package com.zhidian.bases.worms.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class NullPipeline implements Pipeline {

	public void process(ResultItems resultItems, Task task) {
		// 不做任何操作
		System.out.println("NullPipeline In here!");
	}

}
