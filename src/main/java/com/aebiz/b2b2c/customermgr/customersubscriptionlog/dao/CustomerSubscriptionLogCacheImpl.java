package com.aebiz.b2b2c.customermgr.customersubscriptionlog.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.vo.CustomerSubscriptionLogModel;
import com.aebiz.b2b2c.customermgr.customersubscriptionlog.vo.CustomerSubscriptionLogQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerSubscriptionLogCacheImpl
		extends
		BaseMemcachedCache<CustomerSubscriptionLogModel, CustomerSubscriptionLogQueryModel>
		implements CustomerSubscriptionLogDAO {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerSubscriptionLogDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerSubscriptionLogCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERSUBSCRIPTIONLOG_KEY);
	}

	/**
	 * 
	 * @Description: TODO(通过会员编号查询出该会员所有订阅的详细信息)    
	 * @author XP  
	 * @param customerUuid
	 * @return
	 * @date 2015-12-28 下午4:39:26
	 */
	public List<CustomerSubscriptionLogModel> getSubscriptionListByCustomerUuid(
			String customerUuid) {
		List<String> uuids = this
				.getSubscriptionUuidsByCustomerUuid(customerUuid);
		// 2：在内存中找 这些uuid对应的对象
		List<CustomerSubscriptionLogModel> listM = new ArrayList<CustomerSubscriptionLogModel>();
		List<String> noUuids = new ArrayList<String>();
		if(uuids!=null&&uuids.size()>0){
		for (String uuid : uuids) {
			Object obj = mcc
					.get(CustomerCacheConstant.CUSTOMER_CUSTOMERSUBSCRIPTIONLOG_KEY
							+ uuid);
			if (obj != null) {
				CustomerSubscriptionLogModel m = (CustomerSubscriptionLogModel) obj;
				listM.add(m);
			} else {
				noUuids.add(uuid);
			}
		}
		// 3：内存中找不到对应对象的uuid，从数据库里面获取，并设置到缓存中
		if (noUuids.size() > 0) {
			for (String uuid : noUuids) {
				CustomerSubscriptionLogModel m = (CustomerSubscriptionLogModel) dao
						.getByUuid(uuid);
				// 并设置到缓存中
				mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERSUBSCRIPTIONLOG_KEY
						+ uuid, m);

				listM.add(m);
			}
		}
		}
		return listM;
	}
	/**
	 * 
	 * @Description: (根据患者的id获取描述的uuids)    
	 * @author XP  
	 * @param customerUuid
	 * @return
	 * @date 2015-12-29 下午4:04:26
	 */
	@Override
	public List<String> getSubscriptionUuidsByCustomerUuid(String customerUuid) {
	    List<String> uuids = (List<String>) this.mcc
                    .get(CustomerCacheConstant.CUSTOMER_CUSTOMERSUBSCRIPTIONLOG_KEY_UUIDS
                                    + customerUuid);
	        if (uuids == null || uuids.size() < 0) {
	            uuids = this.dao.getSubscriptionUuidsByCustomerUuid(customerUuid);
	            this.mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERSUBSCRIPTIONLOG_KEY_UUIDS
                            + customerUuid, uuids);
	        }

	        return uuids;
	}
}
