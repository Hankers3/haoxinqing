package com.aebiz.b2b2c.websiteoperation.customerservice.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.websiteoperation.customerservice.vo.CustomerServiceModel;
import com.aebiz.b2b2c.websiteoperation.customerservice.vo.CustomerServiceQueryModel;

public interface CustomerServiceDAO extends BaseDAO<CustomerServiceModel,CustomerServiceQueryModel>{

	List<CustomerServiceModel> getAll();

}