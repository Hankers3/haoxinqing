package com.aebiz.b2b2c.customermgr.storeback.service.customerstorelevel.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelModel;
import com.aebiz.b2b2c.customermgr.storeback.dao.customerstorelevel.CustomerStoreLevelDAO;
import com.aebiz.b2b2c.customermgr.storeback.service.customerstorelevel.CustomerStoreLevelInteractive;

@Service
@Transactional
public class CustomerStoreLevelInteractiveImpl implements
		CustomerStoreLevelInteractive {
	private CustomerStoreLevelDAO myDao = null;

	@Autowired
	public void setMyDao(CustomerStoreLevelDAO dao) {
		this.myDao = dao;
	}

	/**
	 * 通过商户编号获取商户会员等级列表
	 */
	@Override
	public List<CustomerStoreLevelModel> getStoreLevelModelList(String storeUuid) {
		return this.myDao.getStoreLevelModelList(storeUuid);
	}

}
