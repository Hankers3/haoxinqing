package com.aebiz.b2b2c.visitprecept.casecategory.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.visitprecept.casecategory.vo.CaseCategoryModel;
import com.aebiz.b2b2c.visitprecept.casecategory.vo.CaseCategoryQueryModel;

public interface CaseCategoryService extends
		BaseService<CaseCategoryModel, CaseCategoryQueryModel> {

	/**
	 * 通过id得到名字
	 * @param caseCategoryUuid
	 * @return
	 */
	public String getNameByUuid(String caseCategoryUuid);

}
