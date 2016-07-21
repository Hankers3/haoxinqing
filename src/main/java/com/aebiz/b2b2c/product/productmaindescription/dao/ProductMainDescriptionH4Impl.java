package com.aebiz.b2b2c.product.productmaindescription.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmainbaseprice.vo.ProductMainBasePriceModel;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionModel;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionQueryModel;

@Repository
public class ProductMainDescriptionH4Impl extends BaseH4Impl<ProductMainDescriptionModel,ProductMainDescriptionQueryModel> implements ProductMainDescriptionDAO {
	/**
	 * 根据商品uuid集合 删除描述信息
	 * @param productUuids
	 */
	public void deletesByProductUuids(List<String> productUuids){
		String hql = "update ProductMainDescriptionModel o set o.delFlag = 2 where o.productUuid in (:productUuids)";
		 Map<String, Object> mapParams = new HashMap();
		 mapParams.put("productUuids", productUuids.toArray());
		 
		 exeUpdate(hql, mapParams);
	}
	
	/**
	 * 根据商品uuid 查询描述信息
	 * @param productUuid
	 * @return
	 */
	public ProductMainDescriptionModel getByProductUuid(String productUuid){
		if(StringUtil.isEmpty(productUuid)){
			return null;
		}
		String hql = "select o from ProductMainDescriptionModel o where o.productUuid =:productUuid and o.delFlag=:delFlag ";
		
		Query q = getH4Session().createQuery(hql);
		q.setString("productUuid", productUuid);
		q.setString("delFlag", ProductMainBasePriceModel.DEL_FLAG_VALID);
		Object obj=q.uniqueResult();
		if(obj!=null){
			return (ProductMainDescriptionModel)obj;
		}
		return null;
	}
	
	public String getUuidByProductUuid(String productUuid){
		if(StringUtil.isEmpty(productUuid)){
			return null;
		}
		String hql = "select o.uuid from ProductMainDescriptionModel o where o.productUuid =:productUuid and o.delFlag=:delFlag ";
		
		Query q = getH4Session().createQuery(hql);
		q.setString("productUuid", productUuid);
		q.setString("delFlag", ProductMainBasePriceModel.DEL_FLAG_VALID);
		Object obj=q.uniqueResult();
		if(obj!=null){
			return (String)obj;
		}
		return "";
	}
	
	/**
	 * 恢复商品描述表,根据所选productUuid集合
	 * @param productUuids 商品productUuid集合
	 * 
	 */
	public void recycleProductDesc(List<String> productUuids){
		if(productUuids==null ||productUuids.size()==0){
			return;
		}
		
		String hql = "update ProductMainDescriptionModel o set o.delFlag=:delFlag where o.productUuid in (:productUuids)";
		Map<String, Object> mapParams = new HashMap();
		mapParams.put("delFlag", ProductMainModel.DEL_FLAG_VALID);
		mapParams.put("productUuids", productUuids.toArray());

		exeUpdate(hql, mapParams);
	}
}
