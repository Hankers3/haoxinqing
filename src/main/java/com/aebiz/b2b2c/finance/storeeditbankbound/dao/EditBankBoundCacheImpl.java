package com.aebiz.b2b2c.finance.storeeditbankbound.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.common.FinanceCacheConstant;
import com.aebiz.b2b2c.finance.storeeditbankbound.vo.EditBankBoundModel;
import com.aebiz.b2b2c.finance.storeeditbankbound.vo.EditBankBoundQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class EditBankBoundCacheImpl extends
		BaseMemcachedCache<EditBankBoundModel, EditBankBoundQueryModel>
		implements EditBankBoundDAO {
	@Resource(name = FinanceCacheConstant.FINANCE_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private EditBankBoundDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public EditBankBoundCacheImpl() {
		super(FinanceCacheConstant.FINANCE_STORE_EDIT_BANK_BOUND);
	}

	public EditBankBoundModel getStoreEditBankBoundByStoreUuid(String storeUuid) {
		EditBankBoundModel m = null;
		String uuid = this.myDao
				.getStoreEditBankBoundUuidByStoreUuid(storeUuid);
		if (!StringUtil.isEmpty(uuid)) {
			Object obj = this.mcc
					.get(FinanceCacheConstant.FINANCE_STORE_EDIT_BANK_BOUND
							+ uuid);
			if (obj != null) {
				m = (EditBankBoundModel) obj;
			} else {
				m = this.myDao.getByUuid(uuid);
				if (m != null) {
					this.mcc.set(
							FinanceCacheConstant.FINANCE_STORE_EDIT_BANK_BOUND
									+ uuid, m);
				}
			}
		}
		return m;
	}

	public String getStoreEditBankBoundUuidByStoreUuid(String storeUuid) {
		return myDao.getStoreEditBankBoundUuidByStoreUuid(storeUuid);
	}

}
