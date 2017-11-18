package com.zhou.service;

import org.hibernate.criterion.DetachedCriteria;

import com.zhou.bean.PageBean;
import com.zhou.bean.SaleVisit;

public interface SaleVisitService {

	void save(SaleVisit saleVisit);

	PageBean<SaleVisit> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);

}
