package com.aebiz.b2b2c.finance.ledgerbystore.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.common.FinanceCacheConstant;
import com.aebiz.b2b2c.finance.ledgerbystore.vo.LedgerByStoreModel;
import com.aebiz.b2b2c.finance.ledgerbystore.vo.LedgerByStoreQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class LedgerByStoreCacheImpl extends
		BaseMemcachedCache<LedgerByStoreModel, LedgerByStoreQueryModel>
		implements LedgerByStoreDAO {
	@Resource(name = FinanceCacheConstant.FINANCE_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private LedgerByStoreDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public LedgerByStoreCacheImpl() {
		super(FinanceCacheConstant.FINANCE_LEDGER_BY_STORE);
	}
	/**
         * 根据商户uuid查询LedgerByStoreModel对象
         * @param accountUuid
         * @return 
         * LedgerByStoreModel
         */
	@Override
	public LedgerByStoreModel getLedgerByStoreModelByAccountUuid(
			String accountUuid) {
		LedgerByStoreModel m = null;
		String uuid = this.myDao.getLedgerByStoreUuidByAccountUuid(accountUuid);
		if (!StringUtil.isEmpty(uuid)) {
			Object obj = this.mcc
					.get(FinanceCacheConstant.FINANCE_LEDGER_BY_STORE + uuid);
			if (obj != null) {
				m = (LedgerByStoreModel) obj;
			} else {
				m = this.myDao.getByUuid(uuid);
				this.mcc.set(FinanceCacheConstant.FINANCE_LEDGER_BY_STORE
						+ uuid, m);
			}
		}
		return m;
	}
	/**
         * 根据商户uuid查询LedgerByStoreModel对象的uuid
         * 
         * @param accountUuid
         * @return LedgerByStoreModel
         */
	@Override
	public String getLedgerByStoreUuidByAccountUuid(String accountUuid) {
		Object obj = this.mcc
				.get(FinanceCacheConstant.FINANCE_LEDGER_BY_STORE_ACCOUNTID
						+ accountUuid);

		LedgerByStoreModel m = null;
		if (obj != null) {
			m = (LedgerByStoreModel) obj;
		} else {
			m = myDao.getLedgerByStoreModelByAccountUuid(accountUuid);
			this.mcc.set(FinanceCacheConstant.FINANCE_LEDGER_BY_STORE_ACCOUNTID
					+ accountUuid, m);
		}

		return this.myDao.getLedgerByStoreUuidByAccountUuid(accountUuid);
	}

        @Override
        public double sumLedgerRate(List<String> accountUuids) {
           
            return   myDao.sumLedgerRate(accountUuids);
        }
	
	
	
	

}
