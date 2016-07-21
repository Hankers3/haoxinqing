package com.aebiz.b2b2c.finance.storeservicecharge.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.common.FinanceCacheConstant;
import com.aebiz.b2b2c.finance.storeservicecharge.vo.StoreServiceChargeModel;
import com.aebiz.b2b2c.finance.storeservicecharge.vo.StoreServiceChargeQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class StoreServiceChargeCacheImpl
		extends
		BaseMemcachedCache<StoreServiceChargeModel, StoreServiceChargeQueryModel>
		implements StoreServiceChargeDAO {
	@Resource(name = FinanceCacheConstant.FINANCE_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private StoreServiceChargeDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public StoreServiceChargeCacheImpl() {
		super(FinanceCacheConstant.FINANCE_STORE_SERVICE_CHARGE);
	}

	@Override
	public StoreServiceChargeModel getUnderPayServiceCharge(String accountUuid) {
		StoreServiceChargeModel m = null;
		String uuid = this.myDao.getUnderPayServiceChargeUuid(accountUuid);
		if (!StringUtil.isEmpty(uuid)) {
			Object obj = this.mcc
					.get(FinanceCacheConstant.FINANCE_STORE_SERVICE_CHARGE
							+ uuid);
			if (obj != null) {
				m = (StoreServiceChargeModel) obj;
			} else {
				m = this.myDao.getByUuid(uuid);
				if (m != null) {
					this.mcc.set(
							FinanceCacheConstant.FINANCE_STORE_SERVICE_CHARGE
									+ uuid, m);
				}
			}
		}
		return m;
	}

	@Override
	public String getUnderPayServiceChargeUuid(String accountUuid) {
		return this.getUnderPayServiceChargeUuid(accountUuid);
	}

}
