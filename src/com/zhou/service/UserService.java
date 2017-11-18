package com.zhou.service;


import com.zhou.bean.User;

public interface UserService {

	void register(User user);

	User login(User user);
}
