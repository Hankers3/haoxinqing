package com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.vo.ThirdInterfaceTemplateModel;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.vo.ThirdInterfaceTemplateQueryModel;

public interface ThirdInterfaceTemplateService
		extends
		BaseService<ThirdInterfaceTemplateModel, ThirdInterfaceTemplateQueryModel> {
	
	
	/**
	 * 根据模板id取到对象
	 */
	public ThirdInterfaceTemplateModel getThirdInterfaceTemplateModelByTemplateId(String templateId);
	
}
