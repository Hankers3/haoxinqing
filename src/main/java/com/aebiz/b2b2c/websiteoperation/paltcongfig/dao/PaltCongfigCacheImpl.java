package com.aebiz.b2b2c.websiteoperation.paltcongfig.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.websiteoperation.common.WebsiteOperateCacheConstant;
import com.aebiz.b2b2c.websiteoperation.customerquiz.vo.CustomerQuizModel;
import com.aebiz.b2b2c.websiteoperation.paltcongfig.vo.PaltCongfigModel;
import com.aebiz.b2b2c.websiteoperation.paltcongfig.vo.PaltCongfigQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class PaltCongfigCacheImpl extends
		BaseMemcachedCache<PaltCongfigModel, PaltCongfigQueryModel> implements
		PaltCongfigDAO {

	@Resource(name = WebsiteOperateCacheConstant.WEBSITE_OPERATE_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private PaltCongfigDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public PaltCongfigCacheImpl() {
		super(WebsiteOperateCacheConstant.WEBSITE_OPERATE_PLATCONFIG);
	}

	@Override
	public void update(PaltCongfigModel m) {
		this.dao.update(m);
		this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_OPERATE_PLATCONFIG + m.getUuid());
	}
	
	
}
