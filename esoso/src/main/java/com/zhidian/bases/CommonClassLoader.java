package com.zhidian.bases;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class CommonClassLoader extends ClassLoader {
	public CommonClassLoader(){}
	public CommonClassLoader(ClassLoader clas) {
		super(clas);
	}
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String root = System.getProperty("webapp.root");
		if(root!=null){
			if(name==null){
				throw new ClassNotFoundException();
			}
			String file = resolveName(name);
			byte[] result = loadClassFile(root+File.separator+"WEB-INF"+File.separator+"classes2"+File.separator+file);
			return defineClass(name, result, 0, result.length);
		}
		return super.findClass(name);
	}

	private String resolveName(String name) {
		return name.replace(".", File.separator)+".class";
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
}
