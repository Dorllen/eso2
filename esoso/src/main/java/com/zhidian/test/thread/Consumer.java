package com.zhidian.test.thread;

public class Consumer implements Runnable {
	String name;

	ProductStack ps = null;

	Consumer(ProductStack ps, String name) {
		this.ps = ps;
		this.name = name;
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			ps.pop(name);
			try {
				Thread.sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
