package com.aebiz.b2b2c.customermgr.customercomb.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthModel;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthQueryModel;
import com.aebiz.b2b2c.customermgr.customercomb.vo.CustomerCombModel;
import com.aebiz.b2b2c.customermgr.customercomb.vo.CustomerCombQueryModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerCombCacheImpl extends
		BaseMemcachedCache<CustomerCombModel, CustomerCombQueryModel> implements
		CustomerCombDao {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerCombDao dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerCombCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERCOMB_KEY);
	}

	@Override
	public void update(CustomerCombModel m) {
		dao.update(m);
		this.mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMERCOMB_KEY
				+ m.getUuid());
	}
	
	@Override
	public void delete(CustomerCombModel m) {
		super.delete(m);
		super.deleteCache(m);
	}

}
