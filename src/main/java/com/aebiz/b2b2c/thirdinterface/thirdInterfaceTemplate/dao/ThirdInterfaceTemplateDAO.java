package com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.vo.ThirdInterfaceTemplateModel;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.vo.ThirdInterfaceTemplateQueryModel;

public interface ThirdInterfaceTemplateDAO extends
		BaseDAO<ThirdInterfaceTemplateModel, ThirdInterfaceTemplateQueryModel> {
	/**
	 * 根据模板id取到对象
	 */
	public ThirdInterfaceTemplateModel getThirdInterfaceTemplateModelByTemplateId(
			String templateId);
}