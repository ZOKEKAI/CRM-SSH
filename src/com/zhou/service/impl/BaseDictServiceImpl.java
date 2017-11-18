package com.zhou.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zhou.bean.BaseDict;
import com.zhou.dao.BaseDictDao;
import com.zhou.service.BaseDictService;

@Transactional
public class BaseDictServiceImpl implements BaseDictService {
	private BaseDictDao baseDictDao;
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}

}
