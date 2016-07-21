package com.aebiz.b2b2c.product.frontproductcategorybrandrel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.vo.FrontProductCategoryBrandRelModel;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.vo.FrontProductCategoryBrandRelQueryModel;

public interface FrontProductCategoryBrandRelService
		extends
		BaseService<FrontProductCategoryBrandRelModel, FrontProductCategoryBrandRelQueryModel> {
	/**
	 * 保存分类和品牌的关联关系
	 * 
	 * @param categoryUuid
	 * @param brands
	 */
	public void saveRelate(String categoryUuid, List<String> brands);

	/**
	 * 根据前台分类id获取分类关联的品牌id集合
	 * 
	 * @param cateUuid
	 * @return
	 */
	public List<String> getBrandIdsByCateUuid(String cateUuid);

	/**
	 * 根据分类id删除分类和品牌的关联关系
	 * 
	 * @param cateUuid
	 */
	public void deleteByCateUuid(String cateUuid);

	/**
	 * 根据品牌uuid集合和分类uuid,删除关系数据
	 * 
	 * @param brandUuids
	 *            品牌uuid集合
	 * @param categoryUuid
	 *            分类uuid
	 */
	public void deleteByBrandUuidsAndCategoryUuid(List<String> brandUuids,
			String categoryUuid);

}
