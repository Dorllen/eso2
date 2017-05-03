package com.zhidian.bases;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestClassLoader extends ClassLoader {
	byte[] result;

	TestClassLoader(String path) {
		result = loadClassFile(path);
	}

	private byte[] loadClassFile(String path) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(path));
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = in.read(b)) != -1) {
				out.write(b, 0, i);
			}
			return out.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class<?> claz = findLoadedClass(name);
		if (claz == null) {
			if (this.getParent() != null) {
				try {
					claz = this.getParent().loadClass(name);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (claz == null) {
				claz = this.defineClass(name, result, 0, result.length);
			}
		}
		return claz;
	}

	public static void main(String[] args) {
		TestClassLoader test = new TestClassLoader("D://Documents/.metadata/.plugins/org.eclipse.wst.server.core"
				+ "/tmp1/wtpwebapps/esoso/WEB-INF/classes/com/zhidian/bases/worms/pipeline/Result.class");
		try {
			Class<?> clz = test.loadClass("com.common.Result");
//			Class<?> clz = Class.forName("com.common.Result");
			Object o = clz.newInstance();
			System.out.println(o);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
