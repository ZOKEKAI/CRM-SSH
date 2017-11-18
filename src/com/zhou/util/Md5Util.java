package com.zhou.util;

import org.springframework.util.DigestUtils;

public class Md5Util {

	/**
	 * 加密密码
	 * @param password
	 * @return
	 */
	public static String md5Pwd(String password){
		for (int i = 0; i < 10; i++) {
			password = DigestUtils.md5DigestAsHex(password.getBytes());
		}
		return password;
	}
}
