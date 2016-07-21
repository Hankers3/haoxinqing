package com.aebiz.b2b2c.websiteoperation.customerquiz.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.websiteoperation.common.WebsiteOperateCacheConstant;
import com.aebiz.b2b2c.websiteoperation.customerquiz.vo.CustomerQuizModel;
import com.aebiz.b2b2c.websiteoperation.customerquiz.vo.CustomerQuizQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerQuizCacheImpl extends
		BaseMemcachedCache<CustomerQuizModel, CustomerQuizQueryModel>
		implements CustomerQuizDAO {

	@Resource(name = WebsiteOperateCacheConstant.WEBSITE_OPERATE_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerQuizDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerQuizCacheImpl() {
		super(WebsiteOperateCacheConstant.WEBSITE_CUSTOMERQUIZ_KEY);
	}

	@Override
	public void update(CustomerQuizModel m) {
		this.dao.update(m);
		this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_CUSTOMERQUIZ_KEY + m.getUuid());
	}
	
    
      
	
	
	
}
