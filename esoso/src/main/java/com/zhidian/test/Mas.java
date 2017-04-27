package com.zhidian.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Mas {
	protected int id=3;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id is:"+id;
	}
	public static void main(String[] args) {
		Class<?> claz = Mas.class;
		try {
			Constructor<?> c  = claz.getConstructor();
			Mas m = (Mas) c.newInstance();
			System.out.println(m);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
