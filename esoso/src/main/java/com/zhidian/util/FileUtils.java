package com.zhidian.util;

import java.io.File;

public class FileUtils {
	public static boolean mkdirs(File f){
		if(f!=null){
			if(!f.exists()){
				return f.mkdirs();
			}
		}
		return false;
	}
	public static boolean mkdirs(String path){
		File f = new File(path);
		return mkdirs(f);
	}
}
