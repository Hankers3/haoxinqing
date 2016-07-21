package com.aebiz.b2b2c.finance.storebondcharge.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.finance.common.FinanceCacheConstant;
import com.aebiz.b2b2c.finance.storebondcharge.vo.StoreBondChargeModel;
import com.aebiz.b2b2c.finance.storebondcharge.vo.StoreBondChargeQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class StoreBondChargeCacheImpl extends
		BaseMemcachedCache<StoreBondChargeModel, StoreBondChargeQueryModel>
		implements StoreBondChargeDAO {
	@Resource(name = FinanceCacheConstant.FINANCE_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private StoreBondChargeDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public StoreBondChargeCacheImpl() {
		super(FinanceCacheConstant.FINANCE_STORE_BOND_CHARGE);
	}

}
