package com.zhidian.bases.worm;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zhidian.bases.worms.pipeline.BasePagePipeline;
import com.zhidian.bases.worms.pipeline.ResultSimplePipeline;
import com.zhidian.bases.worms.processor.BasePageProcessor;
import com.zhidian.bases.worms.processor.BaseResultPageProcessor;
import com.zhidian.model.sys.PullPageDataTaskModel;
import com.zhidian.model.sys.PullPageObjectModel;
import com.zhidian.model.sys.PullResultDataTaskModel;
import com.zhidian.model.sys.PullResultPageModel;

import us.codecraft.webmagic.Spider;

@Component
public class PullBaseService {
	private Logger log = LoggerFactory.getLogger(getClass());

	public List<PullResultPageModel> startPullPageDataFromList(List<PullResultDataTaskModel> models) {
		if (models != null && models.size() > 0) {
			List<PullResultPageModel> pullList = new ArrayList<PullResultPageModel>(models.size());
			long start = System.currentTimeMillis();
			log.info("All startPullPageDataFromList start time -> {}", start);
			for (int i = 0; i < models.size(); i++) {
				PullResultDataTaskModel model = models.get(i);
				if (model == null) {
					continue;
				}
				// 获得对象
				Class<?> claz;
				try {
					claz = Class.forName(model.getPageProcess());
					if (claz == null) {
						continue;
					}
					// 反射加载
					Constructor<?> c = claz.getConstructor(PullResultPageModel.class);
					if (model.getPom() == null) {
						log.warn("Don't has PullPageModel for website -> {} , url -> {} ", model.getWebsite(),
								model.getFrom());
						continue;
					}
					BaseResultPageProcessor<?> pageProcessor;
					try {
						pageProcessor = (BaseResultPageProcessor<?>) c.newInstance(model.getPom());

						ResultSimplePipeline<PullResultPageModel> pipeline = new ResultSimplePipeline<PullResultPageModel>();

						long s = System.currentTimeMillis();
						Spider.create(pageProcessor).addUrl(model.getFrom()).addPipeline(pipeline).run();
						long e = System.currentTimeMillis();
						log.info("{} spend time is -> {}", model.getFrom(), (e - s) / 1000);
						// 数据装载
						pullList.add(pipeline.getObj());
					} catch (Exception e) {
						log.info("PullBaseService downloading page ... error ...url:{}", model.getFrom());
						e.printStackTrace();
					}
				} catch (NoSuchMethodException e2) {
					e2.printStackTrace();
				} catch (SecurityException e2) {
					e2.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			long end = System.currentTimeMillis();
			log.info("All startPullPageDataFromList end time -> {} and spend time is {} s", end, (end - start) / 1000);
			return pullList;
		}
		return null;
	}

	public List<PullPageObjectModel> startPullDataFromMapCompleteScheduleQueues(List<PullPageDataTaskModel> models) {
		if (models != null && models.size() > 0) {
			List<PullPageObjectModel> pullList = new ArrayList<PullPageObjectModel>(models.size());
			long start = System.currentTimeMillis();
			log.info("All startPullDataFromMapCompleteScheduleQueues start time -> {}", start);
			ExecutorService service = Executors.newFixedThreadPool(5);
			CompletionService<PullPageObjectModel> cs = new ExecutorCompletionService<PullPageObjectModel>(service);
			for (int i = 0; i < models.size(); i++) {
				final PullPageDataTaskModel model = models.get(i);
				cs.submit(new Callable<PullPageObjectModel>() {
					public PullPageObjectModel call() throws Exception {
						if (model == null) {
							return null;
						}
						// 获得对象
						Class<?> claz = Class.forName(model.getPageProcess());
						if (claz == null) {
							return null;
						}
						try {
							// 反射加载
							Constructor<?> c = claz.getConstructor(PullPageObjectModel.class);
							if (model.getPom() == null) {
								log.warn("Don't has PullPageModel for website -> {} , url -> {} ", model.getWebsite(),
										model.getFrom());
								return null;
							}
							BasePageProcessor<?> pageProcessor = (BasePageProcessor<?>) c.newInstance(model.getPom());
							BasePagePipeline pipeline = new BasePagePipeline();
							try {
								long s = System.currentTimeMillis();
								Spider.create(pageProcessor).addUrl(model.getFrom()).addPipeline(pipeline).run();
								long e = System.currentTimeMillis();
								log.info("{} spend time is -> {}", model.getFrom(), (e - s) / 1000);
								// 数据装载
								return pipeline.getModel();

							} catch (Exception e) {
								log.info("PullBaseService downloading page ... error ...url:{}", model.getFrom());
								e.printStackTrace();
							}
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
						return null;
					}

				});
			}
			for (int i = 0; i < models.size(); i++) {
				try {
					// 执行最大时间10s
					PullPageObjectModel pull = cs.take().get(10, TimeUnit.SECONDS);
					if (pull != null) {
						pullList.add(pull);
					}
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
			if (!service.isShutdown()) {
				service.shutdown();
			}
			long end = System.currentTimeMillis();
			log.info("All startPullDataFromMapCompleteScheduleQueues end time -> {} and spend time is {} s", end,
					(end - start) / 1000);
			return pullList;
		}
		return null;
	}

}
