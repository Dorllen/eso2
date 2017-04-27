package com.zhidian.test.thread;

public class ApjRun {
	public static void main(String[] args) {
		Apj apj = new Apj();
//		Apj apj2 = new Apj();
//		Thread t1 = new Thread(apj,"thread01 ");
////		Thread t2 = new Thread(apj2,"thread02 ");
//		t1.start();
//		t2.start();
		
		// synchronized 方法
		
		for(int i=0;i<10;i++){
			Thread t1 = new Thread(new Apj(),"thread0"+i);
			t1.start();
		}
		
	}
	
/*
 * 结论：
 * synchronized代码块：
 * 如果synchronized(this)，则在Thread t1 = new Thread(apj);Thread t2 = new Thread(apj); 得出的结构是按序执行。0-10输出
 * 如果synchronized(this)，则在Thread t1 = new Thread(apj);Thread t2 = new Thread(apj2); 得出的结构是乱序执行。0-10错位输出
 * 如果synchronized(Apj.class)，则在Thread t1 = new Thread(apj);Thread t2 = new Thread(apj2); 得出的结构是按序执行。0-10输出
 * 
 * synchronized方法：
 * 如果synchronized锁静态方法，锁的是类，类访问都受限
 * 如果synchronized锁非静态方法，锁的是对象，同一对象不同线程访问都会阻塞
 * 
 * 
 */
}
