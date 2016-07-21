package com.aebiz.b2b2c.basicdata.logisticscompany.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.basicdata.logisticscompany.vo.LogisticsCompanyModel;
import com.aebiz.b2b2c.basicdata.logisticscompany.vo.LogisticsCompanyQueryModel;

public interface LogisticsCompanyDAO extends
		BaseDAO<LogisticsCompanyModel, LogisticsCompanyQueryModel> {

	/**
	 * 得到物流公司列表
	 * 
	 * @return
	 */
	public List<LogisticsCompanyModel> getLogisticsCompanyList();

	/**
	 * 得到物流公司列表
	 * 
	 * @return
	 */
	public List<String> getLogisticsCompanyIdsList();
}