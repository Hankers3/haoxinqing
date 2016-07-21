package com.aebiz.b2b2c.servicestaff.doctorcategory.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.doctorcategory.vo.DoctorCategoryModel;
import com.aebiz.b2b2c.servicestaff.doctorcategory.vo.DoctorCategoryQueryModel;

public interface DoctorCategoryDAO extends BaseDAO<DoctorCategoryModel,DoctorCategoryQueryModel>{
	/**
	 * 判断分类名 是否存在
	 * @param categoryName
	 * @return
	 */
	public String checkCateGoryName(String categoryName);
}