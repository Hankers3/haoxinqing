package com.aebiz.b2b2c.product.productcategoryrel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

import com.aebiz.b2b2c.product.productcategoryrel.vo.ProductCategoryRelModel;
import com.aebiz.b2b2c.product.productcategoryrel.vo.ProductCategoryRelQueryModel;

@Repository
public class ProductCategoryRelH4Impl extends BaseH4Impl<ProductCategoryRelModel,ProductCategoryRelQueryModel> implements ProductCategoryRelDAO {
	/**
	 * 根据前后台分类uuid，获取关系数据
	 * @param frontCategoryUuid
	 * 					前天分类uuid
	 * @param platCategoryUuid
	 * 					后台分类uuid
	 * @return
	 */
	public ProductCategoryRelModel getByFrontCategoryUuidAndPlatCategoryUuid(String frontCategoryUuid,String platCategoryUuid){
		if(StringUtil.isEmpty(frontCategoryUuid) ||StringUtil.isEmpty(platCategoryUuid) ){
			return null;
		}
		String hql="select o from ProductCategoryRelModel o where o.frontCategoryUuid=:frontCategoryUuid and o.platCategoryUuid=:platCategoryUuid";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("frontCategoryUuid", frontCategoryUuid);
		query.setString("platCategoryUuid", platCategoryUuid);
		
		Object obj=query.uniqueResult();
		if(obj!=null){
			return (ProductCategoryRelModel)obj;
		}
		 
		return null;
	}
	
	/**
	 * 删除没有关联的后台分类
	 * 
	 * @param needDeleteUuids
	 * @param frontCategoryUuid
	 */
	public void deletesByFrontAndPlatfromCategoryUuids(List<String> needDeleteUuids,String frontCategoryUuid) {
		if(StringUtil.isEmpty(frontCategoryUuid) || needDeleteUuids==null || needDeleteUuids.size()==0){
			return ;
		}
		String hql = "delete from ProductCategoryRelModel o where o.platCategoryUuid in (:platCategoryUuids) and o.frontCategoryUuid=:frontCategoryUuid ";
		Map<String, Object> mapParams = new HashMap();
		mapParams.put("platCategoryUuids", needDeleteUuids.toArray());
		mapParams.put("frontCategoryUuid",frontCategoryUuid);
		exeUpdate(hql, mapParams);
	}
	
	/**
	 * 根据前台分类uuid 获取后台分类uuid集合
	 * @param frontCategoryUuid
	 * @return
	 */
	public List<String> getPlatfromCategoryUuidsByFrontCategoryUuid(String frontCategoryUuid){
		if(StringUtil.isEmpty(frontCategoryUuid) ){
			return null;
		}
		String hql="select o.platCategoryUuid from ProductCategoryRelModel o where o.frontCategoryUuid=:frontCategoryUuid  ";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("frontCategoryUuid", frontCategoryUuid);
		
		List<String> list=query.list();

		return list;
	}
	
	
	/**
	 * 根据前台分类uuid集合, 获取后台分类uuid集合
	 * @param frontCategoryUuid
	 * @return
	 */
	public List<String> getPlatfromCategoryUuidsByFrontCategoryUuids(List<String> frontCategoryUuids){
		if(frontCategoryUuids==null || frontCategoryUuids.size()==0){
			return null;
		}
		String hql="select o.platCategoryUuid from ProductCategoryRelModel o where o.frontCategoryUuid in(:frontCategoryUuids)  ";
		Query query = this.getH4Session().createQuery(hql);
		query.setParameterList("frontCategoryUuids", frontCategoryUuids);
		
		List<String> list=query.list();
		return list;
	}
	
	/**
	 * 根据后台分类uuid 获取前台分类uuid集合
	 * @param frontCategoryUuid
	 * @return
	 */
	public List<String> getFrontCategoryUuidsByPlatCategoryUuid(String platCategoryUuid){
		if(StringUtil.isEmpty(platCategoryUuid) ){
			return null;
		}
		String hql="select o.frontCategoryUuid from ProductCategoryRelModel o where o.platCategoryUuid=:platCategoryUuid  ";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("platCategoryUuid", platCategoryUuid);
		
		List<String> list=query.list();

		return list;
	}
}
