package com.zhou.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.zhou.bean.Customer;
import com.zhou.bean.PageBean;
import com.zhou.dao.CustomerDao;
import com.zhou.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao  ;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		
		int totalSize = customerDao.findCount(criteria); //101
		List<Customer> list = customerDao.findByPage(criteria , currentPage , pageSize);
		
//		pageSize   10  100 /10  =10 
		
		PageBean<Customer> pageBean = new PageBean<Customer>();
		
		pageBean.setCurrentPage(currentPage);
		
		//初级代码：  能整除，就取商数、  不能整除，就是结果 + 1
//		int totalPage = totalSize % pageSize  == 0 ? totalSize / pageSize : (totalSize / pageSize) +1;
		
		pageBean.setTotalPage((int) Math.ceil(totalSize * 1.0 / pageSize));
		
		pageBean.setPageSize(pageSize);
		pageBean.setTotalSize(totalSize);
		
		pageBean.setList(list);
		
		
		return pageBean;
	}


	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}


	@Override
	public Customer findById(Serializable cust_id) {
		return (Customer) customerDao.findById(cust_id);
	}


	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}


	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

}
