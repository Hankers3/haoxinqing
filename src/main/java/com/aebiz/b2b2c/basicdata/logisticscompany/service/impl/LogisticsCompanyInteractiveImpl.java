package com.aebiz.b2b2c.basicdata.logisticscompany.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basicdata.logisticscompany.dao.LogisticsCompanyDAO;
import com.aebiz.b2b2c.basicdata.logisticscompany.service.LogisticsCompanyInteractive;
import com.aebiz.b2b2c.basicdata.logisticscompany.vo.LogisticsCompanyModel;

@Service
@Transactional
public class LogisticsCompanyInteractiveImpl implements
		LogisticsCompanyInteractive {
	private LogisticsCompanyDAO myDao;

	@Autowired
	public void setMyDao(LogisticsCompanyDAO myDao) {
		this.myDao = myDao;
	}

	/**
	 * 得到物流公司列表
	 */
	public List<LogisticsCompanyModel> getLogisticsCompanyList() {
		return myDao.getLogisticsCompanyList();
	}

}
