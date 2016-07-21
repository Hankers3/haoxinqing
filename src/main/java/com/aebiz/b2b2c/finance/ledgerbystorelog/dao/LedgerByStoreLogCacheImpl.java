package com.aebiz.b2b2c.finance.ledgerbystorelog.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.finance.common.FinanceCacheConstant;
import com.aebiz.b2b2c.finance.ledgerbystorelog.vo.LedgerByStoreLogModel;
import com.aebiz.b2b2c.finance.ledgerbystorelog.vo.LedgerByStoreLogQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class LedgerByStoreLogCacheImpl extends
		BaseMemcachedCache<LedgerByStoreLogModel, LedgerByStoreLogQueryModel>
		implements LedgerByStoreLogDAO {
	@Resource(name = FinanceCacheConstant.FINANCE_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private LedgerByStoreLogDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public LedgerByStoreLogCacheImpl() {
		super(FinanceCacheConstant.FINANCE_LEDGER_BY_STORE_LOG);
	}
}
