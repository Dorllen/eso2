package com.zhidian.bases;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader {
	byte[] result;

	public CustomClassLoader(String path) {
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
					// 父亲找不到就算了.. 不报异常
				}
			}
			if (claz == null) {
				claz = this.defineClass(name, result, 0, result.length);
			}
		}
		return claz;
	}
}
