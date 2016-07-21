package com.aebiz.b2b2c.product.productcatetemprel.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.productcatetemprel.vo.ProductCateTempRelModel;
import com.aebiz.b2b2c.product.productcatetemprel.vo.ProductCateTempRelQueryModel;

/**
 * 平台分类和商品模板关系数据操作</br>
 * 在操作平台分类的时候，可以关联商品模板</br>
 * 平台分类和商品模板是多对多的关系
 * 
 * @author huangpinpin
 *
 */
public interface ProductCateTempRelDAO extends BaseDAO<ProductCateTempRelModel,ProductCateTempRelQueryModel>{
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
	 * 根据模板id集合和分类uuid删除分类模板关系表数据</br>
	 * 在分类关联模板时，弹出框列表中点击取消关联调用
	 * @param needDeleteUuids 模板uuid集合
	 * @param categoryUuid 分类uuid
	 */
	public void deleteByIdAndCategoryUuid(List<String> needDeleteUuids,String categoryUuid);
}