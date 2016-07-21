package com.aebiz.b2b2c.basicdata.logisticscompany.service;

import java.util.List;

import com.aebiz.b2b2c.basicdata.logisticscompany.vo.LogisticsCompanyModel;

/**
 * 基础数据库对外接口
 * 
 * @author likun
 * 
 */
public interface LogisticsCompanyInteractive {

	/**
	 * 得到物流公司列表
	 * 
	 * @return
	 */
	public List<LogisticsCompanyModel> getLogisticsCompanyList();

}
