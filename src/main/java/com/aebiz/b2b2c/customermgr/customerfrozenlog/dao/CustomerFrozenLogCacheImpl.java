package com.aebiz.b2b2c.customermgr.customerfrozenlog.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogModel;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerFrozenLogCacheImpl extends
		BaseMemcachedCache<CustomerFrozenLogModel, CustomerFrozenLogQueryModel>
		implements CustomerFrozenLogDAO {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerFrozenLogDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerFrozenLogCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERFROZENLOG_KEY);
	}

	/**
	 * 根据会员编号获取会员最新冻结日志记录
	 */
	@Override
	public CustomerFrozenLogModel getFrozenLog(String customerUuid) {
		String uuid = this.getFrozenLogUuid(customerUuid);

		if (!StringUtil.isEmpty(uuid)) {
			// 从缓存中取得对象
			CustomerFrozenLogModel cm = (CustomerFrozenLogModel) mcc
					.get(CustomerCacheConstant.CUSTOMER_CUSTOMERFROZENLOG_KEY
							+ customerUuid);

			// 如果对象为空，则从数据库中查询
			if (cm == null) {
				cm = (CustomerFrozenLogModel) dao.getByUuid(uuid);

				// 从数据库中查询，如果存在，则放入到缓存
				if (cm != null) {
					mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERFROZENLOG_KEY
							+ customerUuid, cm);
				}
			}
			return cm;
		}
		return null;
	}

	/**
	 * 根据会员编号获取会员最新解冻日志记录
	 */
	@Override
	public CustomerFrozenLogModel getUnFrozenLog(String customerUuid) {
		String uuid = this.getUnFrozenLogUuid(customerUuid);

		if (!StringUtil.isEmpty(uuid)) {
			// 从缓存中取得对象
			CustomerFrozenLogModel cm = (CustomerFrozenLogModel) mcc
					.get(CustomerCacheConstant.CUSTOMER_CUSTOMERFROZENLOG_KEY
							+ customerUuid);

			// 如果对象为空，则从数据库中查询
			if (cm == null) {
				cm = (CustomerFrozenLogModel) dao.getByUuid(uuid);

				// 从数据库中查询，如果存在，则放入到缓存
				if (cm != null) {
					mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERFROZENLOG_KEY
							+ customerUuid, cm);
				}
			}
			return cm;
		}
		return null;
	}

	@Override
	public String getFrozenLogUuid(String customerUuid) {
		return dao.getFrozenLogUuid(customerUuid);
	}

	@Override
	public String getUnFrozenLogUuid(String customerUuid) {
		return dao.getUnFrozenLogUuid(customerUuid);
	}

}
