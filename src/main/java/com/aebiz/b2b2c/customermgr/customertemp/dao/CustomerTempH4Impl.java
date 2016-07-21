package com.aebiz.b2b2c.customermgr.customertemp.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.customermgr.customertemp.vo.CustomerTempModel;
import com.aebiz.b2b2c.customermgr.customertemp.vo.CustomerTempQueryModel;

@Repository
public class CustomerTempH4Impl extends
		BaseH4Impl<CustomerTempModel, CustomerTempQueryModel> implements
		CustomerTempDAO {

}
