package com.aebiz.b2b2c.product.producttemplateattrrel.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.producttemplateattrrel.vo.ProductTemplateAttrRelModel;
import com.aebiz.b2b2c.product.producttemplateattrrel.vo.ProductTemplateAttrRelQueryModel;
/**
 * 
 * 商品模板和属性关系数据操作
 * 
 * 一个模板可以关联多个属性，比如模板里面有颜色/尺寸等属性
 * 
 * @author huangpinpin
 *
 */
public interface ProductTemplateAttrRelDAO extends BaseDAO<ProductTemplateAttrRelModel,ProductTemplateAttrRelQueryModel>{
	/**
	 * 根据模板uuid查找已经关联该模板的属性uuid
	 * 用于过滤已经关联该模板属性的列表查询
	 * @param templateUuid 模板uuid
	 * @return
	 */
	public List<String> getAttributeUuidsByUuid(String templateUuid);

	
	/**
	 * 根据模板uuid查找已经关联该模板的属性集合
	 * @param templateUuid 模板
	 * @return
	 */
	public List<ProductTemplateAttrRelModel> getAttributeByUuid(String templateUuid);
	
	/**
	 * 根据模板uuid查找规格属性集合
	 * @param templateUuid 模板
	 * @return
	 */
	public List<ProductTemplateAttrRelModel> getSpecAttributeUuidsByUuid(String templateUuid);
	
	/**
	 * 根据模板uuid查找不是规格属性集合
	 * @param templateUuid 模板
	 * @return
	 */
	public List<ProductTemplateAttrRelModel> getNotSpecAttributeUuidsByUuid(String templateUuid);
	
	
}