package com.zhou.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.apache.struts2.util.StrutsTypeConverter;

public class DateConvertion extends StrutsTypeConverter{
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");

	
	/*
	 * 转化从页面收到的数据(non-Javadoc)
	 * values 就是页面收到的数据 。为什么是个数组类型呢? 因为页面上可能name ="username" , name ="username"
	 * String ----> Date
	 */
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		
		String value = values[0];
		try {
			return format.parse(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 转化输出到页面的数据
	 * o :输出出去的日期对象
	 * Date ---> String
	 */
	@Override
	public String convertToString(Map context, Object o) {
		return format.format(o);
	}

	
	
}
