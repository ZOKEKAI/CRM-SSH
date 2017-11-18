package com.zhou.web.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhou.bean.PageBean;
import com.zhou.bean.SaleVisit;
import com.zhou.bean.User;
import com.zhou.service.SaleVisitService;
import com.zhou.util.Constant;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

	private SaleVisit saleVisit;
	private SaleVisitService saleVisitService;
	private Date start_visit_time;
	private Date end_visit_time;
	
	
	private int currentPage = 1 ;
	private int pageSize = 5;
	
	public void setStart_visit_time(Date start_visit_time) {
		this.start_visit_time = start_visit_time;
	}
	
	public void setEnd_visit_time(Date end_visit_time) {
		this.end_visit_time = end_visit_time;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}
	
	public String save(){
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		saleVisit.setUser(user);
		saleVisitService.save(saleVisit);
		return Constant.SAVE_SUCCESS;
	}
	
	
	public String findByPage(){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(SaleVisit.class);
		
		//客户名称校验
		
		if(saleVisit.getCustomer() != null  && !StringUtils.isEmpty(saleVisit.getCustomer().getCust_id())){
			criteria.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
		}
		
		//拜访时间校验
		
		if(start_visit_time != null && end_visit_time != null){
			//两个都有时间
			criteria.add(Restrictions.between("visit_time", start_visit_time, end_visit_time));
		}else{
			//可能都不选，也有可能只选一个
			if(start_visit_time != null){
				//选择了左边的时间
				criteria.add(Restrictions.ge("visit_time", start_visit_time));
			}
			if(end_visit_time != null){
				//选择了左边的时间
				criteria.add(Restrictions.le("visit_time", end_visit_time));
			}
		}
		
		
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(criteria, currentPage , pageSize);
		
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return Constant.PAGE_SUCCESS;
	}
	
	
	
	
	@Override
	public SaleVisit getModel() {
		if(saleVisit == null){
			saleVisit = new SaleVisit();
		}
		
		return saleVisit;
	}

}
