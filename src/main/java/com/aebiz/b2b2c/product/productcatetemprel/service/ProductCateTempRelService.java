package com.aebiz.b2b2c.product.productcatetemprel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.productcatetemprel.vo.ProductCateTempRelModel;
import com.aebiz.b2b2c.product.productcatetemprel.vo.ProductCateTempRelQueryModel;


/**
 * 平台分类和商品模板关系业务接口</br>
 * 在操作平台分类的时候，可以关联商品模板</br>
 * 平台分类和商品模板是多对多的关系
 * 
 * @author huangpinpin
 *
 */
public interface ProductCateTempRelService extends BaseService<ProductCateTempRelModel,ProductCateTempRelQueryModel>{

	/**
	 * 
	 * 根据分类uuid获取已关联模板uuid</br>
	 * 在分类关联模板时，弹出框列表中屏蔽已经关联该分类的模板关联按钮</br>
	 * 一个分类可以关联多个模板
	 * @param categoryUuid 分类uuid
	 * @return
	 */
	public List<String> getTemplateUuidsByCategoryUuid(String categoryUuid);
	
	/**
	 * 关联模板到该分类下</br>
	 * 在分类编辑也中关联模板弹出框中，点击批量关联时使用
	 * @param needRelUuids 关联模板uuid
	 * @param categoryUuid 分类uuids
	 */
	public void relation(List<String> needRelUuids, String categoryUuid);
	
	/**
	 * 根据模板id集合和分类uuid删除分类模板关系表数据</br>
	 * 在分类关联模板时，弹出框列表中点击取消关联调用
	 * @param needDeleteUuids 模板uuid集合
	 * @param categoryUuid 分类uuid
	 */
	public void deleteByIdAndCategoryUuid(List<String> needDeleteUuids,String categoryUuid);
}
