package com.zhou.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.zhou.bean.User;
import com.zhou.dao.UserDao;
import com.zhou.service.UserService;
import com.zhou.util.Md5Util;

@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void register(User user) {
		
		String newPwd = Md5Util.md5Pwd(user.getUser_password());
		
		user.setUser_password(newPwd);
		
		userDao.save(user);
	}

	@Override
	public User login(User user) {
		
		String newPwd = Md5Util.md5Pwd(user.getUser_password());
		
		user.setUser_password(newPwd);
		
		return userDao.login(user);
	}

}
