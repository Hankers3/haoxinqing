package com.aebiz.b2b2c.product.productcategoryplatform.service;

import java.util.List;
import java.util.Map;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformModel;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformQueryModel;

/**
 * 平台分类业务接口
 * 1.平台分类用于发布商品时选择分类
 * 2.平台分类用于创建商户时,选择商户所属分类
 * 3.分类还得关联模板/品牌/标签分类
 * 
 * @author huangpinpin
 *
 */
public interface ProductCategoryPlatformService extends BaseService<ProductCategoryPlatformModel,ProductCategoryPlatformQueryModel>{
	/**
	 * 根据平台父分类uuid查询子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<ProductCategoryPlatformModel> getSubProductCategoryByParentUuid(String parentUuid);
	
	/**
	 * 该方法用于生成fullpath时使用，查询该fullpath是否已经存在
	 * @param fullPath 
	 * @return
	 */
	public List<ProductCategoryPlatformModel> getProductCategoryPlatformModelByFullPath(String fullPath);
	
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
	 * 批量添加分类
	 * @param list
	 */
	public void addBatch(List<ProductCategoryPlatformModel> list);
	
	/**
	 * 根据分类uuid获取分类名称
	 * @param uuid 分类uuid
	 * @return
	 */
	public String getNameByCategoryUuid(String uuid);
	

	/**
	 * 获取分类路径全名称 例：通用分类>电子>手机
	 * @param uuid 分类uuid
	 * @param parentName 
	 * @return
	 */
	public String getAllNameByUuid(String uuid,String parentName);
	
	/**
	 * 对外接口：根据集合uuids 查出 分类集合
	 * @param uuids
	 * @return
	 */
	public Map<String, ProductCategoryPlatformModel> getCategorysByUuids(List<String> uuids);
}
