package com.zhou.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhou.bean.Customer;
import com.zhou.bean.SaleVisit;
import com.zhou.dao.SaleVisitDao;

public class SaleVisitDaoImpl extends HibernateDaoSupport implements SaleVisitDao {

	@Override
	public void save(SaleVisit saleVisit) {
		getHibernateTemplate().save(saleVisit);
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
	public List<SaleVisit> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		criteria.setProjection(null);
		
		return (List<SaleVisit>) getHibernateTemplate().findByCriteria(criteria, (currentPage - 1 ) * pageSize, pageSize);
	}

}
