package com.aebiz.b2b2c.servicestaff.doctorcategory.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.servicestaff.doctorcategory.vo.DoctorCategoryModel;
import com.aebiz.b2b2c.servicestaff.doctorcategory.vo.DoctorCategoryQueryModel;

@Repository
public class DoctorCategoryH4Impl extends BaseH4Impl<DoctorCategoryModel,DoctorCategoryQueryModel> implements DoctorCategoryDAO {
	/**
	 * 判断分类名 是否存在
	 * @param categoryName
	 * @return
	 */
	@Override
	public String checkCateGoryName(String categoryName) {
		StringBuffer hql = new StringBuffer(" select o.uuid  from DoctorCategoryModel  as o ");
		hql.append(" where o.categoryName =:categoryName ");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("categoryName", categoryName);
		
		List  list =q.list();
		if(list !=null && list.size()>0){
			return "1";
		}
		return "0";
	}

}
