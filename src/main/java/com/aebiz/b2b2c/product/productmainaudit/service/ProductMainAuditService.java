package com.aebiz.b2b2c.product.productmainaudit.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.productmainaudit.vo.ProductMainAuditModel;
import com.aebiz.b2b2c.product.productmainaudit.vo.ProductMainAuditQueryModel;

public interface ProductMainAuditService extends BaseService<ProductMainAuditModel,ProductMainAuditQueryModel>{
	/**
	 * 根据字段和商品uuid，删除审核记录
	 * @param auditField 审核字段
	 * @param productUuid 商品Uuid
	 * @return
	 */
	public void deleteByAuditFieldAndProductUuid(String auditField,String productUuid);
	
	/**
	 * 根据字段和商品uuid,查询审核记录
	 * @param auditField 审核字段
	 * @param productUuid 商品Uuid
	 * @return
	 */
	public ProductMainAuditModel getByAuditFieldAndProductUuid(String auditField,String productUuid);
	
	/**
	 * 根据审核类型和商品uuid,删除审核记录集合
	 * @param auditType 审核类型
	 * @param productUuid 商品Uuid
	 * @return
	 */
	public void deleteByAuditTypeAndProductUuid(String auditType,String productUuid);
	
	/**
	 * 根据商品Uuid获取审核记录
	 * @param productUuid 商品Uuid
	 * @return
	 */
	public List<ProductMainAuditModel> getByProductUuid(String productUuid);
	
	/**
	 * 创建字段审核不通过记录
	 * @param m 
	 * @return
	 */
	public void noPassFieldName(ProductMainAuditModel m);
	
	/**
	 * 审核字段通过
	 * @param auditField 字段	
	 * @param productUuid 商品uuid
	 */
	public void auditPass(String auditField,String productUuid);
	
	/**
	 * 批量审核字段通过
	 * @param auditType	审核类型
	 * @param productUuid	商品uuid
	 */
	public void bacthAuditPass(String auditType,String productUuid);
	
	/**
	 * 判断该商品是否存在审核不通过记录
	 * 
	 * @param productUuid 商品uuid
	 * @return
	 */
	public boolean isExist(String productUuid);
}
