package com.aebiz.b2b2c.product.productimagelibrary.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.productimagelibrary.vo.ProductImageLibraryModel;
import com.aebiz.b2b2c.product.productimagelibrary.vo.ProductImageLibraryQueryModel;

public interface ProductImageLibraryDAO
		extends
			BaseDAO<ProductImageLibraryModel, ProductImageLibraryQueryModel> {

	/**
	 * 根据分类的uuid获取该分类下的所有图片
	 * 
	 * @param categoryUuid
	 * @return List<ProductImageLibraryModel>
	 */
	public List<ProductImageLibraryModel> getProductImageLibraryModelsByCategoryUuid(
			String categoryUuid,int nowPage ,int pageShow);
	
	/**
	 * 根据分类的uuid获取该分类下的所有图片总数
	 * 
	 * @param categoryUuid
	 * @return int
	 */
	public int getProductImageLibraryModelsByCategoryUuid(
			String categoryUuid);
}