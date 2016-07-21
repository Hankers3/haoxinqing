package com.aebiz.b2b2c.customermgr.customercategory.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.customercategory.vo.CustomerCategoryModel;
import com.aebiz.b2b2c.customermgr.customercategory.vo.CustomerCategoryQueryModel;

public interface CustomerCategoryDAO extends BaseDAO<CustomerCategoryModel,CustomerCategoryQueryModel>{

	/**
	 * 判断分类名 是否存在
	 * @param categoryName
	 * @return
	 */
	String checkCateGoryName(String categoryName);

	/**
	 * 得到患者分类model的集合
	 */
	List<CustomerCategoryModel> getCustomerCategoryModelList();

}