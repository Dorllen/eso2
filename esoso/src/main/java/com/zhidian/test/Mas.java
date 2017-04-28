package com.zhidian.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Mas {
	protected int id = 3;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id is:" + id;
	}

	public static void main(String[] args) {
		Class<?> claz = Mas.class;
		try {
			Constructor<?> c = claz.getConstructor();
			Mas m = (Mas) c.newInstance();
			System.out.println(m);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
