package com.aebiz.b2b2c.websiteoperation.customerservice.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.websiteoperation.customerservice.vo.CustomerServiceModel;
import com.aebiz.b2b2c.websiteoperation.customerservice.vo.CustomerServiceQueryModel;

public interface CustomerServiceService extends BaseService<CustomerServiceModel,CustomerServiceQueryModel>{

	List<CustomerServiceModel> getAll();

}
