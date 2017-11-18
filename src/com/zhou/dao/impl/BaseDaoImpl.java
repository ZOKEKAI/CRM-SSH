package com.zhou.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zhou.bean.Customer;
import com.zhou.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class clazz;

	//1. 通过构造方法，让子类传递class上来，但是这有个问题就是所有的子类都必须写构造。
	/*public BaseDaoImpl(Class clazz) {
		super();
		this.clazz = clazz;
	}*/

	//2. 其实java也提供了方法获取类上面的泛型是具体上面类型
	public BaseDaoImpl(){
		//需求： 获取BaseDaoImpl<T> T的具体类型
		/*
		 *  获取泛型
		 *  this :子类对象  ---- this --> 谁调用了这个方法，this 就是谁。
		 *  this.getClass(): 子类的字节码对象
		 *  this.getClass().getGenericSuperclass() : 得到父类的字节码对象，但是带有了泛型。
		 *  
		 *  虽然上面的方法返回值是Type类型，但是要 转化成ParameterizedType ，因为它身上定义了获取泛型具体类型的方法。
		 *  type.getActualTypeArguments() ： 有可能类上面有多个泛型， [0] , 表示取第一个。
		 *  请问是否能够拿到自己类上面的泛型类型。不能。 要想后去这个类的泛型是什么类型，必须从子类着手。
		 */
		//Class clazz = BaseDaoImpl.class; //无法获取到泛型， 仅仅是拿到了字节码
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		
		clazz = (Class) type.getActualTypeArguments()[0];
	}
	
	
	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	@Override
	public int findCount(DetachedCriteria criteria) {
		System.out.println("调用了basedaoimpl 中的findCount~~");
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
		if(list.size() > 0 ){
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public T findById(Serializable cust_id) {
		
		//这里的class是什么类型，从父类的角度来讲，它是不知道，因为根本不知道子类给什么类型过来。
		//不能直接具体类。 那么这个类型就得是父类问子类要。  new 子类()  ---> 调用子类的构造方法  ---> super 0----> 调用父类的构造
		return (T) getHibernateTemplate().get(clazz, cust_id);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(clazz));
	}

	@Override
	public List<T> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		
		System.out.println("调用了basedaoimpl 中的findBypage~~");
		
		criteria.setProjection(null);
		
		return (List<T>) getHibernateTemplate().findByCriteria(criteria, (currentPage-1)*pageSize, pageSize);
	}

}
