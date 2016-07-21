package com.aebiz.b2b2c.product.producttemplateattr.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrModel;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrQueryModel;
/**
 * 
 * 商品模板属性数据操作
 * 
 * 
 * @author huangpinpin
 *
 */
public interface ProductTemplateAttrDAO extends BaseDAO<ProductTemplateAttrModel,ProductTemplateAttrQueryModel>{
	/**
	 * 根据模板uuids,获取属性
	 * @param templateUuids
	 * @return
	 */
	public List<ProductTemplateAttrModel> getByUuids(List<String> uuids);
}