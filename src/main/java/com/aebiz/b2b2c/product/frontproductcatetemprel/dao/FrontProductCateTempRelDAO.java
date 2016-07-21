package com.aebiz.b2b2c.product.frontproductcatetemprel.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.frontproductcatetemprel.vo.FrontProductCateTempRelModel;
import com.aebiz.b2b2c.product.frontproductcatetemprel.vo.FrontProductCateTempRelQueryModel;

public interface FrontProductCateTempRelDAO extends BaseDAO<FrontProductCateTempRelModel,FrontProductCateTempRelQueryModel>{
	/**
	 * 根据前台分类id获取关联的分类属性模板json数据
	 * 
	 * @param categoryUuid
	 * 				前台分类uuid
	 * @return
	 */
	public String getAttrJsonByCategoryUuid(String categoryUuid);
	
	
	/**
	 * 根据前台分类uuid集合,获取属性模板json数据集合
	 * 
	 * @param categoryUuids
	 * 				前台分类uuid集合
	 * @return
	 */
	public List<String> getAttrJsonsByCategoryUuids(List<String> categoryUuids);
	
	/**
	 * 根据前台分类uuid,获取关联数据对象
	 * 
	 * @param frontCategoryUuid
	 * @return
	 */
	public FrontProductCateTempRelModel getByFrontCategoryUuid(String frontCategoryUuid);
	
}