package com.aebiz.b2b2c.finance.ledgerbystorecategory.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.common.FinanceCacheConstant;
import com.aebiz.b2b2c.finance.ledgerbystorecategory.vo.LedgerByStoreCategoryModel;
import com.aebiz.b2b2c.finance.ledgerbystorecategory.vo.LedgerByStoreCategoryQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class LedgerByStoreCategoryCacheImpl
		extends
		BaseMemcachedCache<LedgerByStoreCategoryModel, LedgerByStoreCategoryQueryModel>
		implements LedgerByStoreCategoryDAO {
	@Resource(name = FinanceCacheConstant.FINANCE_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private LedgerByStoreCategoryDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public LedgerByStoreCategoryCacheImpl() {
		super(FinanceCacheConstant.FINANCE_LEDGER_BY_STORECATEGORY);
	}

	@Override
	public LedgerByStoreCategoryModel getLedgerByStoreCategoryModelByAccountUuidAndCategoryUuid(
			String accountUuid, String categoryUuid) {
		LedgerByStoreCategoryModel m = null;
		String uuid = this.myDao
				.getLedgerByStoreCategoryUuidByAccountUuidAndCategoryUuid(
						accountUuid, categoryUuid);
		if (!StringUtil.isEmpty(uuid)) {
			Object obj = this.mcc
					.get(FinanceCacheConstant.FINANCE_LEDGER_BY_STORECATEGORY
							+ uuid);
			if (obj != null) {
				m = (LedgerByStoreCategoryModel) obj;
			} else {
				m = this.myDao.getByUuid(uuid);
				this.mcc.set(
						FinanceCacheConstant.FINANCE_LEDGER_BY_STORECATEGORY
								+ uuid, m);
			}
		}
		return m;
	}

	@Override
	public String getLedgerByStoreCategoryUuidByAccountUuidAndCategoryUuid(
			String accountUuid, String categoryUuid) {

		String compKeyId = accountUuid + "/" + categoryUuid;

		Object obj = this.mcc
				.get(FinanceCacheConstant.FINANCE_LEDGER_BY_STORECATEGORY_AIDWITHCID
						+ compKeyId);

		LedgerByStoreCategoryModel m = null;

		if (obj != null) {
			m = (LedgerByStoreCategoryModel) obj;
		} else {
			m = myDao
					.getLedgerByStoreCategoryModelByAccountUuidAndCategoryUuid(
							accountUuid, categoryUuid);
			this.mcc.set(
					FinanceCacheConstant.FINANCE_LEDGER_BY_STORECATEGORY_AIDWITHCID
							+ compKeyId, m);
		}

		if (m != null) {
			return m.getUuid();
		}

		return "";
	}
}
