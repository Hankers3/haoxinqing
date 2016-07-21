package com.aebiz.b2b2c.product.frontproductcatetemprel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.frontproductcatetemprel.vo.FrontProductCateTempRelModel;
import com.aebiz.b2b2c.product.frontproductcatetemprel.vo.FrontProductCateTempRelQueryModel;
import com.aebiz.b2b2c.product.productcategoryfront.vo.SelectAttributeJson;

public interface FrontProductCateTempRelService
		extends
		BaseService<FrontProductCateTempRelModel, FrontProductCateTempRelQueryModel> {
	/**
	 * 根据前台分类uuid,获取属性模板json数据集合
	 * 
	 * @param categoryUuid
	 *            前台分类uuid集合
	 * @return
	 */
	public List<SelectAttributeJson> getAttrJsonByCategoryUuid(
			String categoryUuid);

	/**
	 * 根据前台分类uuid集合,获取属性模板json数据集合
	 * 
	 * @param categoryUuids
	 *            前台分类uuid集合
	 * @return
	 */
	public List<SelectAttributeJson> getAttrJsonsByCategoryUuids(
			List<String> categoryUuids);

	/**
	 * 根据前台分类uuid,获取关联数据对象
	 * 
	 * @param frontCategoryUuid
	 * @return
	 */
	public FrontProductCateTempRelModel getByFrontCategoryUuid(
			String frontCategoryUuid);
	
	/**
	 * 前台分类关联属性
	 * 
	 * @param frontCategoryUuid
	 * @param attrJson
	 */
	public void saveAttribute(String frontCategoryUuid,String attrJson);

}
