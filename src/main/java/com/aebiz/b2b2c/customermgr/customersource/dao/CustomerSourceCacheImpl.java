package com.aebiz.b2b2c.customermgr.customersource.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceModel;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class CustomerSourceCacheImpl extends
		BaseMemcachedCache<CustomerSourceModel, CustomerSourceQueryModel>
		implements CustomerSourceDAO {

	@Resource(name = CustomerCacheConstant.CUSTOMER_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CustomerSourceDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CustomerSourceCacheImpl() {
		super(CustomerCacheConstant.CUSTOMER_CUSTOMERSOURCE_KEY);
	}

	/**
	 * 通过会员编号获取会员来源信息
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	public CustomerSourceModel getCustomerSourceModelByCustomerUuid(
			String customerUuid) {
		String uuid = this.getCustomerSourceIdByCustomerUuid(customerUuid);

		if (!StringUtil.isEmpty(uuid)) {
			// 从缓存中取得对象
			CustomerSourceModel cm = (CustomerSourceModel) mcc
					.get(CustomerCacheConstant.CUSTOMER_CUSTOMERSOURCE_KEY
							+ uuid);

			// 如果对象为空，则从数据库中查询
			if (cm == null) {
				cm = (CustomerSourceModel) dao.getByUuid(uuid);

				// 从数据库中查询，如果存在，则放入到缓存
				if (cm != null) {
					mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERSOURCE_KEY
							+ uuid, cm);
				}
			}
			return cm;
		}
		return null;
	}

	@Override
	public String getCustomerSourceIdByCustomerUuid(String customerUuid) {
		return dao.getCustomerSourceIdByCustomerUuid(customerUuid);
	}

	/**
	 * 邀请码列表
	 * 
	 * @return
	 */
	@Override
	public List<String> getInviteCodes() {
		return dao.getInviteCodes();
	}
	
	/**
	 * 
	 * @Description: (这里用一句话描述这个方法的作用)    
	 * @author XP  
	 * @param cashId
	 * @return
	 * @date 2015-12-29 下午4:15:32
	 */
	public List<String> getAllCutomerSourceUuids(String cashId) {
	    List<String> uuids = (List<String>) this.mcc.get(CustomerCacheConstant.CUSTOMER_CUSTOMERSOURCE_KEY_UUIDS+ cashId);
            if (uuids == null || uuids.size() < 0) {
                    uuids = this.dao.getAllCutomerSourceUuids(cashId);
                    this.mcc.set(CustomerCacheConstant.CUSTOMER_CUSTOMERSOURCE_KEY_UUIDS
                                    + cashId , uuids);
            }
            return uuids;
	}

	/**
	 * 根据注册生成的邀请码 MyInviteCode字段查询CustomerSourceModel
	 * 
	 * @param cashId
	 *            注册生成的邀请码 MyInviteCode
	 * @return CustomerSourceModel集合
	 */
	@Override
	public List<CustomerSourceModel> getModelByMyInviteCodes(String cashId) {
		List<String> uuids = this.getAllCutomerSourceUuids(cashId);
		List<CustomerSourceModel> listM = new ArrayList<CustomerSourceModel>();
		List<String> noUuids = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc
						.get(CustomerCacheConstant.CUSTOMER_CUSTOMERSOURCE_KEY
								+ uuid);
				if (obj != null) {
					CustomerSourceModel m = (CustomerSourceModel) obj;
					listM.add(m);
				} else {
					noUuids.add(uuid);
				}
			}
			if (noUuids.size() > 0) {
				for (String uuid : noUuids) {
					CustomerSourceModel m = dao.getByUuid(uuid);
					if (m != null) {
						this.mcc.set(
								CustomerCacheConstant.CUSTOMER_CUSTOMERSOURCE_KEY
										+ uuid, m);
						listM.add(m);
					}
				}
			}
		}
		return listM;
	}

}
