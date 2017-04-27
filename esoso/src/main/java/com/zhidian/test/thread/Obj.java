package com.zhidian.test.thread;

import java.util.HashMap;
import java.util.Map;

public class Obj {
	private int i = 0;
	Map<String, Integer> map = new HashMap<String, Integer>();

	public void process() {
		System.out.println("Current Thread:" + Thread.currentThread().getName() +this);
		System.out.println("Current Thread:" + Thread.currentThread().getName() + " and my key is:" + map.get("key"));
		// 运行结果显示上条语句获得的key，和下一条语句所获得的key可能不同.synchronized锁方法无效！
		System.out.println("Current Thread:" + Thread.currentThread().getName() + " and my value is :" + (i++)
				+ " and key is:" + map.get("key"));
		map.put("key", (int) (Math.random() * 10));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getI() {
		return i;
	}

	public Map<String, Integer> getMap() {
		return map;
	}
}
