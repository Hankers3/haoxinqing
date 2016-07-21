package com.aebiz.b2b2c.customermgr.storeback.dao.customerstorelevelrel;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.customermgr.customerstorelevelrel.vo.CustomerStoreLevelRelModel;
import com.aebiz.b2b2c.customermgr.customerstorelevelrel.vo.CustomerStoreLevelRelQueryModel;

@Repository
public class CustomerStoreLevelRelH4Impl extends
		BaseH4Impl<CustomerStoreLevelRelModel, CustomerStoreLevelRelQueryModel>
		implements CustomerStoreLevelRelDAO {

}
