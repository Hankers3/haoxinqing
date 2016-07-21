package com.aebiz.b2b2c.websiteoperation.customerdiseasere.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.websiteoperation.customerdiseasere.vo.CustomerDiseaseReModel;
import com.aebiz.b2b2c.websiteoperation.customerdiseasere.vo.CustomerDiseaseReQueryModel;

@Repository
public class CustomerDiseaseReH4Impl extends BaseH4Impl<CustomerDiseaseReModel,CustomerDiseaseReQueryModel> implements CustomerDiseaseReDAO {
	/**
	 * 获取附件表信息
	 * @param diseaseUuid
	 * @return
	 */
	@Override
	public CustomerDiseaseReModel getCustomerDiseaseReModel(String diseaseUuid) {
		StringBuffer hql = new StringBuffer(" from CustomerDiseaseReModel as o where 1=1 ");
		hql.append(" and o.diseaseUuid =:diseaseUuid ");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("diseaseUuid", diseaseUuid);
		 
		List<CustomerDiseaseReModel> list = q.list();
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		
		return null;
	}

}
