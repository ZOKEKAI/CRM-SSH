package com.zhou.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zhou.bean.Customer;

public interface CustomerDao<T> extends BaseDao<T>{

	/*void save(Customer customer);

	int findCount(DetachedCriteria criteria);

	List<Customer> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);

	void delete(Customer customer);

	Customer findById(Serializable cust_id);

	void update(Customer customer);

	List<Customer> findAll();
*/
}
