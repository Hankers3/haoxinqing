package com.aebiz.b2b2c.product.interactive.productcategoryfront.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.product.interactive.productcategoryfront.vo.ProductCategoryFrontInteractiveModel;
import com.aebiz.b2b2c.product.interactive.productcategoryfront.vo.ProductCategoryFrontInteractiveQueryModel;
import com.aebiz.b2b2c.product.productcategoryfront.vo.ProductCategoryFrontModel;

@Repository
public class ProductCategoryFrontInteractiveH4Impl extends BaseH4Impl<ProductCategoryFrontInteractiveModel,ProductCategoryFrontInteractiveQueryModel> implements ProductCategoryFrontInteractiveDAO {
	
	/**
	 * 
	 * 对外接口：通过商品分类获取子分类
	 * @param parentUuid
	 * 				父分类uuid
	 * @param storeUuid
	 * 				商户uuid
	 * @return
	 */
	public List<ProductCategoryFrontModel> getSubProductCategoryByUuid(String parentUuid){
		if(parentUuid==null){
			return null;
		}
		StringBuffer hql = new StringBuffer("select o from ProductCategoryFrontModel o where o.parentUuid=:parentUuid ");
		hql.append(" order by o.position asc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("parentUuid", parentUuid);
		
		List<ProductCategoryFrontModel> list=query.list();
		return list;
	}
	
	/**
	 * 根据分类父uuid，分类Uuid集合查询分类集合（带分页面）
	 * @param parentUuid
	 * @param uuids
	 * @param start
	 * @param pageNow
	 * @return
	 */
	public List<ProductCategoryFrontModel> getProductCategorys(String parentUuid,String[] uuids,int start,int pageNow){
		StringBuffer hql=new StringBuffer("select o from ProductCategoryFrontModel o where 1=1 ");
		hql.append(" and o.parentUuid= :parentUuid");
		if(uuids!=null && uuids.length>0){
			hql.append(" and o.uuid in (:uuids)");
		}
		hql.append(" order by o.opeTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("parentUuid", parentUuid);
		if(uuids!=null && uuids.length>0){
			query.setParameterList("uuids", uuids);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageNow);
		List<ProductCategoryFrontModel> tempList=query.list();
		if(tempList!=null && tempList.size()>0){
			return  tempList;
		}
		return null;
	}
	/**
	 * 根据父分类uuid查询分类数
	 * @param parentUuid
	 * @return
	 */
	public int getProductCategoryCount(String parentUuid){
		StringBuffer hql=new StringBuffer("select count(o) from ProductCategoryFrontModel o where 1=1 ");
		hql.append(" and o.parentUuid= :parentUuid");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("parentUuid", parentUuid);
		return ((Number)query.uniqueResult()).intValue();
	}
	
	/**
	 * 通过分类uuid查询分类
	 * @param categoruUuid
	 * @return
	 */
	public ProductCategoryFrontModel getByCategoryUuid(String categoryUuid){
		StringBuffer hql=new StringBuffer("select o from ProductCategoryFrontModel o where 1=1 ");
		hql.append(" and o.uuid= :uuid");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", categoryUuid);
		Object obj=query.uniqueResult();
		if(obj!=null){
			return (ProductCategoryFrontModel)obj;
		}
		return null;
	}
	
 
}
