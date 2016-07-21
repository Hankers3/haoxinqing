package com.aebiz.b2b2c.websiteoperation.customerdiseasere.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.websiteoperation.customerdiseasere.vo.CustomerDiseaseReModel;
import com.aebiz.b2b2c.websiteoperation.customerdiseasere.vo.CustomerDiseaseReQueryModel;

public interface CustomerDiseaseReDAO extends BaseDAO<CustomerDiseaseReModel,CustomerDiseaseReQueryModel>{
	/**
	 * 获取附件表信息
	 * @param diseaseUuid
	 * @return
	 */
	public CustomerDiseaseReModel getCustomerDiseaseReModel(String diseaseUuid);
}