package com.aebiz.b2b2c.cms.storetags.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.storetags.vo.StoreTagsModel;
import com.aebiz.b2b2c.cms.storetags.vo.StoreTagsQueryModel;

public interface StoreTagsDAO extends BaseDAO<StoreTagsModel,StoreTagsQueryModel>{
	/**
	 * 获取某一商户下发布的标签
	 * 
	 * @param storeUuid
	 * @return
	 */
	public List<StoreTagsModel> getStoreTagsModelsByStoreUuid(String storeUuid);
}