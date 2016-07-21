package com.aebiz.b2b2c.basicdata.sensitiveword.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.basicdata.commom.BasicDataCacheConstant;
import com.aebiz.b2b2c.basicdata.sensitiveword.vo.SensitiveWordModel;
import com.aebiz.b2b2c.basicdata.sensitiveword.vo.SensitiveWordQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class SensitiveWordCacheImpl extends
		BaseMemcachedCache<SensitiveWordModel, SensitiveWordQueryModel>
		implements SensitiveWordDAO {

	@Resource(name = BasicDataCacheConstant.BASIC_DATA_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private SensitiveWordDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public SensitiveWordCacheImpl() {
		super(BasicDataCacheConstant.BASIC_DATA_SENSITIVEWORD);
	}
}
