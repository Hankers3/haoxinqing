package com.aebiz.b2b2c.product.productcategoryrel.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.productcategoryrel.vo.ProductCategoryRelModel;
import com.aebiz.b2b2c.product.productcategoryrel.vo.ProductCategoryRelQueryModel;

public interface ProductCategoryRelDAO extends BaseDAO<ProductCategoryRelModel,ProductCategoryRelQueryModel>{
	/**
	 * 根据前后台分类uuid，获取关系数据
	 * @param frontCategoryUuid
	 * 					前天分类uuid
	 * @param platCategoryUuid
	 * 					后台分类uuid
	 * @return
	 */
	public ProductCategoryRelModel getByFrontCategoryUuidAndPlatCategoryUuid(String frontCategoryUuid,String platCategoryUuid);
	
	
	/**
	 * 根据前台分类uuid 获取后台分类uuid集合
	 * @param frontCategoryUuid
	 * @return
	 */
	public List<String> getPlatfromCategoryUuidsByFrontCategoryUuid(String frontCategoryUuid);
	
	/**
	 * 根据后台分类uuid 获取前台分类uuid集合
	 * @param frontCategoryUuid
	 * @return
	 */
	public List<String> getFrontCategoryUuidsByPlatCategoryUuid(String platCategoryUuid);
	
	/**
	 * 根据前台分类uuid集合, 获取后台分类uuid集合
	 * @param frontCategoryUuid
	 * @return
	 */
	public List<String> getPlatfromCategoryUuidsByFrontCategoryUuids(List<String> frontCategoryUuids);
	
	/**
	 * 删除没有关联的后台分类
	 * 
	 * @param needDeleteUuids
	 * @param frontCategoryUuid
	 */
	public void deletesByFrontAndPlatfromCategoryUuids(List<String> needDeleteUuids,String frontCategoryUuid);
}