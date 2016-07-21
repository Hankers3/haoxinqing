package com.aebiz.b2b2c.servicestaff.doctorcategory.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.servicestaff.doctorcategory.vo.DoctorCategoryModel;
import com.aebiz.b2b2c.servicestaff.doctorcategory.vo.DoctorCategoryQueryModel;

public interface DoctorCategoryService extends BaseService<DoctorCategoryModel,DoctorCategoryQueryModel>{
	/**
	 * 判断分类名 是否存在
	 * @param categoryName
	 * @return
	 */
	public boolean checkCateGoryName(String categoryName);
}
