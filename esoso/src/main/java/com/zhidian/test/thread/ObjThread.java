package com.zhidian.test.thread;

public class ObjThread implements Runnable {

	Obj obj;

	public ObjThread(Obj obj) {
		this.obj = obj;
	}

	public void run() {
		synchronized (obj) {
			obj.process();
		}
	}

}
