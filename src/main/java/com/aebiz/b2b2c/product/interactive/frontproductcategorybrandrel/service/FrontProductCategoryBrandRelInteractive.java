package com.aebiz.b2b2c.product.interactive.frontproductcategorybrandrel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.vo.FrontProductCategoryBrandRelModel;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.vo.FrontProductCategoryBrandRelQueryModel;

/**
 * 商品品牌关联前台分类对外接口
 * @author huyingying
 *
 */
public interface FrontProductCategoryBrandRelInteractive
		extends
		BaseService<FrontProductCategoryBrandRelModel, FrontProductCategoryBrandRelQueryModel> {
	
	/**
	 * 通过前台分类查询商品品牌uuid集合
	 * @param categoryUuid
	 * @return
	 */
	public List<String> getBrandUuidsByCategoryUuid(String categoryUuid);
}
