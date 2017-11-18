package com.zhou.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhou.bean.Customer;
import com.zhou.dao.CustomerDao;

public class CustomerDaoImpl2 /*extends HibernateDaoSupport implements CustomerDao*/ {

/*	@Override
	public void save(Customer customer) {
		getHibernateTemplate().save(customer);
	}

	@Override
	public int findCount(DetachedCriteria criteria) {
		 
		//只想要总条数。  setProjection   | where : add()
		criteria.setProjection(Projections.rowCount());
		
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Customer> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		
		//select * from user  limit ? , ? ;  firstResult 就是第一个？的值 ， maxResults 就是第二个?的值
		
		//第一个？ ： 代表的跳过前面的多少条记录。  
		1   0  5
		
		2 ： 5 ， 5
		
		3： 10  5
		
		4  15  ， 5
		//第二个? : 返回多少条记录
		System.out.println("--------------");
		
		//由于这里使用的criteria 对象和上面的findCount方法使用的criteria是同一个对象， 在上面已经对这个对象设置了projection
		//所以在这里要重置一下，才能获取到当前页的记录
		criteria.setProjection(null);
		
		List<Customer> list = (List<Customer>) getHibernateTemplate().findByCriteria(criteria, (currentPage - 1 ) * pageSize, pageSize);
		return list;
	}

	@Override
	public void delete(Customer customer) {
		getHibernateTemplate().delete(customer);
	}

	@Override
	public Customer findById(Serializable cust_id) {
		return getHibernateTemplate().get(Customer.class, cust_id);
	}

	@Override
	public void update(Customer customer) {
		getHibernateTemplate().update(customer);
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Customer.class));
	}
*/
}
