package com.aebiz.b2b2c.customermgr.customersubscription.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customersubscription.vo.CustomerSubscriptionModel;
import com.aebiz.b2b2c.customermgr.customersubscription.vo.CustomerSubscriptionQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerSubscriptionCacheImpl
		extends
		BaseMemcachedCache<CustomerSubscriptionModel, CustomerSubscriptionQueryModel>
		implements CustomerSubscriptionDAO {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerSubscriptionDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerSubscriptionCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERSUBSCRIPTION_KEY);
	}

	@Override
	public List<CustomerSubscriptionModel> getByCondition(
			CustomerSubscriptionQueryModel qm, int start, int pageShow) {
		return this.dao.getByCondition(qm, start, pageShow);
	}

	/**
	 * 根据会员订阅编号取消会员订阅
	 * 
	 * @param needUpdateUuids
	 */
	public void updateSubscriptionState(List<String> needUpdateUuids,
			String subState) {
		dao.updateSubscriptionState(needUpdateUuids, subState);

		if (needUpdateUuids != null && needUpdateUuids.size() > 0) {
			for (String uuid : needUpdateUuids) {
				mcc.delete(CustomerCacheConstant.CUSTOMER_CUSTOMERSUBSCRIPTION_KEY
						+ uuid);
			}
		}
	}

        @Override
        public void updateSubscriptionState(List<String> needUpdateUuids) {
            dao.updateSubscriptionState(needUpdateUuids);
        }
}
