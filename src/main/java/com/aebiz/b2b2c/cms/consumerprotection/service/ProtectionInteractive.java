package com.aebiz.b2b2c.cms.consumerprotection.service;

import java.util.List;

import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;

public interface ProtectionInteractive {
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
