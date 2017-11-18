package com.zhou.service;

import java.util.List;

import com.zhou.bean.BaseDict;

public interface BaseDictService {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
