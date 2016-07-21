package com.aebiz.b2b2c.customermgr.customerinfomodify.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.customermgr.customerinfomodify.vo.CustomerInfoModifyModel;
import com.aebiz.b2b2c.customermgr.customerinfomodify.vo.CustomerInfoModifyQueryModel;

@Repository
public class CustomerInfoModifyH4Impl extends
		BaseH4Impl<CustomerInfoModifyModel, CustomerInfoModifyQueryModel>
		implements CustomerInfoModifyDAO {

}
