package com.aebiz.b2b2c.customermgr.customercategory.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.customermgr.customercategory.vo.CustomerCategoryModel;
import com.aebiz.b2b2c.customermgr.customercategory.vo.CustomerCategoryQueryModel;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelModel;

@Repository
public class CustomerCategoryH4Impl extends
		BaseH4Impl<CustomerCategoryModel, CustomerCategoryQueryModel> implements
		CustomerCategoryDAO {

	/**
	 * 判断患者分类名 是否存在
	 * 
	 * @param categoryName
	 * @return
	 */
	@Override
	public String checkCateGoryName(String categoryName) {
		StringBuffer hql = new StringBuffer(
				" select o.uuid  from CustomerCategoryModel  as o ");
		hql.append(" where o.categoryName =:categoryName ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("categoryName", categoryName);

		List list = q.list();
		if (list != null && list.size() > 0) {
			return "1";
		}
		return "0";
	}

	/**
	 * 得到患者分类model的集合
	 */
	@Override
	public List<CustomerCategoryModel> getCustomerCategoryModelList() {
		StringBuffer hql = new StringBuffer(
				" from CustomerCategoryModel where 1=1 ");
		Query query = this.getH4Session().createQuery(hql.toString());
		List<CustomerCategoryModel> customerCategoryModel = query.list();

		return customerCategoryModel;
	}

}
