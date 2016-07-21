package com.aebiz.b2b2c.cms.storeback.dao.protectionproductrel;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelModel;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelQueryModel;

@Repository
public class StoreProtectionProductRelH4Impl extends BaseH4Impl<ProtectionProductRelModel,ProtectionProductRelQueryModel> implements StoreProtectionProductRelDAO {
	
	/**
	 * 通过商户的id和权益id获取所有商品
	 * @param storeUuid	商户的id
	 * @param protectionUuid	权益id
	 * @param start	开始页
	 * @param page	页面大小
	 * @return	List&ltProtectionProductRelModel&gt
	 */
	@SuppressWarnings("unchecked")
	public List<ProtectionProductRelModel> getProducts(String storeUuid, String protectionUuid, int start, int page) {
		String hql = "from ProtectionProductRelModel " +
				"where storeUuid = :storeUuid " +
				"and protectionUuid = :protectionUuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setParameter("storeUuid", storeUuid);
		query.setParameter("protectionUuid", protectionUuid);
		query.setFirstResult(start);
		query.setMaxResults(page);
		List<ProtectionProductRelModel> list = query.list();
		return list;
	}
	
	/**
	 * 通过商户的id和权益id获取所有商品
	 * @param storeUuid	商户的id
	 * @param protectionUuid	权益id
	 * @return	List&ltProtectionProductRelModel&gt
	 */
	@SuppressWarnings("unchecked")
	public List<ProtectionProductRelModel> getProducts(String storeUuid, String protectionUuid) {
		String hql = "from ProtectionProductRelModel " +
				"where storeUuid = :storeUuid " +
				"and protectionUuid = :protectionUuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setParameter("storeUuid", storeUuid);
		query.setParameter("protectionUuid", protectionUuid);
		List<ProtectionProductRelModel> list = query.list();
		return list;
	}
	

	/**
	 * 通过用户id，商品id，权益id删除一个商品服务。
	 * @param storeId	商品id
	 * @param productUuid	商品id
	 * @param protectionUuid	权益id
	 */
	public void deleteProtectionPrRel(String storeId, String productUuid,
			String protectionUuid) {
		String hql = "delete ProtectionProductRelModel where storeUuid=:storeUuid and productUuid=:productUuid and protectionUuid=:protectionUuid";
		
		Query query = this.getH4Session().createQuery(hql);
		query.setParameter("storeUuid", storeId);
		query.setParameter("productUuid", productUuid);
		query.setParameter("protectionUuid", protectionUuid);
		query.executeUpdate();
	}
}
