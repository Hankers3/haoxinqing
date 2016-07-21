package com.aebiz.b2b2c.customermgr.customercategory.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customercategory.vo.CustomerCategoryModel;
import com.aebiz.b2b2c.customermgr.customercategory.vo.CustomerCategoryQueryModel;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelModel;

public interface CustomerCategoryService extends BaseService<CustomerCategoryModel,CustomerCategoryQueryModel>{

	/**
	 * 判断分类名 是否存在
	 * @param categoryName
	 * @return
	 */
	boolean checkCateGoryName(String categoryName);

	/**
	 * 得到患者分类model的集合
	 */
	List<CustomerCategoryModel> getCustomerCategoryModelList();

}
