package com.aebiz.b2b2c.product.productmainaudit.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productmainaudit.vo.ProductMainAuditModel;
import com.aebiz.b2b2c.product.productmainaudit.vo.ProductMainAuditQueryModel;

@Repository
public class ProductMainAuditH4Impl extends BaseH4Impl<ProductMainAuditModel,ProductMainAuditQueryModel> implements ProductMainAuditDAO {
	
	/**
	 * 根据字段和商品uuid,删除审核记录
	 * @param auditField
	 * @param productUuid
	 * @return
	 */
	public void deleteByAuditFieldAndProductUuid(String auditField,String productUuid){
		String hql = "delete from ProductMainAuditModel o where o.productUuid=:productUuid and o.auditField=:auditField ";
		Map<String, Object> mapParams = new HashMap();
		mapParams.put("productUuid", productUuid);
		mapParams.put("auditField", auditField);
		exeUpdate(hql, mapParams);
	}
	
	/**
	 * 根据字段和商品uuid,查询审核记录
	 * @param auditField
	 * @param productUuid
	 * @return
	 */
	public ProductMainAuditModel getByAuditFieldAndProductUuid(String auditField,String productUuid){
		if(StringUtil.isEmpty(auditField)|| StringUtil.isEmpty(productUuid)){
			return null;
		}
		//拼接sql
		String hql = "select o from ProductMainAuditModel o where o.productUuid=:productUuid and o.auditField=:auditField ";
		Query q = getH4Session().createQuery(hql);
		//商品uuid设置
		q.setString("productUuid", productUuid);
		//商品字段设置
		q.setString("auditField", auditField);
		//查询
		Object temp = q.uniqueResult();
		if(temp!=null){//是否存在该对象
			return (ProductMainAuditModel)temp;
		}
		return null;
	}
	
	/**
	 * 根据商品Uuid获取审核记录
	 * @param productUuid
	 * @return
	 */
	public List<ProductMainAuditModel> getByProductUuid(String productUuid){
		if(StringUtil.isEmpty(productUuid)){
			return null;
		}
		//拼接sql
		String hql = "select o from ProductMainAuditModel o where o.productUuid=:productUuid ";
		Query q = getH4Session().createQuery(hql);
		//商品uuid设置
		q.setString("productUuid", productUuid);
		//查询
		List<ProductMainAuditModel> list=q.list();
		return list;
	}
	
	/**
	 * 根据商品Uuid获取审核记录数量
	 * @param productUuid
	 * @return
	 */
	public int getCountByProductUuid(String productUuid){
		if(StringUtil.isEmpty(productUuid)){
			return 0;
		}
		//拼接sql
		String hql = "select count(o) from ProductMainAuditModel o where o.productUuid=:productUuid ";
		Query q = getH4Session().createQuery(hql);
		//商品uuid设置
		q.setString("productUuid", productUuid);
		
		return ((Number)q.uniqueResult()).intValue();
	}
	
	/**
	 * 根据审核类型和商品uuid,删除审核记录集合
	 * @param auditType 审核类型
	 * @param productUuid 商品Uuid
	 * @return
	 */
	public void deleteByAuditTypeAndProductUuid(String auditType,String productUuid){
		if(StringUtil.isEmpty(auditType)||StringUtil.isEmpty(productUuid)){
			return ;
		}
		String hql = "delete from ProductMainAuditModel o where o.productUuid=:productUuid and o.auditType=:auditType ";
		Map<String, Object> mapParams = new HashMap();
		mapParams.put("productUuid", productUuid);
		mapParams.put("auditType", auditType);
		exeUpdate(hql, mapParams);
	}
}
