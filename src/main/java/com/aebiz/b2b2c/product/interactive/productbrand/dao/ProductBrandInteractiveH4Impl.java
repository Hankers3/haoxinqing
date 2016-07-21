package com.aebiz.b2b2c.product.interactive.productbrand.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandModel;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandQueryModel;

/**
 * 商品品牌数据操作
 * 
 * @author huyingying
 *
 */
@Repository
public class ProductBrandInteractiveH4Impl extends BaseH4Impl<ProductBrandModel,ProductBrandQueryModel> implements ProductBrandInteractiveDAO {
	
	/**
	 * 通过商品品牌uuids集合查询商品品牌集合
	 * @param uuids
	 * @param start
	 * @param pageShow
	 * @return
	 */
	public List<ProductBrandModel> getProductBrandsByUuids(List<String> uuids,int start,int pageShow){
		
		StringBuffer hql = new StringBuffer("select o from ProductBrandModel o where 1=1 ");
		if(uuids!=null && uuids.size()>0){
			hql.append(" and o.uuid in (:uuids) ");
		}
		hql.append(" order by o.opeTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		if(uuids!=null && uuids.size()>0){
			query.setParameterList("uuids", uuids);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageShow);
		List<ProductBrandModel> objs=query.list();
		if(objs!=null && objs.size()>0){
			return objs;
		}
		return null;
	}
	
}
