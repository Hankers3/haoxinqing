package com.aebiz.b2b2c.product.producttemplateattr.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrModel;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrQueryModel;
/**
 * 
 * 商品模板属性数据接口
 * 
 * 
 * @author huangpinpin
 *
 */
public interface ProductTemplateAttrService extends BaseService<ProductTemplateAttrModel,ProductTemplateAttrQueryModel>{
	
	/**
	 * 根据模板uuids,获取属性
	 * @param templateUuids
	 * @return
	 */
	public List<ProductTemplateAttrModel> getByUuids(List<String> uuids);
}
