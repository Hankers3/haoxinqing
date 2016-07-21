package com.aebiz.b2b2c.visitprecept.casecategory.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.visitprecept.casecategory.vo.CaseCategoryModel;
import com.aebiz.b2b2c.visitprecept.casecategory.vo.CaseCategoryQueryModel;

public interface CaseCategoryDAO extends BaseDAO<CaseCategoryModel,CaseCategoryQueryModel>{

	/**
	 * 通过id得到名字
	 * @param caseCategoryUuid
	 * @return
	 */
	public String getNameByUuid(String caseCategoryUuid);

}