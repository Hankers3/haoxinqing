package com.aebiz.b2b2c.cms.consumerprotection.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionQueryModel;

public interface ConsumerProtectionDAO extends
		BaseDAO<ConsumerProtectionModel, ConsumerProtectionQueryModel> {
	/**
	 * 根据商品id获取此商品参与的商家服务
	 * 
	 * @param productUuid
	 * @param storeUuid
	 * @return
	 */
	public List<ConsumerProtectionModel> getByProductUuid(String productUuid,
			String storeUuid);
}