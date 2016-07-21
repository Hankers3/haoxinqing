package com.aebiz.b2b2c.visitprecept.casecategory.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.visitprecept.casecategory.vo.CaseCategoryModel;
import com.aebiz.b2b2c.visitprecept.casecategory.vo.CaseCategoryQueryModel;

@Repository
public class CaseCategoryH4Impl extends BaseH4Impl<CaseCategoryModel,CaseCategoryQueryModel> implements CaseCategoryDAO {
	/**
	 * 通过id得到名字
	 * @param caseCategoryUuid
	 * @return
	 */
	public String getNameByUuid(String caseCategoryUuid) {
		StringBuffer hql =  new StringBuffer(" select ccm.categoryName from CaseCategoryModel as ccm where 1=1 ");
		hql.append(" and ccm.uuid =:uuid ");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("uuid", caseCategoryUuid);
		List list = q.list();
		if(list !=null && list.size()>0){
			return (String) list.get(0);
		}
		return "";
	}

}
