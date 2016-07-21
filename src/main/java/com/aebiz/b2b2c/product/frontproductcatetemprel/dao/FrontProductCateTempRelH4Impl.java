package com.aebiz.b2b2c.product.frontproductcatetemprel.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.frontproductcatetemprel.vo.FrontProductCateTempRelModel;
import com.aebiz.b2b2c.product.frontproductcatetemprel.vo.FrontProductCateTempRelQueryModel;

@Repository
public class FrontProductCateTempRelH4Impl
		extends
		BaseH4Impl<FrontProductCateTempRelModel, FrontProductCateTempRelQueryModel>
		implements FrontProductCateTempRelDAO {
	// 根据前台分类id获取关联的分类属性模板json数据
	public String getAttrJsonByCategoryUuid(String categoryUuid) {
		StringBuffer hql = new StringBuffer(
				"select o.attrJson from FrontProductCateTempRelModel o where o.categoryUuid=:categoryUuid");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("categoryUuid", categoryUuid);
		
		Object obj = q.uniqueResult();
		if(obj != null){
			return obj.toString();
		}
		return "";
	}
	
	/**
	 * 根据前台分类uuid集合,获取属性模板json数据集合
	 * 
	 * @param categoryUuids
	 * 				前台分类uuid集合
	 * @return
	 */
	public List<String> getAttrJsonsByCategoryUuids(List<String> categoryUuids){
		StringBuffer hql = new StringBuffer(
				"select o.attrJson from FrontProductCateTempRelModel o where o.categoryUuid in(:categoryUuids)");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameterList("categoryUuids", categoryUuids);
		
		List<String> list = q.list();
		return list;
	}
	
	/**
	 * 根据前台分类uuid,获取关联数据对象
	 * 
	 * @param frontCategoryUuid
	 * @return
	 */
	public FrontProductCateTempRelModel getByFrontCategoryUuid(String frontCategoryUuid){
		if(StringUtil.isEmpty(frontCategoryUuid)){
			return null;
		}
		
		StringBuffer hql = new StringBuffer(
				"select o from FrontProductCateTempRelModel o where o.categoryUuid=:frontCategoryUuid");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("frontCategoryUuid", frontCategoryUuid);
		
		Object obj = q.uniqueResult();
		if(obj != null){
			return (FrontProductCateTempRelModel) obj;
		}
		return null;
		
	}
}
