package com.zhidian.test.thread;

public class Apj implements Runnable{
	private int id = 0;
	
	public synchronized void run() {
//		synchronized(Apj.class){
//			for(int i=0;i<10;i++){
//				System.out.println("current_thread："+Thread.currentThread()+" and id is:"+id++);
//			}
//		}
		
		for(int i=0;i<10;i++){
			System.out.println("current_thread："+Thread.currentThread()+" and id is:"+id++);
		}
		
	}

}
