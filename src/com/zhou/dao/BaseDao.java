package com.zhou.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	
	void save(T t);
	
	void delete(T t);
	
	void update(T t);
	
	int findCount(DetachedCriteria criteria);
	
	T findById(Serializable cust_id);
	
	List<T> findAll();
	
	List<T> findByPage(DetachedCriteria criteria , int currentPage , int pageSize);

}
