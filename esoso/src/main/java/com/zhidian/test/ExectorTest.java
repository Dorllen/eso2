package com.zhidian.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExectorTest {
	public static void main(String[] args) {
		ExecutorService server = Executors.newFixedThreadPool(3);
		Future<String> f = server.submit(new Callable<String>(){

			@Override
			public String call() throws Exception {
				System.out.println("---");
				Thread.sleep(3);
				return "helloBMAn";
			}
			
		});
		try {
			System.out.println("222");
			System.out.println(f.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
