package com.zhou.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import com.zhou.bean.User;
import com.zhou.service.UserService;
import com.zhou.util.Md5Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestUserService {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testMd5(){
		
		String pwd = "123";
//		sha-1 \ md5  MessageDigest \ Ciypher
		String md5=	DigestUtils.md5DigestAsHex(pwd.getBytes());
		System.out.println("md5="+md5);
		
		System.out.println(Md5Util.md5Pwd(pwd));
	}

	
	@Test
	public void testRegister(){
		
		User user = new User();
		user.setUser_code("admin123");
		user.setUser_name("管理员");
		user.setUser_password("admin123");
		user.setUser_state(1);
		
		userService.register(user);
		
		System.out.println("注册用户成功~~~");
	}
}
