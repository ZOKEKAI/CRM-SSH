package com.zhou.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhou.bean.BaseDict;
import com.zhou.dao.BaseDictDao;

public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		String hql = "from BaseDict where dict_type_code = ?";
		return (List<BaseDict>) getHibernateTemplate().find(hql, dict_type_code);
	}

}
