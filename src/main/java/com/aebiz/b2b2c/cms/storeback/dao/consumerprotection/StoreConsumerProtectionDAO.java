package com.aebiz.b2b2c.cms.storeback.dao.consumerprotection;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionQueryModel;

public interface StoreConsumerProtectionDAO extends
		BaseDAO<ConsumerProtectionModel, ConsumerProtectionQueryModel> {

	/**
	 * 通过商户id获取参与的服务保障
	 * @param storeUuid	商户id
	 * @return
	 */
	public List<ConsumerProtectionModel> getAttainById(String storeUuid);
	
	/**
	 * 通过商户id获取没有参与的服务保障
	 * @param storeUuid 商户id
	 * @return
	 */
	public List<ConsumerProtectionModel> getNonparticipantById(String storeUuid,List<String> ids);
}