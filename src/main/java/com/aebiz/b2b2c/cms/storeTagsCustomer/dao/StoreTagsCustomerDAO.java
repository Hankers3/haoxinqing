package com.aebiz.b2b2c.cms.storeTagsCustomer.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.storeTagsCustomer.vo.StoreTagsCustomerModel;
import com.aebiz.b2b2c.cms.storeTagsCustomer.vo.StoreTagsCustomerQueryModel;

public interface StoreTagsCustomerDAO extends
		BaseDAO<StoreTagsCustomerModel, StoreTagsCustomerQueryModel> {
	/**
	 * 获取某一商户下,某个会员关联的标签id
	 * 
	 * @param storeUuid
	 * @return
	 */
	public List<String> getByStoreUuidAndCustomerUuid(String storeUuid,
			String customerUuid);
	
	/**
	 * 商户会员管理商户标签时、保存商户、标签、会员的关联关系
	 * 
	 * @param m
	 */
	public void deleteStoreTagsCustomerRel(String storeUuid, String customerUuid);
}