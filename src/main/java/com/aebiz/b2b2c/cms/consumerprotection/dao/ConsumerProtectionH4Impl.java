package com.aebiz.b2b2c.cms.consumerprotection.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionQueryModel;

@Repository
public class ConsumerProtectionH4Impl extends
		BaseH4Impl<ConsumerProtectionModel, ConsumerProtectionQueryModel>
		implements ConsumerProtectionDAO {
	/**
	 * 根据商品id获取此商品参与的商家服务
	 * 
	 * @param productUuid
	 * @param storeUuid
	 * @return
	 */
	public List<ConsumerProtectionModel> getByProductUuid(String productUuid,
			String storeUuid) {
		StringBuffer hql = new StringBuffer(
				"select o from ConsumerProtectionModel o,ProtectionProductRelModel pp ");
		hql.append(" where o.uuid=pp.protectionUuid and pp.storeUuid=:storeUuid and pp.productUuid=:productUuid ");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("storeUuid", storeUuid);
		q.setParameter("productUuid", productUuid);

		return q.list();
	}
}
