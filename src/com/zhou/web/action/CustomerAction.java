package com.zhou.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.zhou.bean.Customer;
import com.zhou.bean.PageBean;
import com.zhou.service.CustomerService;
import com.zhou.util.Constant;
import com.zhou.util.MyFileUtil;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer ;
	
//	private File file; - setUpload(){}
	private File upload;  //这个upload要写成跟 页面上交过来的文件标签中的name属性值一样
	private String  uploadContentType; //文件类型  = input + ContentType
	private String  uploadFileName; //文件类型 = input + FileName
	
	private CustomerService customerService;
	private List<Customer> list;
	
	private int currentPage =1;
	private int pageSize = 5;
	
	
	public List<Customer> getList() {
		return list;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	
	
	public String save() throws IOException{
		

		//客户名称
		if(StringUtils.isEmpty(customer.getCust_name())){
			addFieldError("error_result", "客户名称不能为空");
			return Constant.INPUT_ERROR;
		}
		
		//所属行业 写这种严谨一点。 
		if(customer.getCust_industry() != null && StringUtils.isEmpty(customer.getCust_industry().getDict_id())){
			addFieldError("error_result", "必须选择所属行业");
			return Constant.INPUT_ERROR;
		}
		
		//信息来源
		if(customer.getCust_source() != null && StringUtils.isEmpty(customer.getCust_source().getDict_id())){
			addFieldError("error_result", "必须选择客户来源");
			return Constant.INPUT_ERROR;
		}
		
		//客户级别
		if(customer.getCust_level() != null && StringUtils.isEmpty(customer.getCust_level().getDict_id())){
			addFieldError("error_result", "必须选择客户级别");
			return Constant.INPUT_ERROR;
		}
		
		
		//联系地址
		if(StringUtils.isEmpty(customer.getCust_address())){
			addFieldError("error_result", "联系地址不能为空");
			return Constant.INPUT_ERROR;
		}
		
		//联系电话
		if(StringUtils.isEmpty(customer.getCust_phone())){
			addFieldError("error_result", "联系电话不能为空");
			return Constant.INPUT_ERROR;
		}
		

		
		/*
		 * 基础： 保存图片
		 * 
		 * List<FileItem> list = uploadserlvet .parse(request);
		 * 
		 * for(FileItem fileItem : list){
		 * 		if(fileItem.isFormFiled()){
		 * 			//到底这份数据是不是普通的字符串数据
		 * 		}else{
		 * 			//表明是个文件数据
		 * 
		 * 			inputStream
		 * 			outputStream
		 * 
		 * 			i/o 对接 存图片
		 * 		}
		 * }
		 * 
		 * struts : 已经帮我们做了这些内容， 并且它也获取到了页面交过来的文件数据。  *.temp 临时文件
		 * 
		 * 		把struts的临时文件转存成我们想要的图片文件即可。
		 * 
		 * 
		 */
		System.out.println("file="+upload);
		System.out.println("fileContentType="+uploadContentType);
		System.out.println("filename="+uploadFileName);
		
		//8级目录
		String fileName= MyFileUtil.getFileName(uploadFileName);
		//File destFile = new File("D:/File/img/"+fileName);
		File destFile = new File("D:/File/img" , fileName);
		FileUtils.copyFile(upload, destFile);
		
		/*
		 * 设置客户的图片路径
		 * 
		 * 真实的图片路径：  D:/File/img/aa.jpg
		 * 
		 * 显示图片 ： <img src="${pageContext.request.contextPath}/${customer.cust_image}">
		 * 
		 * 		变成的路径是： /CRM-SSH/img/aa.jpg
		 * 
		 * 
		 * 考虑以下，数据库里面存放什么值呢?  img/aa.jpg
		 * 
		 * 推理： 映射的虚拟路径怎么写：
		 * 
		 * 		<Context docBase ="D:File/img"  path="/CRM-SSH/img">
		 * 
		 * 	CRM-SSH/img/aa.jpg
		 */
		customer.setCust_image("img/"+fileName);

		customerService.save(customer); 
		
		return Constant.SAVE_SUCCESS;
	}
	
	
	public String findByPage(){
		
		/*
		 * 
		 * 1. 点击左侧的客户列表会调用这个方法获取分页数据，注意： 这次调用并没有任何的条件要添加 。
		 * 
		 * 2. 点击列表页面上的筛选，也会调用这个方法，但是筛选会带过来跳转 ， 针对客户级别为VIP的客户 100   有条件 where 客户级别 == vip客户
		 * 
		 * 假如不写离线条件查询，那么代码需要这么写。 --- 按照以前的代码写。
		 * Action:
		 * 		customerService.findByPage(customer ，  currentPage , pageSize);
		 * 
		 * Service : 
		 * 
		 * 		dao。findByPage(customer ，  currentPage , pageSize);
		 * 
		 * dao ：
		 * 
		 * 		findByPage(customer ，  currentPage , pageSize){
		 * 			String sql = "select * from customer"
		 * 
		 * 				//如果页面上有6个条件有可能传递过来，那么需要在dao层写6个if判定。  如果有60个，就得写60个。
		 * 			if(客户名称不是空){
		 * 				sql = sql + "where "
		 * 			}
		 * 	
		 * 		}
		 * 
		 * 		hibernate :
		 * 
		 * 			提供了一种对象 离线条件查询DetachedCriteria ,  这有什么用， 这就会让我们在action层级的位置就用它来封装了条件。
		 * 
		 * 			并且底层dao层，采用QBC的查询语法。  也就是再也不用写where ， 我们在action的时候只要面向对象去不断追加条件即可。
		 * 
		 * 
		 * 	推理得出： 要需要传递一个参数 DetachedCriteria
		 */
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		
		
		
		//客户名称校验
		if(!StringUtils.isEmpty(customer.getCust_name())){
			criteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		
		//客户行业校验
		
		if(customer.getCust_industry()!= null && !StringUtils.isEmpty(customer.getCust_industry().getDict_id())){
			criteria.add(Restrictions.eq("cust_industry.dict_id", customer.getCust_industry().getDict_id()));
		}
		
		//客户来源校验
		
		if(customer.getCust_source()!= null && !StringUtils.isEmpty(customer.getCust_source().getDict_id())){
			criteria.add(Restrictions.eq("cust_source.dict_id", customer.getCust_source().getDict_id()));
		}
		
		//客户级别校验
		
		if(customer.getCust_level()!= null && !StringUtils.isEmpty(customer.getCust_level().getDict_id())){
			criteria.add(Restrictions.eq("cust_level.dict_id", customer.getCust_level().getDict_id()));
		}
		
		//客户电话校验
		if(!StringUtils.isEmpty(customer.getCust_phone())){
			criteria.add(Restrictions.like("cust_phone", "%"+customer.getCust_phone()+"%"));
		}
		
		
		//客户地址校验
		if(!StringUtils.isEmpty(customer.getCust_address())){
			criteria.add(Restrictions.like("cust_address", "%"+customer.getCust_address()+"%"));
		}
		
		PageBean<Customer> pageBean = customerService.findByPage(criteria ,  currentPage , pageSize);
		
		System.out.println("pageBean="+pageBean);
		
		//push  |  <set  | 属性>
		ValueStack stack = ActionContext.getContext().getValueStack();
		
		stack.push(pageBean);
		
		return Constant.PAGE_SUCCESS;
	}
	
	public String delete(){
		
		customerService.delete(customer);
		
		return Constant.DELETE_SUCCESS;
	}
	
	/**
	 * 根据ID查询具体的客户信息，为后面的更新做准备
	 * @return
	 */
	public String edit(){
		
		//1. 查询到数据
		customer = customerService.findById(customer.getCust_id());
		
		
		//2. 存到值栈
		ActionContext.getContext().getValueStack().set("customer", customer);
		
		return Constant.EDIT_SUCCESS;
	}
	public String update(){
		
		customerService.update(customer);
		
		
		return Constant.UPDATE_SUCCESS;
	}
	public String findAllByAjax(){
		
		list = customerService.findAll();
		
		
		return Constant.JSON_SUCCESS;
	}
	
	@Override
	public Customer getModel() {
		if(customer == null){
			customer = new Customer();
		}
		return customer;
	}

}
