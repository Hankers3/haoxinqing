package com.aebiz.b2b2c.customermgr.customeraudit.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthModel;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerAuditCacheImpl extends
		BaseMemcachedCache<CustomerAuthModel, CustomerAuthQueryModel> implements
		CustomerAuditDAO {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerAuditDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerAuditCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERAUDIT_KEY);
	}

	/**
	 * 会员实名认证审核通过
	 */
	@Override
	public void doAuditPass(String customerUuid, String auditReason) {
		dao.doAuditPass(customerUuid, auditReason);

		String uuid = this.getAuditUuidByCustomerUuid(customerUuid);
		mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMERAUDIT_KEY + uuid);

		CustomerAuthModel customerAuth = dao.getByUuid(uuid);
		if (customerAuth != null) {
			mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERAUDIT_KEY + uuid,
					customerAuth);
		}
	}

	/**
	 * 会员实名认证审核不通过
	 */
	@Override
	public void doAuditUnPass(String customerUuid, String auditReason) {
		dao.doAuditUnPass(customerUuid, auditReason);
		String uuid = this.getAuditUuidByCustomerUuid(customerUuid);
		mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMERAUDIT_KEY + uuid);

		CustomerAuthModel customerAuth = dao.getByUuid(uuid);
		if (customerAuth != null) {
			mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERAUDIT_KEY + uuid,
					customerAuth);
		}
	}

	@Override
	public String getAuditUuidByCustomerUuid(String customerUuid) {
		return dao.getAuditUuidByCustomerUuid(customerUuid);
	}

}
