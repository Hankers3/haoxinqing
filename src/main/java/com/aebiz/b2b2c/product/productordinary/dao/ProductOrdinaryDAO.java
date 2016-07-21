package com.aebiz.b2b2c.product.productordinary.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.productpub.vo.ProductModel;
import com.aebiz.b2b2c.product.productpub.vo.ProductQueryModel;

public interface ProductOrdinaryDAO extends BaseDAO<ProductModel,ProductQueryModel>{
	/**
	 * 查询普通商品
	 * @param needPage
	 * @param qm
	 * @param start
	 * @param pageShow
	 * @return
	 */
	public List<ProductModel> getProductListByCondition( boolean needPage, ProductQueryModel qm, int start, int pageShow);
	
	public int getCount(ProductQueryModel paramQM);
	
}