package com.aebiz.b2b2c.product.interactive.frontproductcategorybrandrel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.dao.FrontProductCategoryBrandRelDAO;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.vo.FrontProductCategoryBrandRelModel;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.vo.FrontProductCategoryBrandRelQueryModel;
import com.aebiz.b2b2c.product.interactive.frontproductcategorybrandrel.service.FrontProductCategoryBrandRelInteractive;

@Service
@Transactional
public class FrontProductCategoryBrandRelInteractiveImpl
		extends
		BaseServiceImpl<FrontProductCategoryBrandRelModel, FrontProductCategoryBrandRelQueryModel>
		implements FrontProductCategoryBrandRelInteractive {
	
	private FrontProductCategoryBrandRelDAO myDao = null;
	
	@Autowired
	public void setMyDao(FrontProductCategoryBrandRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}
	
	/**
	 * 通过前台分类查询商品品牌uuid集合
	 * @param categoryUuid
	 * @return
	 */
	public List<String> getBrandUuidsByCategoryUuid(String categoryUuid){
		return myDao.getBrandIdsByCateUuid(categoryUuid);
	}
	
}