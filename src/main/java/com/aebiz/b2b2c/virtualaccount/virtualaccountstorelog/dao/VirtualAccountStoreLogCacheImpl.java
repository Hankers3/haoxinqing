package com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.virtualaccount.common.VirtualAccountCacheConstant;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.vo.VirtualAccountStoreLogModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.vo.VirtualAccountStoreLogQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class VirtualAccountStoreLogCacheImpl
		extends
		BaseMemcachedCache<VirtualAccountStoreLogModel, VirtualAccountStoreLogQueryModel>
		implements VirtualAccountStoreLogDAO {
	@Resource(name = VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private VirtualAccountStoreLogDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public VirtualAccountStoreLogCacheImpl() {
		super(VirtualAccountCacheConstant.VIRTUAL_ACCOUNT_STORE_LOG);
	}
}
