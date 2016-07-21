package com.aebiz.b2b2c.cms.storeback.dao.consumerprotection;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionQueryModel;

@Repository
public class StoreConsumerProtectionH4Impl extends
		BaseH4Impl<ConsumerProtectionModel, ConsumerProtectionQueryModel>
		implements StoreConsumerProtectionDAO {

	/**
	 * 通过商户id获取参与的服务保障
	 * @param storeUuid	商户id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ConsumerProtectionModel> getAttainById(String storeUuid) {
		String hql  = " select o " +
				"from ConsumerProtectionModel o , ProtectionStoreRelModel pprm " +
				"where o.uuid=pprm.protectionUuid and pprm.storeUuid=:storeUuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setParameter("storeUuid", storeUuid);
		List<ConsumerProtectionModel> list = query.list();
		return list;
	}
	
	/**
	 * 通过商户id获取没有参与的服务保障
	 * @param storeUuid 商户id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ConsumerProtectionModel> getNonparticipantById(String storeUuid,List<String> ids) {
		String hql = "select c from ConsumerProtectionModel c where 1 = 1";
		if(ids != null & ids.size() > 0){
			hql += " and c.uuid not in (:ids)";
			Query query = this.getH4Session().createQuery(hql);
			query.setParameterList("ids", ids);
			List<ConsumerProtectionModel> list = query.list();
			return list;
		}else{
			Query query = this.getH4Session().createQuery(hql);
			List<ConsumerProtectionModel> list = query.list();
			if(list != null && list.size() > 0)
			{
				return list;
			}
		}
		return null;
	}
}
