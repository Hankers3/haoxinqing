package com.aebiz.b2b2c.product.producttemplatecate.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.producttemplatecate.vo.ProductTemplateCateModel;
import com.aebiz.b2b2c.product.producttemplatecate.vo.ProductTemplateCateQueryModel;
/**
 * 模板分类数据操作
 * 
 * 由于模板会很多，所以给它个分类，方便维护和查找
 * 
 * @author huangpinpin
 *
 */
public interface ProductTemplateCateDAO extends BaseDAO<ProductTemplateCateModel,ProductTemplateCateQueryModel>{
	/**
	 * 根据uuid获取模板分类名称
	 * @param uuid 分类uuid
	 * @return
	 */
	public String getCategoryNameByUuid(String uuid);
}