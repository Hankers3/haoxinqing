package com.aebiz.b2b2c.customermgr.customersubscribecontent.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.vo.CustomerSubscribeContentModel;
import com.aebiz.b2b2c.customermgr.customersubscribecontent.vo.CustomerSubscribeContentQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerSubscribeContentCacheImpl
		extends
		BaseMemcachedCache<CustomerSubscribeContentModel, CustomerSubscribeContentQueryModel>
		implements CustomerSubscribeContentDAO {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerSubscribeContentDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerSubscribeContentCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERSUBSCRIBECONTENT_KEY);
	}

	/**
	 * 检查订阅编号是否存在
	 * 
	 * 当编辑时，需要排除本身的Id
	 */
	public boolean checkSubscribeNoExist(String subscribeNo, String uuid) {
		return dao.checkSubscribeNoExist(subscribeNo, uuid);
	}

	@Override
	public String getSubscribeNoByUuid(String uuid) {
		// 从缓存中取得对象
		CustomerSubscribeContentModel cscm = (CustomerSubscribeContentModel) mcc
				.get(CustomerCacheConstant.CUSTOMER_CUSTOMERSUBSCRIBECONTENT_KEY
						+ uuid);

		// 如果对象为空，则从数据库中查询
		if (cscm == null) {
			cscm = (CustomerSubscribeContentModel) dao.getByUuid(uuid);

			// 从数据库中查询，如果存在，则放入到缓存
			if (cscm != null) {
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERSUBSCRIBECONTENT_KEY
						+ uuid, cscm);
			}
		}

		// 如果为空，则返回空
		if (cscm == null) {
			return "";
		}

		return cscm.getSubscribeNo();
	}

}
