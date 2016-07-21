package com.aebiz.b2b2c.product.productmainbaseprice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productmainbaseprice.vo.ProductMainBasePriceModel;
import com.aebiz.b2b2c.product.productmainbaseprice.vo.ProductMainBasePriceQueryModel;

@Repository
public class ProductMainBasePriceH4Impl extends
		BaseH4Impl<ProductMainBasePriceModel, ProductMainBasePriceQueryModel>
		implements ProductMainBasePriceDAO {
	/**
	 * 根据商品uuid集合 删除价格信息
	 * 
	 * @param productUuids
	 */
	public void deletesByProductUuids(List<String> productUuids) {
		String hql = "delete from ProductMainBasePriceModel o where o.productUuid in (:productUuids)";
		Map<String, Object> mapParams = new HashMap();
		mapParams.put("productUuids", productUuids.toArray());

		exeUpdate(hql, mapParams);
	}

	/**
	 * 根据productUuid获取商品价格信息model
	 * 
	 * @param productUuid
	 * @return
	 */
	public ProductMainBasePriceModel getProductMainBasePriceModelByProductUuid(
			String productUuid) {
		if (StringUtil.isEmpty(productUuid)) {
			return null;
		}
		String hql = "select o from ProductMainBasePriceModel o where o.productUuid =:productUuid ";

		Query q = getH4Session().createQuery(hql);
		q.setString("productUuid", productUuid);

		Object obj = q.uniqueResult();
		if (obj != null) {
			return (ProductMainBasePriceModel) obj;
		}
		return null;
	}

	/**
	 * 根据productUuid获取商品价格
	 * 
	 * @param productUuid
	 * @return
	 */
	public double getShopPrice(String productUuid) {
		StringBuffer hql = new StringBuffer(
				"select o.shopPrice from ProductMainBasePriceModel o where o.productUuid =:productUuid ");

		Query q = getH4Session().createQuery(hql.toString());
		q.setString("productUuid", productUuid);

		return (Double) q.uniqueResult();

	}

	/**
	 * 根据productUuid获取计费方式
	 * 
	 * @param productUuid
	 * @return
	 */
	public String getChargetype(String productUuid) {
		StringBuffer hql = new StringBuffer(
				"select o.chargetype from ProductMainBasePriceModel o where o.productUuid =:productUuid ");

		Query q = getH4Session().createQuery(hql.toString());
		q.setString("productUuid", productUuid);
		
		List list = q.list();
		if(list.size()>0){
			return (String) list.get(0);
		}else{
			return "";
		}
		

	}
}
