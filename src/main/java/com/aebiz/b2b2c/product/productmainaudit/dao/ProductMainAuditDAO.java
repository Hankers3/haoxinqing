package com.aebiz.b2b2c.product.productmainaudit.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.productmainaudit.vo.ProductMainAuditModel;
import com.aebiz.b2b2c.product.productmainaudit.vo.ProductMainAuditQueryModel;

public interface ProductMainAuditDAO extends BaseDAO<ProductMainAuditModel,ProductMainAuditQueryModel>{
	/**
	 * 根据字段和商品uuid，删除审核记录
	 * @param auditField
	 * @param productUuid
	 * @return
	 */
	public void deleteByAuditFieldAndProductUuid(String auditField,String productUuid);
	
	/**
	 * 根据字段和商品uuid,查询审核记录
	 * @param auditField
	 * @param productUuid
	 * @return
	 */
	public ProductMainAuditModel getByAuditFieldAndProductUuid(String auditField,String productUuid);
	
	/**
	 * 根据商品Uuid获取审核记录
	 * @param productUuid
	 * @return
	 */
	public List<ProductMainAuditModel> getByProductUuid(String productUuid);
	
	/**
	 * 根据审核类型和商品uuid,删除审核记录集合
	 * @param auditType 审核类型
	 * @param productUuid 商品Uuid
	 * @return
	 */
	public void deleteByAuditTypeAndProductUuid(String auditType,String productUuid);
	
	/**
	 * 根据商品Uuid获取审核记录数量
	 * @param productUuid
	 * @return
	 */
	public int getCountByProductUuid(String productUuid);
}