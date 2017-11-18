package com.zhou.dao;

import com.zhou.bean.User;

public interface UserDao {

	void save(User user);

	User login(User user);

}
