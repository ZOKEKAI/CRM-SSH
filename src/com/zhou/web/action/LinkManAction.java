package com.zhou.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import com.zhou.bean.LinkMan;
import com.zhou.bean.PageBean;
import com.zhou.service.LinkManService;
import com.zhou.util.Constant;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private LinkMan linkMan;
	private int currentPage = 1 ;
	private int pageSize = 5;
	
	private LinkManService linkManService;
	private List<LinkMan> list ;
	private Long cid;
	
	public List<LinkMan> getList() {
		return list;
	}
	
	public void setCid(Long cid) {
		this.cid = cid;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	public String save(){
		linkManService.save(linkMan);
		return Constant.SAVE_SUCCESS;
	}
	
	
	public String findByPage(){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		
		//联系人名称校验
		
		if(!StringUtils.isEmpty(linkMan.getLkm_name())){
			criteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		
		//联系人性别校验
		
		if(!StringUtils.isEmpty(linkMan.getLkm_gender())){
			criteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		
		//所属客户
		
		if(linkMan.getCustomer() != null &&!StringUtils.isEmpty(linkMan.getCustomer().getCust_id())){
			criteria.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		
		//联系人电话校验
		
		if(!StringUtils.isEmpty(linkMan.getLkm_phone())){
			criteria.add(Restrictions.like("lkm_phone", "%"+linkMan.getLkm_phone()+"%"));
		}
		
		//联系人邮箱校验
		
		if(!StringUtils.isEmpty(linkMan.getLkm_email())){
			criteria.add(Restrictions.like("lkm_email", "%"+linkMan.getLkm_email()+"%"));
		}
		
		//联系人手机校验
		
		if(!StringUtils.isEmpty(linkMan.getLkm_mobile())){
			criteria.add(Restrictions.like("lkm_mobile", "%"+linkMan.getLkm_mobile()+"%"));
		}
		
		
		//联系人qq校验
		
		if(!StringUtils.isEmpty(linkMan.getLkm_qq())){
			criteria.add(Restrictions.like("lkm_qq", "%"+linkMan.getLkm_qq()+"%"));
		}
		
		
		
		PageBean<LinkMan> pageBean = linkManService.findByPage(criteria , currentPage , pageSize);
		
		//这里要把pageBean 存储到值栈去。 push  | set |属性
		ActionContext.getContext().getValueStack().push(pageBean);
		
		 
		return Constant.PAGE_SUCCESS;
	}
	
	public String delete(){
		
		linkManService.delete(linkMan);
		
		return Constant.DELETE_SUCCESS;
	}
	
	public String edit(){
		
		//使用属性的方式存值到值栈 KEY:
		linkMan = linkManService.findById(linkMan.getLkm_id());
		
		return Constant.EDIT_SUCCESS;
	}
	
	public String update(){
		
		linkManService.update(linkMan);
		
		return Constant.UPDATE_SUCCESS;
	}
	
	public String findAllByCid(){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		criteria.add(Restrictions.eq("customer.cust_id", cid)); //cid = 客户的ID
		list = linkManService.findByCid(criteria);
		
		return Constant.JSON_SUCCESS;
	}
	
	public LinkMan getLinkMan() {
		return linkMan;
	}
	
	@Override
	public LinkMan getModel() {
		if(linkMan == null){
			linkMan = new LinkMan();
		}
		return linkMan;
	}

}
