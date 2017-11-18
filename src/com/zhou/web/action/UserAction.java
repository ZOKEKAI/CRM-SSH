package com.zhou.web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.net.httpserver.Authenticator.Success;
import com.zhou.bean.User;
import com.zhou.service.UserService;
import com.zhou.util.Constant;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user;
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	
	public String login(){
		User logingUser = userService.login(user);
		
		System.out.println("登录结果："+logingUser);
		
		if(logingUser != null){
			//登录成功
			
			ServletActionContext.getRequest().getSession().setAttribute("user", logingUser);
			
			return Constant.LOGIN_SUCCESS;
		}else{
			
			/*
				 //添加错误的回显， struts会把这个错误信息，存放到值栈里面，如果不考虑显示样式的话，可以直接使用struts标签来获取它。
				<s:fielderror>
					<s:param>result</s:param>
				</s:fielderror>
			*/
			addFieldError("result", "用户名或者密码错误!");
			addFieldError("result", "aa");
			addFieldError("result", "bb");
			
			//登录失败
			return Constant.LOGIN_ERROR;
		}
		
	}
	
	

	@Override
	public User getModel() {
		if(user == null){
			user = new User();
		}
		return user;
	}

}
