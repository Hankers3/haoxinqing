package com.aebiz.b2b2c.product.productcategoryfront.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.productcategoryfront.vo.ProductCategoryFrontModel;
import com.aebiz.b2b2c.product.productcategoryfront.vo.ProductCategoryFrontQueryModel;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformModel;
import com.aebiz.b2b2c.product.producttemplate.vo.ProductTemplateModel;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrModel;
import com.aebiz.b2b2c.product.sysback.web.productcategoryfront.vo.AttributeAndValueWebModel;

public interface ProductCategoryFrontService extends BaseService<ProductCategoryFrontModel,ProductCategoryFrontQueryModel>{
	/**
	 * 根据前台父分类uuid查询子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<ProductCategoryFrontModel> getSubProductCategoryByParentUuid(String parentUuid);
	
	/**
	 * 批量添加分类
	 * @param list
	 */
	public void addBatch(List<ProductCategoryFrontModel> list);
	
	/**
	 *  前台分类关联属性时，可选属性列表
	 * @param frontCategoryUuid
	 * @param categoryPlatfromUuids
	 * @return
	 */
	public List<ProductTemplateAttrModel> canSelectAttributeList(String frontCategoryUuid,List<String> categoryPlatfromUuids);
	
	/**
	 * 根据前台父分类uuid查询子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<ProductCategoryFrontModel> getFrontCategoryByUuids(List<String> uuids);
	
	/**
	 * 获取页面展示的属性和属性值，并把数据库中有的选中
	 * 
	 * @param attributes
	 * 				数据库中有的属性
	 * @param frontCategoryUuid
	 * 				前台分类uuid
	 * @return
	 */
	public List<AttributeAndValueWebModel> buildAttributeWebList(List<ProductTemplateAttrModel> attributes,String frontCategoryUuid);
	
	/**
	 * 该方法用于生成fullpath时使用，查询该fullpath是否已经存在
	 * @param fullPath 
	 * @return
	 */
	public List<ProductCategoryFrontModel> getProductCategoryFrontModelByFullPath(String fullPath);
	
	/**
	 * 生成fullpath规则
	 * @param parentId
	 * @param sheetIdNo
	 * @return
	 */
	public String getSheetNo(String parentId,int sheetIdNo);
	
	/**
	 * 生成fullpath规则
	 * @param parentId
	 * @param sheetIdNo
	 * @return
	 */
	public String convertSheetIdNo(String parentId, int sheetIdNo) ;
	
	/**
	 * 获取结束时的长路径
	 * 
	 * @param fullPath
	 * @return
	 */
	public String getEndFullPath(String fullPath);
	
	/**
	 * 根据fullpath 获取其所有子分类uuid集合（包括本身）
	 * @param fullPath
	 * @return
	 */
	public List<String> getUuidsByFullPath(String fullPath);
	
	/**
	 * 更新前台分类
	 * @param m
	 * @param isAddLog
	 * 		true 表示添加log日志
	 */
	public void updateCategory(ProductCategoryFrontModel m,boolean isAddLog);
}
