package com.zhou.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhou.bean.Customer;
import com.zhou.dao.CustomerDao;

public class CustomerDaoImpl  extends BaseDaoImpl<Customer> implements CustomerDao<Customer> {

	/*
	public CustomerDaoImpl(){
		//super();
		super(Customer.class);
	}*/
	
}
