package com.aebiz.b2b2c.cms.storetags.service;

import java.util.List;

import com.aebiz.b2b2c.cms.storetags.vo.StoreTagsModel;

/**
 * 
 * 对外提供有关商户标签操作的接口<br/>
 * 
 * @author cj
 * 
 */
public interface StoreTagsInteractive {

	/**
	 * 获取某一商户下发布的标签
	 * 
	 * @param storeUuid
	 * @return
	 */
	public List<StoreTagsModel> getStoreTagsModelsByStoreUuid(String storeUuid);

	/**
	 * 获取某一商户下,某个会员关联的标签id
	 * 
	 * @param storeUuid
	 * @return
	 */
	public List<StoreTagsModel> getByStoreUuidAndCustomerUuid(String storeUuid,
			String customerUuid);

	/**
	 * 商户会员管理商户标签时、保存商户、标签、会员的关联关系
	 * 
	 * @param m
	 */
	public void saveStoreTagsCustomerRel(String tagUuid, String storeUuid,
			String customerUuid);

	/**
	 * 商户会员管理商户标签时、保存商户、标签、会员的关联关系
	 * 
	 * @param m
	 */
	public void deleteStoreTagsCustomerRel(String storeUuid, String customerUuid);
}
