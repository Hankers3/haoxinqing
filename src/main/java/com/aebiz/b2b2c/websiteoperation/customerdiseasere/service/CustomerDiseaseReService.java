package com.aebiz.b2b2c.websiteoperation.customerdiseasere.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.websiteoperation.customerdiseasere.vo.CustomerDiseaseReModel;
import com.aebiz.b2b2c.websiteoperation.customerdiseasere.vo.CustomerDiseaseReQueryModel;

public interface CustomerDiseaseReService extends BaseService<CustomerDiseaseReModel,CustomerDiseaseReQueryModel>{
	/**
	 * 获取附件表信息
	 * @param diseaseUuid
	 * @return
	 */
	public CustomerDiseaseReModel getCustomerDiseaseReModel(String diseaseUuid);
}
