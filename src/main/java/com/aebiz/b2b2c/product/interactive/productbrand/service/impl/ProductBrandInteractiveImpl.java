package com.aebiz.b2b2c.product.interactive.productbrand.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.product.interactive.productbrand.dao.ProductBrandInteractiveDAO;
import com.aebiz.b2b2c.product.interactive.productbrand.service.ProductBrandInteractive;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandModel;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandQueryModel;

/**
 * 
 * @author huyingying
 * 
 */
@Service
@Transactional
public class ProductBrandInteractiveImpl extends
		BaseServiceImpl<ProductBrandModel, ProductBrandQueryModel> implements
		ProductBrandInteractive {
	
	private ProductBrandInteractiveDAO myDao = null;
	
	@Autowired
	public void setMyDao(ProductBrandInteractiveDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}
	
	/**
	 * 通过商品平铺uuids集合查询商品品牌集合
	 * @param uuids
	 * @param start
	 * @param pageShow
	 * @return
	 */
	public List<ProductBrandModel> getProductBrandsByUuids(List<String> uuids,int start,int pageShow){
		return myDao.getProductBrandsByUuids(uuids, start, pageShow);
	}
	

	
}