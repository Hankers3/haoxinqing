package com.aebiz.b2b2c.finance.customerbankrel.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.common.FinanceCacheConstant;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelModel;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerBankRelCacheImpl extends
		BaseMemcachedCache<CustomerBankRelModel, CustomerBankRelQueryModel>
		implements CustomerBankRelDAO {
	@Resource(name = FinanceCacheConstant.FINANCE_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerBankRelDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public CustomerBankRelCacheImpl() {
		super(FinanceCacheConstant.FINANCE_CUSTOMER_BANK_REL);
	}

	public void update(CustomerBankRelModel m) {
		super.update(m);

		// 更新CUSTOMERUUID
		this.mcc.set(
				FinanceCacheConstant.FINANCE_CUSTOMER_BANK_REL_CID
						+ m.getCustomerUuid(), m);
	}
	
	

	public void delete(CustomerBankRelModel m) {
		super.delete(m);

		Object obj = this.mcc
				.get(FinanceCacheConstant.FINANCE_CUSTOMER_BANK_REL_CID
						+ m.getCustomerUuid());
		if (obj != null) {
			this.mcc.delete(FinanceCacheConstant.FINANCE_CUSTOMER_BANK_REL_CID
					+ m.getCustomerUuid());
		}
	}
	/**
         * 根据会员的uuid查询绑定申请对象,来判断该会员是否已经申请过绑定了
         * @param uuid
         * @return 
         * CustomerBankRelModel
         */
	@Override
	public CustomerBankRelModel getCustomerBankRelModelByCustomerUuid(String id) {
		CustomerBankRelModel m = null;
		String uuid = this.getCustomerBankRelUuidByCustomerUuid(id);

		if (!StringUtil.isEmpty(uuid)) {
			Object obj = this.mcc
					.get(FinanceCacheConstant.FINANCE_CUSTOMER_BANK_REL + uuid);
			if (obj != null) {
				m = (CustomerBankRelModel) obj;
			} else {
				m = this.myDao.getByUuid(uuid);
				if (m != null) {
					this.mcc.set(FinanceCacheConstant.FINANCE_CUSTOMER_BANK_REL
							+ uuid, m);
				}
			}
		}
		return m;
	}
        /**
         * 根据会员的编号获得会员和银行绑定的编号
         * 
         * @param customerUuid
         * @return
         */
	@Override
	public String getCustomerBankRelUuidByCustomerUuid(String customerUuid) {
		CustomerBankRelModel m = null;
		Object obj = this.mcc
				.get(FinanceCacheConstant.FINANCE_CUSTOMER_BANK_REL_CID
						+ customerUuid);
		if (obj != null) {
			m = (CustomerBankRelModel) obj;
		} else {
			m = myDao.getCustomerBankRelModelByCustomerUuid(customerUuid);

			if (m != null) {
				this.mcc.set(FinanceCacheConstant.FINANCE_CUSTOMER_BANK_REL_CID
						+ customerUuid, m);
			}
		}

		if (m != null) {
			return m.getUuid();
		}
		return "";
	}
}
