package com.zhou.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.zhou.bean.BaseDict;
import com.zhou.service.BaseDictService;
import com.zhou.util.Constant;

public class BaseDictAction  extends ActionSupport {

	private String dict_type_code;
	private  List<BaseDict> list ;
	
	private BaseDictService baseDictService;
	
	public List<BaseDict> getList() {
		return list;
	}
	
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	
	public String findByAjax() throws IOException{
		
		list = baseDictService.findByTypeCode(dict_type_code);
		
		/*//list ---> json
		String json  = new Gson().toJson(list);
		
		//写出去
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);*/
		
		return Constant.JSON_SUCCESS;
	}
}
