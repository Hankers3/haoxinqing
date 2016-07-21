package com.aebiz.b2b2c.finance.storefinanceaccount.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.common.FinanceCacheConstant;
import com.aebiz.b2b2c.finance.storefinanceaccount.vo.StoreFinanceAccountModel;
import com.aebiz.b2b2c.finance.storefinanceaccount.vo.StoreFinanceAccountQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class StoreFinanceAccountCacheImpl
		extends
		BaseMemcachedCache<StoreFinanceAccountModel, StoreFinanceAccountQueryModel>
		implements StoreFinanceAccountDAO {
	@Resource(name = FinanceCacheConstant.FINANCE_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private StoreFinanceAccountDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public StoreFinanceAccountCacheImpl() {
		super(FinanceCacheConstant.FINANCE_STORE_FINANCE_ACCOUNT);
	}

	@Override
	protected void updateCache(StoreFinanceAccountModel m) {
		super.updateCache(m);
		if (m != null) {
			this.mcc.set(
					FinanceCacheConstant.FINANCE_STORE_FINANCE_ACCOUNT
							+ m.getAccountUuid(), m);
		}
	}

	@Override
	protected void deleteCache(StoreFinanceAccountModel m) {
		super.deleteCache(m);
		if (m != null) {
			this.mcc.delete(FinanceCacheConstant.FINANCE_STORE_FINANCE_ACCOUNT
					+ m.getAccountUuid());
		}
	}

	@Override
	public StoreFinanceAccountModel getStoreFinanceAccountModelByAccount(
			String accountUUid) {
		Object obj = this.mcc
				.get(FinanceCacheConstant.FINANCE_STORE_FINANCE_ACCOUNT
						+ accountUUid);
		if (obj != null) {
			return (StoreFinanceAccountModel) obj;
		}
		StoreFinanceAccountModel m = null;
		String uuid = this.myDao
				.getStoreFinanceAccountUuidByAccount(accountUUid);
		if (!StringUtil.isEmpty(uuid)) {
			obj = this.mcc
					.get(FinanceCacheConstant.FINANCE_STORE_FINANCE_ACCOUNT
							+ uuid);
			if (obj != null) {
				m = (StoreFinanceAccountModel) obj;
			} else {
				m = this.myDao.getByUuid(uuid);
				if (m != null) {
					this.mcc.set(
							FinanceCacheConstant.FINANCE_STORE_FINANCE_ACCOUNT
									+ uuid, m);
					this.mcc.set(
							FinanceCacheConstant.FINANCE_STORE_FINANCE_ACCOUNT
									+ m.getAccountUuid(), m);
				}
			}
		}
		return m;
	}

	@Override
	public String getStoreFinanceAccountUuidByAccount(String accountUUid) {
		return this.myDao.getStoreFinanceAccountUuidByAccount(accountUUid);
	}

}
