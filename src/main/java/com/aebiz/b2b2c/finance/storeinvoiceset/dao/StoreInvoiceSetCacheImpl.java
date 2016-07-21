package com.aebiz.b2b2c.finance.storeinvoiceset.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.common.FinanceCacheConstant;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetModel;
import com.aebiz.b2b2c.finance.storeinvoiceset.vo.StoreInvoiceSetQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class StoreInvoiceSetCacheImpl extends
		BaseMemcachedCache<StoreInvoiceSetModel, StoreInvoiceSetQueryModel>
		implements StoreInvoiceSetDAO {
	@Resource(name = FinanceCacheConstant.FINANCE_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private StoreInvoiceSetDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public StoreInvoiceSetCacheImpl() {
		super(FinanceCacheConstant.FINANCE_STORE_INVOICE_SET);
	}

	@Override
	public void update(StoreInvoiceSetModel m) {
		super.update(m);
		if(null != m){
			mcc.set(FinanceCacheConstant.FINANCE_STORE_INVOICE_SET
					+ m.getAccountUuid(), m);
		}
	}

	@Override
	public void delete(StoreInvoiceSetModel m) {
		super.delete(m);
		if(null != m){
			mcc.delete(FinanceCacheConstant.FINANCE_STORE_INVOICE_SET
					+ m.getAccountUuid());
		}
	}

	@Override
	public StoreInvoiceSetModel getStoreInvoiceSetWebModelByAccoutUuid(
			String accountUuid) {
		if(StringUtil.isEmpty(accountUuid)){
			return null;
		}
		StoreInvoiceSetModel ssm = (StoreInvoiceSetModel)mcc.get(FinanceCacheConstant.FINANCE_STORE_INVOICE_SET + accountUuid);
		if(null!=ssm){
			return ssm;
		}
		StoreInvoiceSetModel m = null;
		String uuid = this.myDao
				.getStoreInvoiceSetWebUuidByAccoutUuid(accountUuid);
		if (!StringUtil.isEmpty(uuid)) {
			Object obj = this.mcc
					.get(FinanceCacheConstant.FINANCE_STORE_INVOICE_SET + uuid);
			if (obj != null) {
				m = (StoreInvoiceSetModel) obj;
				this.mcc.set(FinanceCacheConstant.FINANCE_STORE_INVOICE_SET
						+ m.getAccountUuid(), m);
			} else {
				m = this.myDao.getByUuid(uuid);
				if (m != null) {
					this.mcc.set(FinanceCacheConstant.FINANCE_STORE_INVOICE_SET
							+ uuid, m);
					this.mcc.set(FinanceCacheConstant.FINANCE_STORE_INVOICE_SET
							+ m.getAccountUuid(), m);
				}
			}
		}
		return m;
	}

	@Override
	public String getStoreInvoiceSetWebUuidByAccoutUuid(String accountUuid) {
		return this.myDao.getStoreInvoiceSetWebUuidByAccoutUuid(accountUuid);
	}

}
