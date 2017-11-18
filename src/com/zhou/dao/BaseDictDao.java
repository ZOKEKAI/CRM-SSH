package com.zhou.dao;

import java.util.List;
import com.zhou.bean.BaseDict;

public interface BaseDictDao {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
