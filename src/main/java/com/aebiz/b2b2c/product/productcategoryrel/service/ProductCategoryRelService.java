package com.aebiz.b2b2c.product.productcategoryrel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.productcategoryrel.vo.ProductCategoryRelModel;
import com.aebiz.b2b2c.product.productcategoryrel.vo.ProductCategoryRelQueryModel;

public interface ProductCategoryRelService extends BaseService<ProductCategoryRelModel,ProductCategoryRelQueryModel>{
	
	/**
	 * 判断是否已经关联
	 * @param frontCategoryUuid
	 * 					前天分类uuid
	 * @param platCategoryUuid
	 * 					后台分类uuid
	 * @return
	 */
	public boolean isHasRelCategory(String frontCategoryUuid,String platCategoryUuid);
	
	
	/**
	 * 前台关联后台分类
	 * 
	 * @param platCategoryUuids
	 * @param frontCategoryUuid
	 */
	public void batchAdd(List<String> platCategoryUuids,String frontCategoryUuid);
	
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
