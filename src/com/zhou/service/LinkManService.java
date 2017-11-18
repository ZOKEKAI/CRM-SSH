package com.zhou.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zhou.bean.LinkMan;
import com.zhou.bean.PageBean;

public interface LinkManService {

	void save(LinkMan linkMan);

	PageBean<LinkMan> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);

	void delete(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	List<LinkMan> findByCid(DetachedCriteria  criteria);

}
