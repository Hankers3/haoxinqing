package com.aebiz.b2b2c.cms.storeback.dao.protectionproductrel;


import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelModel;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelQueryModel;

public interface StoreProtectionProductRelDAO extends BaseDAO<ProtectionProductRelModel,ProtectionProductRelQueryModel>{

	/**
	 * 通过商户的id和权益id获取所有商品（带分页的）
	 * @param storeUuid	商户的id
	 * @param protectionUuid	权益id
	 * @param start	开始页
	 * @param page	页面大小
	 * @return	List&ltProtectionProductRelModel&gt
	 */
	List<ProtectionProductRelModel> getProducts(String storeUuid, String protectionUuid, int start, int page);
	
	/**
	 * 通过商户的id和权益id获取所有商品（不带分页的）
	 * @param storeUuid	商户的id
	 * @param protectionUuid	权益id
	 * @return	List&ltProtectionProductRelModel&gt
	 */
	List<ProtectionProductRelModel> getProducts(String storeUuid, String protectionUuid);

	/**
	 * 通过用户id，商品id，权益id删除一个商品服务。
	 * @param storeId	商品id
	 * @param productUuid	商品id
	 * @param protectionUuid	权益id
	 */
	void deleteProtectionPrRel(String storeId, String productUuid,String protectionUuid);

}