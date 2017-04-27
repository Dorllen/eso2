package com.zhidian.test.thread;

import java.util.Map;

public class ObjRun {

//  Thread thread = new Thread(new ObjThread(obj),"Thread"+i);
//	Current Thread:Thread0 and my value is :0
//	Current Thread:Thread1 and my value is :1
//	Current Thread:Thread2 and my value is :2
//	Current Thread:Thread5 and my value is :3
//	Current Thread:Thread9 and my value is :4
//	Current Thread:Thread4 and my value is :5
//	Current Thread:Thread8 and my value is :6
//	Current Thread:Thread3 and my value is :7
//	Current Thread:Thread7 and my value is :8
//	Current Thread:Thread6 and my value is :9

	public static void main(String[] args) {
		Obj obj =new Obj();
		ObjThread t = new ObjThread(obj);
		for(int i=0;i<10;i++){
			Thread thread = new Thread(t,"Thread"+i);
			// Thread thread = new Thread(new ObjThread(obj),"Thread"+i);  会造成数据冲突。重复出现相同值
//			new ObjThread(obj).run();
			thread.start();
		}
		System.out.println(obj.getI());
		if(obj.getMap()!=null){
			System.out.println("map :"+obj.getMap().get("key"));
		}
		System.out.println("End");
	}
//  ObjThread t = new ObjThread(obj); Thread thread = new Thread(t,"Thread"+i);
//	Current Thread:Thread0 and my value is :0
//	Current Thread:Thread2 and my value is :1
//	Current Thread:Thread1 and my value is :2
//	Current Thread:Thread4 and my value is :4
//	Current Thread:Thread3 and my value is :3
//	Current Thread:Thread6 and my value is :5
//	Current Thread:Thread5 and my value is :7
//	Current Thread:Thread7 and my value is :6
//	Current Thread:Thread8 and my value is :8
//	Current Thread:Thread9 and my value is :9
}
