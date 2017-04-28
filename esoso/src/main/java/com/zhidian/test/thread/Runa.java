package com.zhidian.test.thread;

import com.zhidian.bases.worms.processor.SegmentFaultResultProcessor;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;

public class Runa implements Runnable{
	Pipeline pipe;
	public Runa(Pipeline pipe){
		this.pipe = pipe;
	}
	private String[] str = new String[]{"python","java"};
	private static int i = 0;
	
	
	public  void run() {
		System.out.println("name:"+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Spider.create(new SegmentFaultResultProcessor())
//		.addPipeline(pipe)
//		.get("https://segmentfault.com/search?q="+str[i++]);
	}
}
