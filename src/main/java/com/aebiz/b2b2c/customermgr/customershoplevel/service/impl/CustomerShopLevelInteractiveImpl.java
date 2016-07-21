package com.aebiz.b2b2c.customermgr.customershoplevel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.customermgr.customershoplevel.dao.CustomerShopLevelDAO;
import com.aebiz.b2b2c.customermgr.customershoplevel.service.CustomerShopLevelInteractive;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelModel;

@Service
@Transactional
public class CustomerShopLevelInteractiveImpl implements
		CustomerShopLevelInteractive {
	private CustomerShopLevelDAO myDao = null;

	@Autowired
	public void setMyDao(CustomerShopLevelDAO dao) {
		this.myDao = dao;
	}

	/**
	 * 得到平台会员等级model的集合
	 */
	@Override
	public List<CustomerShopLevelModel> getShopLevelModelList() {
		return this.myDao.getShopLevelModelList();
	}

}
