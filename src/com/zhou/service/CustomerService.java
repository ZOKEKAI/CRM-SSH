package com.zhou.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zhou.bean.Customer;
import com.zhou.bean.PageBean;

public interface CustomerService {

	void save(Customer customer);

	PageBean<Customer> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);

	void delete(Customer customer);

	Customer findById(Serializable cust_id);

	void update(Customer customer);

	List<Customer> findAll();  
 
}
