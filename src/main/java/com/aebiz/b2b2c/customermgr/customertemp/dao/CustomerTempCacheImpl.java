package com.aebiz.b2b2c.customermgr.customertemp.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customertemp.vo.CustomerTempModel;
import com.aebiz.b2b2c.customermgr.customertemp.vo.CustomerTempQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerTempCacheImpl extends
		BaseMemcachedCache<CustomerTempModel, CustomerTempQueryModel> implements
		CustomerTempDAO {
	
	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerTempDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerTempCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERTEMP_KEY);
	}

}
