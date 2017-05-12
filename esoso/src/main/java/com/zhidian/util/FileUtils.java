package com.zhidian.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileExistsException;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

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
	/**
	 * @throws FileExistsException 
	* @Title: keepMultipartFile
	* @Description: TODO(用于存储文件到目录，非临时目录)
	* @param @param file
	* @param @param path
	* @param @param igExit 是否忽略文件存在
	* @param @return    参数
	* @return String    返回类型 返回文件名
	* @throws
	*/
	public static String keepMultipartFile(MultipartFile file, String path, boolean igExit) throws FileExistsException {
		if(file!=null){
			String name = file.getOriginalFilename();
			if(name!=null&&name.trim().length()>0){
				// 名称存在
				File f = new File(path,name);
				mkdirs(f);// 简立目录
				if(f.isFile()&&f.exists()){
					if(!igExit){
						throw new FileExistsException();
					}
				}
				// 进行文件写入操作
				try {
					FileCopyUtils.copy(file.getBytes(), f);
					return name;
				} catch (IOException e) {
				}
			}
		}
		return null;
	}
	/**
	* @Title: checkIsSuffixForExit
	* @Description: TODO(文件存在则校验后缀，成功则返回true，文件为null,返回true)
	* @param @param file
	* @param @param suffix
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	*/
	public static boolean checkIsSuffixForExit(MultipartFile file, String suffix) {
		if(file!=null){
			if(file.getOriginalFilename()==null||file.getOriginalFilename().trim().length()==0){
				return false;
			}else{
				if(file.getOriginalFilename().lastIndexOf("."+suffix)<=0){
					return false;
				}
			}
		}
		return true;
	}

}
