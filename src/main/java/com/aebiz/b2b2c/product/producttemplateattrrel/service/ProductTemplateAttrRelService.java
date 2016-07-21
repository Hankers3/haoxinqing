package com.aebiz.b2b2c.product.producttemplateattrrel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.producttemplateattrrel.vo.ProductTemplateAttrRelModel;
import com.aebiz.b2b2c.product.producttemplateattrrel.vo.ProductTemplateAttrRelQueryModel;
/**
 * 
 * 商品模板和属性关系业务实现
 * 
 * 一个模板可以关联多个属性，比如模板里面有颜色/尺寸等属性
 * 
 * @author huangpinpin
 *
 */
public interface ProductTemplateAttrRelService extends BaseService<ProductTemplateAttrRelModel,ProductTemplateAttrRelQueryModel>{
	
	/**
	 * 根据模板uuid查找已经关联该模板的属性uuid
	 * @param templateUuid 模板
	 * @return
	 */
	public List<String> getAttributeUuidsByUuid(String templateUuid);
	
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
	
	/**
	 * 根据模板uuid查找已经关联该模板的属性集合
	 * @param templateUuid 模板
	 * @return
	 */
	public List<ProductTemplateAttrRelModel> getAttributeByUuid(String templateUuid);
	
	/**
	 * 关联所选择的属性到该模板下
	 * @param needRelUuids 所选属性uuid
	 * @param attributeUuid 模板uuid
	 */
	public void relation(List<String> needRelUuids, String templateUuid);
	
	/**
	 * 批量更新模板属性关系表
	 * @param selectRelUuids
	 */
	public void batchUpdate(List<String> selectRelUuids);
	 
}
