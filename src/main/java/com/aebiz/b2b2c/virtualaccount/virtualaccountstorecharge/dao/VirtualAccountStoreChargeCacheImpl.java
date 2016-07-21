package com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.virtualaccount.common.VirtualAccountCacheConstant;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.vo.VirtualAccountStoreChargeModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.vo.VirtualAccountStoreChargeQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class VirtualAccountStoreChargeCacheImpl
		extends
		BaseMemcachedCache<VirtualAccountStoreChargeModel, VirtualAccountStoreChargeQueryModel>
		implements VirtualAccountStoreChargeDAO {
	@Resource(name = VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private VirtualAccountStoreChargeDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public VirtualAccountStoreChargeCacheImpl() {
		super(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_STORE_CHARGE);
	}

}
