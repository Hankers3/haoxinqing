package com.aebiz.b2b2c.cms.protectionstorerel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.cms.protectionstorerel.vo.ProtectionStoreRelModel;
import com.aebiz.b2b2c.cms.protectionstorerel.vo.ProtectionStoreRelQueryModel;

public interface ProtectionStoreRelService extends BaseService<ProtectionStoreRelModel,ProtectionStoreRelQueryModel>{

	/**
	 * 保存消费者权益保障服务和商户关联关系
	 * @param ids	选择权益的id集合的id集合
	 * @param storeId	商户id
	 */
	public void saveProtectionStores(List<String> ids, String storeId);
}
