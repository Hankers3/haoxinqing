package com.aebiz.b2b2c.finance.customeraccount.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.common.FinanceCacheConstant;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerAccountCacheImpl extends
		BaseMemcachedCache<CustomerAccountModel, CustomerAccountQueryModel>
		implements CustomerAccountDAO {
	@Resource(name = FinanceCacheConstant.FINANCE_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerAccountDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public CustomerAccountCacheImpl() {
		super(FinanceCacheConstant.FINANCE_CUSTOMER_ACCOUNT);
	}
	
	@Override
	public void update(CustomerAccountModel m) {
		super.update(m);
		if(null != m){
			mcc.set(FinanceCacheConstant.FINANCE_CUSTOMER_ACCOUNT
					+ m.getCustomerUuid(), m);
		}
	}

	@Override
	public void delete(CustomerAccountModel m) {
		super.delete(m);
		if(null != m){
			mcc.delete(FinanceCacheConstant.FINANCE_CUSTOMER_ACCOUNT
					+ m.getCustomerUuid());
		}
	}

	@Override
	public CustomerAccountModel getCustomerAccountModelByCustomerUuid(
			String customerUuid) {
		CustomerAccountModel cam = (CustomerAccountModel)mcc.get(FinanceCacheConstant.FINANCE_CUSTOMER_ACCOUNT + customerUuid);
		if(cam!=null){
			return cam;
		}
		
		CustomerAccountModel m = myDao.getCustomerAccountModelByCustomerUuid(customerUuid);;

		// 2.通过会员账户编号获得缓存的数据
		if (null !=m) {
			this.mcc.set(FinanceCacheConstant.FINANCE_CUSTOMER_ACCOUNT
					+ m.getUuid(), m);
			this.mcc.set(FinanceCacheConstant.FINANCE_CUSTOMER_ACCOUNT
					+ m.getCustomerUuid(), m);
		}
		return m;
	}

	/**
	 * 通过会员UUID获得会员的账户对象
	 * 1.会员账户对象需要维护UUID作为KEY <br />
	 * 2.会员账户对象需要维护CUSTOMERUUID作为KEY <br />
	 * 
	 */
	@Override
	public String getCustomerAccountUuidByCustomerUuid(String customerUuid) {

		String uuid = (String) this.mcc.get(FinanceCacheConstant.FINANCE_CUSTOMER_ACCOUNT_UUID + customerUuid);
		    // 如果id为空
	        if (StringUtil.isEmpty(uuid)) {
	            uuid = myDao.getCustomerAccountUuidByCustomerUuid(customerUuid);
	                // 从数据库中查询，如果存在，则放入到缓存
	            if (!StringUtil.isEmpty(uuid)) {
	                        mcc.set(FinanceCacheConstant.FINANCE_CUSTOMER_ACCOUNT_UUID + customerUuid,
	                                customerUuid);
	                    }
	        }
	        return uuid;  
	}
}
