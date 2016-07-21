package com.aebiz.b2b2c.product.productordinary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.productmain.service.ProductMainService;
import com.aebiz.b2b2c.product.productmainbaseprice.service.ProductMainBasePriceService;
import com.aebiz.b2b2c.product.productordinary.dao.ProductOrdinaryDAO;
import com.aebiz.b2b2c.product.productordinary.service.ProductOrdinaryService;
import com.aebiz.b2b2c.product.productpub.service.ProductService;
import com.aebiz.b2b2c.product.productpub.vo.ProductModel;
import com.aebiz.b2b2c.product.productpub.vo.ProductQueryModel;
import com.aebiz.b2b2c.product.productpub.vo.ProductType;

@Service
@Transactional
public class ProductOrdinaryServiceImpl extends BaseServiceImpl<ProductModel,ProductQueryModel> implements ProductOrdinaryService {
	private ProductOrdinaryDAO myDao = null;
	
	@Autowired
	private ProductMainService mainService;
	@Autowired
	private ProductMainBasePriceService priceService;
	
	/* 商品公用service*/
	@Autowired
	private ProductService productService;
	
	
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductOrdinaryDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	
	
	@Override
	public List<ProductModel> getByCondition(ProductQueryModel qm, int start,
			int pageShow) {
		return myDao.getProductListByCondition(true, qm, start, pageShow);
	}
	
	public int getCount(ProductQueryModel qm) {
		return myDao.getCount(qm);
	}
	
	@Override
	public void deletes(List<String> needDeleteUuids) {
		priceService.deletesByProductUuids(needDeleteUuids);
	
		super.deletes(needDeleteUuids);
	}
	
	/**
	 * 发布商品
	 * @param m
	 */
	public String save(ProductModel m) {
		return productService.save(m, ProductType.HOUSEKEEP.getValue());
	}


	
	
}