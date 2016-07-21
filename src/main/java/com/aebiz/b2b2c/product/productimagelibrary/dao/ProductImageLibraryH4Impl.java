package com.aebiz.b2b2c.product.productimagelibrary.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.product.productimagelibrary.vo.ProductImageLibraryModel;
import com.aebiz.b2b2c.product.productimagelibrary.vo.ProductImageLibraryQueryModel;

@Repository
public class ProductImageLibraryH4Impl
		extends
			BaseH4Impl<ProductImageLibraryModel, ProductImageLibraryQueryModel>
		implements
			ProductImageLibraryDAO {

	/**
	 * 根据分类的uuid获取该分类下的所有图片带分页
	 * 
	 * @param categoryUuid
	 * @return List<ProductImageLibraryModel>
	 */
	public List<ProductImageLibraryModel> getProductImageLibraryModelsByCategoryUuid(
			String categoryUuid,int nowPage ,int pageShow){
		StringBuffer hql = new StringBuffer();
		hql.append(" from ProductImageLibraryModel  p where p.categoryUuid =:categoryUuid order by p.upLoadTime desc");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		
		query.setFirstResult(nowPage);
		query.setMaxResults(pageShow);
		
		query.setString("categoryUuid", categoryUuid);
		
		return (List<ProductImageLibraryModel>)query.list();
	}
	
	
	/**
	 * 根据分类的uuid获取该分类下的所有图片总数
	 * 
	 * @param categoryUuid
	 * @return int
	 */
	public int getProductImageLibraryModelsByCategoryUuid(
			String categoryUuid){
		StringBuffer hql = new StringBuffer();
		hql.append(" from ProductImageLibraryModel  p where p.categoryUuid =:categoryUuid order by p.upLoadTime desc ");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		
		query.setString("categoryUuid", categoryUuid);
		List<ProductImageLibraryModel> list = (List<ProductImageLibraryModel>)query.list();
		if(list != null && list.size() >0){
			return list.size();
		}
		
		return 0;
	}
}
