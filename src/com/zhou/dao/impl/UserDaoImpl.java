package com.zhou.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhou.bean.User;
import com.zhou.dao.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public void save(User user) {
		getHibernateTemplate().save(user);
		
	}

	@Override
	public User login(User user) {
		//select* from user where user_code  = ?  and user_password = ? & user_state=1;
		String sql = "from  User where user_code = ? and user_password=? and user_state = 1";

		
		//这个find方法永远都是返回list
		List<User> list = (List<User>) getHibernateTemplate().find(sql, user.getUser_code() , user.getUser_password());
		if(list != null && list.size() > 0 ){
			return list.get(0);
		} 
		return null; 
	}

}
