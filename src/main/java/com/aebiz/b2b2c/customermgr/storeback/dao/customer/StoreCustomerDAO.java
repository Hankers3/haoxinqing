package com.aebiz.b2b2c.customermgr.storeback.dao.customer;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelModel;

public interface StoreCustomerDAO extends
		BaseDAO<CustomerModel, CustomerQueryModel> {

	/**
	 * 通过会员编号查询该会员
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	public CustomerModel getCustomerByCustomerUuid(String customerUuid);

	/**
	 * 通过商户会员等级编号查询商户会员等级
	 * 
	 * @param storeLevelUuid
	 *            商户会员等级编号
	 * @return
	 */
	public CustomerStoreLevelModel getCustomerStoreLevelByUuid(
			String storeLevelUuid);

	/**
	 * 通过商户编号获取该商户的所有会员等级
	 * 
	 * @param storeUuid
	 *            商户编号
	 * @return
	 */
	public List<CustomerStoreLevelModel> getCustomerStoreLevelsByStoreUuid(
			String storeUuid);

	/**
	 * 通过会员编号和商户编号查询会员在这个店铺的等级
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @param storeUuid
	 *            商户编号
	 * @return
	 */
	public CustomerStoreLevelModel getCustomerStoreLevel(String customerUuid,
			String storeUuid);
}