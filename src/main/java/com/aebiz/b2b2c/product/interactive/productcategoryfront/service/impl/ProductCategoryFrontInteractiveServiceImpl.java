package com.aebiz.b2b2c.product.interactive.productcategoryfront.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.interactive.productcategoryfront.dao.ProductCategoryFrontInteractiveDAO;
import com.aebiz.b2b2c.product.interactive.productcategoryfront.service.ProductCategoryFrontInteractiveService;
import com.aebiz.b2b2c.product.interactive.productcategoryfront.vo.ProductCategoryFrontInteractiveModel;
import com.aebiz.b2b2c.product.interactive.productcategoryfront.vo.ProductCategoryFrontInteractiveQueryModel;
import com.aebiz.b2b2c.product.productcategoryfront.vo.ProductCategoryFrontModel;

@Service
@Transactional
public class ProductCategoryFrontInteractiveServiceImpl extends BaseServiceImpl<ProductCategoryFrontInteractiveModel,ProductCategoryFrontInteractiveQueryModel> implements ProductCategoryFrontInteractiveService {
	private ProductCategoryFrontInteractiveDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductCategoryFrontInteractiveDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 
	 * 通过商品分类uuid获取子分类
	 * @param uuid
	 * @return
	 */
	public List<ProductCategoryFrontModel> getSubProductCategoryByUuid(String parentUuid){
		return myDao.getSubProductCategoryByUuid(parentUuid);
	}
	
	/**
	 * 对外接口：获取所有店铺分类
	 * 
	 * @param parentUuid
	 * 			父类uuid
	 * @return
	 */
	public List<ProductCategoryFrontInteractiveModel> getAllProductCategoryFront(String parentUuid){
		List<ProductCategoryFrontModel> tempList=getSubProductCategoryByUuid(parentUuid);
		if(tempList==null || tempList.size()==0){
			return null;
		}
		List<ProductCategoryFrontInteractiveModel> roorCategorys=changeToInteractiveModel(tempList);
		for (ProductCategoryFrontInteractiveModel roorCategory : roorCategorys) {
			List<ProductCategoryFrontInteractiveModel> list=this.getAllProductCategoryFront(roorCategory.getUuid());
			roorCategory.setSubProductCategoryFront(list);
		}
		return roorCategorys;
	}
	
	/**
	 * 根据分类父uuid，分类Uuid集合查询分类集合（带分页）,三级分类
	 * @param parentUuid
	 * @param uuids
	 * @param start
	 * @param pageNow
	 * @return
	 */
	public List<ProductCategoryFrontInteractiveModel> getProductCategorys(String parentUuid,String[] uuids,int start,int pageNow){
		List<ProductCategoryFrontModel> tempList=myDao.getProductCategorys(parentUuid, uuids, start, pageNow);
		if(tempList==null || tempList.size()==0){
			return null;
		}
		List<ProductCategoryFrontInteractiveModel> roorCategorys=changeToInteractiveModel(tempList);
		return roorCategorys;
	}
	
	/**
	 * 根据分类uuid查询分类,三级分类
	 * @param parentUuid
	 * @param uuids
	 * @param start
	 * @param pageNow
	 * @return
	 */
	public ProductCategoryFrontInteractiveModel getByCategoryUuid(String categoryUuid){
		ProductCategoryFrontModel category=myDao.getByCategoryUuid(categoryUuid);
		ProductCategoryFrontInteractiveModel model=new ProductCategoryFrontInteractiveModel();
		if(category!=null){
			model.setUuid(category.getUuid());
			model.setCategoryName(category.getCategoryName());
			model.setCategoryUrl(category.getCategoryUrl());
			model.setIcon(category.getIcon());
			List<ProductCategoryFrontModel> subList=myDao.getSubProductCategoryByUuid(categoryUuid);
			List<ProductCategoryFrontInteractiveModel> roorCategorys=changeToInteractiveModel(subList);
			for (ProductCategoryFrontInteractiveModel roorCategory : roorCategorys) {
				List<ProductCategoryFrontInteractiveModel> list=changeToInteractiveModel(this.getSubProductCategoryByUuid(roorCategory.getUuid()));
				roorCategory.setSubProductCategoryFront(list);
			}
			model.setSubProductCategoryFront(roorCategorys);
		}
		return model;
	}
	
	/**
	 * 根据父分类uuid查询分类数
	 * @param parentUuid
	 * @return
	 */
	public int getProductCategoryCount(String parentUuid){
		return myDao.getProductCategoryCount(parentUuid);
	}
	
	/**
	 * 组织接口对象
	 * @param tempList
	 * @return
	 */
	private List<ProductCategoryFrontInteractiveModel> changeToInteractiveModel (List<ProductCategoryFrontModel> tempList) {
		List<ProductCategoryFrontInteractiveModel> list=new ArrayList<ProductCategoryFrontInteractiveModel>();
		if (tempList != null && tempList.size() > 0) {
			for (ProductCategoryFrontModel data : tempList) {
				ProductCategoryFrontInteractiveModel model=new ProductCategoryFrontInteractiveModel();
				model.setUuid(data.getUuid());
				model.setCategoryName(data.getCategoryName());
				model.setCategoryUrl(data.getCategoryUrl());
				model.setIcon(data.getIcon());
				list.add(model);
			}
		}
		return list;
	}
}