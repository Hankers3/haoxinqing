package com.aebiz.b2b2c.customermgr.customerloginlog.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customerinfo.dao.CustomerInfoDAO;
import com.aebiz.b2b2c.customermgr.customerloginlog.vo.CustomerLoginLogModel;
import com.aebiz.b2b2c.customermgr.customerloginlog.vo.CustomerLoginLogQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerLoginLogCacheImpl extends
		BaseMemcachedCache<CustomerLoginLogModel, CustomerLoginLogQueryModel>
		implements CustomerLoginLogDAO {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerInfoDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerLoginLogCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERLOGINLOG_KEY);
	}
}
