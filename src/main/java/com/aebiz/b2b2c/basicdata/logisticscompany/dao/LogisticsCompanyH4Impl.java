package com.aebiz.b2b2c.basicdata.logisticscompany.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.basicdata.logisticscompany.vo.LogisticsCompanyModel;
import com.aebiz.b2b2c.basicdata.logisticscompany.vo.LogisticsCompanyQueryModel;

@Repository
public class LogisticsCompanyH4Impl extends
		BaseH4Impl<LogisticsCompanyModel, LogisticsCompanyQueryModel> implements
		LogisticsCompanyDAO {

	/**
	 * 得到物流公司列表
	 */
	public List<LogisticsCompanyModel> getLogisticsCompanyList() {
		StringBuffer hql = new StringBuffer(
				" from LogisticsCompanyModel where 1=1 ");

		Query query = this.getH4Session().createQuery(hql.toString());

		List<LogisticsCompanyModel> lcmList = query.list();

		return lcmList;
	}

	/**
	 * 得到物流公司列表
	 * 
	 * @return
	 */
	public List<String> getLogisticsCompanyIdsList() {
		StringBuffer hql = new StringBuffer(
				" select o.uuid from LogisticsCompanyModel o where 1=1 ");

		Query query = this.getH4Session().createQuery(hql.toString());

		List<String> lcmList = query.list();

		return lcmList;
	}
}
