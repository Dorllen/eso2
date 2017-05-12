package com.zhidian.util;

import java.io.File;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zhidian.exception.FileExistException;

public class FileUtils {
	public static String TempFileName = "temp";

	public static boolean mkdirs(File f) {
		if (f != null) {
			if (!f.exists()) {
				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				return true;
			}
		}
		return false;
	}

	public static boolean mkdirs(String path) {
		File f = new File(path);
		return mkdirs(f);
	}

	/**
	 * @param code
	 * @throws FileExistException
	 * @Title: keepMultipartFileToTemp @Description:
	 *         TODO(保存文件到classes2/temp/com/...临时目录。code为识别码) @param @param
	 *         file @param @param root d:/..../WEB-INF/classes2 @param @param
	 *         fPath com/bases/dfdf @param @param
	 *         igExit @param @return @param @throws FileExistsException
	 *         参数 @return String 返回类型 @throws
	 */
	public static String keepMultipartFileToTemp(MultipartFile file, String root, String fPath, boolean igExit,
			String code) throws FileExistException {
		if (file != null) {
			String name = file.getOriginalFilename();
			if (name != null && name.trim().length() > 0) {
				// 名称存在
				File f = new File(root + File.separator + TempFileName + File.separator + code + File.separator + fPath,
						name);
				mkdirs(f);// 简立目录
				try {
					if (!igExit) {
						// 校验非temp文件目录是否存在文件
						File tfile = new File(root + File.separator + File.separator + fPath, name);
						if (tfile != null && tfile.exists() && tfile.isFile()) {
							throw new FileExistException();
						}
					}
					if (!f.exists()) {
						f.createNewFile();// 创建文件
					}
					// 进行文件写入操作
					try {
						FileCopyUtils.copy(file.getBytes(), f);
						// name需要做处理，去掉
						return fPath + File.separator + name;
					} catch (IOException e) {
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}

	public static String fileNameHandlerWithOutSuffix(String name) {// 有.的name
		// com.sdf.cfd.java -> com/sdf/cfd
		if (name != null && name.trim().length() > 0) {
			name = name.substring(0, name.lastIndexOf('.'));
			return name.replace(File.separatorChar, '.');
		}
		return name;
	}

	/**
	 * @Title: checkIsSuffixForExit @Description:
	 *         TODO(文件存在则校验后缀，成功则返回true，文件为null,返回true) @param @param
	 *         file @param @param suffix @param @return 参数 @return boolean
	 *         返回类型 @throws
	 */
	public static boolean checkIsSuffixForExit(MultipartFile file, String suffix) {
		if (file != null) {
			if (file.getOriginalFilename() == null || file.getOriginalFilename().trim().length() == 0) {
				return false;
			} else {
				if (file.getOriginalFilename().lastIndexOf("." + suffix) <= 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @Title: copyFileToPath @Description: TODO(这里用一句话描述这个方法的作用) @param @param
	 *         fPath d:/....WEB-INF/classes2/temp/201345654 @param @param
	 *         filePathAndName com/abd/sdf.class @param @param keepPath
	 *         d:/....WEB-INF/classes2 参数 @return void 返回类型 @throws
	 */
	public static void copyFileToPath(String fPath, String filePathAndName, String keepPath) {
		if (fPath != null && filePathAndName != null && keepPath != null && keepPath.trim().length() > 0
				&& filePathAndName.trim().length() > 0 && fPath.trim().length() > 0) {
			File f = new File(fPath, filePathAndName);
			if (f != null && f.exists()) {
				File out = new File(keepPath, filePathAndName);
				FileUtils.mkdirs(out);
				if (out != null && !out.exists()) {
					try {
						out.createNewFile();
					} catch (IOException e) {
					}
				}
				try {
					FileCopyUtils.copy(f, out);
				} catch (IOException e) {
				}
			}
		}
	}

	public static void deleteFileOnExist(String fPath, String filePathAndName) {
		if (fPath != null && filePathAndName != null && fPath.trim().length() > 0
				&& filePathAndName.trim().length() > 0) {
			File f = new File(fPath, filePathAndName);
			if (f != null && f.exists()) {
				if (f.isFile()) {
					try {
						f.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		String s = "D:/Documents//.metadata/null/org.eclipse.wst.server.core/tmp1/wtpwebapps/esoso/WEB-INF/classes2/temp/1494602936723";
		String f = "com/zhidian/bases/worms/model/UrlUtils.class";
		deleteFileOnExist(s, f);
	}

}
