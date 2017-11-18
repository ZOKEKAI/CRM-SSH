package com.zhou.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.zhou.bean.Customer;
import com.zhou.bean.PageBean;
import com.zhou.bean.SaleVisit;
import com.zhou.dao.SaleVisitDao;
import com.zhou.service.SaleVisitService;

@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
	
	private SaleVisitDao saleVisitDao;
	
	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}

	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		int totalSize = saleVisitDao.findCount(criteria); //101
		List<SaleVisit> list = saleVisitDao.findByPage(criteria , currentPage , pageSize);
		
//		pageSize   10  100 /10  =10 
		
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		
		pageBean.setCurrentPage(currentPage);
		
		//初级代码：  能整除，就取商数、  不能整除，就是结果 + 1
//		int totalPage = totalSize % pageSize  == 0 ? totalSize / pageSize : (totalSize / pageSize) +1;
		
		pageBean.setTotalPage((int) Math.ceil(totalSize * 1.0 / pageSize));
		
		pageBean.setPageSize(pageSize);
		pageBean.setTotalSize(totalSize);
		
		pageBean.setList(list);
		
		return pageBean;
	}

}
