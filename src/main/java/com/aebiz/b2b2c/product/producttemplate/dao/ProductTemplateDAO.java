package com.aebiz.b2b2c.product.producttemplate.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.producttemplate.vo.ProductTemplateModel;
import com.aebiz.b2b2c.product.producttemplate.vo.ProductTemplateQueryModel;

/**
 * 商品模板数据操作</br>
 * 在发布商品时，不同种类的商品它的模板所不同，比如一件衣服他有 颜色：黄色、绿色和尺寸：XXL、XL 之分</br>
 * 此时可以做个服装模板然后关联颜色和尺寸属性，属性性再关联属性值
 * 
 * @author huangpinpin
 *
 */
public interface ProductTemplateDAO extends BaseDAO<ProductTemplateModel,ProductTemplateQueryModel>{
	/**
	 * 根据集合uuid查询模板集合
	 * 
	 * @param uuids
	 * @return
	 */
	public List<ProductTemplateModel> getProductTemplateByUuids(List<String> uuids);
	
	/**
	 * 根据模板uuid查询模板名称
	 * @param uuid
	 * @return
	 */
	public String getTemplateNameByUuids(String uuid);
}