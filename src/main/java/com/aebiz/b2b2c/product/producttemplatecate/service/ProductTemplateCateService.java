package com.aebiz.b2b2c.product.producttemplatecate.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.producttemplatecate.vo.ProductTemplateCateModel;
import com.aebiz.b2b2c.product.producttemplatecate.vo.ProductTemplateCateQueryModel;
/**
 * 模板分类业务接口
 * 
 * 由于模板会很多，所以给它个分类，方便维护和查找
 * 
 * @author huangpinpin
 *
 */
public interface ProductTemplateCateService extends BaseService<ProductTemplateCateModel,ProductTemplateCateQueryModel>{
	/**
	 * 根据uuid获取分裂名称
	 * @param uuid 分类uuid
	 * @return
	 */
	public String getCategoryNameByUuid(String uuid);
	
}
