package com.zhou.util;

import java.util.UUID;

public class MyFileUtil {

	
	/**
	 * 获取uuid的文件名字
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName){
		//icon_01.jpg --->lkaldsjflajslfdjalsjdflajsdf.jpg
		
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		
		String prefix = UUID.randomUUID().toString().replaceAll("-", "");
		
		return prefix + suffix;
	}
}

