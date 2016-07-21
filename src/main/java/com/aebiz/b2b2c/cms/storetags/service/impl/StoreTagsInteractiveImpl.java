package com.aebiz.b2b2c.cms.storetags.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.cms.storeTagsCustomer.service.StoreTagsCustomerService;
import com.aebiz.b2b2c.cms.storeTagsCustomer.vo.StoreTagsCustomerModel;
import com.aebiz.b2b2c.cms.storetags.dao.StoreTagsDAO;
import com.aebiz.b2b2c.cms.storetags.service.StoreTagsInteractive;
import com.aebiz.b2b2c.cms.storetags.vo.StoreTagsModel;

@Service
@Transactional
public class StoreTagsInteractiveImpl implements StoreTagsInteractive {
	private StoreTagsDAO myDao = null;
	@Autowired
	private StoreTagsCustomerService stcService;

	@Autowired
	public void setMyDao(StoreTagsDAO dao) {
		this.myDao = dao;
	}

	/**
	 * 获取某一商户下发布的标签
	 * 
	 * @param storeUuid
	 * @return
	 */
	public List<StoreTagsModel> getStoreTagsModelsByStoreUuid(String storeUuid) {
		List<StoreTagsModel> list = this.myDao
				.getStoreTagsModelsByStoreUuid(storeUuid);
		return list;
	}

	/**
	 * 获取某一商户下,某个会员关联的标签
	 * 
	 * @param storeUuid
	 * @return
	 */
	public List<StoreTagsModel> getByStoreUuidAndCustomerUuid(String storeUuid,
			String customerUuid) {
		List<StoreTagsModel> list = new ArrayList<StoreTagsModel>();
		List<String> tageUuids = this.stcService.getByStoreUuidAndCustomerUuid(
				storeUuid, customerUuid);
		if(tageUuids != null && tageUuids.size() > 0){
			for(String id:tageUuids){
				StoreTagsModel m = this.myDao.getByUuid(id);
				list.add(m);
			}
		}
		return list;
	}

	/**
	 * 商户会员管理商户标签时、保存商户、标签、会员的关联关系
	 * 
	 * @param m
	 */
	public void saveStoreTagsCustomerRel(String tagUuid, String storeUuid,
			String customerUuid) {
		StoreTagsCustomerModel m = new StoreTagsCustomerModel();
		m.setTagUuid(tagUuid);
		m.setStoreUuid(storeUuid);
		m.setCustomerUuid(customerUuid);
		this.stcService.create(m);
	}
	
	/**
	 * 商户会员管理商户标签时、保存商户、标签、会员的关联关系
	 * 
	 * @param m
	 */
	public void deleteStoreTagsCustomerRel(String storeUuid, String customerUuid){
		List<String> tageUuids = this.stcService.getByStoreUuidAndCustomerUuid(
				storeUuid, customerUuid);
		this.stcService.deleteStoreTagsCustomerRel(storeUuid,customerUuid);
	}

}
