package com.aebiz.b2b2c.customermgr.customerloginlog.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.customermgr.customerloginlog.vo.CustomerLoginLogModel;
import com.aebiz.b2b2c.customermgr.customerloginlog.vo.CustomerLoginLogQueryModel;

@Repository
public class CustomerLoginLogH4Impl extends
		BaseH4Impl<CustomerLoginLogModel, CustomerLoginLogQueryModel> implements
		CustomerLoginLogDAO {

}
