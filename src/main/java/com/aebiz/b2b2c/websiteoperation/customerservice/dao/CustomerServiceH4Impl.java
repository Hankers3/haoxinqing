package com.aebiz.b2b2c.websiteoperation.customerservice.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.websiteoperation.customerservice.vo.CustomerServiceModel;
import com.aebiz.b2b2c.websiteoperation.customerservice.vo.CustomerServiceQueryModel;

@Repository
public class CustomerServiceH4Impl extends BaseH4Impl<CustomerServiceModel,CustomerServiceQueryModel> implements CustomerServiceDAO {
	
	public List<CustomerServiceModel> getAll(){
		StringBuffer hql = new StringBuffer("from CustomerServiceModel o order by o.opeTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		List<CustomerServiceModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
