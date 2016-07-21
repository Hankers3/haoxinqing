package com.aebiz.b2b2c.customermgr.customerinfomodify.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customerinfomodify.vo.CustomerInfoModifyModel;
import com.aebiz.b2b2c.customermgr.customerinfomodify.vo.CustomerInfoModifyQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerInfoModifyCacheImpl
		extends
		BaseMemcachedCache<CustomerInfoModifyModel, CustomerInfoModifyQueryModel>
		implements CustomerInfoModifyDAO {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerInfoModifyDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerInfoModifyCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERINFOMODIFY_KEY);
	}

}
