package com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.vo.ThirdInterfaceTemplateModel;
import com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.vo.ThirdInterfaceTemplateQueryModel;

@Repository
public class ThirdInterfaceTemplateH4Impl
		extends
		BaseH4Impl<ThirdInterfaceTemplateModel, ThirdInterfaceTemplateQueryModel>
		implements ThirdInterfaceTemplateDAO {
	/**
	 * 根据模板id取到对象
	 */
	public ThirdInterfaceTemplateModel getThirdInterfaceTemplateModelByTemplateId(
			String templateId) {
		 String hql = "select o from ThirdInterfaceTemplateModel o where o.templateId=:templateId ";
	    
		 Query q = getH4Session().createQuery(hql);
		 q.setParameter("templateId", templateId);
		 List<ThirdInterfaceTemplateModel> tempList = q.list();
		 if(tempList!=null&&tempList.size()>0){
			 return tempList.get(0);
		 }
		 return null;
	}

}
